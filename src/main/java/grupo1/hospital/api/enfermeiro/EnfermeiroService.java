package grupo1.hospital.api.enfermeiro;

import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import grupo1.hospital.api.dao.EnfermeiroDao;
import grupo1.hospital.api.dao.PessoaDao;
import grupo1.hospital.api.pessoa.Pessoa;

@Component
public class EnfermeiroService implements EnfermeiroInterface{

	private EnfermeiroDao enfermeiroDao;
	private PessoaDao pessoaDao;

	@Override
	@Produces("application/json")
	public Enfermeiro getEnfermeiro(Integer id) {
		enfermeiroDao = new EnfermeiroDao();
		Enfermeiro m = enfermeiroDao.findById(id);
		return m;
	}

	@Override
	public Enfermeiro salvarEnfermeiro(Enfermeiro enfermeiro) {
		pessoaDao = new PessoaDao();
		enfermeiroDao = new EnfermeiroDao();

		if(enfermeiro.getIdPessoa() == 0) {
			Pessoa pessoaEnfermeiro = new Pessoa(enfermeiro.getNome(), enfermeiro.getCpf(),
					enfermeiro.getRg(), enfermeiro.getTelefone(), enfermeiro.getDtNascimento(), enfermeiro.getSexo(), enfermeiro.getIdPessoa());
			Integer idPessoa = pessoaDao.inserir(pessoaEnfermeiro);
			enfermeiro.setIdPessoa(idPessoa);
		}
		Integer idEnfermeiro = enfermeiroDao.inserir(enfermeiro);
		enfermeiro.setId(idEnfermeiro);
		
		return enfermeiro;
	}

	@Override
	@Produces("application/json")
	public List<Enfermeiro> getListaEnfermeiro() {
		enfermeiroDao = new EnfermeiroDao();
		List<Enfermeiro> enfermeiros = enfermeiroDao.getTodosEnfermeiros();
		return enfermeiros;
	}

	@Override
	@Produces("application/json")
	public Enfermeiro atualizarEnfermeiro(Enfermeiro enfermeiro) {
		pessoaDao = new PessoaDao();
		enfermeiroDao = new EnfermeiroDao();
		Pessoa pessoaEnfermeiro = new Pessoa(enfermeiro.getNome(), enfermeiro.getCpf(),
				enfermeiro.getRg(), enfermeiro.getTelefone(), enfermeiro.getDtNascimento(), enfermeiro.getSexo(), enfermeiro.getIdPessoa());
		
		Boolean vp = pessoaDao.atualizar(pessoaEnfermeiro);
		Boolean vpc = enfermeiroDao.atualizar(enfermeiro);
		if(!vp || !vpc) {
			enfermeiro.setId(0);
			enfermeiro.setIdPessoa(0);
		}

		return enfermeiro;
	}

	@Override
	public Boolean excluir(Integer id) {
		enfermeiroDao = new EnfermeiroDao();
		return enfermeiroDao.excluir(id);
	}
}
