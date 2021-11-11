package grupo1.hospital.api.medico;

import java.time.LocalDate;

import grupo1.hospital.api.pessoa.Pessoa;

public class Medico extends Pessoa {

	private Integer id;
	private String especialidade;

	public Medico(String nome, String cpf, Integer rg, String telefone, LocalDate dtNascimento, 
			String sexo, Integer idPessoa, String especialidade) {
		super(nome, cpf, rg, telefone, dtNascimento, sexo, idPessoa);
		this.especialidade = especialidade;
	}

	/* Getters */
	public Integer getId() {
		return this.id;
	}
	
	public String getEspecialidade() {
		return this.especialidade;
	}

	/* Setters */
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
	/* Overrides */
	@Override
	public String toString() {
		return "Nome: "+this.getNome()+" CPF: "+this.getCpf()+" RG: "+this.getRg()+" Telefone: "+this.getTelefone()+" Especialidade: "+this.getEspecialidade();
	}
}
