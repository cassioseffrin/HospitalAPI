package grupo1.hospital.api.medico;

import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import grupo1.hospital.api.dao.MedicoDao;
import grupo1.hospital.api.dao.PessoaDao;
import grupo1.hospital.api.pessoa.Pessoa;

@Component
public class MedicoService implements MedicoInterface{

	private MedicoDao medicoDao;
	private PessoaDao pessoaDao;

	@Override
	@Produces("application/json")
	public Medico getMedico(Integer id) {
		medicoDao = new MedicoDao();
		Medico m = medicoDao.findById(id);
		return m;
	}

	@Override
	public Medico salvarMedico(Medico medico) {
		pessoaDao = new PessoaDao();
		medicoDao = new MedicoDao();

		if(medico.getIdPessoa() == 0) {
			Pessoa pessoaMedico = new Pessoa(medico.getNome(), medico.getCpf(),
					medico.getRg(), medico.getTelefone(), medico.getDtNascimento(), medico.getSexo(), medico.getIdPessoa());
			Integer idPessoa = pessoaDao.inserir(pessoaMedico);
			medico.setIdPessoa(idPessoa);
		}
		Integer idMedico = medicoDao.inserir(medico);
		medico.setId(idMedico);
		
		return medico;
	}

	@Override
	@Produces("application/json")
	public List<Medico> getListaMedico() {
		medicoDao = new MedicoDao();
		List<Medico> medicos = medicoDao.getTodosMedicos();
		return medicos;
	}

	@Override
	@Produces("application/json")
	public Medico atualizarMedico(Medico medico) {
		pessoaDao = new PessoaDao();
		medicoDao = new MedicoDao();
		Pessoa pessoaMedico = new Pessoa(medico.getNome(), medico.getCpf(),
				medico.getRg(), medico.getTelefone(), medico.getDtNascimento(), medico.getSexo(), medico.getIdPessoa());
		
		Boolean vp = pessoaDao.atualizar(pessoaMedico);
		Boolean vpc = medicoDao.atualizar(medico);
		System.out.println(vp);
		System.out.println(vpc);
		if(!vp || !vpc) {
			medico.setId(0);
			medico.setIdPessoa(0);
		}

		return medico;
	}

	@Override
	@Produces("application/json")
	public Boolean excluir(Integer id) {
		medicoDao = new MedicoDao();
		return medicoDao.excluir(id);
	}
}
