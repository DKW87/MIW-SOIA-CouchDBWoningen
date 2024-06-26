package javacouchdb;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Verbruiker;

public class VerbruikerCouchDBDAO extends AbstractCouchDBDAO {

	private Gson gson;
	
	public VerbruikerCouchDBDAO(CouchDBaccess couchDBaccess) {
		super(couchDBaccess);
		gson = new Gson();
	}
	
	public String saveSingleVerbruiker(Verbruiker verbruiker) {
		// Verbruiker object omzetten naar JsonObject, zodat het opgeslagen kan worden mbv save()
		String jsonString = gson.toJson(verbruiker);
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject();
		return saveDocument(jsonObject);
	}

	public Verbruiker getVerbruikerByDocId(String doc_Id) {
		// Als je het id van een couchDB document weet, kun je daarmee een Verbruiker ophalen
		return gson.fromJson(getDocumentById(doc_Id), Verbruiker.class);
	}

	public Verbruiker getVerbruiker(String postcode, int huisnummer) {
		// Haal alle documenten op, in de vorm van JsonObjecten; zet om naar Verbruiker en test op postcode-huisnummer
		Verbruiker resultaat;
		for (JsonObject jsonObject : getAllDocuments()) {
			resultaat = gson.fromJson(jsonObject, Verbruiker.class);
			if (resultaat.getPostcode().equals(postcode)  && (resultaat.getHuisnummer() == huisnummer)) {
				return resultaat;
			}
		}
		return null;
	}

	public void deleteVerbruiker(Verbruiker verbruiker) {
		// Op basis van _id en _rev kun je een document in CouchDB verwijderen
		String[] idAndRev = getIdAndRevOfVerbruiker(verbruiker);
		deleteDocument(idAndRev[0], idAndRev[1]);
	}

	public String[] getIdAndRevOfVerbruiker(Verbruiker verbruiker) {
		// Vind het _id en _rev van document behorend bij een Verbruiker met postcode-huisnummer combinatie
		String[] idAndRev = new String[2];
		for (JsonObject jsonObject : getAllDocuments()) {
			if (jsonObject.has("postcode") && jsonObject.get("postcode").getAsString().equals(verbruiker.getPostcode())
					&& jsonObject.get("huisnummer").getAsInt() == verbruiker.getHuisnummer()) {
				idAndRev[0] = jsonObject.get("_id").getAsString();
				idAndRev[1] = jsonObject.get("_rev").getAsString();
			}
		}
		return idAndRev;
	}

	public String updateVerbruiker(Verbruiker verbruiker) {
		// Haal _id en _rev van document op behorend bij verbruiker
		// Zet verbruiker om in JsonObject
		String[] idAndRev = getIdAndRevOfVerbruiker(verbruiker);
		String jsonString = gson.toJson(verbruiker);
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject();
		// Voeg _id en _rev toe aan JsonObject, nodig voor de update van een document.
		jsonObject.addProperty("_id" , idAndRev[0]);
		jsonObject.addProperty("_rev" , idAndRev[1]);
		return updateDocument(jsonObject);
	}
}
