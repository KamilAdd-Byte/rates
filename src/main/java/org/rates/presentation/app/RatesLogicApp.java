package org.rates.presentation.app;

import org.rates.data.currency.NbpValueCurrency;
import org.rates.data.gold.NbpValueGold;
import org.rates.presentation.scrap.ScrapCurrency;
import org.rates.presentation.view.AppInfo;
import org.rates.presentation.view.AppMenu;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class RatesLogicApp implements Runnable {

    private AppMenu appMenu;
    private AppInfo appInfo;
    private static final int EXIT = 0;
    private static final int CODE = 2;
    private static final int SEARCH = 3;
    private static final int GOLD = 4;

    private static String userName;
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        getApiInfo();
        getWelcomeUser();

        int userChoice = 8;

        while (userChoice!=0){
            appInfo.printAppBanner();
            appMenu.printOption();
            userChoice = scanner.nextInt();

            switch (userChoice) {
                case SEARCH -> {
                    System.out.println(RatesLogicApp.userName + " wyszukaj interesujących Cię danych wg wzoru:");
                    try {
                        getChoiceCurrencyFields();
                        NbpValueCurrency nbpValueCurrency = new NbpValueCurrency();
                        nbpValueCurrency.getCurrencyValueOnNbpApi();
                    } catch (FileNotFoundException e) {
                        e.getMessage();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                case CODE -> {
                    ScrapCurrency scrapCurrency = new ScrapCurrency();
                    scrapCurrency.getCodeWithWiki();
                }

                case GOLD -> {
                    NbpValueGold nbpValueGold = new NbpValueGold();
                    nbpValueGold.onlyPrintGoldValueInConsole();
                }

                case EXIT -> {
                    appInfo.printProgramExit();
                    scanner.close();
                }

                default -> appInfo.printDefaultValue();
            }
        }
    }

    private void initDisplay() {
        appInfo = new AppInfo();
        appMenu = new AppMenu();
    }

    private void getApiInfo() {
        initDisplay();
        appInfo.printAppBanner();
        appInfo.printTitle();
        appInfo.printAuthor();
        appInfo.printVersion();
    }

    private void getWelcomeUser() {
        System.out.println("Jak się do Ciebie zwracać?");
        String userName = scanner.nextLine();
        RatesLogicApp.userName = userName;
    }

    private void getChoiceCurrencyFields() {
       //select table
        System.out.println("Krok 1: Podaj wartość tabeli");
        String outputD = "";
        /**
         * && ->
         */
        while (!outputD.equals("A") && !outputD.equals("B") && !outputD.equals("C")){
            System.out.println("Możliwe wartości:  A   B   C" + "  *możesz podać z małej litery*");
            System.out.println();
            String outputTable = scanner.next().toUpperCase();
            outputD=outputTable;
        }
        NbpValueCurrency.setTable(outputD.toUpperCase());

        //select currency
        System.out.println("Krok 2: Podaj trzy literowy KOD waluty");
        String output = "";

        while (output.length()!=3) {
            System.out.println("Podaj poprawną wartość: wzór -> ABC" + "  *możesz podać z małej litery*");
            System.out.println();
            String outputCurrency = scanner.next();
            output = outputCurrency;
        }
        NbpValueCurrency.setCurrency(output.toUpperCase());

        //select date
        System.out.println("Krok 3: Podaj datę by otrzymać wartość wybranej przez siebie waluty: <<||" + NbpValueCurrency.getCurrency() + "||>> wzór -> YYYY-mm-DD");
        String outputDate = scanner.next();
        LocalDate localDate = LocalDate.parse(outputDate);

        NbpValueCurrency.setDate(localDate);
        System.out.println("Date " + localDate + "given by " + userName);
        scanner.nextLine();

    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        RatesLogicApp.userName = userName;
    }
}
