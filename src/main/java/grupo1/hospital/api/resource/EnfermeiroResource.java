package grupo1.hospital.api.resource;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import grupo1.hospital.api.enfermeiro.Enfermeiro;
import grupo1.hospital.api.enfermeiro.EnfermeiroInterface;

@Path("enfermeiro")
public class EnfermeiroResource {
	private EnfermeiroInterface enfermeiroInterface;

	@Autowired
	public EnfermeiroResource(EnfermeiroInterface enfermeiro) {
		this.enfermeiroInterface = enfermeiro;
	}

	@GET
	@Produces("application/json")
	public Enfermeiro getEnfermeiro(@QueryParam("id") Integer id) {
		return this.enfermeiroInterface.getEnfermeiro(id);
	}
	
	@GET
	@Path("/todos")
	@Produces("application/json")
	public List<Enfermeiro> getListaEnfermeiro(){
		return this.enfermeiroInterface.getListaEnfermeiro();
	}
	
	@POST
	@Produces("application/json")
	public Enfermeiro cadastraEnfermeiro(@RequestBody() Enfermeiro enfermeiro) {
		return this.enfermeiroInterface.salvarEnfermeiro(enfermeiro);
	}
	
	@PUT
	@Path("/atualizar")
	@Produces("application/json")
	public Enfermeiro atualizaEnfermeiro(@RequestBody() Enfermeiro enfermeiro) {
		return this.enfermeiroInterface.atualizarEnfermeiro(enfermeiro);
	}
	
	@DELETE
	@Path("/excluir")
	@Produces("application/json")
	public Boolean atualizaEnfermeiro(@QueryParam("id") Integer id) {
		return this.enfermeiroInterface.excluir(id);
	}
}
