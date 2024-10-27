package ru.clevertec.edu.ykv.repository;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import org.bson.BsonValue;
import org.bson.Document;
import ru.clevertec.edu.ykv.entity.Rocket;
import ru.clevertec.edu.ykv.mapper.RocketMapper;
import ru.clevertec.edu.ykv.util.Constants;
import ru.clevertec.edu.ykv.util.DataSource;
import ru.clevertec.edu.ykv.util.DataSourceRocket;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class RocketRepository implements Repository<Rocket> {


    private static final RocketMapper ROCKET_MAPPER = RocketMapper.INSTANCE;
    private final DataSource dataSource = new DataSourceRocket();

    @Override
    public Rocket save(Rocket rocket) {
        Document document = ROCKET_MAPPER.rocketToDocument(rocket);

        InsertOneResult result = dataSource.getCollection().insertOne(document);
        BsonValue insertedId = result.getInsertedId();
        if (insertedId != null) {
            String id = insertedId.asObjectId().getValue().toString();
            rocket.setId(id);
            return rocket;
        }
        return null;
    }

    @Override
    public Rocket findByUuid(UUID uuid) {
        Document document = dataSource.getCollection()
                .find(eq(Constants.ROCKET_UUID, uuid))
                .first();

        if (document != null) {
            return ROCKET_MAPPER.documentToRocket(document);
        }
        return null;
    }

    @Override
    public List<Rocket> findAll() {
        List<Rocket> rockets = new ArrayList<>();
        try (MongoCursor<Document> documents = dataSource.getCollection()
                .find()
                .iterator()) {

            while (documents.hasNext()) {
                Document document = documents.next();
                rockets.add(ROCKET_MAPPER.documentToRocket(document));
            }
        }
        return rockets;
    }

    @Override
    public Rocket update(UUID rocketUuid, Rocket rocket) {
        dataSource.getCollection()
                .updateOne(
                        eq(Constants.ROCKET_UUID, rocketUuid),
                        Updates.combine(
                                Updates.set(Constants.ROCKET_NAME, rocket.getName()),
                                Updates.set(Constants.ROCKET_TYPE, rocket.getRocketType()),
                                Updates.set(Constants.ROCKET_COUNTRY, rocket.getCountry()),
                                Updates.set(Constants.ROCKET_START_DATE, rocket.getStartTestPeriod()),
                                Updates.set(Constants.ROCKET_END_DATE, rocket.getEndTestPeriod())
                        )
                );


        Document document = dataSource.getCollection()
                .find(eq(Constants.ROCKET_UUID, rocketUuid))
                .first();

        if (document != null) {
            return ROCKET_MAPPER.documentToRocket(document);
        }
        return null;
    }


    @Override
    public void delete(UUID rocketUuid) {
        dataSource.getCollection().deleteOne(eq(Constants.ROCKET_UUID, rocketUuid));
    }

}
