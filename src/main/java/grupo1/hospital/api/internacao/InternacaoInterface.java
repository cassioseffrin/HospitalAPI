package grupo1.hospital.api.internacao;

import java.util.List;


public interface InternacaoInterface {
	public Internacao getInternacao(Integer id);
	public Internacao salvarInternacao(Internacao internacao);
	public List<Internacao> getListaInternacoes();
	public Internacao atualizarInternacao(Internacao internacao);
	public Boolean excluir(Integer id);
}
