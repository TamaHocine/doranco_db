package fr.doranco.salaires.main;

import java.util.Map;

import fr.doranco.salaires.model.UserDao;

public class MainGetSalaire {

	public static void main(String[] args) {
		

			try {
				UserDao userDao = new UserDao();

				float salaireAnnuel = userDao.getSalaireAnnuel(2022, 9);
				System.out.println("Salaire annuel de l'utilisateur avec id = 9 sur l'annee 2022 est : " + salaireAnnuel);
				Map<Short,Map<String,Float>> salaires= userDao.getSalaires(9);
				
				for (Map.Entry<Short, Map<String,Float>> entry : salaires.entrySet()) {
		            Short annee = entry.getKey();
		            System.out.println("-----------------------"+annee+"------------------------");
		            //val = liste mois et salaire
		            Map<String,Float> val = entry.getValue();
		            System.out.println("salaires: "+val);

		        }
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}


