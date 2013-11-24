package bullshit_paper.module.article;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class OnetArticleProvider implements IArticleProvider{
    
    private final String chromeUserAgent = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36";
    private final String searchURL = "http://plejada.onet.pl/szukaj/wiadomosci,1,1,szukaj.html?qt=";
    
    private String buildQuery(List<String> tags) {
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
    public List<Document> getDocuments(List<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return null;
        }
        try {
            LinkedList<Document> res = new LinkedList<>();
            String url = searchURL + buildQuery(tags);
            Document doc = Jsoup.connect(url).userAgent(chromeUserAgent).get();
            for (Element e : doc.select("#searchProxyMain .link a")) {
                String articleURL = e.attr("href");
                
                Document article = Jsoup.connect(articleURL).userAgent(chromeUserAgent).get();
                res.add(article);
            }
            return res;
        } catch (IOException ex) {
            return null;
        }
    }
}
