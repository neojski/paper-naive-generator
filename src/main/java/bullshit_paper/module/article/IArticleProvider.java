package bullshit_paper.module.article;

import java.util.List;
import org.jsoup.nodes.Document;

public interface IArticleProvider {
    public List<Document> getDocuments(List<String> tags);
}
