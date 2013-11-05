package bullshit_paper;

import java.net.URL;

class Image implements IImage {

    private final URL url;

    public Image(URL url) {
        this.url = url;
    }

    @Override
    public URL getURL() {
        return url;
    }

}
