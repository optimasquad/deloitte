
package com.tiny.api.service.service;

import com.tiny.url.model.TinyUrlResponse;

/**
 * @author jmahajan
 *
 */
public interface TinyUrlService {

	/**
	 * @return
	 */
	public TinyUrlResponse createTinyUrl(String longURL);

}
