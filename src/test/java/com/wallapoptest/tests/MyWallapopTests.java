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
import com.wallapoptest.pageobjects.PO_NavView;
import com.wallapoptest.pageobjects.PO_NewOfferView;
import com.wallapoptest.pageobjects.PO_Properties;
import com.wallapoptest.pageobjects.PO_RegisterView;
import com.wallapoptest.pageobjects.PO_View;
import com.wallapoptest.util.SeleniumUtils;
import static org.junit.Assert.assertTrue;

@TestConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		MyWallapopSpringApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyWallapopTests {

	@Autowired
	private InsertSampleDataService insertSampleDataService;
	@Autowired
	private UserService userService;
	@Autowired
	ProductOfferService prodOfferService;
	@Autowired
	ProductPurchaseService prodPurchaseService;

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
		insertSampleDataService.restartDBData();
		insertSampleDataService.init();
		driver.navigate().to(URL);
	}

	@After
	public void tearDown() throws ParseException {
		driver.manage().deleteAllCookies();
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

	@Test
	public void PR13() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@mail.com", "admin");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "users-menu", PO_View.getTimeout());
		elementos.get(0).click();
		PO_HomeView.clickOption(driver, "/user/list", "class", "btn btn-primary");
		assertTrue(driver.findElements(By.xpath("//table[@id='usersTable']/tbody/tr")).size() == 7);
		List<WebElement> checks = driver.findElements(By.xpath("//input[@type='checkbox']"));
		checks.get(0).click();
		driver.findElement(By.id("btnDel")).click();
		assertTrue(driver.findElements(By.xpath("//table[@id='usersTable']/tbody/tr")).size() == 6);
		assertTrue(userService.getUserByEmail("testmail@mail.com") == null);
	}

	@Test
	public void PR14() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@mail.com", "admin");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "users-menu", PO_View.getTimeout());
		elementos.get(0).click();
		PO_HomeView.clickOption(driver, "/user/list", "class", "btn btn-primary");
		assertTrue(driver.findElements(By.xpath("//table[@id='usersTable']/tbody/tr")).size() == 7);
		List<WebElement> checks = driver.findElements(By.xpath("//input[@type='checkbox']"));
		// En este caso borramos el penúltimo, ya que también mostramos el administrador
		// y el programa no permitirá borrarlo
		checks.get(checks.size() - 2).click();
		driver.findElement(By.id("btnDel")).click();
		assertTrue(driver.findElements(By.xpath("//table[@id='usersTable']/tbody/tr")).size() == 6);
		assertTrue(userService.getUserByEmail("testmail6@mail.com") == null);
	}

	// En este caso borramos a los 3 primeros
	@Test
	public void PR15() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@mail.com", "admin");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "users-menu", PO_View.getTimeout());
		elementos.get(0).click();
		PO_HomeView.clickOption(driver, "/user/list", "class", "btn btn-primary");
		assertTrue(driver.findElements(By.xpath("//table[@id='usersTable']/tbody/tr")).size() == 7);
		List<WebElement> checks = driver.findElements(By.xpath("//input[@type='checkbox']"));
		checks.get(0).click();
		checks.get(1).click();
		checks.get(2).click();
		driver.findElement(By.id("btnDel")).click();
		assertTrue(driver.findElements(By.xpath("//table[@id='usersTable']/tbody/tr")).size() == 4);
		// TODO try catch para los nulls
		assertTrue(userService.getUserByEmail("testmail@mail.com") == null);
		assertTrue(userService.getUserByEmail("testmail2@email.com") == null);
		assertTrue(userService.getUserByEmail("testmail3@email.com") == null);
	}

	@Test
	public void PR16() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "testmail@mail.com", "123456");
		assertTrue(driver.findElements(By.xpath("//table[@id='createdOffers']/tbody/tr")).size() == 3);

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "offers-menu", PO_View.getTimeout());
		elementos.get(0).click();
		PO_HomeView.clickOption(driver, "/offer/add", "class", "btn btn-primary");
		PO_NewOfferView.fillForm(driver, "ItemPR16", "Test número 16", 16);
		System.out.println(driver.findElements(By.xpath("//table[@id='createdOffers']/tbody/tr")).size());
		assertTrue(driver.findElements(By.xpath("//table[@id='createdOffers']/tbody/tr")).size() == 4);
	}

	@Test
	public void PR17() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "testmail@mail.com", "123456");
		assertTrue(driver.findElements(By.xpath("//table[@id='createdOffers']/tbody/tr")).size() == 3);
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "offers-menu", PO_View.getTimeout());
		elementos.get(0).click();
		PO_HomeView.clickOption(driver, "/offer/add", "class", "btn btn-primary");
		PO_NewOfferView.fillForm(driver, "", "Test número 17", 17.17);
		driver.findElement(By.id("title")).getAttribute("validationMessage");
		PO_HomeView.clickOption(driver, "home", "class", "private-home");
		assertTrue(driver.findElements(By.xpath("//table[@id='createdOffers']/tbody/tr")).size() == 3);
	}

	@Test
	public void PR18() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "testmail@mail.com", "123456");
		assertTrue(driver.findElements(By.xpath("//table[@id='createdOffers']/tbody/tr")).size() == 3);

	}

	@Test
	public void PR19() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "testmail@mail.com", "123456");
		assertTrue(driver.findElements(By.xpath("//table[@id='createdOffers']/tbody/tr")).size() == 3);
		driver.findElements(By.linkText("Eliminar oferta")).get(0).click();
		assertTrue(driver.findElements(By.xpath("//table[@id='createdOffers']/tbody/tr")).size() == 2);

	}

	@Test
	public void PR20() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "testmail@mail.com", "123456");
		assertTrue(driver.findElements(By.xpath("//table[@id='createdOffers']/tbody/tr")).size() == 3);
		driver.findElements(By.linkText("Eliminar oferta"))
				.get(driver.findElements(By.linkText("Eliminar oferta")).size() - 1).click();
		assertTrue(driver.findElements(By.xpath("//table[@id='createdOffers']/tbody/tr")).size() == 2);

	}

	@Test
	public void PR21() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "testmail@mail.com", "123456");
		PO_HomeView.clickOption(driver, "market", "class", "btn btn-primary");
		WebElement busqueda = driver.findElement(By.name("textToSearch"));
		busqueda.click();
		busqueda.clear();
		busqueda.sendKeys("");
		driver.findElement(By.id("btnSearch")).click();
		int total = 0;
		total = driver.findElements(By.xpath("//table[@id='tableOffers']/tbody/tr")).size();
		System.out.println(total);
		driver.findElements(By.xpath("//a[contains(@class, 'page-link')]")).get(2).click();
		total += driver.findElements(By.xpath("//table[@id='tableOffers']/tbody/tr")).size();
		System.out.println(total);
		assertTrue(total == 10);
	}

	@Test
	public void PR22() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "testmail@mail.com", "123456");
		PO_HomeView.clickOption(driver, "market", "class", "btn btn-primary");
		WebElement busqueda = driver.findElement(By.name("textToSearch"));
		busqueda.click();
		busqueda.clear();
		busqueda.sendKeys("uselessssss search");
		driver.findElement(By.id("btnSearch")).click();
		assertTrue(driver.findElements(By.xpath("//table[@id='tableOffers']/tbody/tr")).size() == 0);

	}

	@Test
	public void PR23() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "testmail2@mail.com", "123456");
		assertTrue(driver.findElements(By.xpath("//table[@id='purchasedOffers']/tbody/tr")).size() == 2);
		assertTrue(
				(driver.findElement(By.xpath("//table[@id='userData']/tbody/tr[1]/td[3]"))).getText().equals("42.0"));
		PO_HomeView.clickOption(driver, "market", "class", "btn btn-primary");
		WebElement busqueda = driver.findElement(By.name("textToSearch"));
		busqueda.click();
		busqueda.clear();
		busqueda.sendKeys("iPad");
		driver.findElement(By.id("btnSearch")).click();
		WebElement oferta = driver.findElements(By.xpath("//table[@id='tableOffers']/tbody/tr")).get(0);
		oferta.findElement(By.name("buy")).click();
		PO_HomeView.clickOption(driver, "home", "class", "private-home");
		assertTrue(driver.findElements(By.xpath("//table[@id='purchasedOffers']/tbody/tr")).size() == 3);
		assertTrue(
				(driver.findElement(By.xpath("//table[@id='userData']/tbody/tr[1]/td[3]"))).getText().equals("13.0"));

	}

	@Test
	public void PR24() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "testmail2@mail.com", "123456");
		assertTrue(driver.findElements(By.xpath("//table[@id='purchasedOffers']/tbody/tr")).size() == 2);
		assertTrue(
				(driver.findElement(By.xpath("//table[@id='userData']/tbody/tr[1]/td[3]"))).getText().equals("42.0"));
		PO_HomeView.clickOption(driver, "market", "class", "btn btn-primary");
		WebElement busqueda = driver.findElement(By.name("textToSearch"));
		busqueda.click();
		busqueda.clear();
		busqueda.sendKeys("dejar");
		driver.findElement(By.id("btnSearch")).click();
		WebElement oferta = driver.findElements(By.xpath("//table[@id='tableOffers']/tbody/tr")).get(0);
		oferta.findElement(By.name("buy")).click();
		PO_HomeView.clickOption(driver, "home", "class", "private-home");
		assertTrue(driver.findElements(By.xpath("//table[@id='purchasedOffers']/tbody/tr")).size() == 3);
		assertTrue((driver.findElement(By.xpath("//table[@id='userData']/tbody/tr[1]/td[3]"))).getText().equals("0.0"));
	}

	@Test
	public void PR25() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "testmail@mail.com", "123456");
		assertTrue(
				(driver.findElement(By.xpath("//table[@id='userData']/tbody/tr[1]/td[3]"))).getText().equals("57.58"));
		PO_HomeView.clickOption(driver, "market", "class", "btn btn-primary");
		WebElement busqueda = driver.findElement(By.name("textToSearch"));
		busqueda.click();
		busqueda.clear();
		busqueda.sendKeys("Oasis");
		driver.findElement(By.id("btnSearch")).click();
		WebElement oferta = driver.findElements(By.xpath("//table[@id='tableOffers']/tbody/tr")).get(0);
		oferta.findElement(By.name("buy")).click();
		driver.findElement(By.id("alert"));

	}

	@Test
	public void PR26() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "testmail2@mail.com", "123456");
		assertTrue(driver.findElements(By.xpath("//table[@id='purchasedOffers']/tbody/tr")).size() == 2);
	}

	@Test
	public void PR27() {

		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH());

		// Página principal
		driver.navigate().to(URL);
		PO_NavView.changeIdiom(driver, "btnEnglish");
		PO_HomeView.checkWelcome(driver, PO_Properties.getENGLISH());
		PO_NavView.changeIdiom(driver, "btnSpanish");
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());

		// Opciones de usuario
		driver.navigate().to("http://localhost:8099/login");
		PO_LoginView.fillForm(driver, "testmail@mail.com", "123456");
		PO_NavView.changeIdiom(driver, "btnEnglish");
		PO_View.checkElement(driver, "text", "Private area");
		PO_NavView.changeIdiom(driver, "btnSpanish");
		PO_View.checkElement(driver, "text", "Área privada");

		// Listado de usuarios
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@mail.com", "admin");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "users-menu", PO_View.getTimeout());
		elementos.get(0).click();
		PO_HomeView.clickOption(driver, "/user/list", "class", "btn btn-primary");
		PO_NavView.changeIdiom(driver, "btnEnglish");
		PO_View.checkElement(driver, "text", "Registered users");
		PO_View.checkElement(driver, "text", "Delete selected users");
		PO_NavView.changeIdiom(driver, "btnSpanish");
		PO_View.checkElement(driver, "text", "Usuarios en el sistema");
		PO_View.checkElement(driver, "text", "Borrar usuarios seleccionados");
		
		// Alta de oferta
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "testmail@mail.com", "123456");
		List<WebElement> elementos1 = SeleniumUtils.EsperaCargaPagina(driver, "id", "offers-menu",
				PO_View.getTimeout());
		elementos1.get(0).click();
		PO_HomeView.clickOption(driver, "/offer/add", "class", "btn btn-primary");
		PO_NavView.changeIdiom(driver, "btnEnglish");
		PO_View.checkElement(driver, "text", "Offer");
		PO_View.checkElement(driver, "text", "Description");
		PO_View.checkElement(driver, "text", "Price");
		PO_NavView.changeIdiom(driver, "btnSpanish");
		PO_View.checkElement(driver, "text", "Nombre del producto");
		PO_View.checkElement(driver, "text", "Descripción");
		PO_View.checkElement(driver, "text", "Precio");

	}

	@Test
	public void PR28() {
		driver.navigate().to("http://localhost:8099/user/list");
		PO_View.checkElement(driver, "text", "Iniciar sesión");
	}

	@Test
	public void PR29() {
		driver.navigate().to("http://localhost:8099/home");
		PO_View.checkElement(driver, "text", "Iniciar sesión");
	}

	@Test
	public void PR30() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "testmail@mail.com", "123456");
		driver.navigate().to("http://localhost:8099/user/list");
		PO_View.checkElement(driver, "text", "Forbidden");
	}

}
