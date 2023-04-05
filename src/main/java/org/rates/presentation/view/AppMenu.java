package org.rates.presentation.view;

public class AppMenu extends Display {
    private final String option = "Wybierz opcję: " + "\n" + "<< 0 >> Wyjście z aplikacji" + "\n" + "<< 2 >> Ściąga z kodami walut (ISO 4217)" + "\n"
            + "<< 3 >> Szukaj wartości waluty" + "\n" + "<< 4 >> Pokaż 30 ostatnich cen złota";
    public void printOption() {
        displayContent(option);
    }
}
