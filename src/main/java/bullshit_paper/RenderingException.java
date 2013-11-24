package bullshit_paper;

public class RenderingException extends Exception
{
    public RenderingException()
    {
    }

    public RenderingException(String msg)
    {
        super(msg);
    }
    
    public RenderingException(Throwable innerExc)
    {
	super(innerExc);
    }    
    
    public RenderingException(String msg, Throwable innerExc)
    {
	super(msg, innerExc);
    }
}
