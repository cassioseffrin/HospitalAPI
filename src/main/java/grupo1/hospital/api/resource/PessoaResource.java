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

import grupo1.hospital.api.pessoa.PessoaInterface;
import grupo1.hospital.api.pessoa.Pessoa;

@Path("pessoa")
public class PessoaResource {

	private PessoaInterface pessoaInterface;

	@Autowired
	public PessoaResource(PessoaInterface pessoa) {
		this.pessoaInterface = pessoa;
	}

	@GET
	@Produces("application/json")
	public Pessoa getPessoa(@QueryParam("id") Integer id) {
		return this.pessoaInterface.getPessoa(id);
	}
	
	@GET
	@Path("/todos")
	@Produces("application/json")
	public List<Pessoa> getListaPessoa(){
		return this.pessoaInterface.getListaPessoa();
	}
	
	@POST
	@Produces("application/json")
	public Pessoa cadastraPessoa(@RequestBody() Pessoa pessoa) {
		return this.pessoaInterface.salvarPessoa(pessoa);
	}
	
	@PUT
	@Path("/atualizar")
	@Produces("application/json")
	public Pessoa atualizaPessoa(@RequestBody() Pessoa pessoa) {
		return this.pessoaInterface.atualizarPessoa(pessoa);
	}
	
	@DELETE
	@Path("/excluir")
	@Produces("application/json")
	public Boolean excluirPessoa(@QueryParam("id") Integer id) {
		return this.pessoaInterface.excluir(id);
	}
}
