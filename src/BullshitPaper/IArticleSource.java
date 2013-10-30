package BullshitPaper;

import java.util.*;

public interface IArticleSource
{
	List<IArticle> getArticles(List<String> tags);
}
