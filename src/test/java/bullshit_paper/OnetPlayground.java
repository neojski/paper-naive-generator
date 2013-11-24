package bullshit_paper;

import bullshit_paper_pdf.PDFRenderer;
import com.itextpdf.text.DocumentException;
import static java.lang.Math.min;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class OnetPlayground {

	@Test
    public void test() throws IOException, DocumentException, RenderingException {
        OnetArticleParser articleParser = new OnetArticleParser();
        OnetArticleProvider articleProvider = new OnetArticleProvider();

        List<IArticle> articles = new LinkedList<>();
        for (Document doc : articleProvider.getDocuments(Arrays.asList("doda"))) {
            IArticle article = articleParser.parseDocument(doc);
            if (article == null) {
                continue;
            }
            articles.add(article);
        }
        for (IArticle article : articles) {
            System.out.printf("title %s: %s\n", article.getDate(), article.getTitle());
            String content = article.getContent();

            List<IImage> images = article.getImages();
            for (IImage image : images) {
                System.out.println(image.getURL());
            }

            content = content.substring(0, min(100, content.length()));
            System.out.println("article: " + content);
            System.out.println("comments: ");
            for (IComment comment : article.getComments()) {
                content = comment.getContent();
                content = content.substring(0, min(100, content.length()));
                System.out.printf("comment (%s) %s: %s\n", comment.getDate(), comment.getAuthor(), content);
            }
            System.out.println("\n");
        }
        ArticleMixer mixer = new ArticleMixer();
	PDFRenderer renderer = new PDFRenderer();
	List<PaperElement> elements = new ArrayList<>();
	for (IArticle art : mixer.Mix(articles)) elements.add((Article)art);
	elements.add(new SudokuGenerator().generate());
	List<PaperSection> sections = new ArrayList<>();
	sections.add(new PaperSection("onet bp", elements, java.awt.Color.BLUE));
        renderer.render(new FileOutputStream("onettest.pdf"), "Onet BP", sections);
    }
}
