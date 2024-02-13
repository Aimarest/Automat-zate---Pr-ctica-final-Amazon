package com.selenium.utils;
import java.util.ArrayList;


public class CompletProduct {

    String title;
    Double price;

    public CompletProduct(String productTitle, Double productPrice) {
        this.title = productTitle;
        this.price = productPrice;
    }
    public CompletProduct(){
        this.title = "";
        this.price = 0.0;
    }

    public Double getPrice () {
        return price;
    }
    public String getTitle() {
        return title;
    }

    /* Método que sirve para crear un array de productos, a partir de la lista de títulos y la lista de precios */
    public static CompletProduct [] createCompleteProduct( ArrayList<String> ListOfStrings, ArrayList<Double> ListOfDoubles) throws Exception {
        try {
            CompletProduct [] object = new CompletProduct[ListOfStrings.size()];

            for (int i = 0; i <= (ListOfStrings.size() -1); i++) {
                String title = ListOfStrings.get(i);
                Double productPrice = ListOfDoubles.get(i);
                object[i] = new CompletProduct(title, productPrice);
            }
            return object;
        }catch (Exception e) {
            throw new Exception("Is not possible to create an array of completeProducts");
        }
    }

    /* Método que sirve para ordenar los productos del array por precio, de menor a mayor. Devuelve el array ordenado. */
    public static CompletProduct [] orderByPrice (CompletProduct [] args) {
        System.out.println("Paso 7: Ordena por precio de más bajo a más alto");

        for (int i = 0; i <= args.length ; i++) {
            for (int l = (i + 1); l < args.length; l++) {
                Double price1 = args[i].getPrice();
                Double price2 = args[l].getPrice();
                if (price2 < price1) {
                    CompletProduct aux;
                    aux = args[i];
                    args[i] = args[l];
                    args[l] = aux;
                }
            }
        }
        return args;
    }
    /* Método que sirve para eliminar los productos repetidos del array */
    public static CompletProduct [] DeleteCopies (CompletProduct [] args) {
        System.out.println("Elimino los productos repetidos");
        for (int i = 0; i < args.length; i++) {
            for (int j = 1; j < args.length -1; j ++) {
                String firstTitle = args[i].getTitle();
                String secondTitle = args[j].getTitle();
                if((i != j) && (firstTitle == secondTitle)){
                    args[j] = args[j + 1];
                }
            }
        }
        return args;
    }
    /* Método que sirve para mostrar los productos que hay en el array */
    public static void showProducts (CompletProduct [] products) {
        System.out.println("Paso 8: Imprime por pantalla el nombre de los productos de la primera pantalla y el precio");
        for (CompletProduct obj:products) {
            System.out.println("El producto se llama " + obj.getTitle() + ". Su precio es: " + obj.getPrice() + " euros");
        }
    }
}
