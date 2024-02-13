package com.selenium.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import static com.selenium.utils.Common.getSublist;

/* Esta clase contiene los métodos en los que se utilizan los comandos de Selenium, luego éstos serán utilizados en los diferentes pageObjects. */

public class BasePage {

    protected WebDriver driver;

    /* Declaro el producto en una variable para que pueda ser cambiado por otro. */
    String product = "compresor electrico";

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void click(By locator) throws Exception {
        try {
            driver.findElement(locator).click();
        }catch (Exception e) {
            throw new Exception("Could not click on the button with the locator " +  locator );
        }
    }

    public void validateTitle(String title) throws Exception {
        try {
            String titlePage = driver.getTitle();
            String expectedTitle = title;

            if (titlePage.equalsIgnoreCase(expectedTitle)) {
                System.out.println("Title Matched");

            } else System.out.println("Title didn't match");
        } catch (Exception e) {
            throw new Exception("The title page is not the expected");
        }
    }
    public void ValidateElementOfAPage(By locator, String stringSearched) throws Exception {
        try {
            WebElement element = driver.findElement(locator);
            String name = element.getAttribute("aria-label");
            if (name.contains(stringSearched)){
                System.out.println("The string searched matched with the text of the element at the current page ");
            } else System.out.println("The string searched didn't match with the text of the element at the current page");
        }catch (Exception e) {
            throw new Exception("The element wich we try to compare is not enable yet");
        }

    }
    public void waitToClick(By locator) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement element = driver.findElement(locator);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        }catch (Exception e) {
            throw new Exception("The element is not clickable yet");
        }

    }
   public void createWait(By locator) {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
       WebElement element = driver.findElement(locator);
       wait.until(ExpectedConditions.visibilityOf(element));
   }
    public void sendKeys(WebElement input, String text) throws Exception {

        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            wait.until(ExpectedConditions.visibilityOf(input));
            input.clear();
            input.sendKeys(text);
            input.submit();
        }catch (Exception e) {
            throw new Exception("Is not possible write and send text at the element with this element , is not enabled yet");
            }
        }
    public void sendKeys(By locator, String text) throws Exception {
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            WebElement input = driver.findElement(locator);
            input.clear();
            input.sendKeys(text);
            input.submit();
        }catch (Exception e) {
            throw new Exception("Is not possible write and send text at the element with this element , is not enabled yet");
        }
    }
    public WebElement getOneElementOfTheList(List<WebElement> list, int position) throws Exception {
        try{
            return list.get(position);
        }catch (Exception e) {
            throw new Exception("Is not possible get this element of the list");
        }
    }

    public ArrayList<Double> getDoublePrimePrices(List <WebElement> elements) throws Exception {
        try {
            List<List<WebElement>> spansOfMoney = getSublist(elements, 3);
            ArrayList<Double> allPrices = new ArrayList<>();
            Double completePrice;

            for (List<WebElement> spanMoney : spansOfMoney) {

                Double euros = 0.0;
                Double cents = 0.0;

                for (int j = 0; j < spanMoney.size(); j++) {
                    String money = spanMoney.get(j).getText();
                    if (j == 0) {
                        euros = new Double(money);
                    } else if (j == 1) {
                        cents = new Double(money) / 100;
                    }
                }
                completePrice = euros + cents;
                allPrices.add(completePrice);
            }
            return allPrices;
        }catch (Exception e) {
            throw new Exception("Is not possible get the double valor of the prices");
        }
    }
    public List<WebElement> getListOfElements (By locator) {
        List<WebElement> oneList = driver.findElements(locator);
        return oneList;
    };
    public String getInnerText(By locator) {
        WebElement element = driver.findElement(locator);
        String textElement = element.getAttribute("innerText");
        return textElement;
    }
    public String getAtribute(By locator, String atributte) {
        WebElement element = driver.findElement(locator);
        String textAtributte = element.getAttribute(atributte);
        return textAtributte;
    }
    public void moveToSecondWindow () throws Exception {
        try {
            //Obtener todas las ventanas
            Set<String> allHandles = driver.getWindowHandles();

            //Obtener la ventana actual
            String currentWindowHandle = allHandles.iterator().next();

            //Eliminarla
            allHandles.remove(allHandles.iterator().next());

            //Obtener la segunda ventana
            String lastHandle = allHandles.iterator().next();

            //Cambiar a la segunda ventana
            driver.switchTo().window(lastHandle);

        }catch (Exception e) {
            throw new Exception("Is not possible move to the second window");
        }
    }
    public void click(WebElement element) throws Exception {
        try {
            element.click();
        }catch (Exception e) {
            throw new Exception("Could not click on the button " +  element);
        }
    }
    public boolean isDisplayed(By locator) throws Exception {
        Boolean isDisplayed = false;
        try {
            WebDriverWait wait = new WebDriverWait( driver,  Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            if(driver.findElement(locator).isDisplayed()){
                isDisplayed = true;
            }
        }catch (Exception e) {
            throw new Exception("The element with this locator " + locator + " is not displayed yet");
        }
        return isDisplayed;
    }
    public boolean isDisplayed(WebElement element) throws Exception {
        try{
            return element.isDisplayed();
        }catch (Exception e) {
            throw new Exception("The element is not displayed yet");
        }
    }
    public boolean isClickable(By locator) throws Exception{
        try {
            return driver.findElement(locator).isEnabled();
        }catch (Exception e) {
            throw new Exception("The element with this locator " + locator + " is not enabled yet");
        }
    }
    public String getTitle() throws Exception {
        try {
            return driver.getTitle();
        }catch (Exception e) {
            throw new Exception("The title page is not enabled yet");
        }
    }
    public String getTextByLocator(By locator) {
        return  driver.findElement(locator).getText();
    }
    public static void waitForElement(WebDriver driver, WebElement element) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public List<WebElement> getList(By locator) {
        return driver.findElements(locator);
    }
}
