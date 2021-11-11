package grupo1.hospital.api.servico;

import grupo1.hospital.api.dao.EnfermeiroDao;
import grupo1.hospital.api.dao.MedicoDao;
import grupo1.hospital.api.dao.PacienteDao;
import grupo1.hospital.api.enfermeiro.Enfermeiro;
import grupo1.hospital.api.medico.Medico;
import grupo1.hospital.api.paciente.Paciente;

public class Servico {
	private Integer id;
	private String descricao;
	private Medico medico;
	private Paciente paciente;
	private Enfermeiro enfermeiro;

	public Servico(String descricao, Integer idMedico, Integer idPaciente, Integer idEnfermeiro){
		this.descricao = descricao;
		MedicoDao medicoDao = new MedicoDao();
		this.medico = medicoDao.findById(idMedico);
		PacienteDao pacienteDao = new PacienteDao();
		this.paciente = pacienteDao.findById(idPaciente);
		EnfermeiroDao enfermeiroDao = new EnfermeiroDao();
		this.enfermeiro = enfermeiroDao.findById(idEnfermeiro);
	}

	/* Getters */
	public Integer getId() {
		return this.id;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public Medico getMedico(){
		return this.medico;
	}
	
	public Paciente getPaciente(){
		return this.paciente;
	}
	
	public Enfermeiro getEnfermeiro(){
		return this.enfermeiro;
	}

	/* Setters */
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setMedico(Integer idMedico){
		MedicoDao medicoDao = new MedicoDao();
		this.medico = medicoDao.findById(idMedico);
	}
	
	public void setEnfermeiro(Integer idEnfermeiro){
		EnfermeiroDao enfermeiroDao = new EnfermeiroDao();
		this.enfermeiro = enfermeiroDao.findById(idEnfermeiro);
	}
	
	public void setPaciente(Integer idPaciente){
		PacienteDao pacienteDao = new PacienteDao();
		this.paciente = pacienteDao.findById(idPaciente);
	}
}
