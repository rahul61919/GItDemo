package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.GoogleMapsApi;
import pojo.Location;

public class TestDataBuild {
	public GoogleMapsApi addPlacePayload(String name, String language, String address) {
		GoogleMapsApi p = new GoogleMapsApi();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("www.rahulshettyacadamy.com");
		Location l = new Location();
		l.setLat(-30.232323);
		l.setLng(30.12121);
		p.setLocation(l);
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		return p;
	}
	
	public String deletePlacePayload(String place_id) {
		return "{\r\n    \"place_id\":\""+place_id+"\"\r\n}\r\n";
	}
}
