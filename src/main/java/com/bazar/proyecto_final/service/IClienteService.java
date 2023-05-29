package com.bazar.proyecto_final.service;

import com.bazar.proyecto_final.model.Cliente;
import java.util.List;

public interface IClienteService {
    
    //Lectura
    public List<Cliente> getClientes();
    
    //Lectura de un determinado cliente
    public Cliente findCliente(Long id_cliente);
    
    //Alta
    public void saveCliente(Cliente cliente);
    
    //Baja
    public void deleteCliente(Long id_cliente);
    
    //Edicion
    public void editCliente(Long id_cliente, String nombre, String apellido, String dni);

}
