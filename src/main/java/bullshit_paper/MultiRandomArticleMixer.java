package bullshit_paper;

import java.util.LinkedList;
import java.util.List;

/**
 * Mix many articles using MarkovChain into one.
 */
public class MultiRandomArticleMixer implements IArticleMixer{

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
        
        String all = "";
        for (IArticle article : articles) {
           all += article.getContent() + " ";
        }
        MarkovChain mc = new MarkovChain();
        mc.build(all);
        String randomText = mc.generateRandomText(20);
        
        
        newArticles.add(new Article(baseArticle.getTitle(), randomText, baseArticle.getDate(), baseArticle.getComments(), baseArticle.getImages()));
        
        return newArticles;
    }
}
