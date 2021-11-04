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

import grupo1.hospital.api.internacao.Internacao;
import grupo1.hospital.api.internacao.InternacaoInterface;

@Path("internacao")
public class InternacaoResource {
	private InternacaoInterface internacaoInterface;

	@Autowired
	public InternacaoResource(InternacaoInterface internacao) {
		this.internacaoInterface = internacao;
	}

	@GET
	@Produces("application/json")
	public Internacao getInternacao(@QueryParam("id") Integer id) {
		return this.internacaoInterface.getInternacao(id);
	}
	
	@GET
	@Path("/todos")
	@Produces("application/json")
	public List<Internacao> getListaInternacao(){
		return this.internacaoInterface.getListaInternacoes();
	}
	
	@POST
	@Produces("application/json")
	public Internacao cadastraInternacao(@RequestBody() Internacao internacao) {
		return this.internacaoInterface.salvarInternacao(internacao);
	}
	
	@PUT
	@Path("/atualizar")
	@Produces("application/json")
	public Internacao atualizaInternacao(@RequestBody() Internacao internacao) {
		return this.internacaoInterface.atualizarInternacao(internacao);
	}
	
	@DELETE
	@Path("/excluir")
	@Produces("application/json")
	public Boolean atualizaInternacao(@QueryParam("id") Integer id) {
		return this.internacaoInterface.excluir(id);
	}
}
