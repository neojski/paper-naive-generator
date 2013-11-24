package bullshit_paper;

import bullshit_paper_pdf.PDFRenderer;
import com.itextpdf.text.DocumentException;
import static java.lang.Math.min;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class OnetPlayground {

	@Test
    public void test() throws IOException, DocumentException, RenderingException {

        ArticleMixer mixer = new ArticleMixer();
	PDFRenderer renderer = new PDFRenderer();
	SectionInfo[] sectionInfos = new SectionInfo[] { 
	    new SectionInfo(Arrays.asList("doda"), "Doda", java.awt.Color.PINK, true),
	    new SectionInfo(Arrays.asList("Smoleńsk"), "Smoleńsk", java.awt.Color.BLACK, false),
	    new SectionInfo(Arrays.asList("śnieg"), "Śnieg", java.awt.Color.BLUE, true) };
	List<PaperSection> sections = new ArrayList<>();
	for (SectionInfo si : sectionInfos) {
	    List<IArticle> articles = getArticles(si._tags);
	    List<PaperElement> elements = new ArrayList<>();
	    for (IArticle art : mixer.Mix(articles)) elements.add((Article)art);
	    if (si._sudoku) elements.add(new SudokuGenerator().generate());
	    sections.add(new PaperSection(si._title, elements, si._headerColor));
	}
	renderer.render(new FileOutputStream("onettest.pdf"), "Onet BP", sections);
    }
    
    private static class SectionInfo
    {
	public List<String> _tags;
	public String _title;
	public java.awt.Color _headerColor;
	public boolean _sudoku;
	
	public SectionInfo(List<String> tags, String title, java.awt.Color headerColor, boolean sudoku)
	{
	    _tags = tags;
	    _title = title;
	    _headerColor = headerColor;
	    _sudoku = sudoku;
	}
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
        for (IArticle article : articles) {
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
        }
	return articles;
    }
}