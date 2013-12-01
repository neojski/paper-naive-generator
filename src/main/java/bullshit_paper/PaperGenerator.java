package bullshit_paper;

import bullshit_paper_pdf.*;
import java.util.*;
import java.io.*;
import org.jsoup.nodes.*;

public class PaperGenerator
{
    public void generate(String title, List<SectionInfo> sectionInfos, FileOutputStream out) throws Exception
    {
	ArticleMixer mixer = new ArticleMixer();
	List<PaperSection> sections = new ArrayList<>();
	for (SectionInfo si : sectionInfos) {
	    List<IArticle> articles = getArticles(si.getTags());
	    List<PaperElement> elements = new ArrayList<>();
	    for (IArticle art : mixer.Mix(articles)) elements.add((Article)art);
	    if (si.getSudoku()) elements.add(new SudokuGenerator().generate());
	    sections.add(new PaperSection(si.getName(), elements, si.getHeaderColor()));
	}
	PDFRenderer renderer = new PDFRenderer();
	renderer.render(out, title, sections);	
    }
    
    private List<IArticle> getArticles(List<String> tags)
    {
        OnetArticleParser articleParser = new OnetArticleParser();
        OnetArticleProvider articleProvider = new OnetArticleProvider();

        List<IArticle> articles = new LinkedList<>();
        for (Document doc : articleProvider.getDocuments(tags)) {
            IArticle article = articleParser.parseDocument(doc);
            if (article == null) {
                continue;
            }
            if (articles.isEmpty() || !articles.get(articles.size()-1).getTitle().equals(article.getTitle())) articles.add(article);
        }
     /*   for (IArticle article : articles) {
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
        }*/
	return articles;
    }    
}
