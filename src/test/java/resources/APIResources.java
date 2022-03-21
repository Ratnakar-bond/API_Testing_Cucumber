package resources;

public enum APIResources {
	
	
	//enum is a special class in java which is collection of constants or method
	addPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");
	
	final String resource;
	//for enum class we need to define a constructor always which accpet string argument
	//so now from ste defintion class where we have define when() method come to this and use resource which is addplaceAPI /getPlaceAPI etc
	//but it will only load it to return that value we need to create another method which is below one getResource
	APIResources(String resource) {
                   this.resource=resource;// assign this resource to  local keyword resource
	}
	
	//this is a method to use the resource value which return resource value but the resource value is local to above APIResource constructor
	// so to use it global we have to create a variable private String resource 
	//And in APIResource constructor we need to define it so that variable resource can use the value of string argument resource globally
   public String getResource() {
	   
	   return resource;
   }
}

	


