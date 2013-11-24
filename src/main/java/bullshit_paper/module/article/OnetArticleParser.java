package bullshit_paper.module.article;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class OnetArticleParser implements IArticleParser {
    // replace non-braking space with space
    private String normalizeWhitespace(String str){
        return str.replaceAll("\\u00a0", " ");
    }

    private List<IComment> retrieveComments(Document doc) {
        LinkedList<IComment> res = new LinkedList<>();
        for (Element e : doc.select("#forum .k_nForum_ReaderItem")) {
            String commentAuthor = e.select(".k_author").text();
            String commentContent = e.select(".k_content").text();
            String commentDateString = normalizeWhitespace(e.select(".k_nForum_CommentInfo > span:first-child").text());
            if (commentAuthor.length() > 0) {
                commentAuthor = commentAuthor.substring(1, commentAuthor.length() - 2);
            }
            res.add(new Comment(commentContent, commentAuthor, OnetDate.parse(commentDateString)));
        }
        return res;
    }

    private IArticle retrieveArticle(Document doc) {
        String content = doc.select(".detail.intext").text();
        String title = doc.select("#mainTitle").text();
        String dateString = doc.select(".datePublished").text();

        if (content.isEmpty() || title.isEmpty() || dateString.isEmpty()) {
            return null;
        }

        return new Article(title, content, OnetDate.parse(dateString), retrieveComments(doc), retrieveImages(doc));
    }

    private List<IImage> retrieveImages(Document doc) {
        LinkedList<IImage> images = new LinkedList<>();
        String url = doc.select("#mainPhoto img").attr("src");
        try {
            Image image = new Image(new URL(url));
            images.add(image);
        } catch (MalformedURLException ex) {
            // malformed url - don't add image
        } catch (IOException ex) {
            // can't construct image - don't add
        }
        return images;
    }

    @Override
    public IArticle parseDocument(Document document) {
        if (document == null) {
            return null;
        }
        return retrieveArticle(document);
    }
}
