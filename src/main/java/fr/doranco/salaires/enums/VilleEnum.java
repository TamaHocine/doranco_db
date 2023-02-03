package fr.doranco.salaires.enums;

public enum VilleEnum {
	PARIS("Paris"), LYON("Lyon"), MARSAILLE("Marseille"), AIX_EN_PROVENCE("Aix_En_Provence");

	private String Ville;

	private VilleEnum(String ville) {
		this.Ville = ville;
	}

	public String getValue() {
		return Ville;
	}

}
