package fr.doranco.salaires.main;

import fr.doranco.salaires.entity.Salaire;
import fr.doranco.salaires.enums.MoisEnum;
import fr.doranco.salaires.model.UserDao;

public class MainAddSalaire {

	public static void main(String[] args) throws Exception {
		Salaire s = new Salaire(2019, MoisEnum.SEPTEMBRE.getValue(), 3000f);
		UserDao userDao = new UserDao();
//		
//		//on appelle la methode getsalaire
//		
		System.out.println( "Le salaire a été bien ajouté  id = "  +userDao.addSalaire(s, 9));
		
         System.out.println("Le salaire annuel de l'annee 2022 de l'utilisateur 9 est :" + userDao.getSalaireAnnuel(2022, 9) );
	}

}


