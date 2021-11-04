package grupo1.hospital.api.enfermeiro;

import java.util.List;

public interface EnfermeiroInterface {
	public Enfermeiro getEnfermeiro(Integer id);
	public Enfermeiro salvarEnfermeiro(Enfermeiro enfermeiro);
	public List<Enfermeiro> getListaEnfermeiro();
	public Enfermeiro atualizarEnfermeiro(Enfermeiro enfermeiro);
	public Boolean excluir(Integer id);
}
