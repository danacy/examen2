package co.edu.poli.examen2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.examen2.model.Producto;
import co.edu.poli.examen2.services.ProductoServices;


@RestController
@RequestMapping("api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoServices productoService; 
    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {

        List<Producto> productos = productoService.getAllProductos();
        return ResponseEntity.ok(productos);
    
}
@GetMapping("/{id}")
public ResponseEntity<Producto> getProductoById(@PathVariable String id) {
    Producto producto = productoService.getProductoById(id);
    if (producto != null) {
        return ResponseEntity.ok(producto);
    } else {
        return ResponseEntity.notFound().build();
    }
}
@PostMapping
public ResponseEntity<Producto> saveProducto(@RequestBody Producto producto) {
    Producto savedProducto = productoService.saveProducto(producto);
    return ResponseEntity.ok(savedProducto);

}
@PutMapping("/{id}")
public ResponseEntity<Producto> updateProducto(@PathVariable String id, @RequestBody Producto producto) {
    producto.setId(id);
    Producto updatedProducto = productoService.updateProducto(producto);
    if (updatedProducto != null) {
        return ResponseEntity.ok(updatedProducto);
    } else {
        return ResponseEntity.notFound().build();
    }
}
@DeleteMapping("/{id}")
public ResponseEntity<Producto> deleteProducto(@PathVariable String id) {
    Producto productoEliminado = productoService.getProductoById(id);
    if (productoEliminado != null) {
        productoService.deleteProducto(id);
        return ResponseEntity.ok(productoEliminado);
    } else {
        return ResponseEntity.notFound().build();
    }
}

}


