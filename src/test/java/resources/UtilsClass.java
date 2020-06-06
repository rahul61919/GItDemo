package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UtilsClass {
	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException {
		if (req == null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addFilter(RequestLoggingFilter.logRequestTo(log)).
				addFilter(ResponseLoggingFilter.logResponseTo(log))
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		 return req;
	}
		return req;
	}	

	public String getGlobalValue(String key) throws IOException {
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream("D:\\12-30 batch workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
			prop.load(fis);
			return prop.getProperty(key);
			
	}
	
	public String getJsonPath(Response response, String key) {
		 String resp = response.asString();
		  JsonPath js = new JsonPath(resp);
		  return js.get(key).toString();
	}
}

//pehle gobal. properties ki file banai fir properties class ka object fir file input stream ka object fir prop object me load kia , fir
//string return kia, method ko static banaya to use it in above method then method ko call kia with dynamic key = baseUrl