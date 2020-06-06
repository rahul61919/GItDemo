package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GoogleMapsApi;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.UtilsClass;

public class StepDefinitions extends UtilsClass{
	Response response;
	RequestSpecification res;
	ResponseSpecification resSpc;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
	
		 res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
		 
	
	
	}           	


	@When("user calls {string} API with {string} HTTP request")
	public void user_calls_API_with_HTTP_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
    	APIResources resourceAPI = APIResources.valueOf(resource);
    	System.out.println(resourceAPI.getResource());
		
		resSpc = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("POST")) 
		response = res.when().post(resourceAPI.getResource());
	
		else if (method.equalsIgnoreCase("GET")) 
			response = res.when().get(resourceAPI.getResource());
		
		
	
	}


    @Then("^The API call get success with status code 200$")
    public void the_api_call_get_success_with_status_code_200() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   assertEquals(response.getStatusCode(), 200);
	}

	  @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	    public void something_in_response_body_is_something(String keyValue, String expectedValue) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		  
		  assertEquals(getJsonPath(response, keyValue).toString(), expectedValue);
	}
	  
	   @And("^verify place_Id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	    public void verify_placeid_created_maps_to_something_using_something(String name,String resource) throws Throwable {
	        place_id = getJsonPath(response, "place_id");
	        res = given().spec(requestSpecification()).queryParam("place_id", place_id);
	        user_calls_API_with_HTTP_request(resource, "GET");
	        String actualName = getJsonPath(response, "name");
	        Assert.assertEquals(actualName, name);
	    }

	    @Given("^Delete place payload$")
	    public void delete_place_payload() throws Throwable {
	        res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	        		
	    }

}
