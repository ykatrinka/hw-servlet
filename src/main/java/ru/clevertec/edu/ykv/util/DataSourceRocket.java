package ru.clevertec.edu.ykv.util;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.UuidRepresentation;

import java.util.ArrayList;

public class DataSourceRocket implements DataSource {

    private final MongoClient instance;
    private MongoDatabase db;
    private MongoCollection<Document> collection;

    public DataSourceRocket() {
        MongoClientSettings settings = MongoClientSettings
                .builder()
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .applyConnectionString(new ConnectionString(Constants.MONGO_DB_URL))
                .build();
        instance = MongoClients.create(settings);
        initDb();
    }

    @Override
    public MongoCollection<Document> getCollection() {
        return collection;
    }

    private void initDb() {
        db = instance.getDatabase(Constants.MONGO_DB_NAME);

        String collectionName = Constants.COLLECTION_NAME;
        if (!collectionIsExists(collectionName)) {
            db.createCollection(collectionName);
        }
        collection = db.getCollection(collectionName);
    }


    private boolean collectionIsExists(String collectionName) {
        return db.listCollectionNames()
                .into(new ArrayList<>())
                .contains(collectionName);
    }

}
