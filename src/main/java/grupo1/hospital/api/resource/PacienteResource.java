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

import grupo1.hospital.api.paciente.Paciente;
import grupo1.hospital.api.paciente.PacienteInterface;

@Path("paciente")
public class PacienteResource {

	private PacienteInterface pacienteInterface;

	@Autowired
	public PacienteResource(PacienteInterface paciente) {
		this.pacienteInterface = paciente;
	}

	@GET
	@Produces("application/json")
	public Paciente getPaciente(@QueryParam("id") Integer id) {
		return this.pacienteInterface.getPaciente(id);
	}
	
	@GET
	@Path("/todos")
	@Produces("application/json")
	public List<Paciente> getListaPaciente(){
		return this.pacienteInterface.getListaPaciente();
	}
	
	@POST
	@Produces("application/json")
	public Paciente cadastraPaciente(@RequestBody() Paciente paciente) {
		return this.pacienteInterface.salvarPaciente(paciente);
	}
	
	@PUT
	@Path("/atualizar")
	@Produces("application/json")
	public Paciente atualizaPaciente(@RequestBody() Paciente paciente) {
		return this.pacienteInterface.atualizarPaciente(paciente);
	}
	
	@DELETE
	@Path("/excluir")
	@Produces("application/json")
	public Boolean excluirPaciente(@QueryParam("id") Integer id) {
		return this.pacienteInterface.excluir(id);
	}
}
