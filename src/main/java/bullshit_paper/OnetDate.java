package bullshit_paper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class OnetDate {

    public static Date parse(String dateString) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        dateString = dateString.toLowerCase();
        dateString = dateString.replace(",", "");
        if (dateString.contains("dzisiaj")) {
            // don't set day
        } else if (dateString.contains("wczoraj")) {
            date.add(Calendar.DATE, -1);
        } else {
            try {
                SimpleDateFormat dayMonthFormat = new SimpleDateFormat("dd MMM", Locale.forLanguageTag("pl"));
                Calendar dayMonthCal = Calendar.getInstance();
                dayMonthCal.setTime(dayMonthFormat.parse(dateString));
                date.set(Calendar.MONTH, dayMonthCal.get(Calendar.MONTH));
                date.set(Calendar.DATE, dayMonthCal.get(Calendar.DATE));
            } catch (ParseException ex) {
                // if parsing fails we recover with current date
            }
        }
        try {
            String hourString = dateString.substring(dateString.length() - 5);
            Calendar hourMinute = Calendar.getInstance();
            hourMinute.setTime(new SimpleDateFormat("HH:mm").parse(hourString));
            date.set(Calendar.HOUR_OF_DAY, hourMinute.get(Calendar.HOUR_OF_DAY));
            date.set(Calendar.MINUTE, hourMinute.get(Calendar.MINUTE));
            return date.getTime();
        } catch (ParseException ex) {
            // if that parsing fails then current hour/minute is used
        } finally {
            return date.getTime();
        }
    }
}
