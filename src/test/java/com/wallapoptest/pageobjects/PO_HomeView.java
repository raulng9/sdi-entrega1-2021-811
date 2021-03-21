package com.wallapoptest.pageobjects;

import org.openqa.selenium.WebDriver;

import com.wallapoptest.util.SeleniumUtils;

public class PO_HomeView extends PO_NavView {

	static public void checkWelcome(WebDriver driver, int language) {
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("welcome.message", language), getTimeout());
	}

	static public void checkChangeIdiom(WebDriver driver, String textIdiom1, String textIdiom2, int locale1,
			int locale2) {
		PO_HomeView.checkWelcome(driver, locale1);
		PO_NavView.changeIdiom(driver, textIdiom2);
		PO_HomeView.checkWelcome(driver, locale2);
		PO_NavView.changeIdiom(driver, textIdiom1);
		PO_HomeView.checkWelcome(driver, locale1);
	}

}
