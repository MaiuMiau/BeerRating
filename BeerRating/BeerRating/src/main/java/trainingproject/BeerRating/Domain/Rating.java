package trainingproject.BeerRating.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
public class Rating {
	
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long id;
	//Hibernate: insert into rating (beer_id, date, flavor_profile, location, rating, serving_style, id) values (?, ?, ?, ?, ?, ?, ?)
	private String date;
	
	private String location;
	private String servingStyle;
	private String flavorProfile;
	private double rate; 
	
	 @ManyToOne
	 @JsonIgnore
	 @JoinColumn(name = "beerId")
	 private Beer beer;

	
	public Rating() {} 
	
	// Parameterized constructor
	public Rating(String date, String location, String servingStyle,String flavorProfile, double rate, Beer beer  ) {
		super();
		
		this.date = date;
		this.location = location;
		this.servingStyle = servingStyle;
		this.flavorProfile = flavorProfile;
		this.rate = rate;
		this.beer = beer;
		
	}

	public Beer getBeer() {
		return beer;
	}

	public void setBeer(Beer beer) {
		this.beer = beer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getServingStyle() {
		return servingStyle;
	}

	public void setServingStyle(String servingStyle) {
		this.servingStyle = servingStyle;
	}

	public String getFlavorProfile() {
		return flavorProfile;
	}

	public void setFlavorProfile(String flavorProfile) {
		this.flavorProfile = flavorProfile;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	/*@Override
	public String toString() {
		return "Rating [id=" + id + ", date=" + date + ", location=" + location + ", servingStyle="
				+ servingStyle + ", flavorProfile=" + flavorProfile + ", rating=" + rating + "]";
	}*/

	@Override
	public String toString() {
		if (this.beer != null)
		return "Rating [id=" + id + ", date=" + date + ", location=" + location + ", servingStyle="
				+ servingStyle + ", flavorProfile=" + flavorProfile + ", rate=" + rate +  this.getBeer() + "]";
		else return "Rating [id=" + id + ", date=" + date + ", location=" + location + ", servingStyle="
		+ servingStyle + ", flavorProfile=" + flavorProfile + ", rate=" + rate + "]";
	}
	
	
	

}
