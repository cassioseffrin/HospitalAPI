package grupo1.hospital.api.paciente;

import java.util.List;

public interface PacienteInterface {
	public Paciente getPaciente(Integer id);
	public Paciente salvarPaciente(Paciente paciente);
	public List<Paciente> getListaPaciente();
	public Paciente atualizarPaciente(Paciente paciente);
	public Boolean excluir(Integer id);
}
