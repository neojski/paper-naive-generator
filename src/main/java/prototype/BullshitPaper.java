/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototype;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

class Retrieve {

    private final String chromeUserAgent = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36";

    String getArticle(String url) throws IOException {
        Document doc = Jsoup.connect(url).userAgent(chromeUserAgent).get();

        // article
        String content = doc.select(".detail.intext").text();
        return content;
    }

    List<String> getComments(String url) throws IOException {
        Document doc = Jsoup.connect(url).userAgent(chromeUserAgent).get();

        LinkedList<String> res = new LinkedList<String>();
        for (Element e : doc.select("#forum .k_nForum_ReaderItem")) {
            String commentAuthor = e.select(".k_author").text();
            String commentContent = e.select(".k_content").text();
            res.add(commentAuthor + "\n" + commentContent);
        }
        return res;
    }

    List<String> getLinks(String tag) throws UnsupportedEncodingException, IOException {
        String url = "http://plejada.onet.pl/szukaj/wiadomosci,1,1,szukaj.html?qt=" + URLEncoder.encode(tag, "UTF-8");
        Document doc = Jsoup.connect(url).userAgent(chromeUserAgent).get();
        LinkedList<String> res = new LinkedList<String>();
        for (Element e : doc.select("#searchProxyMain .link a")) {
            res.add(e.attr("href"));
        }
        return res;
    }
}

public class BullshitPaper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, DocumentException {
        Retrieve retrieve = new Retrieve();
        List<String> links = retrieve.getLinks("smoleńsk");

        String data = "";
        for (String link : links) {
            data += link + "\n";
        }

        String firstUrl = links.get(0);
        data += "\nThe article under the first link is:\n";
        data += retrieve.getArticle(firstUrl) + "\n===\n";

        data += "\nThe the comments:\n";
        for (String comment : retrieve.getComments(firstUrl)) {
            data += comment + "\n";
        }
        CreatePdf.fromString(data);
    }

}
