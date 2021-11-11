package grupo1.hospital.api.agenda;

import java.util.List;

public interface AgendaInterface {
	public Agenda getAgenda(Integer id);
	public Agenda salvarAgenda(Agenda agenda);
	public List<Agenda> getListaAgenda();
	public Agenda atualizarAgenda(Agenda agenda);
	public Boolean excluir(Integer id);
}
