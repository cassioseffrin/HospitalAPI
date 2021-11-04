package grupo1.hospital.api.servico;

import java.util.List;

public interface ServicoInterface {
	public Servico getServico(Integer id);
	public Servico salvarServico(Servico servico);
	public List<Servico> getListaServico();
	public Servico atualizarServico(Servico servico);
	public Boolean excluir(Integer id);
}
