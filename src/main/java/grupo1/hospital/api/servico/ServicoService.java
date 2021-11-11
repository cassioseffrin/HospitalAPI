package grupo1.hospital.api.servico;

import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import grupo1.hospital.api.dao.ServicoDao;

@Component
public class ServicoService implements ServicoInterface{

	private ServicoDao servicoDao;

	@Override
	@Produces("application/json")
	public Servico getServico(Integer id) {
		servicoDao = new ServicoDao();
		Servico s = servicoDao.findById(id);
		return s;
	}

	@Override
	public Servico salvarServico(Servico servico) {
		servicoDao = new ServicoDao();
		Integer idServico = servicoDao.inserir(servico);
		servico.setId(idServico);
		
		return servico;
	}

	@Override
	@Produces("application/json")
	public List<Servico> getListaServico() {
		servicoDao = new ServicoDao();
		List<Servico> servicos = servicoDao.getTodosServicos();
		return servicos;
	}

	@Override
	@Produces("application/json")
	public Servico atualizarServico(Servico servico) {
		servicoDao = new ServicoDao();
		
		Boolean vp = servicoDao.atualizar(servico);
		if(!vp) {
			servico.setId(0);
		}

		return servico;
	}

	@Override
	public Boolean excluir(Integer id) {
		servicoDao = new ServicoDao();
		return servicoDao.excluir(id);
	}
}
