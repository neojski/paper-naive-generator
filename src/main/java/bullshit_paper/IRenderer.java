package bullshit_paper;

import java.io.OutputStream;
import java.util.List;

public interface IRenderer
{
    void render(OutputStream stream, String title, List<PaperSection> sections) throws RenderingException;
}
