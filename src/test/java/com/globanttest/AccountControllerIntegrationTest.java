package com.globanttest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globanttest.domain.Account;
import com.globanttest.domain.GeneratorId;
import com.globanttest.domain.events.AccountEvent;
import com.globanttest.domain.events.AccountEventType;
import com.globanttest.domain.repositories.AccountEventRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MsaApplication.class)
@WebIntegrationTest("server.port:8080")
public class AccountControllerIntegrationTest {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private RestTemplate restTemplate = new TestRestTemplate();
    
    @Mock
    private AccountEventRepository accountEventRepository;
    @Mock 
    private GeneratorId generatorId;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp(){
    	MockitoAnnotations.initMocks(this);
    	final Account account = this.context.getBean(Account.class);
    	ReflectionTestUtils.setField(account, "accountEventRepository", accountEventRepository);
    	ReflectionTestUtils.setField(account, "generatorId", generatorId);
    }
    
    @Test
    public void openAccount() throws Exception {
        final Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("balance", BigDecimal.valueOf(10));
        requestBody.put("accountId", null);
        requestBody.put("accountEventType", AccountEventType.OPEN);

        Mockito.when(generatorId.getRandomId()).thenReturn(1L);
        
        final HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Creating http entity object with request body and headers
        HttpEntity<String> httpEntity =
                new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

        ResponseEntity<String> apiResponse = restTemplate
                .exchange("http://localhost:8080/accounts",HttpMethod.POST,
                        httpEntity,String.class
                       );

        Assert.assertEquals(apiResponse.getStatusCode(),HttpStatus.OK);
        
        AccountEvent acountEvent = new AccountEvent(new BigDecimal(10), 1L, AccountEventType.OPEN);
        
        Mockito.verify(accountEventRepository).persist(acountEvent);
        
    }
    
    @Test
    public void debitAccount() throws Exception {
        final Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("balance", BigDecimal.valueOf(-10));
        requestBody.put("accountId", 1L);
        requestBody.put("accountEventType", AccountEventType.DEBIT);

               
        final HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Creating http entity object with request body and headers
        HttpEntity<String> httpEntity =
                new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

        ResponseEntity<String> apiResponse = restTemplate
                .exchange("http://localhost:8080/accounts/1",HttpMethod.POST,
                        httpEntity,String.class
                       );

        Assert.assertEquals(apiResponse.getStatusCode(),HttpStatus.OK);
        
        AccountEvent acountEvent = new AccountEvent(new BigDecimal(-10), 1L, AccountEventType.DEBIT);
        
        Mockito.verify(accountEventRepository).persist(acountEvent);
        
    }
    
    @Test
    public void creditAccount() throws Exception {
        final Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("balance", BigDecimal.valueOf(10));
        requestBody.put("accountId", 1L);
        requestBody.put("accountEventType", AccountEventType.CREDIT);

               
        final HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Creating http entity object with request body and headers
        HttpEntity<String> httpEntity =
                new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

        ResponseEntity<String> apiResponse = restTemplate
                .exchange("http://localhost:8080/accounts/1",HttpMethod.POST,
                        httpEntity,String.class
                       );

        Assert.assertEquals(apiResponse.getStatusCode(),HttpStatus.OK);
        
        AccountEvent acountEvent = new AccountEvent(new BigDecimal(10), 1L, AccountEventType.CREDIT);
        
        Mockito.verify(accountEventRepository).persist(acountEvent);
        
    }
}
