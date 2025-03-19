package testcases;
import helper.Utility;

import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import helper.Payload;

public class CompareJsonString {

	
	@Test
		void testCompareJson() {

		try {
			String actualJson = Utility.loadJsonToString("src/test/resources/PayloadJson/actual.json");
			String expectedJson = Utility.loadJsonToString("src/test/resources/PayloadJson/expected.json");


			Utility.compareJsons(actualJson,expectedJson);
			
			
		} catch (JsonMappingException e) {
			
			System.out.println("Json Mapping Exception has been raised"+e);
		} catch (JsonProcessingException e) {
			
			System.out.println("Json Processing Exception has been raised"+e);
		}
		

	}

}
