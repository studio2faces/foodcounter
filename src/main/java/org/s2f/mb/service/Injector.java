package org.s2f.mb.service;

import org.s2f.mb.service.db.DatabaseHandler;
import org.s2f.mb.service.mappers.ObjectMapper;

public class Injector {
    private static ObjectMapper om;
    private static DatabaseHandler dth;

    public static ObjectMapper getObjectMapper() {
        if (om == null) {
            om = new ObjectMapper();
        }
        return om;
    }

    public static DatabaseHandler getDatabaseHandler() {
        if (dth == null) {
            dth = new DatabaseHandler();
        }
        return dth;
    }
}
