package fr.doranco.salaires.model;

import java.util.List;

import fr.doranco.salaires.entity.Adresse;

public interface IAdresseDao {
List<Adresse> getAdresses (int userId)throws Exception;
Adresse addAdresse(Adresse adresse, int userId) throws Exception;
void updateAdresse (Adresse adresse, int userId) throws Exception;
}
