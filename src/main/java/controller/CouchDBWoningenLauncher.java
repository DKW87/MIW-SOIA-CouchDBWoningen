package controller;

import com.google.gson.Gson;
import javacouchdb.VerbruikerCouchDBDAO;
import javacouchdb.CouchDBaccess;
import model.Verbruiker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CouchDBWoningenLauncher {

	private static CouchDBaccess couchDBaccess;
	private static VerbruikerCouchDBDAO verbruikerCouchDBDAO;

	public static void main(String[] args) {
		couchDBaccess = new CouchDBaccess("woningen","admin", "admin");
		verbruikerCouchDBDAO = new VerbruikerCouchDBDAO(couchDBaccess);
		saveVerbruikerList(buildVerbruikerList());
	}
		
	private static List<Verbruiker> buildVerbruikerList() {
//		Lees de Json-string in het tekstbestand, maak er Verbruiker objecten van en sla die op in CouchDB
		Gson gson = new Gson();
		List<Verbruiker> verbruikerList = new ArrayList<>();
		try (Scanner bestandLezer = new Scanner(new File("src/main/resources/woningenJson.txt"))) {
			while (bestandLezer.hasNext()) {
				verbruikerList.add(gson.fromJson(bestandLezer.nextLine(), Verbruiker.class));
			}
		} catch (FileNotFoundException schrijfFout) {
			System.out.println("Het bestand lezen is mislukt.");
		}
		return verbruikerList;
	}

	private static void saveVerbruikerList(List<Verbruiker> verbruikerList) {
//		Maak een connectie met CouchDB, gebruik het CouchDBaccess object.
// 		getClient() roept couchDBaccess.openConnection() aan; mocht de connection niet lukken dan is client == null;
		if	(couchDBaccess.getClient() != null) {
			System.out.println("Connection open");
//		Sla alle Verbruikers op in de CouchDb database.
			for (Verbruiker verbruiker : verbruikerList) {
				verbruikerCouchDBDAO.saveSingleVerbruiker(verbruiker);
			}
		}
	}
}
