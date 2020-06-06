package stepDefinations;

import java.io.IOException;

import cucumber.api.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {
		StepDefinitions m = new StepDefinitions();
		if (StepDefinitions.place_id== null) {
		m.add_Place_Payload_with("bhurj khalifa", "German", "Sector 90");
		m.user_calls_API_with_HTTP_request("AddPlaceAPI", "POST");
		m.verify_placeid_created_maps_to_something_using_something("Ultima", "GetPlaceAPI");
	}
	}
}
	