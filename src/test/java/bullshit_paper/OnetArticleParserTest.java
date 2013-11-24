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

public class OnetArticleParserTest {

    private IArticle getExampleDoc() {
        Document doc = getExampleDocument();
        OnetArticleParser articleParser = new OnetArticleParser();
        IArticle article = articleParser.parseDocument(doc);
        return article;
    }

    private Document getExampleDocument() {
        String html = TestHelper.getResourceAsString(new File("test_articles", "onet.html").getPath());
        Document doc = Jsoup.parse(html);
        return doc;
    }

    private Date parseDate(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM y HH:mm");
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
        OnetArticleParser articleParser = new OnetArticleParser();
        IArticle article = articleParser.parseDocument(null);
        assertNull(article);
    }

    @Test
    public void testWrongHTML() {
        String html = "<html></html>";
        Document doc = Jsoup.parse(html);

        OnetArticleParser articleParser = new OnetArticleParser();
        IArticle article = articleParser.parseDocument(doc);

        assertNull(article);
    }

    @Test
    public void testArticleData() {
        IArticle article = getExampleDoc();

        assertEquals("Kevin Smith o nowym kostiumie Batmana: fani będą w szoku", article.getTitle());
        assertThat(article.getContent(), startsWith("Reżyser Kevin Smith widział już kostium Batmana"));
        assertThat(article.getContent(), endsWith("Reżyserią filmu zajmie się Zack Snyder. AS"));
        assertEquals(parseDate("12 November 2013 13:32"), article.getDate());
    }

    @Test
    public void testCommentsData() {
        IArticle article = getExampleDoc();
        List<IComment> comments = article.getComments();

        assertEquals(2, comments.size());

        IComment comment = comments.get(0);
        assertEquals("ja", comment.getAuthor());
        assertEquals("Ci co przeżywają że Affleck będzie batmanem do debile.", comment.getContent());
        assertEquals(parseDate("12 November 2013 18:40"), comment.getDate());
    }
}
