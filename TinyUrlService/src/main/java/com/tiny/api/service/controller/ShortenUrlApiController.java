package com.tiny.api.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.tiny.api.service.service.TinyUrlService;
import com.tiny.url.model.TinyUrlRequest;
import com.tiny.url.model.TinyUrlResponse;

@RestController
public class ShortenUrlApiController implements ShortenUrlApi {

	private static final Logger LOG = LoggerFactory.getLogger(ShortenUrlApiController.class);

	@Autowired
	private TinyUrlService tinyUrlService;

	@Override
	public ResponseEntity<TinyUrlResponse> createTinyUrl(TinyUrlRequest tinyUrlRequest) {

		if (null == tinyUrlRequest.getFullLengthUrl()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		LOG.trace("Creating TinyUrl starts for the input full url {}", tinyUrlRequest.getFullLengthUrl());
		TinyUrlResponse tinyUrlResponse = tinyUrlService.createTinyUrl(tinyUrlRequest.getFullLengthUrl());
		LOG.trace("Creating TinyUrl ends with tiny url {}", tinyUrlResponse.getTinyUrl());
		return new ResponseEntity<>(tinyUrlResponse, HttpStatus.CREATED);
	}

}
