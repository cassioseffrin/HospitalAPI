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

import grupo1.hospital.api.agenda.Agenda;
import grupo1.hospital.api.agenda.AgendaInterface;

@Path("agenda")
public class AgendaResource {
	private AgendaInterface agendaInterface;

	@Autowired
	public AgendaResource(AgendaInterface agenda) {
		this.agendaInterface = agenda;
	}

	@GET
	@Produces("application/json")
	public Agenda getAgenda(@QueryParam("id") Integer id) {
		return this.agendaInterface.getAgenda(id);
	}
	
	@GET
	@Path("/todos")
	@Produces("application/json")
	public List<Agenda> getListaAgenda(){
		return this.agendaInterface.getListaAgenda();
	}
	
	@POST
	@Produces("application/json")
	public Agenda cadastraAgenda(@RequestBody() Agenda agenda) {
		return this.agendaInterface.salvarAgenda(agenda);
	}
	
	@PUT
	@Path("/atualizar")
	@Produces("application/json")
	public Agenda atualizaAgenda(@RequestBody() Agenda agenda) {
		return this.agendaInterface.atualizarAgenda(agenda);
	}
	
	@DELETE
	@Path("/excluir")
	@Produces("application/json")
	public Boolean atualizaAgenda(@QueryParam("id") Integer id) {
		return this.agendaInterface.excluir(id);
	}
}
