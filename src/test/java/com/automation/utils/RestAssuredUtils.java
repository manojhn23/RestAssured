package com.automation.utils;

import com.automation.pojo.ObjectPojo;
import com.automation.pojo.UpdatePojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestAssuredUtils {

    static RequestSpecification requestSpecification = RestAssured.given();
    static Response response;
    static String endPoint;

    public static void setEndPoint(String endPoint) {
        if (endPoint.contains("@")) {
            endPoint = endPoint.replace("@id", ConfigReader.getConfigValue("user.id"));
        }
        RestAssuredUtils.endPoint = endPoint;
    }

    public static void setHeader(String key, String value) {
        requestSpecification = requestSpecification.header(key, value);
    }

    public static void post() {
        requestSpecification.log().all();
        response = requestSpecification.post(endPoint);
        response.then().log().all();
    }

    public static void get() {
        requestSpecification.log().all();
        response = requestSpecification.get(endPoint);
        response.then().log().all();
    }

    public static int getStatusCode() {
        return response.getStatusCode();
    }

    public static String getDataFromFile(String fileName) {
        try {
            return new Scanner(new FileInputStream(ConfigReader.getConfigValue("json.folder.path") + fileName)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setBody(String fileName) {
        String content = getDataFromFile(fileName);
        ObjectMapper mapper = new ObjectMapper();
        ObjectPojo objectPojo = new ObjectPojo();
        try {
            objectPojo = mapper.readValue(content, ObjectPojo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        requestSpecification = requestSpecification.body(objectPojo);
    }

    public static boolean getResponseValue(String key) {
        return response.jsonPath().getString(key).isEmpty();
    }

    public static String getResponseMsg() {
        return response.jsonPath().getString("error");
    }

    public static void setBody(String fileName, String email, String password) {
        String content = getDataFromFile(fileName);
        ObjectMapper mapper = new ObjectMapper();
        ObjectPojo objectPojo = new ObjectPojo();
        try {
            objectPojo = mapper.readValue(content, ObjectPojo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        objectPojo.setEmail(email);
        objectPojo.setPassword(password);
        requestSpecification = requestSpecification.body(objectPojo);
    }

    public static void put() {
        requestSpecification.log().all();
        response = requestSpecification.put(endPoint);
        response.then().log().all();
    }

    public static void setBodyForUpdate(String fileName) {
        String content = getDataFromFile(fileName);
        ObjectMapper mapper = new ObjectMapper();
        UpdatePojo updatePojo = new UpdatePojo();
        try {
            updatePojo = mapper.readValue(content, UpdatePojo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        updatePojo.setJob("Tester");
        requestSpecification = requestSpecification.body(updatePojo);
    }

    public static void delete() {
        requestSpecification.log().all();
        response = requestSpecification.delete(endPoint);
        response.then().log().all();
    }
}
