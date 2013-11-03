package bullshit_paper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class OnetArticleSource implements IArticleSource {

    private final String chromeUserAgent = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36";

    private List<IComment> retrieveComments(Document doc) {
        LinkedList<IComment> res = new LinkedList<>();
        for (Element e : doc.select("#forum .k_nForum_ReaderItem")) {
            String commentAuthor = e.select(".k_author").text();
            String commentContent = e.select(".k_content").text();
            res.add(new Comment(commentContent, commentAuthor, null)); // TODO: date
        }
        return res;
    }

    private IArticle retrieveArticle(String url) {
        try {
            Document doc = Jsoup.connect(url).userAgent(chromeUserAgent).get();
            
            String content = doc.select(".detail.intext").text();
            String title = doc.select("#mainTitle").text();

            return new Article(title, content, new Date(), retrieveComments(doc)); // TODO: date
        } catch (IOException ex) {
            return null;
        }
    }

    private String buildQuery(List<String> tags) {
        if (tags == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            for (String tag : tags) {
                    sb.append(URLEncoder.encode(tag, "UTF-8")).append("+");
            }
            return sb.substring(0, sb.length() - 1);
        } catch (UnsupportedEncodingException ex) {
            return null;
        }
    }

    @Override
    public List<IArticle> getArticles(List<String> tags) {
        try {
            LinkedList<IArticle> res = new LinkedList<>();
            String url = "http://plejada.onet.pl/szukaj/wiadomosci,1,1,szukaj.html?qt=" + buildQuery(tags);
            Document doc = Jsoup.connect(url).userAgent(chromeUserAgent).get();
            for (Element e : doc.select("#searchProxyMain .link a")) {
                String articleURL = e.attr("href");
                IArticle article = retrieveArticle(articleURL);
                if (article != null) {
                    res.add(article);
                }
            }
            return res;
        } catch (IOException ex) {
            return null;
        }
    }

}
