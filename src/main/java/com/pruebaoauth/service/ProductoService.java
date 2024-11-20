package com.pruebaoauth.service;

import com.pruebaoauth.model.Producto;
import com.pruebaoauth.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Crear un nuevo producto.
     */
    public Producto crearProducto(Producto producto) {
        producto.setFechaPublicacion(LocalDateTime.now()); // Establecer la fecha de publicación
        return productoRepository.save(producto); // Guardar el producto en la base de datos
    }

    /**
     * Obtener productos por nombre (búsqueda).
     */
    public List<Producto> obtenerProductosPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre); // Buscar productos por nombre
    }

    /**
     * Obtener todos los productos de un usuario específico.
     */
    public List<Producto> obtenerProductosPorUsuario(Long usuarioId) {
        return productoRepository.findByUsuarioId(usuarioId); // Buscar productos por usuarioId
    }

    /**
     * Obtener un producto por su id.
     */
    public Producto obtenerProductoPorId(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElse(null); // Si el producto no existe, devuelve null
    }

    /**
     * Actualizar un producto existente.
     */
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        if (productoRepository.existsById(id)) {
            productoActualizado.setId(id); // Asegurarse de que el id sea el correcto
            return productoRepository.save(productoActualizado); // Guardar el producto actualizado
        }
        return null; // Si no existe, devolver null
    }

    /**
     * Eliminar un producto por su id.
     */
    public boolean eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id); // Eliminar producto por id
            return true; // Producto eliminado exitosamente
        }
        return false; // Si no existe, no se elimina
    }
}

