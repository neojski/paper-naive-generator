package bullshit_paper;

import org.junit.Test;
import com.itextpdf.text.ImgCCITT;
import java.util.*;
import java.io.*;
import java.net.URL;
import java.text.*;

public class PDFRendererTest {
    private List<String> getResource(int i) throws IOException {
        InputStream resourceAsStream = new FileInputStream(new File("test_articles", "art" + i + ".txt").toString());
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
        for (int i = 1; i <= 5; ++i) {
            List<String> input = getResource(i);
            StringBuilder contentBuilder = new StringBuilder();
            int imgCount = Integer.parseInt(input.get(2));
            List<IImage> images = new ArrayList<>();
            for (int j=0; j<imgCount; ++j) images.add(new Image(new URL(input.get(3+j))));
            for (int j = 3+imgCount; j < input.size(); ++j) {
                contentBuilder.append(input.get(j));
            }
            arts.add(new Article(input.get(0), contentBuilder.toString(), dateFormat.parse(input.get(1)), null, images));
        }
        renderer.Render(out, "bullshitpaper01", arts);
    }


}
