package bullshit_paper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class OnetDate {
    private static Integer getMonth(String month){
        String[] months = {"sty", "lut", "mar", "kwi", "maj", "cze", "lip", "sie", "wrz", "paź", "lis", "gru"};
        return Arrays.asList(months).indexOf(month);
    }

    public static Date parse(String dateString) {
        Calendar date = Calendar.getInstance();
        if (dateString.contains("wczoraj")) {
            date.add(Calendar.DATE, -1);
        } else if(dateString.contains(",")) {
            String[] dayMonth = dateString.substring(0, dateString.indexOf(",")).split(" ");

            Integer day = Integer.parseInt(dayMonth[0], 10);
            
            date.set(Calendar.MONTH, getMonth(dayMonth[1]));
            date.set(Calendar.DATE, day);
            
//            Locale locale = Locale.forLanguageTag("pl");
        }
            
        try {
            String hourString = dateString.substring(dateString.length() - 5);
            Date parse = new SimpleDateFormat("HH:mm").parse(hourString);
            
            date.set(Calendar.HOUR_OF_DAY, parse.getHours());
            date.set(Calendar.MINUTE, parse.getMinutes());
            
            return date.getTime();
        } catch (ParseException ex) {
            // if that parsing fails then current hour/minute is used
        } finally {
            return date.getTime();
        }
    }
}
