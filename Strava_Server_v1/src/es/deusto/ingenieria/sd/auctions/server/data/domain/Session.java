package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.net.UnknownServiceException;
import java.util.Date;

public class Session {

	String title;
	String sport;
	double distance;
	Date start_date;
	Date start_time;
	double duration;
	User user;
	
	String getTitle()
	{
		return this.title;
	}
	
	void setTitle(String title)
	{
		this.title = title;
	}
	
	String getSport() {
		return this.sport;
	}
	
	void setSport(String sport) {
		this.sport = sport;
	}
	
	double getDistance()
	{
		return this.distance;
	}
	
	void setDistance(double distance)
	{
		this.distance = distance;
	}
	
	Date getStartDate()
	{
		return this.start_date;
	}
	
	void setStartDate(Date startDate)
	{
		this.start_date= startDate;
	}
	
	Date getStartTime()
	{
		return this.start_time;
	}
	
	void setStartTime(Date start_time)
	{
		this.start_time= start_time;
	}
	
	double getDuration()
	{
		return this.duration;
	}
	
	void setDuration(double duration)
	{
		this.duration = duration;
	}
	
	User getUser() 
	{
		return this.user;
	}
	
	void setUser(User user)
	{
		this.user = user;
	}
	
	
	//TO DO:: talk to user to update challenge list
	void UpdateChallenges()
	{
		
	}
}
