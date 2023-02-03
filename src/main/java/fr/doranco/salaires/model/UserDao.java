package fr.doranco.salaires.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.doranco.salaires.entity.Adresse;
import fr.doranco.salaires.entity.Salaire;
import fr.doranco.salaires.entity.User;
import fr.doranco.salaires.enums.MoisEnum;
import fr.doranco.salaires.utils.Dates;

public class UserDao implements IUserDao {

	@Override
	public User getUserById(int id) throws Exception {

		if (id <= 0) {
			throw new IllegalArgumentException("L'id du user à rechercher doit être > 0");

		}
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {

			connection = DorancoDataSource.getConnection();
			String requete = "SELECT * FROM user WHERE id = ?";
			ps = connection.prepareStatement(requete);
			ps.setInt(1, id);

			ps.execute();
			rs = ps.getResultSet();
			User user = null;
			if (rs != null && rs.next()) {
				user = new User();
				user.setId(id);
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setTelephone(rs.getString("telephone"));
				user.setEmail(rs.getString("email"));
				user.setDateNaissance(rs.getDate("date_naissance"));
				user.setDateEntree(rs.getDate("date_entree"));
				user.setDateSortie(rs.getDate("date_sortie"));
				user.setGenre(rs.getString("genre"));
				user.setSalaireDeBase(rs.getFloat("salire_de_base"));

				user.setStatut(rs.getString("statut"));

			}
			AdresseDao adresseDao = new AdresseDao();
			List<Adresse> adresses = adresseDao.getAdresses(id);
			for (Adresse adresse : adresses) {
				user.getAdresses().add(adresse);
			}

			return user;
		} finally {
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
	}

	@Override
	public User addUser(User user) throws Exception {

		if (user == null) {
			throw new NullPointerException("Le user à créer ne doit pas être NULL !");
		}
		if (user.getGenre() == null || user.getGenre().trim().isEmpty() || user.getNom() == null
				|| user.getNom().trim().isEmpty() || user.getPrenom() == null || user.getPrenom().trim().isEmpty()
				|| user.getDateNaissance() == null
		// ...Vérifier uniquement les paramètres qui sont obligatoires
		) {
			throw new IllegalAccessException("Un ou plusiseurs paramètre sont manquants !");

		}
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {

			connection = DorancoDataSource.getConnection();
			String requete = "INSERT INTO user (genre, nom, prenom, telephone, date_naissance, date_entree,"
					+ "titre, salaire_de_base, email, statut) VALUES (?,?,?,?,?,?,?,?,?,?)";

			ps = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getGenre());
			ps.setString(2, user.getNom());
			ps.setString(3, user.getPrenom());
			ps.setString(4, user.getTelephone());
			ps.setDate(5, Dates.dateUtilToSql(user.getDateNaissance()));
			ps.setDate(6, Dates.dateUtilToSql(user.getDateEntree()));
			ps.setString(7, user.getTitre());
			ps.setFloat(8, user.getSalaireDeBase());
			ps.setString(9, user.getEmail());
			ps.setString(10, user.getStatut());

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs != null && rs.next()) {
				user.setId(rs.getInt(1));
			}

			List<Adresse> adresses = user.getAdresses();
			AdresseDao adresseDao = new AdresseDao();
			if (adresses != null && !adresses.isEmpty()) {
				for (Adresse adresse : adresses) {
					Adresse addedAdresse = adresseDao.addAdresse(adresse, user.getId());
					adresse.setId(addedAdresse.getId());
				}
			}

			return user;

		} finally {
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
	}

	@Override
	public void updateUser(User user) throws Exception {
		if (user == null) {
			throw new NullPointerException("Le user à créer ne doit pas être NULL !");
		}
		if (user.getGenre() == null || user.getGenre().trim().isEmpty() || user.getNom() == null
				|| user.getNom().trim().isEmpty() || user.getPrenom() == null || user.getPrenom().trim().isEmpty()
				|| user.getDateNaissance() == null
		// ...Vérifier uniquement les paramètres qui sont obligatoires
		) {
			throw new IllegalAccessException("Un ou plusiseurs paramètre sont manquants !");

		}
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			// on récupère une nouvelle connection à la BDD :
			connection = DorancoDataSource.getConnection();
			// On prépare la requête :
			String requete = "UPDATE user SET genre= ? , nom= ?, prenom= ?, telephone= ?, date_naissance= ?, date_entree= ?,"
					+ "titre= ?, salaire_de_base= ?, email= ?, statut= ?) WHERE id = ? ";
			// on associe la requête à la connection grâce à la méthode prepareStatement

			ps = connection.prepareStatement(requete);
			// On remplace les paramètres de la requête (les ?) avec leurs valeurs

			ps.setString(1, user.getGenre());
			ps.setString(2, user.getNom());
			ps.setString(3, user.getPrenom());
			ps.setString(4, user.getTelephone());
			ps.setDate(5, Dates.dateUtilToSql(user.getDateNaissance()));
			ps.setDate(6, Dates.dateUtilToSql(user.getDateEntree()));
			ps.setString(7, user.getTitre());
			ps.setFloat(8, user.getSalaireDeBase());
			ps.setString(9, user.getEmail());
			ps.setString(10, user.getStatut());
			ps.setInt(11, user.getId());
			// On exécute la requête en mode modification (insert ou update ou delete)
			ps.executeUpdate();

			// On vérifie si le user contient des adresses.
			// Si c'est le cas, alors on met à jour les adresses dans la BDD
			if (user.getAdresses() != null && !user.getAdresses().isEmpty()) {
				AdresseDao adresseDao = new AdresseDao();
				for (Adresse a : user.getAdresses()) {
					// On met à jour l'adresse courante (a)
					adresseDao.updateAdresse(a, user.getId());
				}
			}
		} finally {
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
	}

	@Override
	public void deleteUser(User user) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			// on récupère une nouvelle connection à la BDD :
			connection = DorancoDataSource.getConnection();

			// On prépare la requête :
			String requete = "DELETE FROM adresse WHERE user_id = ?";

			// on associe la requête à la connection grâce à la méthode prepareStatement
			ps = connection.prepareStatement(requete);

			ps.setInt(1, user.getId());

			ps.executeUpdate();

//			ps.clearBatch();
//			ps.clearParameters();
			ps.close();

			String requete2 = "DELETE FROM user WHERE id = ?";
			ps = connection.prepareStatement(requete2);

			ps.executeUpdate();

		} finally {
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
	}

	@Override
	public List<User> getUsers() throws Exception {
		AdresseDao adresseDao = new AdresseDao();
		List<Adresse> list = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> users = null;

		try {
			connection = DorancoDataSource.getConnection();
			String requete = "SELECT * FROM user";
			ps = connection.prepareStatement(requete);
			rs = ps.executeQuery();

			users = new ArrayList<User>();
			if (rs != null) {
				users = new ArrayList<User>();
				User user = null;
				while (rs.next()) {
					list = new ArrayList<Adresse>();
					user = new User();
					user.setId(rs.getInt("id"));
					user.setNom(rs.getString("nom"));
					user.setPrenom(rs.getString("prenom"));
					user.setGenre(rs.getString("genre"));
					user.setDateNaissance(rs.getDate("date_naissance"));
					user.setDateEntree(rs.getDate("date_entree"));
					user.setDateSortie(rs.getDate("date_sortie"));
					user.setTitre(rs.getString("titre"));
					user.setSalaireDeBase(rs.getFloat("salaire_de_base"));

					user.setEmail(rs.getString("email"));
					user.setStatut(rs.getString("statut"));
					list = adresseDao.getAdresses(user.getId());
					for (Adresse adresse : list) {
						user.getAdresses().add(adresse);
					}
					users.add(user);
				}
			}
			return users;

		} finally {

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

	}

	@Override
	public float getSalaire(short annee, String mois, int idUser) throws Exception {

		// rthdrthdrydtyjdtyj
		return 0;
	}

	@Override
	public Map<Short, Map<String, Float>> getSalaires(int idUser) throws Exception {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, Float> salaire = null;
		Map<Short, Map<String, Float>> salaires = new LinkedHashMap<Short, Map<String, Float>>();
		
		try {
			connection = DorancoDataSource.getConnection();
			String requete = "SELECT * FROM salaire WHERE user_id = ?";
			ps = connection.prepareStatement(requete);
			ps.setInt(1, idUser);
			rs = ps.executeQuery();
			
			if (rs != null) {
			
				
				while (rs.next()) {
					salaire = new LinkedHashMap<String, Float>();
					salaire.put(MoisEnum.JANVIER.getValue(), rs.getFloat(MoisEnum.JANVIER.getValue()));
					salaire.put(MoisEnum.FEVRIER.getValue(), rs.getFloat(MoisEnum.FEVRIER.getValue()));
					salaire.put(MoisEnum.MARS.getValue(), rs.getFloat(MoisEnum.MARS.getValue()));
					salaire.put(MoisEnum.AVRIL.getValue(), rs.getFloat(MoisEnum.AVRIL.getValue()));
					salaire.put(MoisEnum.MAI.getValue(), rs.getFloat(MoisEnum.MAI.getValue()));
					salaire.put(MoisEnum.JUIN.getValue(), rs.getFloat(MoisEnum.JUIN.getValue()));
					salaire.put(MoisEnum.JUILLET.getValue(), rs.getFloat(MoisEnum.JUILLET.getValue()));
					salaire.put(MoisEnum.AOUT.getValue(), rs.getFloat(MoisEnum.AOUT.getValue()));
					salaire.put(MoisEnum.SEPTEMBRE.getValue(), rs.getFloat(MoisEnum.SEPTEMBRE.getValue()));
					salaire.put(MoisEnum.OCTOBRE.getValue(), rs.getFloat(MoisEnum.OCTOBRE.getValue()));
					salaire.put(MoisEnum.NOVEMBRE.getValue(), rs.getFloat(MoisEnum.NOVEMBRE.getValue()));
					salaire.put(MoisEnum.DECEMBRE.getValue(), rs.getFloat(MoisEnum.DECEMBRE.getValue()));
					salaires.put(rs.getShort("annee"), salaire);	
				}
				return salaires;
			}
		} finally {
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
		
		return null;
		
	}

	@Override
	public int addSalaire(Salaire salaire, int UserId) throws Exception {

		if (salaire == null || salaire.getAnnee() <= 0 || UserId <= 0) {
			throw new IllegalArgumentException("Un ou plusieurs paramètres sont incorrects !");
		}
		// déclarer la connexion
		Connection connection = null;
		// déclarer la préparation de la requete
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement psUpdateOrInsert = null;
		ResultSet rsInsert = null;
		try {
			// établir une connexion avec une base de données en utilisant la classe
			// doranco_db
			connection = DorancoDataSource.getConnection();
			// vérifier si une ligne existe avec l'id d'un utilisateur et l'annee recherchée
			String requete = "SELECT * FROM salaire WHERE annee = ? AND user_id = ?";
			// préparer la requete et la sécuriser avec preparedstatement
			ps = connection.prepareStatement(requete);
			ps.setInt(1, salaire.getAnnee());
			ps.setInt(2, UserId);

			// executer la requete
			rs = ps.executeQuery();
			if (rs.next()) {
				String mois = salaire.getMois();

				// verifier si le salaire qu'on va le rajouter existe deja
				// s'il existe je peux pas l'ecraser
				// s'il n'existe pas je le rajoute

				if (rs.getFloat(mois) != 0) {
					throw new Exception(" ! impossible d'ajouter le salaire pour le mois '" + mois
							+ "' car il est déjà effectué !");

				} else {

					StringBuilder requeteUpdate = new StringBuilder();
					requeteUpdate.append("UPDATE salaire SET ");
					requeteUpdate.append(salaire.getMois());
					requeteUpdate.append(" = ? WHERE annee = ? AND user_id = ? ");
					psUpdateOrInsert = connection.prepareStatement(requeteUpdate.toString());
					psUpdateOrInsert.setFloat(1, salaire.getSalaire());
					psUpdateOrInsert.setInt(2, salaire.getAnnee());
					psUpdateOrInsert.setInt(3, UserId);

					psUpdateOrInsert.executeUpdate();
					return rs.getInt("id");

				}

			} else {
				// pour faire une nouvelle requete insert into (1er update 2 eme insert)

				StringBuilder requeteInsert = new StringBuilder();
				requeteInsert.append("INSERT INTO salaire(annee, ");

				// insert into salaire (annee, mars, ,user_id) pour les valaurs ?,?,?

				requeteInsert.append(salaire.getMois());
				requeteInsert.append(", user_id) VALUES(?,?,?)");

				// mettre a jour les points ?

				psUpdateOrInsert = connection.prepareStatement(requeteInsert.toString(),
						Statement.RETURN_GENERATED_KEYS);
				psUpdateOrInsert.setInt(1, salaire.getAnnee());
				psUpdateOrInsert.setFloat(2, salaire.getSalaire());
				psUpdateOrInsert.setInt(3, UserId);

				// executer ma requete

				psUpdateOrInsert.executeUpdate();
				rsInsert = psUpdateOrInsert.getGeneratedKeys();
				// recuperer ma ligne
				if (rsInsert != null && rsInsert.next()) {
					return rsInsert.getInt(1);
				}

			}
		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();

			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (rsInsert != null && !rsInsert.isClosed()) {
				rsInsert.close();
			}
			if (psUpdateOrInsert != null && !psUpdateOrInsert.isClosed()) {
				psUpdateOrInsert.close();
			}

		}
		return -1;

	}

	@Override
	public float getSalaire(int userId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getSalaireAnnuel(Integer annee, int idUser) throws Exception {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DorancoDataSource.getConnection();
			String requete = "SELECT (janvier + fevrier + mars + avril + mai + juin"
			+"+ juillet + aout +septembre + octobre  + novembre + decembre)as salaire_annuel FROM salaire WHERE annee = ? AND user_id = ?";
			ps = connection.prepareStatement(requete);
			ps.setInt (1, annee);
			ps.setInt(2,  idUser);
			rs = ps.executeQuery();
			rs.next();
			return rs.getFloat("salaire_annuel");
			
		} finally {
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
	}
}

