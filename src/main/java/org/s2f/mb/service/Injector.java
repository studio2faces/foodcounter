package org.s2f.mb.service;

import org.s2f.mb.service.db.DatabaseHandler;
import org.s2f.mb.service.mappers.ObjectMapper;

public class Injector {
    private static ObjectMapper mapper;
    private static DatabaseHandler databaseHandler;

    public static ObjectMapper getObjectMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        return mapper;
    }

    public static DatabaseHandler getDatabaseHandler() {
        if (databaseHandler == null) {
            databaseHandler = new DatabaseHandler();
        }
        return databaseHandler;
    }
}
