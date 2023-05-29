package com.bazar.proyecto_final.controllers;

import com.bazar.proyecto_final.model.Cliente;
import com.bazar.proyecto_final.service.IClienteService;
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
public class ClienteController {
    
    @Autowired
    private IClienteService cliServ;
    
    //Alta de un cliente
    @PostMapping("/clientes/crear")
    public void saveCliente(@RequestBody Cliente cliente){
        
        cliServ.saveCliente(cliente);
        
    }
    
    //Traer lista completa de clientes
    @GetMapping("/clientes")
    public List<Cliente> getClientes(){
        
        List<Cliente> listaClientes = cliServ.getClientes();
        
        return listaClientes;
        
    }
    
    //Traer un cliente en particular
    @GetMapping("/clientes/{id_cliente}")
    public Cliente findCliente (@PathVariable Long id_cliente){
        
        Cliente cliente = cliServ.findCliente(id_cliente);
        
        return cliente;
        
    }
    
    //Eliminamos un cliente
    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    public void deleteCliente(@PathVariable Long id_cliente){
        
        cliServ.deleteCliente(id_cliente);
        
    }
    
    //Editamos un cliente
    @PutMapping("/clientes/editar/{id_cliente}")
    public Cliente editCliente(@PathVariable Long id_cliente, 
                             @RequestParam(required = false, name = "nombre") String nuevoNombre,
                             @RequestParam(required = false, name = "apellido") String nuevoApellido,
                             @RequestParam(required = false, name = "dni") String nuevoDni){
        
        cliServ.editCliente(id_cliente, nuevoNombre, nuevoApellido, nuevoDni);
        
        Cliente clie = cliServ.findCliente(id_cliente);
        
        //devolvemos el cliente modificado para visualizarlo
        return clie;
        
    }
}
