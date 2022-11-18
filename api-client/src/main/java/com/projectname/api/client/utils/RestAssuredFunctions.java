package com.projectname.api.client.utils;

import com.google.gson.GsonBuilder;
import com.projectname.api.client.constants.GlobalParams;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

// Basic REST API methods that can be reused; all with Authorization and bearer token; example with and without body
public class RestAssuredFunctions {

    public static Response get(String accessToken, String uri) {
        return given().contentType(ContentType.JSON).header(GlobalParams.AUTHORIZATION, "Bearer " + accessToken).when()
                .get(uri).then().extract().response();
    }

    public static Response get(Object body, String token, String uri) {
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(body);
        return given().contentType(ContentType.JSON).header(GlobalParams.AUTHORIZATION, "Bearer " + token).body(json).when()
                .get(uri).then().extract().response();
    }

    public static Response post(Object body, String token, String uri) {
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(body);
        return given().contentType(ContentType.JSON).header(GlobalParams.AUTHORIZATION, "Bearer " + token).body(json).when()
                .post(uri).then().extract().response();
    }

    public static Response post(String accessToken, String uri) {
        return given().contentType(ContentType.JSON).header(GlobalParams.AUTHORIZATION, "Bearer " + accessToken).when()
                .post(uri).then().extract().response();
    }

    public static Response post(Object body, String uri) {
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(body);
        return given().contentType(ContentType.JSON).body(json).when()
                .post(uri).then().extract().response();
    }

    public static Response get(String uri) {
        return given().contentType(ContentType.JSON).when()
                .get(uri).then().extract().response();
    }

    //filepath should target resources folder; example: "src/test/resources/approved.jpg"
    //IMPORTANT: .multiPart("files[]", file) - "files[]" needs to replaced with parameter in your project
    public static Response post(Map<String, Object> bodyMap, String token, String uri, String filePath) {
        File file = new File(filePath);
        return given().multiPart("files[]", file).formParams(bodyMap).header(GlobalParams.AUTHORIZATION, "Bearer " + token).when()
                .post(uri).then().extract().response();
    }

    public static Response delete(String accessToken, String uri) {
        return given().header(GlobalParams.AUTHORIZATION,  "Bearer " + accessToken).when()
                .delete(uri).then().extract().response();
    }

    public static Response delete(Object body, String accessToken, String uri) {
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(body);
        return given().contentType(ContentType.JSON).header(GlobalParams.AUTHORIZATION,  "Bearer " + accessToken).body(json).when()
                .delete(uri).then().extract().response();
    }

    public static Response patch(Object body, String token, String uri) {
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(body);
        return given().contentType(ContentType.JSON).header(GlobalParams.AUTHORIZATION, "Bearer " + token).body(json).when()
                .patch(uri).then().extract().response();
    }

    public static Response put(String accessToken, String uri) {
        return given().contentType(ContentType.JSON).header(GlobalParams.AUTHORIZATION, "Bearer " + accessToken).when()
                .put(uri).then().extract().response();
    }

    public static Response put(Object body, String accessToken, String uri) {
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(body);
        return given().contentType(ContentType.JSON).header(GlobalParams.AUTHORIZATION, "Bearer " + accessToken).body(json).when()
                .put(uri).then().extract().response();
    }

    public static Response put(Object body, String uri) {
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(body);
        return given().contentType(ContentType.JSON).body(json).when().put(uri).then().extract().response();
    }

   //if it is basic auth
    public static Response getWithBasicAuth(BasicAuth loginUser, String uri) {
        return given().auth().preemptive()
                .basic(loginUser.getUserName(), loginUser.getPassword()).header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON).when().get(uri).then().extract().response();
    }

    //if it is with client credentials
    public static Response getWithClientCredentials(String token, String uri) {
        return given().auth().preemptive()
                .oauth2(token)
                .contentType(ContentType.JSON)
                .when()
                .get(uri).then().extract().response();
    }
}
