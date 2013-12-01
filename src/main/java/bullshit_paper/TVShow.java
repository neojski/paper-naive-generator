package bullshit_paper;

import java.util.Date;

public class TVShow {
	private String title;
	private Date startTime;
	private Date endTime;
	
	public TVShow(String title, Date startTime, Date endTime){
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public String getTitle(){
		return title;
	}
	
	public Date getStartTime(){
		return startTime;
	}
	
	public Date getEndTime(){
		return endTime;
	}
	
}
