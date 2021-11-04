package br.edu.cassio.model;

import java.io.Serializable;
import java.time.LocalDate;

 
public class Cliente extends Pessoa implements Serializable, Comparable<Object> {
 
 
	private static final long serialVersionUID = 2L;
	
 
	public static final String ARQUIVO_SERIAL = "/Users/cassioseffrin/dev/DevSoftware2021/Farmacia/database/Cliente.obj";

	private String telefone;
	
	private String endereco;
	
	
	public Cliente() {
	}
	
   
	public Cliente(int id, String nome, int rg, int cpf, String sexo, String endereco, String telefone, LocalDate dataNascimento) {
		super(id,nome, rg, cpf,sexo, dataNascimento, telefone );
		this.telefone=telefone;
		this.endereco = endereco;
	}

	public void setTelefone(String f) {
		this.telefone = f;
	}

	public String getTelefone() {
		return this.telefone;
	}

 
	
	 

	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	@Override
	public String toString() {
		return super.toString() + ";" + this.telefone + "\n";
	}
	
	public String imprimir() {
		return String.format("Nome %s\nCPF: %s\nRG: %s\nFone: %s\nEndereco: %s\n ********************\n", 
				getNome(), getCpf(), getRg(), getTelefone(), getEndereco()) ;
	}

	@Override
	public int compareTo(Object o) {
		Cliente cli = (Cliente) o;
		return this.getNome().toLowerCase().compareTo(cli.getNome().toLowerCase());
	}
 

}
