package bullshit_paper;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Mix many articles into one.
 */
public class MultiArticleMixer implements IArticleMixer{

    @Override
    public List<IArticle> Mix(List<IArticle> articles) {
        if (articles == null) {
            return null;
        }
        if (articles.isEmpty()) {
            return null;
        }
        List<IArticle> newArticles = new LinkedList<>();
        IArticle baseArticle = articles.get(0);
        
        List<String> sentences = new LinkedList<>();
        for (IArticle article : articles) {
            String content = article.getContent();
            sentences.addAll(Parser.parseText(content));
        }
        
        Collections.shuffle(sentences);
        sentences = sentences.subList(0, sentences.size() / articles.size());
        
        StringBuilder newContent = new StringBuilder();
        for (String s : sentences) {
            newContent.append(s);
        }
        newArticles.add(new Article(baseArticle.getTitle(), newContent.toString(), baseArticle.getDate(), baseArticle.getComments(), baseArticle.getImages()));
        
        return newArticles;
    }
}
