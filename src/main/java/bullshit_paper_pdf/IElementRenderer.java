package bullshit_paper_pdf;

import com.itextpdf.text.*;
import java.io.*;

interface IElementRenderer<T>
{
    PDFElement render(T obj, PDFStyle style) throws DocumentException, IOException;
}
