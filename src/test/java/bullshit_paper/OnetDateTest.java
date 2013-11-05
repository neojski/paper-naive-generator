package bullshit_paper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class OnetDateTest {

    private void assertDateEquals(Date a, Date b) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'h:m:ss");
        assertEquals(simpleDateFormat.format(a), simpleDateFormat.format(b));
    }

    @Test
    public void testToday() {
        String today = "today 18:35";
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 18);
        date.set(Calendar.MINUTE, 35);

        assertDateEquals(date.getTime(), OnetDate.parse(today));
    }

    @Test
    public void testYesterday() {
        String today = "wczoraj 06:35";
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, -1);
        date.set(Calendar.HOUR_OF_DAY, 6); // wuut. 18 też przechodzi
        date.set(Calendar.MINUTE, 35);

        assertDateEquals(date.getTime(), OnetDate.parse(today));
    }

    @Test
    public void testCustom1() {
        String today = "31 paź, 20:28";
        Calendar date = Calendar.getInstance();
        date.set(Calendar.MONTH, Calendar.OCTOBER);
        date.set(Calendar.DAY_OF_MONTH, 31);
        date.set(Calendar.HOUR_OF_DAY, 20);
        date.set(Calendar.MINUTE, 28);

        assertDateEquals(date.getTime(), OnetDate.parse(today));
    }

    @Test
    public void testCustom2() {
        String today = "8 sie, 09:51";
        Calendar date = Calendar.getInstance();
        date.set(Calendar.MONTH, Calendar.AUGUST);
        date.set(Calendar.DAY_OF_MONTH, 8);
        date.set(Calendar.HOUR_OF_DAY, 9);
        date.set(Calendar.MINUTE, 51);

        assertDateEquals(date.getTime(), OnetDate.parse(today));
    }
}
