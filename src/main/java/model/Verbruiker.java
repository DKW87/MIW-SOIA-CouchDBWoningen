package model;

import java.util.Arrays;

public class Verbruiker {
	private String postcode;
	private int huisnummer;
	private Verbruik verbruik;
	private String[] categorie;

	private final static int MAX_CATEGORIEEN = 2;
	
	public Verbruiker(String postcode, int huisnummer) {
		this.postcode = postcode;
		this.huisnummer = huisnummer;
		categorie = new String[MAX_CATEGORIEEN];
	}

	public Verbruiker() {
		this("0000AA", 0 );
	}
	
	public String toString() {
		StringBuilder resultString = new StringBuilder();
		resultString.append("Verbruiker:\n");
		resultString.append(String.format("\tPostcode: %s, Huisnummer: %d\n", this.postcode, this.huisnummer));
		resultString.append(String.format("\tVerbruik: %s\n", verbruik));
		resultString.append(String.format("\tCategorie: %s", Arrays.toString(categorie)));
		return resultString.toString();
	}

	public String getPostcode() {
		return postcode;
	}

	public int getHuisnummer() {
		return huisnummer;
	}

	public Verbruik getVerbruik() {
		return verbruik;
	}

	public void setVerbruik(Verbruik verbruik) {
		this.verbruik = verbruik;
	}

	public String[] getCategorie() {
		return categorie;
	}

	public void setCategorie(String[] categorie) {
		this.categorie = categorie;
	}
}
