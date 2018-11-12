package trainingproject.BeerRating.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Rating {
	@NotNull
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long id;
	 
	@Size(min=1, max=12)// ei voi laittaa 10 koska commandlinerunner laskee myös string parametrin lainausmerkit
	//@Pattern(regexp = "\\d{2}\\.\\d{2}\\.\\d{4}") ei toimi
	private String date;
	
	@Size(min=3, max=50)
	private String location;
	
	@Size(min=3, max=20)
	private String servingStyle;
	
	@Size(min=3, max=50)
	private String flavorProfile;
	
	@NotNull
	@Range(min = 0, max = 5)
	private int rate; 
	
	 @ManyToOne
	 @JsonIgnore
	 @JoinColumn(name = "beerId")
	 private Beer beer;

	
	public Rating() {} 
	
	// Parameterized constructor
	public Rating(String date, String location, String servingStyle,String flavorProfile, int rate, Beer beer  ) {
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

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		if (this.beer != null)
		return "Rating [id=" + id + ", date=" + date + ", location=" + location + ", servingStyle="
				+ servingStyle + ", flavorProfile=" + flavorProfile + ", rate=" + rate +  this.getBeer() + "]";
		else return "Rating [id=" + id + ", date=" + date + ", location=" + location + ", servingStyle="
		+ servingStyle + ", flavorProfile=" + flavorProfile + ", rate=" + rate + "]";
	}

}
