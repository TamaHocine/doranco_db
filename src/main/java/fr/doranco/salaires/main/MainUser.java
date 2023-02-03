package fr.doranco.salaires.main;

import java.util.List;

import fr.doranco.salaires.entity.Adresse;
import fr.doranco.salaires.entity.User;
import fr.doranco.salaires.enums.GenreEnum;
import fr.doranco.salaires.enums.StatutEnum;
import fr.doranco.salaires.enums.VilleEnum;
import fr.doranco.salaires.model.AdresseDao;
import fr.doranco.salaires.model.UserDao;
import fr.doranco.salaires.utils.Dates;

public class MainUser {

	public static void main(String[] args) {
		
		try {
		Adresse a1 = new Adresse("20","Bouleavrd de Charonne", VilleEnum.PARIS.getValue(), "75020");
		Adresse a2 = new Adresse("21","Rue des Artistes", VilleEnum.LYON.getValue(), "69000");
		
		User user = new User ();
		user.setGenre(GenreEnum.MASCULIN.getValue());
		user.setNom("HUGO");
		user.setPrenom("Victor");
		user.setTelephone("003312344567");
		user.setDateNaissance(Dates.stringToDate("15/02/1960"));
		user.setDateEntree(Dates.stringToDate("23/11/2019"));
		user.setTitre("CDA");
		user.setSalaireDeBase(2300f);
		user.setEmail("higo@gmail.com");
		user.setStatut(StatutEnum.DEVELOPPEUR.getValue());
		
		user.getAdresses().add(a1);
		user.getAdresses().add(a2);
		
		UserDao userDao = new UserDao();
		User createdUser = userDao.addUser(user);
		System.out.println(createdUser);
		for (Adresse a : createdUser.getAdresses()) {
			System.out.println(" => " + a);
			
		}
	
        List<User> users = userDao.getUsers();
        for (User u : users) {
            System.out.println(u);
            AdresseDao adresseDao = new AdresseDao();
            List<Adresse> adresses = adresseDao.getAdresses(u.getId());
            for (Adresse adresse : adresses) {
                System.out.println("   => " + adresse);
            }
        }
		
		}catch(Exception e) {
			e.printStackTrace();
		}

}
}
