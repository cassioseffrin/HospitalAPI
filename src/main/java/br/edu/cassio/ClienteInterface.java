package br.edu.cassio;

import java.util.List;

import br.edu.cassio.model.Cliente;

public interface ClienteInterface {
	public String getCliente(Integer id);
	public List<Cliente> getClientes();
	public void salvar(Cliente cliente);
	public void alterar(Cliente cliente);
}
