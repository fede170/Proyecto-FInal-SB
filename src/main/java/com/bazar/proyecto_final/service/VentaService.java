package com.bazar.proyecto_final.service;

import com.bazar.proyecto_final.dto.VentaMontoMasAltoDTO;
import com.bazar.proyecto_final.model.Cliente;
import com.bazar.proyecto_final.model.Producto;
import com.bazar.proyecto_final.model.Venta;
import com.bazar.proyecto_final.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private IVentaRepository ventaRepo;
    
    @Autowired
    private IProductoService prodServ;

    //Lectura de ventas
    @Override
    public List<Venta> getVentas() {
        
        List<Venta> listaVentas = ventaRepo.findAll();
        
        return  listaVentas;
        
    }

    //Traer una venta determinada
    @Override
    public Venta findVenta(Long codigo_venta) {
        
        Venta venta = ventaRepo.findById(codigo_venta).orElse(null);
        
        return venta;
        
    }

    //Alta de venta
    @Override
    public String saveVenta(Venta venta) {

        List<Producto> listaProductos = venta.getListaProductos();
        String retorno = "";
        
        //nos aseguramos que haya mas de un producto para realizar la venta
        for (Producto prod : listaProductos) {
            Producto producto = prodServ.findProducto(prod.getCodigo_producto());
            Double cantidadDisponible = producto.getCantidad_disponible();
            if(cantidadDisponible > 1){
                ventaRepo.save(venta);
                cantidadDisponible = cantidadDisponible - 1;
                prodServ.editProducto(producto.getCodigo_producto(), producto.getNombre(), producto.getMarca(), producto.getCosto(), cantidadDisponible);
                retorno = "Venta realizada";
            }else{
                retorno = "Stock Insuficiente";
            }
        }

        return retorno;
    }

    //Elimiar venta
    @Override
    public void deleteVenta(Long codigo_venta) {
        
        ventaRepo.deleteById(codigo_venta);
        
    }

    //editar venta
    @Override
    public void editVenta(Long codigo_venta, LocalDate fecha, Double total, List<Producto> listaProductos, Cliente unCliente) {
        
        //Busco el objeto original
        Venta venta = this.findVenta(codigo_venta);
        
        //Seteamos los valores nuevos al objeto encontrado
        venta.setFecha(fecha);
        venta.setTotal(total);
        venta.setListaProductos(listaProductos);
        venta.setUnCliente(unCliente);
        
        //guardamos la venta midificada
        this.saveVta(venta);
        
    }

    //Obtener la lista de productos de una determinada venta
    @Override
    public List<Producto> listaProductosDeDetermVenta(Long codigo_venta) {

        List<Venta> listaVentas = this.getVentas();
        List<Producto> listaProductos = new ArrayList<>();
        
        for ( Venta vent : listaVentas){
            if (Objects.equals(vent.getCodigo_venta(), codigo_venta)) {
                listaProductos = vent.getListaProductos();
            }
        }
        
        return listaProductos;
        
    }

    //Obtener la sumatoria del monto y también cantidad total de ventas de un determinado día
    @Override
    public String ventasPorDia(LocalDate fecha_venta) {
        
        List<Venta> listaVentas = this.getVentas();
        List<Producto> listaProductos = new ArrayList<>();
        
        double sumatoriaMonto = 0;
        int totalVentas = 0;
        
        for(Venta vent : listaVentas){
            if (vent.getFecha().equals(fecha_venta)) {
                listaProductos = vent.getListaProductos();
                for (Producto produ : listaProductos){
                    sumatoriaMonto += produ.getCosto();
                }
                totalVentas += 1;
            }else{
                if (fecha_venta.isAfter(LocalDate.now()) ) {
                    return "La fecha ingresada no es válida";
                }
            }
        }
        
        return "La suma total de lo que se vendio la fecha " + fecha_venta.toString() + " es de " + sumatoriaMonto + ", hubo un total de " + totalVentas + " ventas ese dia";
        
    }
    
    //Obtener el codigo_venta, el total, la cantidad de productos, el nombre del cliente y el
    //apellido del cliente de la venta con el monto más alto de todas. 
    @Override
    public VentaMontoMasAltoDTO montoMasAlto() {
        
        List<Venta> listaVentas = this.getVentas();
        List<Producto> listaProductos = new ArrayList<>();
        Venta ventaMasCara = new Venta(); //gaurdamos la venta con el monto mas elevado 
        
        double sumatoriaMonto = 0;
        double montoMasElevado = 0;
        int cantProductos = 0;
        
        for(Venta vent : listaVentas){
            listaProductos = vent.getListaProductos();
            for (Producto produ : listaProductos){
                    sumatoriaMonto += produ.getCosto();
            }
            if (sumatoriaMonto > montoMasElevado) {
                montoMasElevado = sumatoriaMonto;
                sumatoriaMonto = 0;
                cantProductos = vent.getListaProductos().size();
                ventaMasCara = vent;
            }
        }
        
        //armamos el dto para retornar
        VentaMontoMasAltoDTO ventaPremium = new VentaMontoMasAltoDTO();
        
        ventaPremium.setCodigo_venta(ventaMasCara.getCodigo_venta());
        ventaPremium.setTotal(ventaMasCara.getTotal());
        ventaPremium.setCantProductos(cantProductos);
        ventaPremium.setListaProductos(ventaMasCara.getListaProductos());
        ventaPremium.setNombre_cliente(ventaMasCara.getUnCliente().getNombre());
        ventaPremium.setApellido_cliente(ventaMasCara.getUnCliente().getApellido());
        
        return ventaPremium;
        
    }

    //Guardamos la venta despues de editar
    @Override
    public void saveVta(Venta venta) {
        
        ventaRepo.save(venta);

    }
    
}
