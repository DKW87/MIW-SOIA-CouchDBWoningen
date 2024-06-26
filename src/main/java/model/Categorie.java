package model;

public enum Categorie {
	APPARTEMENT("appartement"), 
	WOONHUIS("woonhuis"), 
	HOEKHUIS("hoekhuis"), 
	VRIJSTAAND("vrijstaand"), 
	KOOP("koop"), 
	HUUR("huur");
	
	private final String categorieAsString;
	
	Categorie(String categorie) {
		this.categorieAsString = categorie;
	}
	
	public String toString() {
		return this.categorieAsString;
	}
}
