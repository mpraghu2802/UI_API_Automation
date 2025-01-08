package com.fisTest.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fisTest.base.Setup;
import com.fisTest.responseEntity.BPIResponse;
import com.fisTest.restUtils.RestManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BpiApiTest extends Setup {

    @Test
    public void bpiValidate(){
        Map<String, String> headers = new HashMap<>();
        headers.put("CONTENT_TYPE", String.valueOf(ContentType.JSON));
        Response response = RestManager.get(headers, properties.getProperty("BPI_URL"));
        Reporter.log("BPI Response: "+response.getBody().prettyPrint());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            BPIResponse bpiResponse = objectMapper.readValue(response.getBody().asString(), BPIResponse.class);
            Set<String> bpiKeySet = bpiResponse.getBpi().getBpi().keySet();
            Reporter.log("BPIs fetched : " + bpiKeySet);
            Assert.assertEquals("Not all BPIs are present", 3, bpiKeySet.size());

            for (String key : bpiKeySet){
                Assert.assertTrue("One of the expected keys {USD, GBP, EUR} is not present", List.of("USD","GBP","EUR").contains(key));

                if(key.equalsIgnoreCase("GBP")){
                    String description = bpiResponse.getBpi().getBpi().get(key).getDescription();
                    Reporter.log("GBP's Description : " +description);
                    Assert.assertEquals("Description not Matching for GBP",description, "British Pound Sterling");
                }
            }

            Reporter.log("All validations Complete!!");

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
