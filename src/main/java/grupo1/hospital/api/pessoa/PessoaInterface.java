package grupo1.hospital.api.pessoa;

import java.util.List;

public interface PessoaInterface {
	public Pessoa getPessoa(Integer id);
	public Pessoa salvarPessoa(Pessoa pessoa);
	public List<Pessoa> getListaPessoa();
	public Pessoa atualizarPessoa(Pessoa pessoa);
	public Boolean excluir(Integer id);
}
