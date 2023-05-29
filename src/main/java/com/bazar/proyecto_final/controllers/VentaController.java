package com.bazar.proyecto_final.controllers;

import com.bazar.proyecto_final.dto.VentaMontoMasAltoDTO;
import com.bazar.proyecto_final.model.Cliente;
import com.bazar.proyecto_final.model.Producto;
import com.bazar.proyecto_final.model.Venta;
import com.bazar.proyecto_final.service.IVentaService;
import java.time.LocalDate;
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
public class VentaController {
    
    @Autowired
    private IVentaService ventaServ;
    
    //Alta de una venta
    @PostMapping("/ventas/crear")
    public String saveVenta(@RequestBody Venta venta){
        
        return ventaServ.saveVenta(venta);
        
    }
    
    //Traer lista completa de ventas
    @GetMapping("/ventas")
    public List<Venta> getVentas(){
        
        List<Venta> listaVentas = ventaServ.getVentas();
        
        return listaVentas;
        
    }
    
    //Traer una venta en particular
    @GetMapping("/ventas/{codigo_venta}")
    public Venta findVenta (@PathVariable Long codigo_venta){
        
        Venta venta = ventaServ.findVenta(codigo_venta);
        
        return venta;
        
    }
    
    //Eliminamos una venta
    @DeleteMapping("/ventas/eliminar/{codigo_venta}")
    public void deleteVenta(@PathVariable Long codigo_venta){
        
        ventaServ.deleteVenta(codigo_venta);
        
    }
    
    //Editamos una venta
    @PutMapping("/ventas/editar/{codigo_venta}")
    public Venta editVenta(@PathVariable Long codigo_venta, 
                             @RequestParam(required = false, name = "fecha") LocalDate nuevaFecha,
                             @RequestParam(required = false, name = "total") Double nuevoTotal,
                             @RequestParam(required = false, name = "listaProductos") List<Producto> nuevaListaProductos,
                             @RequestParam(required = false, name = "unCliente") Cliente nuevoCliente){
        
        ventaServ.editVenta(codigo_venta, nuevaFecha, nuevoTotal, nuevaListaProductos, nuevoCliente);
        
        Venta venta = ventaServ.findVenta(codigo_venta);
        
        //devolvemos la venta modificada para visualizarlo
        return venta;
        
    }
    
    //Obtener la lista de productos de una determinada venta
    @GetMapping("/ventas/productos/{codigo_venta}")
    public List<Producto> listaProductosDeDetermVenta(@PathVariable Long codigo_venta){
        
        return ventaServ.listaProductosDeDetermVenta(codigo_venta);
        
    }
    
    //Obtener la sumatoria del monto y también cantidad total de ventas de un determinado día
    @GetMapping("/ventas/fecha/{fecha_venta}")
    public String ventasPorDia(@PathVariable LocalDate fecha_venta){
        
        return ventaServ.ventasPorDia(fecha_venta);
        
    }
    
    //Obtener el codigo_venta, el total, la cantidad de productos, el nombre del cliente y el
    //apellido del cliente de la venta con el monto más alto de todas.
    @GetMapping("/ventas/mayor_venta")
    public VentaMontoMasAltoDTO ventaMasAlta(){
        
        return ventaServ.montoMasAlto();
        
    }
    
}
