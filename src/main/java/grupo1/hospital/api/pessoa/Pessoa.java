package grupo1.hospital.api.pessoa;

import java.time.LocalDate;

public class Pessoa {
	/* Attributes */
	private String nome;
	private String cpf;
	private Integer rg;
	private String telefone;
	private Integer id;
	private LocalDate dtNascimento;
	private String sexo;
	
	
	/* Constructor */
	public Pessoa (String nome, String cpf, Integer rg, String telefone, LocalDate dtNascimento, String sexo, Integer id) {
		this.nome = nome;
		this.cpf= cpf;
		this.rg = rg;
		this.telefone = telefone;
		this.dtNascimento = dtNascimento;
		this.sexo = sexo;
		this.id = id;
	}

	/* Getters */
	public String getNome() {
		return this.nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public Integer getRg() {
		return this.rg;
	}
	
	public String getTelefone() {
		return this.telefone;
	}
	
	public LocalDate getDtNascimento() {
		return this.dtNascimento;
	}
	
	public Integer getIdPessoa() {
		return this.id;
	}
	
	public String getSexo() {
		return this.sexo;
	}

	/* Setters */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setIdPessoa(Integer id) {
		this.id = id;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	/* Overrides */
	@Override
	public String toString() {
		return "Nome: "+this.nome+" CPF: "+this.cpf+" RG:"+this.rg;
	}

}