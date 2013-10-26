/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bp;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


class Retrieve{
    private final String chromeUserAgent = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36";
    String get(String name) throws IOException{
        String url = "http://podroze.onet.pl/ciekawe/odwiedz-sasiada-bialorus-to-prawdziwa-egzotyka/334mt";
        
        Document doc = Jsoup.connect(url).userAgent(chromeUserAgent).get();
        
        // article
        String content = doc.select(".detail.intext").text();
        System.out.println(content);
        
        // comments
        for(Element e : doc.select("#forum .k_nForum_ReaderItem")){
            String commentAuthor = e.select(".k_author").text();
            String commentContent = e.select(".k_content").text();
            System.out.println(commentAuthor + "\n" + commentContent + "\n\n");
        }
        return null;
    }
}

public class BullshitPaper {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws IOException {
      new Retrieve().get("smolensk");
  }
  
}
