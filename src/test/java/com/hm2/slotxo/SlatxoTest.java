package com.hm2.slotxo;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SlatxoTest {

    @Test
    public void loginPage() {

        System.out.println("Test loginPage...");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        MultiValueMap<String, String> mapParam = new LinkedMultiValueMap<>();
//        mapParam.add("username", otpKey);
//        mapParam.add("password", otpSecret);
//        mapParam.add("msisdn", request.getTel()); //tel

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(mapParam, headers);

        try {

            String baseUrl = "http://ag.slotxo.com/Account/SignIn?url=http%3A%2F%2Fag.slotxo.com%2F&invalidateAntiForgeryToken=False";
            URI uri = new URI(baseUrl);

            ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
//            response = restTemplate.exchange(uri,
//                    HttpMethod.GET, Object.class);

            //==> Send OTP Success
            Gson gson = new Gson();
//            String jsonInString = gson.toJson(response);
//            JsonObject data = new Gson().fromJson(jsonInString, JsonObject.class);
//            JsonObject body = data.get("body").getAsJsonObject();
//            JsonObject dataBody = body.get("data").getAsJsonObject();
//            String token = dataBody.get("token").getAsString();
//            String status = dataBody.get("status").getAsString();

        } catch (Exception e) {
            //==> Send OTP fail
            //==> Add Action Log
        }
    }

}
