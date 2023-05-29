package com.bazar.proyecto_final.service;

import com.bazar.proyecto_final.model.Producto;
import java.util.List;

public interface IProductoService {
    
    //Lectura
    public List<Producto> getProductos();
    
    //Lectura de un determinado producto
    public Producto findProducto(Long codigo_producto);
    
    //Alta
    public void saveProducto(Producto producto);
    
    //Baja
    public void deleteProducto(Long codigo_producto);
    
    //Edicion
    public void editProducto(Long codigo_producto, String nombre, String marca, Double costo, Double cantidad_disponible);

    //Obtener todos los productos cuya cantidad_disponible sea menor a 5
    public List<Producto> cantidadMenorACinco();
    
}
