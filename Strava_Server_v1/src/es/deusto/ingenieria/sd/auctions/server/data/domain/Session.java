package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;


import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge.SportEnum;

public class Session {
	
	private long token;
	private String title;
	private SportEnum sport;
	private double distance;
	private LocalDate start_date;
	private LocalTime start_time;
	private double duration;
	
	
	public String getTitle()
	{
		return this.title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public SportEnum getSport() {
		return this.sport;
	}
	
	public void setSport(SportEnum sport) {
		this.sport = sport;
	}
	
	public double getDistance()
	{
		return this.distance;
	}
	
	public void setDistance(double distance)
	{
		this.distance = distance;
	}
	
	public LocalDate getStartDate()
	{
		return this.start_date;
	}
	
	public void setStartDate(LocalDate startDate)
	{
		this.start_date= startDate;
	}
	
	public LocalTime getStartTime()
	{
		return this.start_time;
	}
	
	public void setStartTime(LocalTime start_time)
	{
		this.start_time= start_time;
	}
	
	public double getDuration()
	{
		return this.duration;
	}
	
	public void setDuration(double duration)
	{
		this.duration = duration;
	}
	
	public long getToken() 
	{
		return this.token;
	}
	
	public void setToken(long token)
	{
		this.token = token;
	}
	
	
	//TO DO:: talk to user to update challenge list
	public void UpdateChallenges()
	{
		
	}
	
	@Override
	public String toString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-YY");
		SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss");
		
		StringBuffer result = new StringBuffer();
		
		result.append("# Session title: ");
		result.append(this.title);
		result.append("# Initial date: ");
		result.append(dateFormatter.format(this.start_date));
		result.append("# Initial time: ");
		result.append(timeFormatter.format(this.start_time));
		result.append("# Distance: ");
		result.append(this.distance);
		result.append("Sport: ");
		result.append(this.sport);
		result.append("Duration: ");
		result.append(this.duration);
		
		return result.toString();	
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return (this.token == ((Session)obj).token) && (this.title == ((Session)obj).title) && (this.start_date == ((Session)obj).start_date) ;
		}
		
		return false;
	}
	
}
