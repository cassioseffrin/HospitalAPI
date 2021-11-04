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

import grupo1.hospital.api.medico.Medico;
import grupo1.hospital.api.medico.MedicoInterface;

@Path("medico")
public class MedicoResource {
	private MedicoInterface medicoInterface;

	@Autowired
	public MedicoResource(MedicoInterface medico) {
		this.medicoInterface = medico;
	}

	@GET
	@Produces("application/json")
	public Medico getMedico(@QueryParam("id") Integer id) {
		return this.medicoInterface.getMedico(id);
	}
	
	@GET
	@Path("/todos")
	@Produces("application/json")
	public List<Medico> getListaMedico(){
		return this.medicoInterface.getListaMedico();
	}
	
	@POST
	@Produces("application/json")
	public Medico cadastraMedico(@RequestBody() Medico medico) {
		return this.medicoInterface.salvarMedico(medico);
	}
	
	@PUT
	@Path("/atualizar")
	@Produces("application/json")
	public Medico atualizaMedico(@RequestBody() Medico medico) {
		return this.medicoInterface.atualizarMedico(medico);
	}
	
	@DELETE
	@Path("/excluir")
	@Produces("application/json")
	public Boolean excluirMedico(@QueryParam("id") Integer id) {
		return this.medicoInterface.excluir(id);
	}
}
