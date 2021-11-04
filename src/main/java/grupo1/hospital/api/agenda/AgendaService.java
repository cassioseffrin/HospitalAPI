package grupo1.hospital.api.agenda;

import java.util.List;

import org.springframework.stereotype.Component;

import grupo1.hospital.api.dao.AgendaDao;

@Component
public class AgendaService implements AgendaInterface{

	private AgendaDao agendaDao;
	
	@Override
	public Agenda getAgenda(Integer id) {
		agendaDao = new AgendaDao();
		Agenda a = agendaDao.findById(id);
		return a;
	}

	@Override
	public Agenda salvarAgenda(Agenda agenda) {
		agendaDao = new AgendaDao();
		Integer idAgenda = agendaDao.inserir(agenda);
		agenda.setId(idAgenda);
		return agenda;
	}

	@Override
	public List<Agenda> getListaAgenda() {
		agendaDao = new AgendaDao();
		List<Agenda> agendas = agendaDao.getTodasAgendas();
		return agendas;
	}

	@Override
	public Agenda atualizarAgenda(Agenda agenda) {
		agendaDao = new AgendaDao();
		
		Boolean vpc = agendaDao.atualizar(agenda);
		if(!vpc) {
			agenda.setId(0);
		}

		return agenda;
	}

	@Override
	public Boolean excluir(Integer id) {
		agendaDao = new AgendaDao();
		return agendaDao.excluir(id);
	}

}
