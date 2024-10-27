package ru.clevertec.edu.ykv.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.clevertec.edu.ykv.dto.RocketRequest;
import ru.clevertec.edu.ykv.dto.RocketResponse;
import ru.clevertec.edu.ykv.entity.Rocket;
import ru.clevertec.edu.ykv.entity.RocketType;
import ru.clevertec.edu.ykv.util.Constants;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper
public interface RocketMapper {

    RocketMapper INSTANCE = Mappers.getMapper(RocketMapper.class);

    @Mapping(target = "uuid", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "id", ignore = true)
    Rocket requestToEntity(RocketRequest rocket);

    RocketResponse entityToResponse(Rocket rocket);

    default Document rocketToDocument(Rocket rocket) {
        return new Document()
                .append(Constants.ROCKET_UUID, rocket.getUuid())
                .append(Constants.ROCKET_NAME, rocket.getName())
                .append(Constants.ROCKET_TYPE, rocket.getRocketType())
                .append(Constants.ROCKET_COUNTRY, rocket.getCountry())
                .append(Constants.ROCKET_START_DATE, rocket.getStartTestPeriod())
                .append(Constants.ROCKET_END_DATE, rocket.getEndTestPeriod());
    }

    default Rocket documentToRocket(Document document) {
        ObjectId id = document.get(Constants.ROCKET_ID, ObjectId.class);
        Date startDate = document.get(Constants.ROCKET_START_DATE, Date.class);
        Date endDate = document.get(Constants.ROCKET_END_DATE, Date.class);

        return Rocket.builder()
                .id(id == null ? null : id.toString())
                .uuid(document.get(Constants.ROCKET_UUID, UUID.class))
                .name(document.getString(Constants.ROCKET_NAME))
                .rocketType(RocketType.valueOf(document.getString(Constants.ROCKET_TYPE)))
                .country(document.getString(Constants.ROCKET_COUNTRY))
                .startTestPeriod(startDate == null ? null : startDate.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate())
                .endTestPeriod(endDate == null ? null : endDate.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate())
                .build();
    }

    default RocketRequest httpRequestToRocketRequest(HttpServletRequest httpRequest) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String jsonString = httpRequest.getReader()
                .lines()
                .collect(Collectors.joining());

        return objectMapper.readValue(jsonString, RocketRequest.class);
    }
}
