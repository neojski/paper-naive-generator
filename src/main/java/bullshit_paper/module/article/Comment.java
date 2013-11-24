
package bullshit_paper.module.article;

import java.util.Date;

public class Comment implements IComment {
    private final String content;
    private final String author;
    private final Date date;

    public Comment(String content, String author, Date date) {
        this.content = content;
        this.author = author;
        this.date = date;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public Date getDate() {
        return date;
    }
    
}
