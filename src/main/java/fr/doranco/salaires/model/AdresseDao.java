package fr.doranco.salaires.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.doranco.salaires.entity.Adresse;

public class AdresseDao implements IAdresseDao {

	@Override
	public List<Adresse> getAdresses(int userId) throws Exception {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Adresse> adresses = null;
	
		try{
			connection = DorancoDataSource.getConnection();
			String requete = "SELECT * FROM adresse WHERE user_id = ?";
			ps = connection.prepareStatement(requete);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			
			adresses = new ArrayList<Adresse> ();
			if (rs != null) {
				adresses = new ArrayList<Adresse>();
				Adresse adresse = null;
				while (rs.next()) {
					adresse = new Adresse();
					adresse.setId(rs.getInt ("id"));
					adresse.setNumero(rs.getString ("numero"));
					adresse.setRue(rs.getString ("rue"));
					adresse.setVille(rs.getString ("ville"));
					adresse.setCodePostal(rs.getString ("code_postal"));
					adresses.add(adresse);
				}
			}
			
				
		}finally {
			
				if (connection != null && !connection.isClosed()) {
					connection.close();
					
				}
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (ps != null && !ps.isClosed()) {
					ps.close();
				}
			
			}
		
		
		return adresses;
	}


	@Override
	public Adresse addAdresse(Adresse adresse, int userId) throws Exception {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DorancoDataSource.getConnection();
			String requete = "INSERT INTO adresse (numero, rue, ville, code_postal, user_id)"
					+ "VALUES(?,?,?,?,?)";
			ps = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, adresse.getNumero());
			ps.setString(2, adresse.getRue());
			ps.setString(3, adresse.getVille());
			ps.setString(4, adresse.getCodePostal());
			ps.setInt(5, userId);
			
			ps.executeUpdate();
		    rs = ps.getGeneratedKeys();
			if (rs != null && rs.next()) {
				adresse.setId(rs.getInt(1));
			}
			
			
			
		} finally  {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		}
		return adresse;
	}


	@Override
	public void updateAdresse(Adresse adresse, int userId) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
