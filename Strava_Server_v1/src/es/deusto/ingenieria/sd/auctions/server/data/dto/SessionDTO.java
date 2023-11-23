package es.deusto.ingenieria.sd.auctions.server.data.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


public class SessionDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String title;
	SportEnum sport;
	double distance;
	LocalDate start_date;
	LocalTime start_time;
	double duration;
	
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
	
	
	
	
}
