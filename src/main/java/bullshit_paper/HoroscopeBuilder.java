package bullshit_paper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HoroscopeBuilder {
	private final String chromeUserAgent = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36";
	private final String url = "http://horoskopy.gazeta.pl/horoskopy-magia/";
	
	private String preparePattern(String zodiacSign){
		String currentDate = "25-11-2013";
		StringBuilder builder = new StringBuilder();
		
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MM-yyyy" );   
			Calendar cal = Calendar.getInstance();    
			cal.setTime( dateFormat.parse(currentDate));    
		
			for(int i=0; i<100; ++i){
				String preparedURL = url + zodiacSign + "/dzienny/" + currentDate;
				
				cal.add(Calendar.DATE, 1 );
				dateFormat.format(cal.getTime()); 
				currentDate = dateFormat.format(cal.getTime());
				
				Document document = Jsoup.connect(preparedURL).userAgent(chromeUserAgent).get();
				String text = document.body().select("div[class=h_ProDesc] p").text();
				builder.append(text);
			}
		}
		catch (IOException | ParseException e2) {
		}
		
		return builder.toString();
	}
	
	public HoroscopeEntry buildEntry(String zodiacSign, int length){
		MarkovChain mc = new MarkovChain();
		mc.build(preparePattern(zodiacSign));
		System.out.println("downloaded " + zodiacSign);
		String content = mc.generateRandomText(length);
		return new HoroscopeEntry(zodiacSign,content);
	}
	
}
