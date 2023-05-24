package utils;


public class payload{

public String userjson() {
	 String payload="{\"name\": \"John Doe\", \"job\": \"Software Engineer\"}";

Shared.Newuser =  payload;
return Shared.Newuser;
}

public String userUpdatejson() {
	 String payload="{\"name\": \"John Doe\", \"job\": \"Mechanical Engineer\"}";

Shared.Updateduser =  payload;
return Shared.Updateduser;
}
}