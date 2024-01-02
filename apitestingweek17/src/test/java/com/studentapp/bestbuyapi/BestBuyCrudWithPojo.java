package com.studentapp.bestbuyapi;

import com.studentapp.model.BestBuyProductsPojo;
import com.studentapp.path.BestBuyEndPoints;
import com.studentapp.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class BestBuyCrudWithPojo {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    int productId;
    static String name = "Khushbu" + TestUtils.getRandomValue();
    static String type = "Patel" + TestUtils.getRandomValue();
    static Integer price = 20 + TestUtils.getRandomNumber();
    static Integer shipping = 3 + TestUtils.getRandomNumber();
    static String upc = "string" + TestUtils.getRandomValue();
    static String description = "Female" + TestUtils.getRandomValue();
    static String manufacturer = "God" + TestUtils.getRandomValue();
    static String model = "1995" + TestUtils.getRandomValue();
    static String url = "string" + TestUtils.getRandomValue();
    static String image = "string" + TestUtils.getRandomValue();
    @Test(priority = 1)
    public void getAllProducts() {
        response = RestAssured.given().log().all()
                .when()
                .get(BestBuyEndPoints.GET_AND_POST_PRODUCTS);
        response.then().log().all().statusCode(200);
    }
    @Test(priority = 2)
    public void postProducts() {
        BestBuyProductsPojo bestBuyProductsPojo = new BestBuyProductsPojo();
        bestBuyProductsPojo.setName(name);
        bestBuyProductsPojo.setType(type);
        bestBuyProductsPojo.setPrice(price);
        bestBuyProductsPojo.setShipping(shipping);
        bestBuyProductsPojo.setUpc(upc);
        bestBuyProductsPojo.setDescription(description);
        bestBuyProductsPojo.setManufacturer(manufacturer);
        bestBuyProductsPojo.setModel(model);
        bestBuyProductsPojo.setUrl(url);
        bestBuyProductsPojo.setImage(image);

        response = given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(bestBuyProductsPojo)
                .post(BestBuyEndPoints.GET_AND_POST_PRODUCTS);
        response.then().log().all().statusCode(201);
        productId = response.path("id");
        System.out.println(productId);
    }
    @Test(priority = 3)
    public void getSingleProductInfo() {
        given()
                .pathParam("id", productId)
                .get(BestBuyEndPoints.GET_BY_ID_PATCH_AND_DELETE)
                .then().statusCode(200)
                .body("name", equalTo(name))
                .body("type", equalTo(type));
    }
    @Test(priority = 4)
    public void patchProduct(){
        BestBuyProductsPojo bestBuyProductsPojo = new BestBuyProductsPojo();
        bestBuyProductsPojo.setName(name);
        bestBuyProductsPojo.setType(type);
        response = given().log().all()
                .pathParam("id", productId)
                .when()
                .body(bestBuyProductsPojo)
                .patch(BestBuyEndPoints.GET_BY_ID_PATCH_AND_DELETE);
        response.then().log().all().statusCode(200);
    }
    @Test(priority = 5)
    public void getSingleProductInfo001() {
        given()
                .pathParam("id", productId)
                .get(BestBuyEndPoints.GET_BY_ID_PATCH_AND_DELETE)
                .then().statusCode(200)
                .body("name", equalTo(name))
                .body("type", equalTo(type));
    }

    @Test(priority = 6)
    public void verifyNewProductInfoDeletedByID() {
        given()
                .pathParam("id", productId)
                .when()
                .delete(BestBuyEndPoints.GET_BY_ID_PATCH_AND_DELETE)
                .then()
                .statusCode(200);
    }
    @Test(priority = 7)
    public void verifyProductInfoDeleted() {
        given()
                .pathParam("id", productId)
                .when()
                .get(BestBuyEndPoints.GET_BY_ID_PATCH_AND_DELETE)
                .then().statusCode(404);
    }
}
