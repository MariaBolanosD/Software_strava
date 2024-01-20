package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import es.deusto.ingenieria.sd.auctions.server.data.dto.SportEnum;

@Entity
public class Challenge {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String name;
	private LocalDate start_date;
	private LocalDate end_date;
	private float target;
	private SportEnum sport;
	private boolean distance_or_time;

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public LocalDate getStartDate()
	{
		return this.start_date;
	}
	
	public void setStartDate(LocalDate start_date)
	{
		this.start_date = start_date;
	}
	
	public LocalDate getEndDate()
	{
		return this.end_date;
	}
	
	public void setEndDate(LocalDate end_date)
	{
		this.end_date = end_date;
	}
	
	public float getTarget()
	{
		return this.target;
	}
	
	public void setTarget(float target)
	{
		this.target = target;
	}
	
	public SportEnum getSport()
	{
		return this.sport;
	}
	
	public void setSport(SportEnum sport)
	{
		this.sport = sport;
	}
	
	public boolean getDistanceorTime()
	{
		return this.distance_or_time;
	}
	
	public void setDistanceorTime(boolean distanceorTime)
	{
		this.distance_or_time = distanceorTime;
	}
	
	@Override
	public String toString() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
		
		StringBuffer result = new StringBuffer();
		
		result.append("# Challenge name: ").append("\n");
		result.append(this.name).append("\n");
		result.append("# Initial date: ").append("\n");
		result.append(dateFormatter.format(this.start_date)).append("\n");
		result.append("# End date:").append("\n");
		result.append(dateFormatter.format(this.end_date)).append("\n");
		// if true -> distance / false -> time
		if(distance_or_time == true) 
		{
			result.append("# Target in km: ").append("\n");
			result.append(this.target).append("\n");
			
		}
		else {
			result.append("# Target in min: ").append("\n");
			result.append(this.target).append("\n");
		}
		result.append("Sport:").append("\n");
		result.append(this.sport).append("\n");
		
		return result.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return (this.name == ((Challenge)obj).name) && (this.start_date == ((Challenge)obj).start_date)&& (this.sport == ((Challenge)obj).sport) ;
		}
		
		return false;
	}
}
