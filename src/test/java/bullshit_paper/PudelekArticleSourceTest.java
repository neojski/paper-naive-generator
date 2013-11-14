package bullshit_paper;

import java.util.*;
import java.io.*;
import org.junit.Test;

public class PudelekArticleSourceTest
{

	public void test() throws IOException
	{
		IArticleSource src = new PudelekArticleSource();
		List<String> tags = new ArrayList<>();
		tags.add("donald");
		tags.add("tusk");
		List<IArticle> arts = src.getArticles(tags);
		try (FileOutputStream out = new FileOutputStream("donaldtusk.pdf")) {
			new PDFRenderer().Render(out, "donald tusk", arts);
		}
	}

}
