package fr.doranco.salaires.model;

import java.util.List;
import java.util.Map;

import fr.doranco.salaires.entity.Salaire;
import fr.doranco.salaires.entity.User;

public interface IUserDao {

	User getUserById(int id) throws Exception;
	User addUser(User user) throws Exception;
	void updateUser (User user) throws Exception;
	void deleteUser (User user) throws Exception;
	List<User> getUsers() throws Exception;
	float getSalaire (int userId) throws Exception;
	
    int addSalaire (Salaire salaire, int IdUser) throws Exception;
	float getSalaire (short annee,String mois,int idUser) throws Exception;
	Map<Short, Map<String, Float>> getSalaires (int idUser) throws Exception;
	float getSalaireAnnuel (Integer annee, int idUser)throws Exception;
	

}
