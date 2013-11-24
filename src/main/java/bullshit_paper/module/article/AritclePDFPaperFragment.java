package bullshit_paper.module.article;

import bullshit_paper.PDFPaperFragment;
import java.io.OutputStream;
import java.util.Arrays;

public class AritclePDFPaperFragment implements PDFPaperFragment{

    @Override
    public void render(OutputStream stream) {
        OnetArticleProvider onetArticleProvider = new OnetArticleProvider();
        IArticle article = new OnetArticleParser().parseDocument(onetArticleProvider.getDocuments(Arrays.asList("onet")).get(0));
        
        new PDFRenderer().Render(stream, "title", Arrays.asList(article));
    }
    
}
