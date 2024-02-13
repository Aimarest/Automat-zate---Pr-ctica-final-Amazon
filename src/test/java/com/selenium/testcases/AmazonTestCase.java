package com.selenium.testcases;

import com.selenium.pageobject.AmazonPage;
import com.selenium.pageobject.GooglePage;
import com.selenium.pageobject.Hooks;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


public class AmazonTestCase {

    protected WebDriver driver = Hooks.getDriver();
    GooglePage GooglePage;
    AmazonPage AmazonPage;
    @Before
    public void setUp() {
        System.out.println("Se está ejecutando el test de Amazon: " );
        GooglePage = new GooglePage(driver);
        AmazonPage = new AmazonPage(driver);
    }
    @Test
    public void TestAmazon() throws Exception {

        /* Utilizo los métodos definidos en los pageObject Google y Amazon */

        GooglePage.AcceptCookies();
        GooglePage.ValidatePage();
        GooglePage.SearchProduct();
        GooglePage.ClickOnAmazonLink();
        GooglePage.changeWindow();
        AmazonPage.validatePage();
        AmazonPage.AcceptCookies();
        AmazonPage.ShowPriceAndDate();
        AmazonPage.SearchProduct();
        AmazonPage.ShowPrimeProductsOrdered();
    }
    @After
    public void tearDown() {
        driver.quit();
        System.out.println("Ha finalizado la ejecución del test.");
    }

}
