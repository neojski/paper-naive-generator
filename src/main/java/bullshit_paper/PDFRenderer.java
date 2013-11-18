package bullshit_paper;

import java.util.*;
import java.awt.AWTError;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;
import com.itextpdf.awt.geom.misc.RenderingHints;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.security.Timestamp;
import java.text.*;

public class PDFRenderer implements IRenderer
{
	static int _maxImageWidth = 300;
	static int _maxImageHeight = 200;
	
	public void Render(OutputStream stream, String title, java.util.List<IArticle> articles)
	{
		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc, stream);
			doc.addTitle(title);
			doc.open();
			BaseFont baseFont = BaseFont.createFont("font.ttf", BaseFont.IDENTITY_H, true);			
			Font paperTitleFont = new Font(baseFont, 20.0f);
			Font titleFont = new Font(baseFont, 12.0f);
			Font dateFont = new Font(baseFont, 8.0f);
			Font contentFont = new Font(baseFont, 10.0f);	
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Chapter chapter = new Chapter(new Paragraph(title, paperTitleFont), 0);
			chapter.setNumberDepth(0);
			for (IArticle art : articles) {
				Section section = chapter.addSection(new Paragraph(art.getTitle(), titleFont));
				section.setNumberDepth(0);
				section.add(new Paragraph(dateFormat.format(art.getDate()), dateFont));
				if (art.getImages() != null) {
					for (IImage img : art.getImages()) {
						java.awt.Image scaledImg = ScaleImage(img.getImage());
						section.add(com.itextpdf.text.Image.getInstance(scaledImg, null));
					}
				}
				section.add(new Paragraph(art.getContent(), contentFont));			
			}
			doc.add(chapter);
			doc.close();
		}
		catch (DocumentException | IOException ex) { }
	}
	
	static java.awt.Image ScaleImage(java.awt.Image img)
	{
		double cff = Math.min((double)_maxImageWidth/img.getWidth(null), (double)_maxImageHeight/img.getHeight(null));
		int w = (int)(img.getWidth(null) * cff);
		int h = (int)(img.getHeight(null) * cff);
        BufferedImage res = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D)res.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(img, 0, 0, w, h, null);		
        return res;
	}
}
