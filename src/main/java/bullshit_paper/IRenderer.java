package bullshit_paper;

import java.io.OutputStream;
import java.util.List;

public interface IRenderer
{
	void Render(OutputStream stream, String title, List<IArticle> articles); 
}
