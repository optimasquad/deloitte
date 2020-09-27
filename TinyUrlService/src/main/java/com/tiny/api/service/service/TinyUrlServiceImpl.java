
package com.tiny.api.service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tiny.api.service.constants.TinyUrlConstants;
import com.tiny.api.service.exception.UrlNotFoundException;
import com.tiny.url.model.TinyUrlResponse;

/**
 * @author jmahajan
 *
 */
@Service
public class TinyUrlServiceImpl implements TinyUrlService {

	private Map<String, String> keyMap = new HashMap<>();
	private Map<String, String> valueMap = new HashMap<>();
	private List<Character> chars;
	private Random random = new Random();

	@PostConstruct
	public void init() {
		chars = TinyUrlConstants.TOTAL_ALPHA_NUMERIC.chars().mapToObj(i -> (char) i).collect(Collectors.toList());
	}

	@Override
	public TinyUrlResponse createTinyUrl(final String fullLengthUrl) {
		TinyUrlResponse tinyUrlResponse = new TinyUrlResponse();
		if (null == fullLengthUrl) {
			throw new UrlNotFoundException("Input url is null", HttpStatus.BAD_REQUEST);
		}
		String shortURL = "";
		String url = urlSanitization(fullLengthUrl);
		if (valueMap.containsKey(url)) {
			shortURL = TinyUrlConstants.DOMAIN + "/" + valueMap.get(url);
		} else {
			shortURL = TinyUrlConstants.DOMAIN + "/" + getShortUrlKey(url);
		}
		tinyUrlResponse.setTinyUrl(shortURL);
		return tinyUrlResponse;
	}

	/**
	 * @param url
	 * @return
	 */
	private String urlSanitization(String url) {
		if (TinyUrlConstants.HTTP.equals(url.substring(0, 7)))
			url = url.substring(7);

		else if (TinyUrlConstants.HTTPS.equals(url.substring(0, 8)))
			url = url.substring(8);

		else if (TinyUrlConstants.FORWARD_SLASH == url.charAt(url.length() - 1))
			url = url.substring(0, url.length() - 1);

		return url;
	}

	/**
	 * This is the method is used to get the ShortUrl key
	 */
	private String getShortUrlKey(String fullLengthUrl) {
		String key = createKey();
		keyMap.put(key, fullLengthUrl);
		valueMap.put(fullLengthUrl, key);
		return key;
	}

	/**
	 * This is the method is used to create the key
	 * 
	 * @return
	 */
	private String createKey() {
		String key = "";
		boolean flag = true;
		while (flag) {
			key = "";
			for (int i = 0; i <= TinyUrlConstants.KEY_LENGTH; i++) {
				key += chars.get(random.nextInt(62));
			}
			if (!keyMap.containsKey(key)) {
				flag = false;
			}
		}
		return key;
	}

}
