package grupo1.hospital.api.internacao;

import java.util.List;

import org.springframework.stereotype.Component;

import grupo1.hospital.api.dao.InternacaoDao;

@Component
public class InternacaoService implements InternacaoInterface{

	private InternacaoDao internacaoDao;

	@Override
	public Internacao getInternacao(Integer id) {
		internacaoDao = new InternacaoDao();
		return internacaoDao.findById(id);
	}

	@Override
	public Internacao salvarInternacao(Internacao internacao) {
		internacaoDao = new InternacaoDao();
		Integer idInternacao = internacaoDao.inserir(internacao);
		internacao.setId(idInternacao);
		return internacao;
	}

	@Override
	public List<Internacao> getListaInternacoes() {
		internacaoDao = new InternacaoDao();
		return internacaoDao.getTodosInternacoes();
		
	}

	@Override
	public Internacao atualizarInternacao(Internacao internacao) {
		internacaoDao = new InternacaoDao();
		Boolean iv = internacaoDao.atualizar(internacao);
		if(!iv) {
			internacao.setId(0);
		}
		return internacao;
	}

	@Override
	public Boolean excluir(Integer id) {
		internacaoDao = new InternacaoDao();
		return internacaoDao.excluir(id);
	}

}
