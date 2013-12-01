package bullshit_paper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.text.Collator;
import java.util.Locale;
import java.util.regex.Pattern;

public class Dict {
    
    private static String odmSearch(String needle) throws IOException {
        URL resource = Class.class.getResource(new File("/dict", "odm").getPath());
        File file = new File(resource.getFile());
        return search(needle, file, ".*\\b" + needle + "\\b(,.*|$)");
    }
    
    private static String mapSearch(String needle) throws IOException {
        URL resource = Class.class.getResource(new File("/dict", "map").getPath());
        File file = new File(resource.getFile());
        return search(needle, file, needle + " :.*");
    }
    
    public static String getType(String needle) throws IOException {
        String line = odmSearch(needle);
        if (line == null) {
            return null;
        }
        String[] words = line.split(", ");
        int p = words.length - 1;
        while (!words[p].equals(needle)) {
            p--;
        }
        for (int i = p; i >= 0; i--) {
            String type = mapSearch(words[i]);
            if (type != null) {
                return type;
            }
        }
        return null;
    }

    /*
     * This code assumes that you're using unix endlines (\n) and utf8 encoding
     */
    public static String search(String needle, File file, String pattern) throws FileNotFoundException, IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

        long l = 0;
        long r = randomAccessFile.length();
        byte[] b = new byte[10024];
        while (l < r) {
            long c = (l + r) / 2;
            
            // calculate l1 and r1 to be the first and the last
            // characters of the line (do not include neither \n)

            // FIXME: slooow
            // find beginning of line
            long l1 = c;
            randomAccessFile.seek(l1);
            while (randomAccessFile.read() != '\n' && l1 > 0) {
                randomAccessFile.seek(--l1);
            }
            if (l1 > 0) {
                ++l1;
            }

            // find end of line
            long r1 = c+1;
            randomAccessFile.seek(r1);
            while (randomAccessFile.read() != '\n' && r1 < randomAccessFile.length()) {
                randomAccessFile.seek(++r1);
            }
            
            randomAccessFile.seek(l1);

            int len = (int) (r1-l1);
            if (len <= 0) {
                return null;
            }
            
            randomAccessFile.readFully(b, 0, len);
            String line = new String(b, 0, len, "utf-8");
            
            Locale locale = Locale.forLanguageTag("pl");
            Collator collator = Collator.getInstance(locale);
            if (collator.compare(line, needle) < 0) {
                l = r1+1;
            } else {
                r = l1;
            }
            if (Pattern.matches(pattern, line)) {
                return line;
            }
        }
        return null;
    }
    
    public static void main(String[] args) throws IOException {
//        System.out.println(mapSearch("a"));
//        System.out.println(mapSearch("łąka"));
//        System.out.println(mapSearch("żyzny"));
        
//        System.out.println(odmSearch("kolor"));
//        System.out.println(odmSearch("łąka"));
//        System.out.println(odmSearch("niematego"));
        System.out.println(getType("a"));
    }
}
