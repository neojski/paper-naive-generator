package bullshit_paper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TVChannel {
	private String name;
	private List<TVShow> shows;
	
	public TVChannel(String name){
		shows = new LinkedList<TVShow>();
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public List<TVShow> getShows(){
		return shows;
	}
	
	private List<String> createMoviesList(){
		List<String> list = new LinkedList<>();
		
		try {
			URL resource = Class.class.getResource(new File("/movies", "movies.txt").getPath());
			File file = new File(resource.getFile());
			Scanner reader = new Scanner(file);
			
			String line = reader.nextLine();
			while(!(line = reader.nextLine()).equals("")){
				list.add(line);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void build(){
		List<String> movies = createMoviesList();
		int size = movies.size();
		Random random = new Random();
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.HOUR_OF_DAY, 22);
		cal.set(Calendar.MINUTE, 45);
		Date last = cal.getTime();
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 10+random.nextInt(20));
		
		while(cal.getTime().compareTo(last) < 0){
			int duration = 60+random.nextInt(90);
			Date startTime = cal.getTime();
			cal.add(Calendar.MINUTE, duration);
			Date endTime = cal.getTime();
			cal.add(Calendar.MINUTE, 10);
			shows.add(new TVShow(movies.get(random.nextInt(size)),startTime,endTime));
		}
	}
	
}
