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

import grupo1.hospital.api.servico.Servico;
import grupo1.hospital.api.servico.ServicoInterface;

@Path("servico")
public class ServicoResource {

	private ServicoInterface servicoInterface;

	@Autowired
	public ServicoResource(ServicoInterface servico) {
		this.servicoInterface = servico;
	}

	@GET
	@Produces("application/json")
	public Servico getServico(@QueryParam("id") Integer id) {
		return this.servicoInterface.getServico(id);
	}
	
	@GET
	@Path("/todos")
	@Produces("application/json")
	public List<Servico> getListaServico(){
		return this.servicoInterface.getListaServico();
	}
	
	@POST
	@Produces("application/json")
	public Servico cadastraServico(@RequestBody() Servico servico) {
		return this.servicoInterface.salvarServico(servico);
	}
	
	@PUT
	@Path("/atualizar")
	@Produces("application/json")
	public Servico atualizaServico(@RequestBody() Servico servico) {
		return this.servicoInterface.atualizarServico(servico);
	}
	
	@DELETE
	@Path("/excluir")
	@Produces("application/json")
	public Boolean excluirServico(@QueryParam("id") Integer id) {
		return this.servicoInterface.excluir(id);
	}
}
