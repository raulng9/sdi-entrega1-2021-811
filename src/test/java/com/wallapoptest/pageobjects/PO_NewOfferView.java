package com.wallapoptest.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_NewOfferView extends PO_NavView {
	static public void fillForm(WebDriver driver, String titlep, String descriptionp, double pricep) {
		WebElement title = driver.findElement(By.id("title"));
		title.click();
		title.clear();
		title.sendKeys(titlep);
		WebElement description = driver.findElement(By.id("description"));
		description.click();
		description.clear();
		description.sendKeys(descriptionp);
		WebElement price = driver.findElement(By.id("price"));
		price.click();
		price.clear();
		price.sendKeys(String.valueOf(pricep));
		By boton = By.id("btnAddOffer");
		driver.findElement(boton).click();
	}
}
