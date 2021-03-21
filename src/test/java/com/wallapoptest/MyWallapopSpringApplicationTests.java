package com.wallapoptest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallapoptest.tests.MyWallapopTests;

@RunWith( SpringRunner.class )
@ContextConfiguration
@SpringBootTest(classes=MyWallapopTests.class)
public class MyWallapopSpringApplicationTests {

	@Test
	public void contextLoads() {
	}

}
