package bullshit_paper;

public class HoroscopeEntry {
	private String zodiacSign;
	private String content;
	
	HoroscopeEntry(String zodiacSign, String content){
		this.zodiacSign = zodiacSign;
		this.content = content;
	}
	
	public String getZodiacSign(){
		return zodiacSign;
	}
	
	public String getContent(){
		return content;
	}
}
