package com.bazar.proyecto_final.dto;

import com.bazar.proyecto_final.model.Producto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VentaMontoMasAltoDTO {
    
    private Long codigo_venta;
    private Double total;
    private int cantProductos;
    private List<Producto> listaProductos;
    private String nombre_cliente;
    private String apellido_cliente;

    public VentaMontoMasAltoDTO() {
    }

    public VentaMontoMasAltoDTO(Long codigo_venta, Double total, int cantProductos, List<Producto> listaProductos, String nombre_cliente, String apellido_cliente) {
        this.codigo_venta = codigo_venta;
        this.total = total;
        this.cantProductos = cantProductos;
        this.listaProductos = listaProductos;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
    }

    
    
}
