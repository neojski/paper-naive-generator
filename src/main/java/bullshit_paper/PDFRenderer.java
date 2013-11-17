package bullshit_paper;

import java.util.*;
import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.text.*;

public class PDFRenderer implements IRenderer
{
	public void Render(OutputStream stream, String title, java.util.List<IArticle> articles)
	{
		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc, stream);
			doc.addTitle(title);
			doc.open();
			Font paperTitleFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 20.0f);
			Font titleFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 12.0f);
			Font dateFont = FontFactory.getFont(FontFactory.TIMES_ITALIC, 8.0f);
			Font contentFont = FontFactory.getFont(FontFactory.TIMES, 10.0f);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Chapter chapter = new Chapter(new Paragraph(title, paperTitleFont), 0);
			chapter.setNumberDepth(0);
			for (IArticle art : articles) {
				Section section = chapter.addSection(new Paragraph(art.getTitle(), titleFont));
				section.setNumberDepth(0);
				section.add(new Paragraph(dateFormat.format(art.getDate()), dateFont));
				if (art.getImages() != null) {
					for (IImage img : art.getImages()) section.add(com.itextpdf.text.Image.getInstance(img.getImage(), null));
				}
				section.add(new Paragraph(art.getContent(), contentFont));			
			}
			doc.add(chapter);
			doc.close();
		}
		catch (DocumentException | IOException ex) { }
	}
}
