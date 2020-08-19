package com.code.test.altimetrik.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {

    public static final String BASE_URL = "https://itunes.apple.com";

    public static final String SEARCH = BASE_URL+"/search";

    public static String formattedDate(String dateString) throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse(dateString);
        return fmt.format(date);
    }

}
