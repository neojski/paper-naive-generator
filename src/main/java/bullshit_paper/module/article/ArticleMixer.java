package bullshit_paper.module.article;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ArticleMixer {

    public List<IArticle> Mix(List<IArticle> articles) {
        List<IArticle> newArticles = new LinkedList<>();
        for (IArticle article : articles) {
            String content = article.getContent();
            List<String> sentences = Parser.parseText(content);
            Collections.shuffle(sentences);
            StringBuilder newContent = new StringBuilder();
            for (String s : sentences) {
                newContent.append(s);
            }
            newArticles.add(new Article(article.getTitle(), newContent.toString(), article.getDate(), article.getComments(), article.getImages()));
        }
        return newArticles;
    }
}
