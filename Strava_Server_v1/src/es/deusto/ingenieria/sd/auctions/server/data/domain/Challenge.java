package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.util.Date;

public class Challenge {
	
	String name;
	Date start_date;
	Date end_date;
	float target;
	String sport;
	boolean distance_or_time;
	
	String getName() {
		return this.name;
	}
	
	void setName(String name)
	{
		this.name = name;
	}
	
	Date getStartDate()
	{
		return this.start_date;
	}
	
	void setStartDate(Date start_date)
	{
		this.start_date = start_date;
	}
	
	Date getEndDate()
	{
		return this.end_date;
	}
	
	void setEndDate(Date end_date)
	{
		this.end_date = end_date;
	}
	
	float getTarget()
	{
		return this.target;
	}
	
	void setTarget(float target)
	{
		this.target = target;
	}
	
	String getSport()
	{
		return this.sport;
	}
	
	void setSport(String sport)
	{
		this.sport = sport;
	}
	
	boolean getDistanceorTime()
	{
		return this.distance_or_time;
	}
	
	void setDistanceorTime(boolean distanceorTime)
	{
		this.distance_or_time = distanceorTime;
	}
	
	
}
