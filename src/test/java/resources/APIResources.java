package resources;



public enum APIResources {

	GetUserAPI("/users"),
	GetUserByIdAPI("/user/{id}"),
	GetUsersByCityAPI("/city/{city}/users"),
	GetInstructionsAPI("/instructions");
			
	

	private String resource;
	
	APIResources(String resource) {
		
		this.resource = resource;
		
	}
	
	
	public String getResource() {
		
		return resource;
	}
	
	
	
}
