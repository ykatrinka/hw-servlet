package ru.clevertec.edu.ykv.util;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public interface DataSource {
    MongoCollection<Document> getCollection();
}
