
package com.tiny.api.service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.tiny.api.service.exception.UrlNotFoundException;
import com.tiny.url.model.TinyUrlResponse;

/**
 * @author jmahajan
 *
 */
public class TinyUrlServiceTest {

	@Mock
	private Random randomNumberMock;

	@InjectMocks
	private TinyUrlServiceImpl tinyUrlService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateTinyUrl() {
		tinyUrlService.init();
		Mockito.when(randomNumberMock.nextInt()).thenReturn(25).thenReturn(12);
		TinyUrlResponse tinyUrlResponse = tinyUrlService.createTinyUrl("www.yahoo.com");
		assertEquals("http://curl.in/aaaaaaaaa", tinyUrlResponse.getTinyUrl());
	}

	@Test(expected = UrlNotFoundException.class)
	public void testInputUrlIsNull() {
		tinyUrlService.createTinyUrl(null);
	}

	@Test
	public void testCreateHttpTinyUrl() {
		tinyUrlService.init();
		Mockito.when(randomNumberMock.nextInt()).thenReturn(400).thenReturn(100);
		TinyUrlResponse tinyUrlResponse = tinyUrlService.createTinyUrl("http://www.yahoo.com");
		assertEquals("http://curl.in/aaaaaaaaa", tinyUrlResponse.getTinyUrl());
	}

	@Test
	public void testCreateHttpsTinyUrl() {
		tinyUrlService.init();
		Mockito.when(randomNumberMock.nextInt()).thenReturn(5260).thenReturn(1200);
		TinyUrlResponse tinyUrlResponse = tinyUrlService.createTinyUrl("https://www.yahoo.com");
		assertEquals("http://curl.in/aaaaaaaaa", tinyUrlResponse.getTinyUrl());
	}

}
