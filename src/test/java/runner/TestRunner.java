package runner;

import java.io.FileReader;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.jayway.jsonpath.JsonPath;

import io.cucumber.junit.CucumberOptions;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import utils.Shared;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features/api.feature", 
glue= {"steps"},
plugin = {"json:target/cucumber.json"})
public class TestRunner {
	
	@BeforeClass
	public static void pickurl() {
	@SuppressWarnings("deprecation")
	JSONParser parser = new JSONParser();
	try {
		 Object obj = parser.parse(new FileReader("src/test/java/utils/Properties.json"));
            JSONObject jsonObject =  (JSONObject) obj;
     
            Shared.getUrl = JsonPath.read(jsonObject,"$.get.getUrl").toString();
            Shared.postUrl = JsonPath.read(jsonObject,"$.post.postUrl").toString();
            Shared.putUrl = JsonPath.read(jsonObject,"$.put.putUrl").toString();
}catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

}

}
