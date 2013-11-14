package bullshit_paper;

import java.io.InputStream;
import java.util.Scanner;

public class TestHelper {
    public static InputStream getResource(String name) {
        return Class.class.getResourceAsStream("/" + name);
    }
    public static String getResourceAsString(String name) {
        InputStream is = getResource(name);
        Scanner scanner = new Scanner(is, "UTF-8");
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNext()) {
            sb.append(scanner.nextLine()).append("\n");
        }
        return sb.toString();
    }
}
