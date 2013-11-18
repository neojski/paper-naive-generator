package bullshit_paper;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PudelekArticleProvider implements IArticleProvider{
	private final String chromeUserAgent = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36";
	private final String searchForm = "http://www.pudelek.pl/szukaj";
	
        @Override
	public List<Document> getDocuments(List<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return null;
        }
        
		LinkedList<Document> documents = new LinkedList<Document>();
		StringBuilder query = new StringBuilder();
		
		for(String tag: tags) query.append(tag).append("+");
		
		if(query.length()>0){
			query.deleteCharAt(query.length()-1);
		}
		
		Document document;
		try {
			document = Jsoup.connect(searchForm + query.toString()).userAgent(chromeUserAgent).get();
			HashSet<String> links = new HashSet<String>();

			for (Element element : document.body().select("div[class=articles clearfix] a")){
				if(element.attr("href").contains("/tag/")){
					continue;
				}
				if(element.attr("href").contains("/#add_comment")){
					continue;
				}
				links.add(element.attr("href"));
			}
			
			for(String link: links){
				if(link != null){
					Document article = Jsoup.connect(link).userAgent(chromeUserAgent).get();
					documents.add(article);
				}
			}
		} catch (IOException e1) {
			return null;
		}
		
		return documents;
	}
}
