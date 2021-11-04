package br.edu.cassio;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.cassio.model.Cliente;

@Path("cliente")
public class ClienteResource {

	private ClienteInterface clienteInterface;

	@Autowired
	public ClienteResource(ClienteInterface cliente) {
		this.clienteInterface = cliente;
	}

	@GET
	public String getCliente(@QueryParam("id") Integer id) {
		return this.clienteInterface.getCliente(id);
	}
	
    @GET
    @Path("/listaClientes")
    @Produces("application/json")
    public List<Cliente> getClientes( ) {
        return this.clienteInterface.getClientes();
    }
    
    @POST
    @Path("/salvar")
    public boolean salvarCliente(@RequestBody Cliente cliente) {
    	System.out.println(cliente.getNome());
    	
    	this.clienteInterface.salvar(cliente);
		return true;
 
    }
    
    
    @POST
    @Path("/alterar")
    public boolean alterarCliente(@RequestBody Cliente cliente) {
    	System.out.println(cliente.getNome());
    	
    	this.clienteInterface.alterar(cliente);
		return true;
 
    }

}