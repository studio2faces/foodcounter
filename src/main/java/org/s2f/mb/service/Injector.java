package org.s2f.mb.service;

import org.s2f.mb.service.db.DatabaseHandler;
import org.s2f.mb.service.mappers.ObjectMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Injector {
    public static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    /*private static ObjectMapper mapper;
    private static DatabaseHandler databaseHandler;*/

    public static ObjectMapper getObjectMapper() {
        return context.getBean("mapper", ObjectMapper.class);
    }

    public static DatabaseHandler getDatabaseHandler() {
        return context.getBean("databaseHandler", DatabaseHandler.class);
    }
}
