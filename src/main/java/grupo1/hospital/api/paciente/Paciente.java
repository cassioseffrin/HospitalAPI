package grupo1.hospital.api.paciente;

import java.time.LocalDate;

import grupo1.hospital.api.pessoa.Pessoa;

public class Paciente extends Pessoa {
	
	private Integer id;
	private Integer gravidade;

	public Paciente(String nome, String cpf, Integer rg, String telefone, LocalDate dtNascimento, 
			String sexo, Integer idPessoa, Integer gravidade) {
		super(nome, cpf, rg, telefone, dtNascimento, sexo, idPessoa);
		this.gravidade = gravidade;
	}

	/* Getters */
	public Integer getId() {
		return this.id;
	}
	
	public Integer getGravidade() {
		return this.gravidade;
	}

	/* Setters */
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setGravidade(Integer gravidade) {
		this.gravidade = gravidade;
	}
	
	/* Overrides */
	@Override
	public String toString() {
		return "Nome: "+this.getNome()+" CPF: "+this.getCpf()+" RG: "+this.getRg()+" Telefone: "+this.getTelefone()+" Gravidade: "+this.getGravidade();
	}
}
