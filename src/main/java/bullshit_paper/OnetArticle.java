package bullshit_paper;

import java.util.Date;
import java.util.List;

public class OnetArticle extends Article {

    private final List<IImage> images;

    public OnetArticle(String title, String content, Date date, List<IComment> comments, List<IImage> images) {
        super(title, content, date, comments);
        this.images = images;
    }

    public List<IImage> getImages() {
        return images;
    }

}
