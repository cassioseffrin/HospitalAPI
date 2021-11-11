package grupo1.hospital.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import grupo1.hospital.api.MySqlSingleton;
import grupo1.hospital.api.medico.Medico;

public class MedicoDao {
private Connection connection;
	
	public MedicoDao() {
		this.connection = MySqlSingleton.getConnection();
	}
	
    public Integer inserir(Medico medico) {
        String sql = "INSERT INTO medico(idPessoa, especialidade) VALUES(?,?)";
        Integer idMedico = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, medico.getIdPessoa());
            stmt.setString(2, medico.getEspecialidade());

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
    
    public Boolean atualizar(Medico medico) {
        String sql = "UPDATE medico SET idPessoa = ?, especialidade = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, medico.getIdPessoa());
            stmt.setString(2, medico.getEspecialidade());
            stmt.setInt(3, medico.getId());
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
        	System.out.print(ex);
            return false;
        }
    }
    
    public Boolean excluir(Integer id) {
        String sql = "DELETE from medico WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
        	System.out.println(ex);
            return false;
        }
    }
    
    public List<Medico> getTodosMedicos() {
        String sql = "SELECT pc.id, pc.especialidade, ps.id as idPessoa, ps.nome, ps.rg, ps.cpf, "
				+ "ps.sexo, ps.dtNascimento, ps.telefone "
				+ "FROM medico pc "
				+ "INNER JOIN pessoa ps ON ps.id = pc.idPessoa ";
        List<Medico> medicos = new ArrayList<Medico>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet res = stmt.executeQuery();
			while (res.next()) {
				Medico m = new Medico(
					res.getString("nome"), 
					res.getString("cpf"), 
					res.getInt("rg"),
					res.getString("telefone"),
					res.getDate("dtNascimento").toLocalDate(), 
					res.getString("sexo"), 
					res.getInt("idPessoa"),
					res.getString("especialidade")
				);
				m.setId(res.getInt("id"));
				medicos.add(m);
			};
        } catch (SQLException ex) {
        	System.out.print(ex);
            medicos = new ArrayList<Medico>();
        }
        return medicos;
    }
    
    public Medico findById(Integer id){
		String sql = 
				"SELECT pc.id, pc.especialidade, ps.id as idPessoa, ps.nome, ps.rg, ps.cpf, "
				+ "ps.sexo, ps.dtNascimento, ps.telefone "
				+ "FROM medico pc "
				+ "INNER JOIN pessoa ps ON ps.id = pc.idPessoa "
				+ "WHERE pc.id = ?;";
		Medico m = null;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet res = stmt.executeQuery();
			if (res.next()) {
				m = new Medico(
					res.getString("nome"), 
					res.getString("cpf"), 
					res.getInt("rg"),
					res.getString("telefone"),
					res.getDate("dtNascimento").toLocalDate(), 
					res.getString("sexo"), 
					res.getInt("idPessoa"),
					res.getString("especialidade")
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
