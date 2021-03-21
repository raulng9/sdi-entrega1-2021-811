package com.wallapoptest.tests;

import java.util.List;

import org.junit.runners.MethodSorters;

import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallapop.services.InsertSampleDataService;
import com.wallapop.services.UserService;
import com.wallapoptest.pageobjects.PO_HomeView;
import com.wallapoptest.pageobjects.PO_LoginView;
import com.wallapoptest.pageobjects.PO_PrivateView;
import com.wallapoptest.pageobjects.PO_Properties;
import com.wallapoptest.pageobjects.PO_RegisterView;
import com.wallapoptest.pageobjects.PO_View;
import com.wallapoptest.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyWallapopTests {

	private String URLInicio = "http://localhost:8099";

	@Autowired
	private InsertSampleDataService init;

	@Autowired
	private UserService userService;

	static String PathFirefox65 = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
	static String Geckdriver024 = "/Users/raul/selenium/geckodriver024mac";

	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8099";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
	
	@Before
	public void setUp() throws Exception{
		//Primero tenemos que limpiar la bd por si hubo cambios en el test anterior
		init.restartDBData();
		init.init();
		driver.navigate().to(URLInicio);
	}

	@After
	public void tearDown() throws Exception {
		driver.manage().deleteAllCookies();
	}

}
