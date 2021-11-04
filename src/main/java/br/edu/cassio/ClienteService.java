package br.edu.cassio;

import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.cassio.dao.ClienteDao;
import br.edu.cassio.model.Cliente;

@Component
public class ClienteService implements ClienteInterface {

	private ClienteDao clienteDao;
	
	
	ClienteService(){
		clienteDao = new ClienteDao();
	}
	
	public String getCliente(Integer id) {
		
		Cliente cli = clienteDao.buscar(id);
		return "Ola cliente " + cli.getNome() + "!";
	}

	@Override
	public List<Cliente> getClientes() {
		return clienteDao.listar();
	}

	@Override
	public void salvar(Cliente cliente) {
		clienteDao.inserir(cliente);
	}

	@Override
	public void alterar(Cliente cliente) {
		clienteDao.alterar(cliente);
		
	}
}