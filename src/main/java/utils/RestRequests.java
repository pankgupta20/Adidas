package utils;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestRequests {

	public static RequestSpecification getRestRequest() {
		RestAssured.baseURI = Constants.BASE_URL;
		RequestSpecification request = RestAssured.given();
		return request;
	}
	
	public static RequestSpecification getRestRequestWithHeaders() {
		RequestSpecification request = getRestRequest();
		request.header("Content-Type","application/json");
		return request;
	}
	
	
}
