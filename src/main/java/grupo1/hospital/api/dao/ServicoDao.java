package grupo1.hospital.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import grupo1.hospital.api.MySqlSingleton;
import grupo1.hospital.api.servico.Servico;

public class ServicoDao {
	private Connection connection;
	
	public ServicoDao() {
		this.connection = MySqlSingleton.getConnection();
	}
	
    public Integer inserir(Servico servico) {
        String sql = "INSERT INTO servico(descricao, idPaciente, idEnfermeiro, idMedico) VALUES(?, ?, ?, ?)";
        Integer idServico = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, servico.getDescricao());
            stmt.setInt(2, servico.getPaciente().getId());
            stmt.setInt(3, servico.getEnfermeiro().getId());
            stmt.setInt(4, servico.getMedico().getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0)
                idServico = 0;
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    idServico = rs.getInt(1);
                }
                rs.close();
            }
            return idServico;
        } catch (SQLException ex) {
        	System.out.print(ex);
            return 0;
        }
    }
    
    public Boolean atualizar(Servico servico) {
        String sql = "UPDATE servico SET descricao = ?, idPaciente = ?, idEnfermeiro = ?, idMedico = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, servico.getDescricao());
            stmt.setInt(2, servico.getPaciente().getId());
            stmt.setInt(3, servico.getEnfermeiro().getId());
            stmt.setInt(4, servico.getMedico().getId());
            stmt.setInt(5, servico.getId());
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
        	System.out.print(ex);
            return false;
        }
    }
    
    public Boolean excluir(Integer id) {
        String sql = "DELETE from servico WHERE id = ?";
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
    
    public List<Servico> getTodosServicos() {
        String sql = "SELECT s.id, s.descricao, s.idPaciente, s.idMedico, s.idEnfermeiro FROM servico s";
        List<Servico> servicos = new ArrayList<Servico>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet res = stmt.executeQuery();
			while (res.next()) {
				Servico m = new Servico(
					res.getString("descricao"), 
					res.getInt("idMedico"),
					res.getInt("idPaciente"), 
					res.getInt("idEnfermeiro")
				);
				m.setId(res.getInt("id"));
				servicos.add(m);
			};
        } catch (SQLException ex) {
        	System.out.print(ex);
        	servicos = new ArrayList<Servico>();
        }
        return servicos;
    }
    
    public Servico findById(Integer id){
		String sql = "SELECT s.id, s.descricao, s.idPaciente, s.idMedico, s.idEnfermeiro FROM servico s WHERE s.id = ?";
		Servico m = null;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet res = stmt.executeQuery();
			if (res.next()) {
				m = new Servico(
					res.getString("descricao"), 
					res.getInt("idMedico"),
					res.getInt("idPaciente"), 
					res.getInt("idEnfermeiro")
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
