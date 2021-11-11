package grupo1.hospital.api.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import grupo1.hospital.api.MySqlSingleton;
import grupo1.hospital.api.pessoa.Pessoa;

public class PessoaDao {
	private Connection connection;
	
	public PessoaDao() {
		this.connection = MySqlSingleton.getConnection();
	}
	
    public Integer inserir(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa(nome, cpf, rg, dtNascimento, telefone, sexo) VALUES(?, ?, ?, ?, ?, ?)";
        Integer idPessoa = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCpf());
            stmt.setInt(3, pessoa.getRg());
            stmt.setDate(4, Date.valueOf(pessoa.getDtNascimento()));
            stmt.setString(5, pessoa.getTelefone());
            stmt.setString(6, pessoa.getSexo());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0)
                idPessoa = 0;
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    idPessoa = rs.getInt(1);
                }
                rs.close();
            }
        } catch (SQLException ex) {
        	System.out.print(ex);
        	idPessoa = 0;
        }
        return idPessoa;
    }
    
    public Boolean excluir(Integer id) {
        String sql = "DELETE from pessoa WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
        	System.out.print(ex);
            return false;
        }
    }
    
    public Boolean atualizar(Pessoa pessoa) {
        String sql = "UPDATE pessoa SET nome = ?, cpf = ?, rg = ?,  dtNascimento = ?, telefone = ?, sexo = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCpf());
            stmt.setInt(3, pessoa.getRg());
            stmt.setDate(4, Date.valueOf(pessoa.getDtNascimento()));
            stmt.setString(5, pessoa.getTelefone());
            stmt.setString(6, pessoa.getSexo());
            stmt.setInt(7, pessoa.getIdPessoa());
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
        	System.out.print(ex);
            return false;
        }
    }
    
    public List<Pessoa> getTodasPessoas() {
        String sql = "SELECT p.id, p.nome, p.rg, p.cpf, p.sexo, p.dtNascimento, p.telefone FROM pessoa p ";
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet res = stmt.executeQuery();
			while (res.next()) {
				Pessoa p = new Pessoa(
					res.getString("nome"), 
					res.getString("cpf"), 
					res.getInt("rg"),
					res.getString("telefone"),
					res.getDate("dtNascimento").toLocalDate(), 
					res.getString("sexo"), 
					res.getInt("id")
				);
				pessoas.add(p);
			};
        } catch (SQLException ex) {
        	System.out.print(ex);
            pessoas = new ArrayList<Pessoa>();
        }
        return pessoas;
    }
    
    public Pessoa findById(Integer id){
		String sql = "SELECT p.id, p.nome, p.rg, p.cpf, p.sexo, p.dtNascimento, p.telefone FROM pessoa p WHERE id = ?";
		Pessoa p = null;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet res = stmt.executeQuery();
			if (res.next()) {
				p = new Pessoa(
					res.getString("nome"), 
					res.getString("cpf"), 
					res.getInt("rg"),
					res.getString("telefone"),
					res.getDate("dtNascimento").toLocalDate(), 
					res.getString("sexo"), 
					res.getInt("id")
				);
			}
		} catch (SQLException e) {
			System.out.print(e);
			p = null;
		}
		
		return p;
	}
}
