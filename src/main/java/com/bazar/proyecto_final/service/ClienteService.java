package com.bazar.proyecto_final.service;

import com.bazar.proyecto_final.model.Cliente;
import com.bazar.proyecto_final.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    private IClienteRepository cliRepo;
    
    //Lectura de clientes
    @Override
    public List<Cliente> getClientes() {
        
        List<Cliente> listaClientes = cliRepo.findAll();
        
        return  listaClientes;
        
    }

    //Lectura de un determinado cliente
    @Override
    public Cliente findCliente(Long id_cliente) {
        
        Cliente cliente = cliRepo.findById(id_cliente).orElse(null);
        
        return cliente;
        
    }

    //Alta de un cliente
    @Override
    public void saveCliente(Cliente cliente) {
        
        cliRepo.save(cliente);
        
    }

    //Baja de un cliente
    @Override
    public void deleteCliente(Long id_cliente) {
        
        cliRepo.deleteById(id_cliente);
        
    }

    //Edicion de un cliente
    @Override
    public void editCliente(Long id_cliente, String nombre, String apellido, String dni) {
        
        //Busco el objeto original
        Cliente cliente = this.findCliente(id_cliente);
        
        //Seteamos los valores nuevos al objeto encontrado
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDni(dni);
        
        //guardamos el producto midificado
        this.saveCliente(cliente);
        
    }
    
    
    
}
