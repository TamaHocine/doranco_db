package fr.doranco.salaires.enums;

public enum MoisEnum {
	JANVIER("janvier"), FEVRIER("fevrier"), MARS("mars"), AVRIL("avril"), MAI("mai"), JUIN("juin"), JUILLET("juillet"),
	AOUT("aout"), SEPTEMBRE("septembre"), OCTOBRE("octobre"), NOVEMBRE("novembre"), DECEMBRE("decembre");

	private String mois;

	private MoisEnum(String mois) {
		this.mois = mois;
	}

	public String getValue() {
		return mois;
	}

}
