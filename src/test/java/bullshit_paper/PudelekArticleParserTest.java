package bullshit_paper;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import static org.junit.Assert.*;

public class PudelekArticleParserTest {

    private IArticle getExampleDoc() {
        Document doc = getExampleDocument();
        PudelekArticleParser articleParser = new PudelekArticleParser();
        IArticle article = articleParser.parseDocument(doc);
        return article;
    }

    private Document getExampleDocument() {
        String html = TestHelper.getResourceAsString(new File("test_articles", "pudelek.html").getPath());
        Document doc = Jsoup.parse(html);
        return doc;
    }

    private Date parseDate(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        Date expected = null;
        try {
            expected = format.parse(dateStr);
        } catch (ParseException ex) {
            fail("date parse error");
        }
        return expected;
    }

    @Test
    public void testNullDocuments() {
        PudelekArticleParser articleParser = new PudelekArticleParser();
        IArticle article = articleParser.parseDocument(null);
        assertNull(article);
    }

    @Test
    public void testWrongHTML() {
        String html = "<html></html>";
        Document doc = Jsoup.parse(html);

        PudelekArticleParser articleParser = new PudelekArticleParser();
        IArticle article = articleParser.parseDocument(doc);

        assertNull(article);
    }

    @Test
    public void testArticleData() {
        IArticle article = getExampleDoc();
        assertEquals("Lis: \"Premier MUSI ZARABIAĆ 50 TYSIĘCY! Zarabia mniej niż reporter \"Faktów\"!\"", article.getTitle());
        assertThat(article.getContent(), startsWith("Sprawozdania partii politycznych z wydawania pieniędzy z budżetu państwa wywołały ogromne emocje."));
        assertThat(article.getContent(), endsWith("Bo decydowali, nie kupimy samolotu dla VIP-ów, bo będzie to uznane za arogancję i \"Super Express\" o tym napisze; nie podnosimy pensji najważniejszym osobom w państwie, bo co ludzie powiedzą."));
	assertEquals(parseDate("22.06.2013"), article.getDate());
	}

    @Test
    public void testCommentsData() {
        IArticle article = getExampleDoc();
        List<IComment> comments = article.getComments();

        assertEquals(5, comments.size());
        IComment comment = comments.get(2);
        assertEquals("gość", comment.getAuthor());
        assertEquals("Sam chce byc premierem czy jak ?! I zarabiac tyle ??", comment.getContent());
        assertEquals(parseDate("22.06.2013"), comment.getDate());
    }

}
