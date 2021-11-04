package grupo1.hospital.api.internacao;

import java.time.LocalTime;

import grupo1.hospital.api.dao.PacienteDao;
import grupo1.hospital.api.dao.PessoaDao;
import grupo1.hospital.api.paciente.Paciente;
import grupo1.hospital.api.pessoa.Pessoa;

public class Internacao {
	private Integer id;
	private LocalTime horario;
	private String quarto;
	private Paciente paciente;
	private Pessoa acompanhante;
	
	public Internacao(LocalTime horario, String quarto, Integer idPaciente, Integer idPessoaAcompanhante) {
		this.horario = horario;
		this.quarto = quarto;
		
		PacienteDao pacienteDao = new PacienteDao();
		this.paciente = pacienteDao.findById(idPaciente);
		
		PessoaDao pessoaDao = new PessoaDao();
		this.acompanhante = pessoaDao.findById(idPessoaAcompanhante);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public String getQuarto() {
		return quarto;
	}

	public void setQuarto(String quarto) {
		this.quarto = quarto;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Integer idPaciente) {
		PacienteDao pacienteDao = new PacienteDao();
		this.paciente = pacienteDao.findById(idPaciente);
	}

	public Pessoa getAcompanhante() {
		return acompanhante;
	}

	public void setAcompanhante(Integer idPessoaAcompanhante) {
		PessoaDao pessoaDao = new PessoaDao();
		this.acompanhante = pessoaDao.findById(idPessoaAcompanhante);
	}
}
