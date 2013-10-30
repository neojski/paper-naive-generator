package BullshitPaper;

import java.io.*;
import java.util.*;

public interface IRenderer
{
	void Render(OutputStream stream, String title, List<IArticle> articles); 
}
