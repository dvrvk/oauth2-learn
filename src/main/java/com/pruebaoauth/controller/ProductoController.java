package com.pruebaoauth.controller;

import com.pruebaoauth.model.Producto;
import com.pruebaoauth.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.crearProducto(producto);
        return ResponseEntity.ok(nuevoProducto); // Devolver el producto creado
    }

    // Obtener todos los productos por nombre (búsqueda)
    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> obtenerProductosPorNombre(@RequestParam String nombre) {
        List<Producto> productos = productoService.obtenerProductosPorNombre(nombre);
        return ResponseEntity.ok(productos); // Devolver los productos encontrados
    }

    // Obtener todos los productos de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Producto>> obtenerProductosPorUsuario(@PathVariable Long usuarioId) {
        List<Producto> productos = productoService.obtenerProductosPorUsuario(usuarioId);
        return ResponseEntity.ok(productos); // Devolver los productos del usuario
    }

    // Obtener un producto por su id
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            return ResponseEntity.ok(producto); // Producto encontrado
        } else {
            return ResponseEntity.notFound().build(); // Producto no encontrado
        }
    }

    // Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto productoActualizado = productoService.actualizarProducto(id, producto);
        if (productoActualizado != null) {
            return ResponseEntity.ok(productoActualizado); // Producto actualizado
        } else {
            return ResponseEntity.notFound().build(); // Producto no encontrado
        }
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        boolean eliminado = productoService.eliminarProducto(id);
        if (eliminado) {
            return ResponseEntity.noContent().build(); // Eliminación exitosa
        } else {
            return ResponseEntity.notFound().build(); // Producto no encontrado
        }
    }
}
