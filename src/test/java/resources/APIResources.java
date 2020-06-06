package resources;

public enum APIResources {
	
	AddPlaceAPI ("maps/api/place/add/json"),
	GetPlaceAPI ("maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	private String resourse;
	
	APIResources(String resource) {
		this.resourse = resource;
	}	
	 public String getResource( ) {
		 return resourse;
	 }
	
	}

