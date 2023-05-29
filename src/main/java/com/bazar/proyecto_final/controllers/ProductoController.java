package com.bazar.proyecto_final.controllers;

import com.bazar.proyecto_final.model.Producto;
import com.bazar.proyecto_final.service.IProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {
    
    @Autowired
    private IProductoService prodServ;
    
    
    //Alta de un producto
    @PostMapping("/productos/crear")
    public void saveProducto(@RequestBody Producto producto){
        
        prodServ.saveProducto(producto);
        
    }
    
    //Traer lista completa de productos
    @GetMapping("/productos")
    public List<Producto> getProductos(){
        
        List<Producto> listaProductos = prodServ.getProductos();
        
        return listaProductos;
        
    }
    
    //Traer un producto en particular
    @GetMapping("/productos/{codigo_producto}")
    public Producto findProducto (@PathVariable Long codigo_producto){
        
        Producto prod = prodServ.findProducto(codigo_producto);
        
        return prod;
        
    }
    
    //Eliminamos un producto
    @DeleteMapping("/productos/eliminar/{codigo_producto}")
    public void deleteProducto(@PathVariable Long codigo_producto){
        
        prodServ.deleteProducto(codigo_producto);
        
    }
    
    //Editamos un producto
    @PutMapping("/productos/editar/{codigo_producto}")
    public Producto editProducto(@PathVariable Long codigo_producto, 
                             @RequestParam(required = false, name = "nombre") String nuevoNombre,
                             @RequestParam(required = false, name = "marca") String nuevaMarca,
                             @RequestParam(required = false, name = "costo") Double nuevoCosto,
                             @RequestParam(required = false, name = "cantidad_disponible") Double nuevaCantidad){
        
        prodServ.editProducto(codigo_producto, nuevoNombre, nuevaMarca, nuevoCosto, nuevaCantidad);
        
        Producto produ = prodServ.findProducto(codigo_producto);
        
        //devolvemos el producto modificado para visualizarlo
        return produ;
        
    }
    
    //Obtener todos los productos cuya cantidad_disponible sea menor a 5
    @GetMapping("/productos/falta_stock")
    public List<Producto> cantidadMenorACinco(){
        
        return prodServ.cantidadMenorACinco();
        
    }
    
}
