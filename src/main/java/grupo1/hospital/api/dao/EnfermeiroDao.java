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
import grupo1.hospital.api.enfermeiro.Enfermeiro;

public class EnfermeiroDao {
	private Connection connection;
	
	public EnfermeiroDao() {
		this.connection = MySqlSingleton.getConnection();
	}
	
    public Integer inserir(Enfermeiro enfermeiro) {
        String sql = "INSERT INTO enfermeiro(idPessoa, dataAdmissao) VALUES(?,?)";
        Integer idMedico = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, enfermeiro.getIdPessoa());
            stmt.setDate(2, Date.valueOf(enfermeiro.getDtAdmissao()));

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0)
                idMedico = 0;
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    idMedico= rs.getInt(1);
                }
                rs.close();
            }
            return idMedico;
        } catch (SQLException ex) {
        	System.out.print(ex);
            return 0;
        }
    }
    
    public Boolean atualizar(Enfermeiro enfermeiro) {
        String sql = "UPDATE enfermeiro SET idPessoa = ?, dataAdmissao = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, enfermeiro.getIdPessoa());
            stmt.setDate(2, Date.valueOf(enfermeiro.getDtAdmissao()));
            stmt.setInt(3, enfermeiro.getId());
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
        	System.out.print(ex);
            return false;
        }
    }
    
    public Boolean excluir(Integer id) {
        String sql = "DELETE from enfermeiro WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public List<Enfermeiro> getTodosEnfermeiros() {
        String sql = "SELECT pc.id, pc.dataAdmissao as dtAdmissao, ps.id as idPessoa, ps.nome, ps.rg, ps.cpf, "
				+ "ps.sexo, ps.dtNascimento, ps.telefone "
				+ "FROM enfermeiro pc "
				+ "INNER JOIN pessoa ps ON ps.id = pc.idPessoa ";
        List<Enfermeiro> enfermeiros = new ArrayList<Enfermeiro>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet res = stmt.executeQuery();
			while (res.next()) {
				Enfermeiro m = new Enfermeiro(
					res.getString("nome"), 
					res.getString("cpf"), 
					res.getInt("rg"),
					res.getString("telefone"),
					res.getDate("dtNascimento").toLocalDate(), 
					res.getString("sexo"), 
					res.getInt("idPessoa"),
					res.getDate("dtAdmissao").toLocalDate()
				);
				m.setId(res.getInt("id"));
				enfermeiros.add(m);
			};
        } catch (SQLException ex) {
        	System.out.print(ex);
        	enfermeiros = new ArrayList<Enfermeiro>();
        }
        return enfermeiros;
    }
    
    public Enfermeiro findById(Integer id){
		String sql = 
				"SELECT pc.id, pc.dataAdmissao as dtAdmissao, ps.id as idPessoa, ps.nome, ps.rg, ps.cpf, "
				+ "ps.sexo, ps.dtNascimento, ps.telefone "
				+ "FROM enfermeiro pc "
				+ "INNER JOIN pessoa ps ON ps.id = pc.idPessoa "
				+ "WHERE pc.id = ?;";
		Enfermeiro m = null;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet res = stmt.executeQuery();
			if (res.next()) {
				m = new Enfermeiro(
					res.getString("nome"), 
					res.getString("cpf"), 
					res.getInt("rg"),
					res.getString("telefone"),
					res.getDate("dtNascimento").toLocalDate(), 
					res.getString("sexo"), 
					res.getInt("idPessoa"),
					res.getDate("dtAdmissao").toLocalDate()
				);
				m.setId(res.getInt("id"));
			}
		} catch (SQLException e) {
			System.out.print(e);
			m = null;
		}
		
		return m;
	}
}
