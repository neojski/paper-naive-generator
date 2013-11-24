package bullshit_paper_pdf;

class Helper
{
    private static int _maxImageWidth = 300;
    private static int _maxImageHeight = 200;    
    
    public static java.awt.Image scaleImage(java.awt.Image img)
    {
	double cff = Math.min((double)_maxImageWidth/img.getWidth(null), (double)_maxImageHeight/img.getHeight(null));
	int w = (int)(img.getWidth(null) * cff);
	int h = (int)(img.getHeight(null) * cff);
	java.awt.image.BufferedImage res = new java.awt.image.BufferedImage(w, h, java.awt.image.BufferedImage.TYPE_INT_RGB);
	java.awt.Graphics2D g2d = (java.awt.Graphics2D)res.createGraphics();
	g2d.addRenderingHints(new java.awt.RenderingHints(java.awt.RenderingHints.KEY_RENDERING, java.awt.RenderingHints.VALUE_RENDER_QUALITY));
	g2d.drawImage(img, 0, 0, w, h, null);		
	return res;
    }
}
