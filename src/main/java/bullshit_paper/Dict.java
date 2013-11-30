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

    public static void search(String needle) throws FileNotFoundException, IOException {

        URL resource = Class.class.getResource(new File("/dict", "odm").getPath());
        File file = new File(resource.getFile());
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

        long l = 0;
        long r = randomAccessFile.length();
        int i = 0;
        byte[] b = new byte[10024];
        while (l < r) {
            long c = (l + r) / 2;
            
            // calculate l1 and r1 to be the first and the last
            // characters of the line (do not include neither \n nor \r)

            // FIXME: slooow
            // find beginning of line
            long l1 = c;
            randomAccessFile.seek(l1);
            while (randomAccessFile.read() != '\n' && l1 > 0) {
                randomAccessFile.seek(--l1);
            }
            ++l1;

            // find end of line
            long r1 = c;
            randomAccessFile.seek(r1);
            while (randomAccessFile.readByte() != '\n' && r1 < randomAccessFile.length() - 1) {
                randomAccessFile.seek(++r1);
            }
            --r1;
            

            randomAccessFile.seek(l1);

            int read = randomAccessFile.read(b, 0, (int) (r1 - l1));
            String line = new String(b, 0, read, "utf-8");

            Locale locale = Locale.forLanguageTag("pl");
            Collator collator = Collator.getInstance(locale);
            if (collator.compare(line, needle) < 0) {
                l = r1 + 2; // \r\n
            } else {
                r = l1;
            }
            
            if (Pattern.matches(".*\\b" + needle + ",?\\b.*", line)) {
                System.out.println(line);
                break;
            }
        }
        // not found
    }

    public static void main(String[] args) throws IOException {
        search("kolor");
        search("łąka");
        search("niematego");
    }
}
