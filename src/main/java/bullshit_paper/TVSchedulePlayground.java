package bullshit_paper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TVSchedulePlayground {
	
	private static String parseDate(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		return dateFormat.format(date);
	}
	
	public static void main(String[] args) throws ParseException{
		TVSchedule schedule = new TVSchedule("Poniedziałek");
		
		schedule.addChannel("TVP1");
		schedule.addChannel("Polsat");
		schedule.addChannel("TVN");
		
		List<TVChannel> channels = schedule.getChannels();
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
