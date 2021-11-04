package grupo1.hospital.api.pessoa;

import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import grupo1.hospital.api.dao.PessoaDao;

@Component
public class PessoaService implements PessoaInterface{
	private PessoaDao pessoaDao;

	@Override
	@Produces("application/json")
	public Pessoa getPessoa(Integer id) {
		pessoaDao = new PessoaDao();
		Pessoa p = pessoaDao.findById(id);
		return p;
	}

	@Override
	@Produces("application/json")
	public Pessoa salvarPessoa(Pessoa pessoa) {
		pessoaDao = new PessoaDao();

		Pessoa pessoaCad = new Pessoa(pessoa.getNome(), pessoa.getCpf(),
				pessoa.getRg(), pessoa.getTelefone(), pessoa.getDtNascimento(), pessoa.getSexo(), pessoa.getIdPessoa());
		Integer idPessoa = pessoaDao.inserir(pessoaCad);
		pessoaCad.setIdPessoa(idPessoa);
		
		return pessoaCad;
	}

	@Override
	@Produces("application/json")
	public List<Pessoa> getListaPessoa() {
		pessoaDao = new PessoaDao();
		List<Pessoa> pessoas = pessoaDao.getTodasPessoas();
		return pessoas;
	}

	@Override
	@Produces("application/json")
	public Pessoa atualizarPessoa(Pessoa pessoa) {
		pessoaDao = new PessoaDao();
		Pessoa pessoaCad = new Pessoa(pessoa.getNome(), pessoa.getCpf(),
			pessoa.getRg(), pessoa.getTelefone(), pessoa.getDtNascimento(), pessoa.getSexo(), pessoa.getIdPessoa());
		
		Boolean vp = pessoaDao.atualizar(pessoaCad);
		if(!vp) {
			pessoaCad.setIdPessoa(0);
		}

		return pessoaCad;
	}

	@Override
	@Produces("application/json")
	public Boolean excluir(Integer id) {
		pessoaDao = new PessoaDao();
		return pessoaDao.excluir(id);
	}
}
