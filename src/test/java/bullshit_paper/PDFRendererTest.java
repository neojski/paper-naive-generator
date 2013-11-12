package bullshit_paper;

import org.junit.Test;
import java.util.*;
import java.io.*;
import java.text.*;

public class PDFRendererTest {
    private List<String> getResource(int i) {
        InputStream resourceAsStream = Class.class.getResourceAsStream(new File("/test_articles", "art" + i + ".txt").toString());
        List<String> input = readStream(resourceAsStream);
        return input;
    }

    private List<String> readStream(InputStream is) {
        Scanner scanner = new Scanner(is);
        LinkedList<String> res = new LinkedList<>();
        while (scanner.hasNext()) {
            res.add(scanner.nextLine());
        }
        return res;
    }

    @Test
    public void testRender() throws IOException, ParseException {

        FileOutputStream out = new FileOutputStream("tst.pdf");
        PDFRenderer renderer = new PDFRenderer();
        List<IArticle> arts = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (int i = 1; i <= 3; ++i) {
            List<String> input = getResource(i);
            StringBuilder contentBuilder = new StringBuilder();
            for (int j = 2; j < input.size(); ++j) {
                contentBuilder.append(input.get(j));
            }
            arts.add(new Article(input.get(0), contentBuilder.toString(), dateFormat.parse(input.get(1)), null, null));
        }
        renderer.Render(out, "bullshitpaper01", arts);
    }


}
