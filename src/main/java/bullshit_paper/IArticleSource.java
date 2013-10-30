package bullshit_paper;

import java.util.List;

public interface IArticleSource
{
	List<IArticle> getArticles(List<String> tags);
}
