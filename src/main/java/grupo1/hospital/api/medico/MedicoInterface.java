package grupo1.hospital.api.medico;

import java.util.List;

public interface MedicoInterface {
	public Medico getMedico(Integer id);
	public Medico salvarMedico(Medico medico);
	public List<Medico> getListaMedico();
	public Medico atualizarMedico(Medico medico);
	public Boolean excluir(Integer id);
}
