
package bullshit_paper;

import static java.lang.Math.min;
import java.util.Arrays;
import java.util.List;

public class OnetPlayground {
    public static void main(String[] args) {
        IArticleSource articleSource = new OnetArticleSource();
        List<IArticle> articles = articleSource.getArticles(Arrays.asList("onet"));
        for(IArticle article : articles){
            System.out.println("title: " + article.getTitle());
            String content = article.getContent();
            content = content.substring(0, min(100, content.length()));
            System.out.println("article: " + content);
            System.out.println("comments: ");
            for(IComment comment : article.getComments()){
                content = comment.getContent();
                content = content.substring(0, min(100, content.length()));
                System.out.println("comment by " + comment.getAuthor() + ": " + content);
            }
        }
            
    }
}
