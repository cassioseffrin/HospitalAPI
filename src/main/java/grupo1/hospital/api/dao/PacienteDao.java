package grupo1.hospital.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import grupo1.hospital.api.MySqlSingleton;
import grupo1.hospital.api.paciente.Paciente;

public class PacienteDao {
	private Connection connection;
	
	public PacienteDao() {
		this.connection = MySqlSingleton.getConnection();
	}
	
    public Integer inserir(Paciente paciente) {
        String sql = "INSERT INTO paciente(idPessoa, gravidade) VALUES(?,?)";
        Integer idPaciente = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, paciente.getIdPessoa());
            stmt.setInt(2, paciente.getGravidade());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0)
                idPaciente = 0;
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    idPaciente = rs.getInt(1);
                }
                rs.close();
            }
            return idPaciente;
        } catch (SQLException ex) {
        	System.out.print(ex);
            return 0;
        }
    }
    
    public Boolean atualizar(Paciente paciente) {
        String sql = "UPDATE paciente SET idPessoa = ?, gravidade = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, paciente.getIdPessoa());
            stmt.setInt(2, paciente.getGravidade());
            stmt.setInt(3, paciente.getId());
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
        	System.out.print(ex);
            return false;
        }
    }
    
    public Boolean excluir(Integer id) {
        String sql = "DELETE from paciente WHERE id = ?";
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
    
    public List<Paciente> getTodosPacientes() {
        String sql = "SELECT pc.id, pc.gravidade, ps.id as idPessoa, ps.nome, ps.rg, ps.cpf, "
				+ "ps.sexo, ps.dtNascimento, ps.telefone "
				+ "FROM paciente pc "
				+ "INNER JOIN pessoa ps ON ps.id = pc.idPessoa ";
        List<Paciente> pacientes = new ArrayList<Paciente>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet res = stmt.executeQuery();
			while (res.next()) {
				Paciente p = new Paciente(
					res.getString("nome"), 
					res.getString("cpf"), 
					res.getInt("rg"),
					res.getString("telefone"),
					res.getDate("dtNascimento").toLocalDate(), 
					res.getString("sexo"), 
					res.getInt("idPessoa"),
					res.getInt("gravidade")
				);
				p.setId(res.getInt("id"));
				pacientes.add(p);
			};
        } catch (SQLException ex) {
        	System.out.print(ex);
            pacientes = new ArrayList<Paciente>();
        }
        return pacientes;
    }
    
    public Paciente findById(Integer id){
		String sql = 
				"SELECT pc.id, pc.gravidade, ps.id as idPessoa, ps.nome, ps.rg, ps.cpf, "
				+ "ps.sexo, ps.dtNascimento, ps.telefone "
				+ "FROM paciente pc "
				+ "INNER JOIN pessoa ps ON ps.id = pc.idPessoa "
				+ "WHERE pc.id = ?;";
		Paciente p = null;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet res = stmt.executeQuery();
			if (res.next()) {
				p = new Paciente(
					res.getString("nome"), 
					res.getString("cpf"), 
					res.getInt("rg"),
					res.getString("telefone"),
					res.getDate("dtNascimento").toLocalDate(), 
					res.getString("sexo"), 
					res.getInt("idPessoa"),
					res.getInt("gravidade")
				);
				p.setId(res.getInt("id"));
			}
		} catch (SQLException e) {
			System.out.print(e);
			p = null;
		}
		
		return p;
	}
}
