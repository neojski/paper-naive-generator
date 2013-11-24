package bullshit_paper.module.article;

import bullshit_paper.PDFPaperFragment;
import com.itextpdf.text.Document;
import java.util.Arrays;

public class AritclePDFPaperFragment implements PDFPaperFragment{

    @Override
    public void render(Document doc) {
        OnetArticleProvider onetArticleProvider = new OnetArticleProvider();
        IArticle article = new OnetArticleParser().parseDocument(onetArticleProvider.getDocuments(Arrays.asList("onet")).get(0));
        
        new PDFRenderer().Render(doc, "title", Arrays.asList(article));
    }
    
}
