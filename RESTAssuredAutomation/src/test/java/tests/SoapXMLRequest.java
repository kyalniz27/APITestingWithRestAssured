package tests;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileInputStream;

public class SoapXMLRequest {
	
	@Test
	public void validateSoapXML() throws Exception{
		
		File file = new File("./SoapRequest/Add.xml");
		
		if (file.exists()) {
			System.out.println(" >> File Exists <<");
		} else {
			throw new Exception();
		}
		FileInputStream fileInputStream = new FileInputStream(file);
		String requestBody = IOUtils.toString(fileInputStream, "UTF-8");
		
		baseURI = "http://www.dneonline.com";
		
		given().contentType("text/xml").accept(ContentType.XML)
		.body(requestBody)
		.when().post("/calculator.asmx")
		.then().statusCode(200).log().all().and()
		.body("//*:AddResult.text()",equalTo("5"));
		
	}

}
