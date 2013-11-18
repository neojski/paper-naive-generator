package bullshit_paper;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PudelekArticleParser implements IArticleParser {
	
        @Override
	public Article parseDocument(Document document){
		if (document == null) {
			return null;
		}
        
		String title = document.title();
		if(title.length()>=10){
			title = title.substring(10);
		}
		else
			title = null;

		String content = document.body().select("div[class=single-entry-text bbtext]").text();
		String date = document.body().select("span.time").text();

		Date formatedDate = null;
		if(date.split(" ").length>1){
			formatedDate = parseDate(date.split(" ")[1]);
		}
		
		if (content.isEmpty() || title.isEmpty() || date.isEmpty()) {
            		return null;
        	}
		
		return new Article(title, content, formatedDate, extractComments(document), extractImages(document));
	}
	
	private Date parseDate(String date){
		SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
		try {
			return format.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	private List<IImage> extractImages(Document document){
		List<IImage> images = new LinkedList<IImage>();
		
		for(Element e: document.body().select("div[class=single-entry-text bbtext] span.photo img")){
			try {
				Image image = new Image(new URL(e.attr("src")));
				images.add(image);
			} catch (IOException ex) {
			}
		}
		
		return images;
	}

	private List<IComment> extractComments(Document document){
		List<IComment> comments = new LinkedList<IComment>();
		
		LinkedList<String> commentContents = new LinkedList<String>();
		LinkedList<String> commentDates = new LinkedList<String>();
		
		Elements tmp = document.body().select("div[class=comments-popular]");
		
		for(Element e: tmp.select("div[class=comment-text]")){
			commentContents.add(e.text());	
		}
		
		for(Element e: tmp.select("span.comment-date")){
			commentDates.add(e.text().split(" ")[0]);	
		}
		
		int index=0;
		
		for(Element e: tmp.select("span.comment-author")){
			String commentAuthor = e.text();
			comments.add(new Comment(commentContents.get(index),commentAuthor, parseDate(commentDates.get(index++))));
		}	
		
		return comments;
	}
}
