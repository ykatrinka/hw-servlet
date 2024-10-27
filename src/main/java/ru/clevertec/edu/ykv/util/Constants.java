package ru.clevertec.edu.ykv.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public static final String MONGO_DB_URL = "mongodb://localhost:27017";
    public static final String MONGO_DB_NAME = "rocket-db";
    public static final String COLLECTION_NAME = "rockets";
    public static final String ROCKET_ID = "_id";
    public static final String ROCKET_UUID = "uuid";
    public static final String ROCKET_NAME = "rocketName";
    public static final String ROCKET_TYPE = "rocketType";
    public static final String ROCKET_COUNTRY = "country";
    public static final String ROCKET_START_DATE = "startTestPeriod";
    public static final String ROCKET_END_DATE = "endTestPeriod";

    public static final String PARAM_ACTION = "action";
    public static final String PARAM_UUID = "uuid";

    public static final String ACTION_FIND_BY_UUID = "findByUuid";
    public static final String ACTION_FIND_ALL = "findAll";

    public static final int STATUS_OK = 200;

    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_PASSWORD = "password";

    public static final String AUTH_USER = "admin";
    public static final String AUTH_PASSWORD = "admin";
}
