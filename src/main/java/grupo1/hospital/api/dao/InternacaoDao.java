package grupo1.hospital.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import grupo1.hospital.api.MySqlSingleton;
import grupo1.hospital.api.internacao.Internacao;

public class InternacaoDao {
private Connection connection;
	
	public InternacaoDao() {
		this.connection = MySqlSingleton.getConnection();
	}
	
    public Integer inserir(Internacao internacao) {
        String sql = "INSERT INTO internacao(idPaciente, horario, quarto, idPessoaAcompanhante) VALUES(?, ?, ?, ?)";
        Integer idInternacao = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, internacao.getPaciente().getId());
            stmt.setTime(2, Time.valueOf(internacao.getHorario()));
            stmt.setString(3, internacao.getQuarto());
            stmt.setInt(4, internacao.getAcompanhante().getIdPessoa());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0)
            	idInternacao = 0;
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                	idInternacao = rs.getInt(1);
                }
                rs.close();
            }
            return idInternacao;
        } catch (SQLException ex) {
        	System.out.print(ex);
            return 0;
        }
    }
    
    public Boolean atualizar(Internacao internacao) {
        String sql = "UPDATE internacao SET idPaciente = ?, horario = ?, quarto = ?, idPessoaAcompanhante = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, internacao.getPaciente().getId());
            stmt.setTime(2, Time.valueOf(internacao.getHorario()));
            stmt.setString(3, internacao.getQuarto());
            stmt.setInt(4, internacao.getAcompanhante().getIdPessoa());
            stmt.setInt(5, internacao.getId());
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
        	System.out.print(ex);
            return false;
        }
    }
    
    public Boolean excluir(Integer id) {
        String sql = "DELETE from internacao WHERE id = ?";
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
    
    public List<Internacao> getTodosInternacoes() {
        String sql = "SELECT i.id, i.idPaciente, i.horario, i.quarto, i.idPessoaAcompanhante FROM internacao i";
        List<Internacao> internacoes = new ArrayList<Internacao>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet res = stmt.executeQuery();
			while (res.next()) {
				Internacao i = new Internacao(
					res.getTime("horario").toLocalTime(), 
					res.getString("quarto"),
					res.getInt("idPaciente"),
					res.getInt("idPessoaAcompanhante")
				);
				i.setId(res.getInt("id"));
				internacoes.add(i);
			};
        } catch (SQLException ex) {
        	System.out.print(ex);
        	internacoes = new ArrayList<Internacao>();
        }
        return internacoes;
    }
    
    public Internacao findById(Integer id){
		String sql = "SELECT i.id, i.idPaciente, i.horario, i.quarto, i.idPessoaAcompanhante FROM internacao i WHERE i.id = ?";
		Internacao i = null;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet res = stmt.executeQuery();
			if (res.next()) {
				i = new Internacao(
					res.getTime("horario").toLocalTime(), 
					res.getString("quarto"),
					res.getInt("idPaciente"),
					res.getInt("idPessoaAcompanhante")
				);
				i.setId(res.getInt("id"));
			}
		} catch (SQLException e) {
			System.out.print(e);
			i = null;
		}
		
		return i;
	}
}
