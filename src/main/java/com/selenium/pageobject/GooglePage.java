package com.selenium.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

public class GooglePage extends BasePage {
    public GooglePage(WebDriver driver) {
        super(driver);
    }
    /* Estos son los localizadores de la página de Google */
    By acceptButton = By.id("L2AGLb");
    By searchBox = By.id("APjFqb");
    By result = By.id("main");
    By amazonProducts = By.xpath("//a[contains(@href,'amazon')]");
    Actions actions = new Actions(driver);

    /*  Acepto las cookies de google  */
    public void AcceptCookies() throws Exception {
        click(acceptButton);
    }

    /* Valido que estoy en la página de Google */
    public void ValidatePage() throws Exception {
        String titlePage = "Google";
        System.out.println("Comprobando que estoy en la página de Google:");
        validateTitle(titlePage);
    }

    /*  Busco el producto  */
    public void SearchProduct () throws Exception {
        sendKeys(searchBox, product);
    }

    /* Espero a que aparezcan los resultados de la búsqueda y accedo al enlace de Amazon */
    public void ClickOnAmazonLink () throws Exception {
        createWait(result);
        List<WebElement> productsLinks = getListOfElements(amazonProducts);
        WebElement firstProduct = getOneElementOfTheList(productsLinks,0);
        actions.moveToElement(firstProduct).perform();
        actions.click(firstProduct).perform();
    }

    /*  Me muevo a la pestaña del navegador que se ha abierto con Amazon  */
    public void changeWindow() throws Exception {
        moveToSecondWindow();
    }
}
