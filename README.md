"Console application based on REST API services. Uses nbp-api (http://api.nbp.pl/) to retrieve data on the last 30 gold quotes and currency values including table code, currency code and date."

Detailed description in Polish:

# 'Rates-App' - java console application



## Currency

You can set:

type of table (A,B,C);

currency code(e.g. EUR - euro); 

and date (dates from Monday to Friday).

http://api.nbp.pl/api/exchangerates/rates/{table}/code}/{date}/


## List of currency codes

- Displaying on the console all currency codes in the form of a list (jsoup was used).


## Value of gold (last 30 quotations)

- displaying on the console the last 30 gold quotations (1g in 1000 gold sample);
- Possibility to display or save to a separate csv file, which you can name yourself.

# Technologies used:

- java 17
- jsoup
- nbp-api
- gson (convert json to POJO);
- jackson (create csv file);

# Install and start the program:

1. Download the **.jar** file from the out/artifacts folder to your computer;
2. Open the terminal of your operating system in the folder where the file was downloaded (about 8 mb);
4. type in the console the command **java -jar REST-java-project.jar currency.App** ;
5. program ready to use.

