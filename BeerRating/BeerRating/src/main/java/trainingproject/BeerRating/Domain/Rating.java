package trainingproject.BeerRating.Domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Rating {
	
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long id;
	
	private String date;
	private String location;
	private String servingStyle;
	private String flavorProfile;
	private double rating; 
	
	
	public Rating() {} 
	
	// Parameterized constructor
	public Rating(Long id,  String date, String location, String servingStyle,String flavorProfile, double rating  ) {
		super();
		
		this.date = date;
		this.location = location;
		this.servingStyle = servingStyle;
		this.flavorProfile = flavorProfile;
		this.rating = rating;
		
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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", date=" + date + ", location=" + location + ", servingStyle="
				+ servingStyle + ", flavorProfile=" + flavorProfile + ", rating=" + rating + "]";
	}

	
	
	

}
