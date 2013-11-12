package bullshit_paper;

import java.util.Date;
import java.util.List;

public interface IArticle
{
	public String getTitle();
	public String getContent();
	public Date getDate();
	public List<IComment> getComments();
	public List<IImage> getImages();
}
