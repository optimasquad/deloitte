
package com.tiny.api.service.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiny.api.service.TinyUrlApplicationTest;
import com.tiny.api.service.service.TinyUrlService;
import com.tiny.url.model.TinyUrlRequest;
import com.tiny.url.model.TinyUrlResponse;

/**
 * @author admin
 *
 */
public class ShortenUrlApiControllerTest extends TinyUrlApplicationTest {

	@InjectMocks
	private ShortenUrlApiController shortenUrlApiController;

	@Mock
	private TinyUrlService tinyUrlService;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(shortenUrlApiController).build();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateTinyUrl() throws Exception {
		TinyUrlRequest tinyUrlRequest = new TinyUrlRequest();
		tinyUrlRequest.setFullLengthUrl("www.yahoo.com");

		TinyUrlResponse tinyUrlResponse = new TinyUrlResponse();
		tinyUrlResponse.setTinyUrl("http://curl.in/vRpNNziQx");

		Mockito.when(tinyUrlService.createTinyUrl(Mockito.anyString())).thenReturn(tinyUrlResponse);
		final MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/v1/shorten-url").content(asJsonString(tinyUrlRequest))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
		assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
		assertEquals("http://curl.in/vRpNNziQx", tinyUrlResponse.getTinyUrl());

	}

	@Test
	public void testTinyUrlThrowsBadRequest() throws Exception {
		final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/shorten-url"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
		assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
	}

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
