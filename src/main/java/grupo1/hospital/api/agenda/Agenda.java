package grupo1.hospital.api.agenda;

import java.time.LocalDate;
import java.time.LocalTime;

import grupo1.hospital.api.dao.PacienteDao;
import grupo1.hospital.api.paciente.Paciente;

public class Agenda {
	private Integer id;
	private LocalDate dtAgenda;
	private LocalTime horario;
	private Paciente paciente;

	public Agenda(LocalDate dtAgenda, LocalTime horario, Integer idPaciente) {
		this.dtAgenda = dtAgenda;
		this.horario = horario;
		PacienteDao pacienteDao = new PacienteDao();
		this.paciente = pacienteDao.findById(idPaciente);
	}

	/* Getters */
	public Integer getId() {
		return this.id;
	}
	
	public LocalDate getDtAgenda() {
		return this.dtAgenda;
	}
	
	public LocalTime getHorario() {
		return this.horario;
	}
	
	public Paciente getPaciente() {
		return this.paciente;
	}

	/* Setters */
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setDtAgenda(LocalDate dtAgenda) {
		this.dtAgenda = dtAgenda;
	}
	
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	
	public void setPaciente(Integer idPaciente) {
		PacienteDao pacienteDao = new PacienteDao();
		this.paciente = pacienteDao.findById(idPaciente);
	}
}
