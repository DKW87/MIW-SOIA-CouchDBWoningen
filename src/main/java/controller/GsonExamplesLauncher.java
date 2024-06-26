package controller;

import com.google.gson.Gson;
import model.Verbruik;
import model.Verbruiker;

public class GsonExamplesLauncher {

	public static void main(String[] args) {
//		Maak een json string van een eenvoudig object
		Verbruik mijnVerbruik = new Verbruik(200, 300, 400);
		Gson gson = new Gson();
		String verbruikJson = gson.toJson(mijnVerbruik);
		System.out.println("Verbruik als json:");
		System.out.println(verbruikJson);
		System.out.println();

//		Maak een json string van een complex object
		Verbruiker gerke = new Verbruiker("1012GB", 34);
		gerke.setVerbruik(mijnVerbruik);
		String[] categorie = {"Hoekhuis", "Koop"};
		gerke.setCategorie(categorie);
		String gerkeJson = gson.toJson(gerke);
		System.out.println("Verbruiker als json");
		System.out.println(gerkeJson);
		System.out.println();

//		Maak een object van een json string
		String jsonVoorbeeld = "{\"naam\": \"gerke\"}";
		Object gerkeAlsObject = gson.fromJson(jsonVoorbeeld, Object.class);
		System.out.println("Json omgezet naar Object");
		System.out.println(gerkeAlsObject);
		System.out.println();

//		Maak een Verbruiker object van een json string
		Verbruiker gerke2 = gson.fromJson(gerkeJson, Verbruiker.class);
		System.out.println("Json omgezet naar Verbruiker");
		System.out.println(gerke2);
	}
}
