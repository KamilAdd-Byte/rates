package org.rates.presentation.view;

public class AppInfo extends Display {

    private final String title = "############# Zapytaj o wartość waluty, złota lub wskaźników giełdy ############# "+"\n" +
            "poszukaj wartości waluty wpisując wartość tabeli, kod waluty (po wybraniu opcji 2 otrzymasz małą ściągę wszystkich kodów)"+"\n"+
            "wpisując datę dowiesz sie jaką wartość dana waluta miała we wskazanym przez Ciebie dniu" + "\n"+
            "#######################################################################################"+"\n"+
            "Technology: Java-19, Jsoup, nbp-api and JAX";
    private final String version = "__________wersja 1.0____________";
    private final String author = "_______@Kamil_Sulejewski__________";
    private final String exit = "Wyjście z programu";
    private final StringBuilder appBanner = new StringBuilder("\n" +
            "\n" +
            "__________                      __              __                           .__          __          \n" +
            "\\____    /____  ______ ___.__._/  |______      |__|   ____   __  _  _______  |  |  __ ___/  |_  ____  \n" +
            "  /     /\\__  \\ \\____ <   |  |\\   __\\__  \\     |  |  /  _ \\  \\ \\/ \\/ /\\__  \\ |  | |  |  \\   __\\/ __ \\ \n" +
            " /     /_ / __ \\|  |_> >___  | |  |  / __ \\_   |  | (  <_> )  \\     /  / __ \\|  |_|  |  /|  | \\  ___/ \n" +
            "/_______ (____  /   __// ____| |__| (____  /\\__|  |  \\____/    \\/\\_/  (____  /____/____/ |__|  \\___  >\n" +
            "        \\/    \\/|__|   \\/                \\/\\______|                        \\/                      \\/ \n" +
            "\n");
    private String other = "Press 1 - search currency or 0 - close program";


    public void printTitle() {
        printValue(title);
    }

    public void printAuthor() {
        printValue(author);
    }
    public void printProgramExit() {
        printValue(exit);
    }
    public void printAppBanner() {
        printValue(appBanner.toString());
    }
    public void printDefaultValue() {
        printValue(other);
    }
    public void printVersion() {
        printValue(version);
    }
}
