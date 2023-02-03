package fr.doranco.salaires.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import fr.doranco.salaires.entity.Salaire;
import fr.doranco.salaires.entity.User;

public class SalaireDao implements ISalaireDao {

	@Override
	public Map<String, Float> getSalaireMinMax() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, Float> getSalaireMoyenByAnnee() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUserAvecSalaireBas(float SeuilSalaire) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	

}
