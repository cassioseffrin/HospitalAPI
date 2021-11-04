package grupo1.hospital.api.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import grupo1.hospital.api.MySqlSingleton;
import grupo1.hospital.api.agenda.Agenda;

public class AgendaDao {
private Connection connection;
	
	public AgendaDao() {
		this.connection = MySqlSingleton.getConnection();
	}
	
    public Integer inserir(Agenda agenda) {
        String sql = "INSERT INTO agenda(dtAgenda, horario, idPaciente) VALUES(?, ?, ?)";
        Integer idAgenda = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, Date.valueOf(agenda.getDtAgenda()));
            stmt.setTime(2, Time.valueOf(agenda.getHorario()));
            stmt.setInt(3, agenda.getPaciente().getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0)
            	idAgenda = 0;
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                	idAgenda = rs.getInt(1);
                }
                rs.close();
            }
            return idAgenda;
        } catch (SQLException ex) {
        	System.out.print(ex);
            return 0;
        }
    }
    
    public Boolean atualizar(Agenda agenda) {
        String sql = "UPDATE agenda SET dtAgenda = ?, horario = ?, idPaciente = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(agenda.getDtAgenda()));
            stmt.setTime(2, Time.valueOf(agenda.getHorario()));
            stmt.setInt(3, agenda.getPaciente().getId());
            stmt.setInt(4, agenda.getId());
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public Boolean excluir(Integer id) {
        String sql = "DELETE from agenda WHERE id = ?";
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
    
    public List<Agenda> getTodasAgendas() {
        String sql = "SELECT id, dtAgenda, horario, idPaciente FROM agenda";
        List<Agenda> agendas = new ArrayList<Agenda>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet res = stmt.executeQuery();
			while (res.next()) {
				Agenda m = new Agenda(
					res.getDate("dtAgenda").toLocalDate(), 
					res.getTime("horario").toLocalTime(),
					res.getInt("idPaciente")
				);
				m.setId(res.getInt("id"));
				agendas.add(m);
			};
        } catch (SQLException ex) {
        	System.out.print(ex);
        	agendas = new ArrayList<Agenda>();
        }
        return agendas;
    }
    
    public Agenda findById(Integer id){
		String sql = "SELECT id, dtAgenda, horario, idPaciente FROM agenda WHERE id = ?";
		Agenda m = null;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet res = stmt.executeQuery();
			if (res.next()) {
				m = new Agenda(
					res.getDate("dtAgenda").toLocalDate(), 
					res.getTime("horario").toLocalTime(),
					res.getInt("idPaciente")
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
