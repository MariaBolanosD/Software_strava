package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import es.deusto.ingenieria.sd.auctions.server.data.dto.SportEnum;

@Entity
public class Session {
	
	@Id
	private String title;
	private SportEnum sport;
	private double distance;
	private LocalDate start_date;
	private LocalTime start_time;
	private double duration;

	@ManyToOne
    @JoinColumn(name = "user_email")
    private User user;
	
	
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
	
//	public void setUser(User user)
//	{
//		this.user = user;
//	}
	
	
	//TO DO:: talk to user to update challenge list
	public void UpdateChallenges()
	{
		
	}
	
	@Override
	public String toString() {
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
	    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	    StringBuilder result = new StringBuilder();

	    result.append("# Session title: ").append(this.title).append("\n");
	    result.append("# Initial date: ").append(dateFormatter.format(this.start_date)).append("\n");
	    result.append("# Initial time: ").append(timeFormatter.format(this.start_time)).append("\n");
	    result.append("# Distance: ").append(this.distance).append("\n");
	    result.append("Sport: ").append(this.sport).append("\n");
	    result.append("Duration: ").append(this.duration).append("\n");

	    return result.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return (this.title == ((Session)obj).title) ;
		}
		
		return false;
	}

	public void setUser(User user2) {
		// TODO Auto-generated method stub
		this.user = user2;
	}
	
}
