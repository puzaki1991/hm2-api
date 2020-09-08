package com.hm2.ws.rest;

import com.google.gson.Gson;
import com.hm2.common.beans.ResponseData;
import com.hm2.common.controllers.BaseController;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest-ws")
public class RestController extends BaseController {

    @GetMapping("/covid-19")
    public ResponseData<Object> getSummary(){
        ResponseData<Object> responseData = new ResponseData<>();
        String jsonInString = null;
        String urlCovid19 = "xxxxxxxxxxxxxxxx";
        try {
            String headerName = "user-agent";
            String headerValue = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add(headerName, headerValue);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

            Object response = restTemplate.exchange(urlCovid19, HttpMethod.GET,entity,Object.class);
            Gson gson = new Gson();
            jsonInString = gson.toJson(response);
            responseData.setData(response);
           setMessageSuccess(responseData);
        } catch (Exception e) {
            setMessageFail(responseData, e);
        }
        return responseData;
    }
}
