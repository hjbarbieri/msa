package com.globanttest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globanttest.domain.events.AccountEventType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MsaApplication.class)
@WebIntegrationTest("server.port:8888")
public class AccountControllerIntegrationTest {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void openAccount() throws JsonProcessingException {
        final Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("balance", BigDecimal.valueOf(10));
        requestBody.put("accountId", Long.valueOf(19));
        requestBody.put("accountEventType", AccountEventType.OPEN);

        final HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Creating http entity object with request body and headers
        HttpEntity<String> httpEntity =
                new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

        Map<String, Object> apiResponse = restTemplate
                .postForObject("http://localhost:8888/accounts",
                        httpEntity,
                        Map.class, Collections.EMPTY_MAP);

        assertNotNull(apiResponse);
        
    }
}
