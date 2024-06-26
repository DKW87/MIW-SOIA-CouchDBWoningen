package controller;

import com.google.gson.JsonObject;
import javacouchdb.CouchDBaccess;
import javacouchdb.VerbruikerCouchDBDAO;
import model.Verbruik;
import model.Verbruiker;

import java.util.List;

public class CouchDBExamplesLauncher {

    private static CouchDBaccess couchDBaccess;
    private static VerbruikerCouchDBDAO verbruikerCouchDBDAO;

    public static void main(String[] args) {
        couchDBaccess = new CouchDBaccess("woningen", "admin", "admin");
        verbruikerCouchDBDAO = new VerbruikerCouchDBDAO(couchDBaccess);
//		Maak een connectie met CouchDB, gebruik het CouchDbaccess object.
// 		getClient() roept couchDBaccess.openConnection() aan; mocht de connection niet lukken dan is client == null;

        if (couchDBaccess.getClient() != null) {
            System.out.println("Connection open");

//	    Hierna volgen stappen die gegevens ophalen uit de CouchDb database.
//	    Maak eerst de CouchDb opdrachten.
//		Uitleg methodes in CouchDB DAO, gebruik gson library.
            // doc_Id is afhankelijk van je eigen database. Zoek een doc_Id die voor jou klopt.
            Verbruiker verbruikerId = verbruikerCouchDBDAO.getVerbruikerByDocId("8ef335aacc154732927aca1a1c51292f");
            System.out.println(verbruikerId);
//
            Verbruiker verbruikerPcNr = verbruikerCouchDBDAO.getVerbruiker("1051AB", 165);
            System.out.println(verbruikerPcNr);
//
            Verbruik mijnVerbruik = new Verbruik(700, 350, 400);
            verbruikerId.setVerbruik(mijnVerbruik);

         //   verbruikerCouchDBDAO.updateVerbruiker(verbruikerId);

          verbruikerCouchDBDAO.deleteVerbruiker(verbruikerPcNr);

            // extra voorbeelden voor het gebruik van views in CouchDB
//          System.out.println();
//          System.out.println("----------  Alle documenten ------------");
//          List<JsonObject> allDocs = couchDBaccess.getClient().view("_all_docs").includeDocs(true).query(JsonObject.class);
//          for (JsonObject jsonObject : allDocs) {
//            System.out.println(jsonObject.getAsJsonObject());
//          }
//          System.out.println("------------------------------------------");

//		Gebruik van views, hoe haal je met ligthcouch library gegevens uit een view in CouchDB
//          System.out.println("----------  Document uit View ------------");
//		    List<JsonObject> test = couchDBaccess.getClient().view("opdrachten/postcode-huisnr").key("1053BC")
//                .query(JsonObject.class);
//		    for (JsonObject jsonObject :test) {
//            System.out.println(jsonObject);
//          }
          System.out.println("----------------------------------------");

            // Voorbeeld met reduce functie
          System.out.println("----------  View met reduce ------------");
//          List<JsonObject> reducer = couchDBaccess.getClient().view("opdrachten/stroom-per-soort").reduce(true).groupLevel(1).query(JsonObject.class);
//
//		    for (JsonObject jsonObject : reducer) {
//			    System.out.println(jsonObject);
//		    }
          System.out.println("----------------------------------------");
        } // end if (voorwaarde dat er een client voor CouchDB is)
    }
}


