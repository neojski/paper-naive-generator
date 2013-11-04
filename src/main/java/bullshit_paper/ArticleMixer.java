package bullshit_paper;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ArticleMixer {
    private class GraphElement {
        String word;
    }
    
    public List<IArticle> Mix(List<IArticle> articles) {
        /*
        for(IArticle article : articles) {
            String content = article.getContent();
            String[] words = content.split("[\\W]");
            Set<GraphElement> graph = new TreeSet<>();
            for(String word : words) {
                
                if( !graph.contains(word) ) {
                    graph.add(new GraphElement());
                }
            }
        }
         * 
         */
        List<IArticle> newArticles = new LinkedList<>();
        for(IArticle article : articles) {
            String content = article.getContent();
            String[] sentences = content.split("[.]");
            java.util.Collections.shuffle(Arrays.asList(sentences));
            StringBuilder newContent = new StringBuilder();
            for(String s : sentences) {
                newContent.append(s).append(".");
            }
            newArticles.add(new Article(article.getTitle(), newContent.toString(), article.getDate(), article.getComments()));
        }
        return newArticles;
    }
}
