package bullshit_paper.module.article;

import org.jsoup.nodes.Document;

public interface IArticleParser {
    public IArticle parseDocument(Document document);
}
