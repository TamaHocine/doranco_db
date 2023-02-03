package fr.doranco.salaires.enums;

public enum StatutEnum {
   DEVELOPPEUR ("Développeur"),
   VENDEUR ("Vendeur"),
   MANGER ("manageur"),
   TECHNICIEN_A_RISQUE ("TECHNICIEN_A_RISQUE");
   
   private String statut;
   private StatutEnum (String status) {
	   this.statut = status;
   }
public String getValue() {
	return statut;
}
   
}
