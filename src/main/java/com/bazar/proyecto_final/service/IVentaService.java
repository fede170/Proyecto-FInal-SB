package com.bazar.proyecto_final.service;

import com.bazar.proyecto_final.dto.VentaMontoMasAltoDTO;
import com.bazar.proyecto_final.model.Cliente;
import com.bazar.proyecto_final.model.Producto;
import com.bazar.proyecto_final.model.Venta;
import java.time.LocalDate;
import java.util.List;

public interface IVentaService {
    
    //Lectura
    public List<Venta> getVentas();
    
    //Lectura de una determinada venta
    public Venta findVenta(Long codigo_venta);
    
    //Alta
    public String saveVenta(Venta venta);
    
    //Baja
    public void deleteVenta(Long codigo_venta);
    
    //Edicion
    public void editVenta(Long codigo_venta, LocalDate fecha, Double total, List<Producto> listaProductos, Cliente unCliente);

    //Obtener la lista de productos de una determinada venta
    public List<Producto> listaProductosDeDetermVenta(Long codigo_venta);
    
    //Obtener la sumatoria del monto y también cantidad total de ventas de un determinado día
    public String ventasPorDia(LocalDate fecha_venta);
    
    //Obtener el codigo_venta, el total, la cantidad de productos, el nombre del cliente y el
    //apellido del cliente de la venta con el monto más alto de todas. 
    public VentaMontoMasAltoDTO montoMasAlto();

    //utilizamos para guardar la venta despues de editar
    public void saveVta(Venta venta);
    
    
}
