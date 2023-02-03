package fr.doranco.salaires.enums;

public enum GenreEnum {

	MASCULIN ("H"),
	FEMININ ("F");
	private String genre;

	private GenreEnum(String genre) {
		this.genre = genre;
	}

	public String getValue() {
		return genre;
	}
	
}
