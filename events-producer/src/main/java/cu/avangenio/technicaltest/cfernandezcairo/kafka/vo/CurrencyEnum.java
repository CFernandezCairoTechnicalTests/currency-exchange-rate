package cu.avangenio.technicaltest.cfernandezcairo.kafka.vo;

import java.util.List;

public class CurrencyEnum {
    public static List<String> currencies = List.of(
             "AED" //United Arab Emirates Dirham
            ,"YER" //Yemeni Rial
            ,"CVE" //Cape Verdean Escudo
            ,"MNT" //Mongolian Tugrik
            ,"SLL" //Sierra Leonean Leone
            ,"MVR" //Maldivian Rufiyaa
            ,"ANG" //Dutch Guilders
            ,"IRR" //Iranian Rial
            ,"IDR" //Indonesian Rupiah
            ,"SDG" //Sudanese Pound
            );

    public static int currenciesAmount = currencies.size();
}
