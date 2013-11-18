package bullshit_paper;

import org.jsoup.nodes.Document;

public interface IArticleParser {
    public IArticle parseDocument(Document document);
}
