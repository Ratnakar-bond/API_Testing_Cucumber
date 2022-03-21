package resources;

import java.util.ArrayList;

import pojo.serilization.LatituteAndLongitude;
import pojo.serilization.Location;

public class TestDataBuilder {
	
	
	public Location addPlaceApi(String name, String language, String address) {
		

		Location loc = new Location();
		loc.setAccuracy(50);
		loc.setName(name);
		loc.setPhone_number("(+91) 983 893 3937");
		loc.setAddress(address);
		loc.setWebsite("https://www.makemytrip.com/");
		loc.setLanguage(language);

		// as for setting types which is arraylist we create a object of array list and
		// add the types in it
		ArrayList<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		// we just pass object in this method
		loc.setTypes(types);

		// as latandlog is a method to access it we create an object and use it 2 method
		// with value define
		LatituteAndLongitude latlong = new LatituteAndLongitude();

		latlong.setLat(-38.383494);
		latlong.setLng(33.427362);

		// we pass the object in the method
		loc.setLocation(latlong);
		
		return loc;
	}
	
	public String deletePlaceApi(String place_id) {
		
		 return "{\r\n    \"place_id\": \""+place_id+"\"\r\n}";
		
		
	}

}
