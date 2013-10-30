package bullshit_paper;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PaperGenerator
{
	IRenderer _renderer;
	List<IArticleSource> _sources;
	IArticleMixer _mixer;
	
	public void Generate(OutputStream stream, List<String> tags, String title)
	{
		List<IArticle> articles = new ArrayList<>();
		for (IArticleSource source : _sources) articles.addAll(source.getArticles(tags));
		articles = _mixer.Mix(articles);
		_renderer.Render(stream, title, articles);
	}
	
	public PaperGenerator(IRenderer renderer, List<IArticleSource> sources, IArticleMixer mixer)
	{
		_renderer = renderer;
		_sources = sources;
		_mixer = mixer;
	}
}
