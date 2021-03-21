package com.wallapoptest.tests;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.runners.MethodSorters;

import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallapop.MyWallapopSpringApplication;
import com.wallapop.entities.ProductOffer;
import com.wallapop.entities.User;
import com.wallapop.services.InsertSampleDataService;
import com.wallapop.services.ProductOfferService;
import com.wallapop.services.ProductPurchaseService;
import com.wallapop.services.UserService;
import com.wallapoptest.pageobjects.PO_HomeView;
import com.wallapoptest.pageobjects.PO_LoginView;
import com.wallapoptest.pageobjects.PO_RegisterView;
import com.wallapoptest.pageobjects.PO_View;
import com.wallapoptest.util.SeleniumUtils;
import static org.junit.Assert.assertTrue;

@TestConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyWallapopSpringApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyWallapopTests {

	@Autowired
	private InsertSampleDataService insertSampleDataService;
	@Autowired
	private UserService userService;
	@Autowired ProductOfferService prodOfferService;
	@Autowired ProductPurchaseService prodPurchaseService;

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
	public void setUp() throws ParseException {
		// Primero tenemos que limpiar la bd por si hubo cambios en el test anterior
		System.out.println(insertSampleDataService);
		insertSampleDataService.restartDBData();
		insertSampleDataService.init();
		//initdb();
		driver.navigate().to(URL);
	}

	@After
	public void tearDown() throws ParseException {
		driver.manage().deleteAllCookies();
		//driver.quit();
	}

	@BeforeClass
	static public void begin() {
	}

	@AfterClass
	static public void end() {
		driver.quit();
	}

	@Test
	public void PR01() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "pr01@mail.com", "TestN1", "ApellidosTest1", "77777", "77777");
		PO_View.checkElement(driver, "text", "Área privada");
	}

	@Test
	public void PR02() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "", "Josefo", "Perez", "77777", "77777");
		driver.findElement(By.id("email")).getAttribute("validationMessage");
		PO_RegisterView.fillForm(driver, "josefo@mail.com", "", "Perez", "77777", "77777");
		driver.findElement(By.id("name")).getAttribute("validationMessage");
		PO_RegisterView.fillForm(driver, "josefo@mail.com", "Josefo", "", "77777", "77777");
		driver.findElement(By.id("lastName")).getAttribute("validationMessage");
	}

	@Test
	public void PR03() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "josefo@mail.com", "Josefo", "Perez", "77777", "7777a");
		PO_View.checkElement(driver, "text", "Las contraseñas no coinciden.");

	}

	@Test
	public void PR04() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "josefo@mail.com", "Josefo", "Perez", "77777", "77777");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.getP();
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "josefo@mail.com", "Josefo", "Perez", "77777", "77777");
		PO_View.checkElement(driver, "text", "Este correo electrónico ya está registrado");
	}

	@Test
	public void PR05() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@mail.com", "admin");
		PO_View.checkElement(driver, "text", "Área privada");
	}

	@Test
	public void PR06() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "testmail@mail.com", "123456");
		PO_View.checkElement(driver, "text", "Área privada");
	}

	@Test
	public void PR07() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "", "123456");
		driver.findElement(By.id("email")).getAttribute("validationMessage");
		PO_LoginView.fillForm(driver, "testmail@mail.com", "");
		driver.findElement(By.id("password")).getAttribute("validationMessage");
	}

	@Test
	public void PR08() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "testmail@mail.com", "wrongpassword");
		driver.findElement(By.id("alert"));
	}

	@Test
	public void PR09() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "invaliduser@mail.com", "123456");
		driver.findElement(By.id("alert"));
	}

	@Test
	public void PR10() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "testmail@mail.com", "123456");
		PO_View.checkElement(driver, "text", "Área privada");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	@Test
	public void PR11() {
		driver.findElements(By.id("logout")).isEmpty();
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "testmail@mail.com", "123456");
		PO_View.checkElement(driver, "text", "Área privada");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		driver.findElements(By.id("logout")).isEmpty();

	}

	@Test
	public void PR12() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@mail.com", "admin");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "users-menu", PO_View.getTimeout());
		elementos.get(0).click();
		PO_HomeView.clickOption(driver, "/user/list", "class", "btn btn-primary");
		assertTrue(driver.findElements(By.xpath("//table[@id='usersTable']/tbody/tr")).size() == 7);
	}
	

}
