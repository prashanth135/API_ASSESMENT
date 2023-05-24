package utils;

import org.hamcrest.Matchers;
import org.junit.Assert;



import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class request {
	
	
	public String sendPut(String uri, Object payload) {
	Response response = RestAssured.given().
			contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			.when()
			.put(uri);
	Assert.assertEquals(200, response.getStatusCode());
	System.out.println("POST Response\n" + response.asString());	
	return response.asString();
}
	
	public String sendPost1(String uri,Object payload) {	
		Response response = RestAssured.given()
				.when()
				.contentType(ContentType.JSON)
				.body(payload)
				.post(uri);
		Assert.assertEquals(201, response.getStatusCode());
		
		return response.getBody().asString();
	}

	
	public String get(String uri) {
	Response response = null;
	response = RestAssured.given()
	    .when()
	    .get(uri);
	Assert.assertEquals(200, response.getStatusCode());
	return response.asString();
	}
	

}
