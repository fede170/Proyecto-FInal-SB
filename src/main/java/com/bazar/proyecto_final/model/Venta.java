package com.bazar.proyecto_final.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Venta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo_venta;
    
    private LocalDate fecha;
    private Double total;
    
    @OneToMany
    private List<Producto> listaProductos;
    
    @OneToOne
    private Cliente unCliente;

    public Venta() {
    }

    public Venta(Long codigo_venta, LocalDate fecha, Double total, List<Producto> listaProductos, Cliente unCliente) {
        this.codigo_venta = codigo_venta;
        this.fecha = fecha;
        this.total = total;
        this.listaProductos = listaProductos;
        this.unCliente = unCliente;
    }

    
}
