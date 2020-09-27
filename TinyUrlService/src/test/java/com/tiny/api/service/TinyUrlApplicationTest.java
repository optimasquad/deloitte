package com.tiny.api.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author admin
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TinyUrlApplicationTest {

	@Test
	public void contextLoad() {
		Assert.assertTrue(Boolean.TRUE);
	}

}
