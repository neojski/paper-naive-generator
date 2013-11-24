package bullshit_paper.module.article;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Image implements IImage {

    private final URL url;
    private final BufferedImage image;

    public Image(URL url) throws IOException {
        image = ImageIO.read(url);
        this.url = url;
    }

    @Override
    public URL getURL() {
        return url;
    }

    @Override
    public java.awt.Image getImage() {
        return image;
    }

}
