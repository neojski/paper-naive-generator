package BullshitPaper;

import java.util.*;

public interface IArticle
{
	public String getTitle();
	public String getContent();
	public Date getDate();
	public List<IComment> getComments();
}
