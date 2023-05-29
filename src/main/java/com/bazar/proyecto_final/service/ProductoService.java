package com.bazar.proyecto_final.service;

import com.bazar.proyecto_final.model.Producto;
import com.bazar.proyecto_final.repository.IProductoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository prodRepo;
    
    //Lectura de productos
    @Override
    public List<Producto> getProductos() {
        
        List<Producto> listaProductos = prodRepo.findAll();
        
        return  listaProductos;
        
    }

    //Alta de productos
    @Override
    public void saveProducto(Producto producto) {
        
        prodRepo.save(producto);
        
    }

    //Baja de productos
    @Override
    public void deleteProducto(Long codigo_producto) {
        
        prodRepo.deleteById(codigo_producto);
        
    }

    //Edicion de productos
    @Override
    public void editProducto(Long codigo_producto, String nombre, String marca, Double costo, Double cantidad_disponible) {
        
        //Busco el objeto original
        Producto produc = this.findProducto(codigo_producto);
        
        //Seteamos los valores nuevos al objeto encontrado
        produc.setNombre(nombre);
        produc.setMarca(marca);
        produc.setCosto(costo);
        produc.setCantidad_disponible(cantidad_disponible);
        
        //guardamos el producto midificado
        this.saveProducto(produc);
        
    }

    //Lecura de un determinado producto
    @Override
    public Producto findProducto(Long codigo_producto) {

        Producto producto = prodRepo.findById(codigo_producto).orElse(null);
        
        return producto;
        
    }

    @Override
    public List<Producto> cantidadMenorACinco() {

        List<Producto> listaProd = this.getProductos();
        List<Producto> listaProdCantMenCinco = new ArrayList<>();
        
        for (Producto produ : listaProd){
            if(produ.getCantidad_disponible() < 5){
                listaProdCantMenCinco.add(produ);
            }
        }
        
        return listaProdCantMenCinco;
    }

    
}
