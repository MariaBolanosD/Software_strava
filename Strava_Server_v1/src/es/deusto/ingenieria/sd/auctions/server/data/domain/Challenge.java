package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import es.deusto.ingenieria.sd.auctions.server.data.dto.SportEnum;

@Entity
public class Challenge {
	@Id
	private String name;
	private LocalDate start_date;
	private LocalDate end_date;
	private float target;
	
	private SportEnum sport;
	private boolean distance_or_time;
	
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
SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-YY - hh:mm");
		
		StringBuffer result = new StringBuffer();
		
		result.append("# Challenge name: ");
		result.append(this.name);
		result.append("# Initial date: ");
		result.append(dateFormatter.format(this.start_date));
		result.append("# End date:");
		result.append(dateFormatter.format(this.end_date));
		// if true -> distance / false -> time
		if(distance_or_time == true) 
		{
			result.append("# Target in km: ");
			result.append(this.target);
			
		}
		else {
			result.append("# Target in min: ");
			result.append(this.target);
		}
		result.append("Sport:");
		result.append(this.sport);
		
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
