package com.selenium.pageobject;

import java.util.*;
import com.selenium.utils.CompletProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.selenium.utils.CompletProduct.*;
import static com.selenium.utils.Common.*;

public class AmazonPage extends BasePage {

	public AmazonPage(WebDriver driver) {
		super(driver);

	}
	/* Estos son los localizadores:   */

	By amazonCookies = By.id("sp-cc-accept");
	By price = By.xpath("//div[@id='corePrice_feature_div']/descendant::span[@class='a-offscreen']");
	By spanText = By.xpath("//div[@id='mir-layout-DELIVERY_BLOCK-slot-PRIMARY_DELIVERY_MESSAGE_LARGE']/span");
	By inputSearch = By.id("twotabsearchtextbox");
	By searchButton = By.id("nav-search-submit-button");
	By ProductsList = By.xpath("//div[@data-component-type='s-search-result']/descendant::i[@aria-label='Amazon Prime']");
	By prices = By.xpath(  "//div[@data-component-type='s-search-result']/descendant::i[@aria-label='Amazon Prime']/ancestor::div[4]/div[@data-cy='price-recipe']/descendant::span[@aria-hidden='true']/span");
	By titleProducts = By.xpath("//div[@data-component-type='s-search-result']/descendant::i[@aria-label='Amazon Prime']/ancestor::div[7]/descendant::span[contains(@class, 'a-text-normal')]");
	String attribute = "data-csa-c-delivery-time";
	By amazonTitle = By.id("nav-logo-sprites");
	String amazon = "Amazon";

	/*  Valido que estoy en la pestaña que quiero, se imprime por pantalla "Title Matched"  */
	public void validatePage() throws Exception {
		System.out.println("Comprobando que estoy en la página de Amazon: " );
		ValidateElementOfAPage(amazonTitle,amazon);
	}

	/*  Acepto cookies de Amazon  */
	public void AcceptCookies() throws Exception {
		waitToClick(amazonCookies);
	}

	/*  Recupero el precio y la fecha de entrega del producto e Imprimo por pantalla el precio y la fecha de entrega del producto */
	public void ShowPriceAndDate() {

		String priceProduct = getInnerText(price);
		String date = getAtribute(spanText, attribute);
		System.out.println("Paso 4: Imprime por pantalla el precio y la fecha de entrega: ");
		System.out.println("El precio del producto es: " + priceProduct);
		System.out.println("La fecha de entrega es: " + date);
	}

	/*   Vuelvo a buscar en el buscador general el producto  */
	public void SearchProduct() throws Exception {
		System.out.println("Paso 5: En el buscador general vuelvo a buscar el producto. ");
		sendKeys(inputSearch, product);
		click(searchButton);
	}
	public void ShowPrimeProductsOrdered() throws Exception {
		/*  Filtro por productos PRIME, eligiendo los productos que tienen esa etiqueta  */

		List<WebElement> products = getListOfElements(ProductsList);
		List<WebElement> primeProductsPrices = getListOfElements(prices);
		List<WebElement> primeProductsText = getListOfElements(titleProducts);
		ArrayList<Double> allPrices = getDoublePrimePrices(primeProductsPrices);
		ArrayList<String> titleOFProducts = getTextOfProducts(primeProductsText);

		/*   Creo un array de objetos, con los atributos título y precio */

		CompletProduct[] objectProduct = createCompleteProduct(titleOFProducts, allPrices);

		/* Ordeno el array de objetos por precio de producto, de menor a mayor  */

		CompletProduct[] objectProductOrdered = orderByPrice(objectProduct);
		CompletProduct [] objectProductWithoutRepeats = DeleteCopies(objectProductOrdered);

		/*   Los muestro por consola  */

		showProducts(objectProductWithoutRepeats);
	}
}



