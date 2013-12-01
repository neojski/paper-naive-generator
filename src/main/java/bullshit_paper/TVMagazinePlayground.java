package bullshit_paper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TVMagazinePlayground {
	
	private static String parseDate(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		return dateFormat.format(date);
	}
	
	public static void main(String[] args) throws ParseException{
		TVMagazine magazine = new TVMagazine("Poniedziałek");
		
		magazine.addChannel("TVP1");
		magazine.addChannel("Polsat");
		magazine.addChannel("TVN");
		
		List<TVChannel> channels = magazine.getChannels();
		for(int i=0; i<channels.size(); ++i){
			TVChannel channel = channels.get(i);
			System.out.println(channel.getName());
			System.out.println("*************************");
			List<TVShow> shows = channel.getShows();
			for(int j=0; j<shows.size(); ++j){
				TVShow show = shows.get(j);
				System.out.println(show.getTitle()+" "+parseDate(show.getStartTime())+" "+parseDate(show.getEndTime()));
			}
		}
	}
}
