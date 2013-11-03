package bullshit_paper;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PudelekArticleSource implements IArticleSource{
	private final String chromeUserAgent = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36";
	private final String searchForm = "http://www.pudelek.pl/szukaj/";
	
	@Override
	public List<IArticle> getArticles(List<String> tags) {
		LinkedList<IArticle> articles = new LinkedList<IArticle>();
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
				Article article = extractArticle(link);
				if(article!=null){
					articles.add(article);
				}
			}
		} catch (IOException e1) {
			return null;
		}
		
		return articles;
	}
	
	private Article extractArticle(String url){
		try {
			Document document = Jsoup.connect(url).userAgent(chromeUserAgent).get();
			String title = document.title().substring(10);
			String content = document.body().select("div[class=single-entry-text bbtext]").text();
			List<IComment> comments = new LinkedList<IComment>();
			
			LinkedList<String> commentsContent = new LinkedList<String>();
			
			for(Element e: document.body().select("div[class=comments-popular]").select("div[class=comment-text]")){
				commentsContent.add(e.text());	
			}
			
			int index=0;
			
			for(Element e: document.body().select("div[class=comments-popular]").select("span.comment-author")){
				String commentAuthor = e.text();
				comments.add(new Comment(commentsContent.get(index++),commentAuthor,new Date()));
			}
			
			return new Article(title, content, new Date(), comments);
		} catch (IOException e) {
			return null;
		}
	}
}
