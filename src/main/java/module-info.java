module CouchDBWoningen {
    requires gson;
    requires lightcouch;
    requires java.sql;

    opens javacouchdb to gson, lightcouch, java.sql;
    opens model to gson;
}