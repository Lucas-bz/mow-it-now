/*
 * Copyright (c) 2020.
 * MowItNowControllerTest.java created by farouk
 */

package fr.bouzidi.mowitnow.controller;

import fr.bouzidi.mowitnow.model.Lawn;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class MowItNowControllerTest {

    @LocalServerPort
    private int port;


    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testUpload() {
        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
        parameters.add("file", new org.springframework.core.io.ClassPathResource("file.txt"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<LinkedMultiValueMap<String, Object>>(parameters, headers);

        ResponseEntity<MowItNowResponse> response = restTemplate.exchange("http://localhost:" + port + "/uploadFile", HttpMethod.POST, entity, MowItNowResponse.class, "");


        // Expect Ok
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getInitial(), Matchers.any(Lawn.class));
        assertThat(response.getBody().getLast(), Matchers.any(Lawn.class));
        assertThat(response.getBody().getLast(), Matchers.notNullValue());


    }
}