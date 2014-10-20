package com.todoapp;

import com.mongodb.*;

import static spark.Spark.setIpAddress;
import static spark.Spark.setPort;
import static spark.SparkBase.staticFileLocation;

/**
 * Created by shekhargulati on 09/06/14.
 */
public class Bootstrap {
    private static final String IP_ADDRESS =  "0.0.0.0";
    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        setIpAddress(IP_ADDRESS);
        setPort(PORT);
        staticFileLocation("/public");
        new TodoResource(new TodoService(mongo()));
    }

    private static DB mongo() throws Exception {
        String host = System.getenv("DB_PORT_27017_TCP_ADDR");
        int port = Integer.parseInt(System.getenv("DB_PORT_27017_TCP_PORT"));
        String dbname = "mydb";
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().connectionsPerHost(20).build();
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), mongoClientOptions);
        mongoClient.setWriteConcern(WriteConcern.SAFE);
        DB db = mongoClient.getDB(dbname);
        return db;
    }
}
