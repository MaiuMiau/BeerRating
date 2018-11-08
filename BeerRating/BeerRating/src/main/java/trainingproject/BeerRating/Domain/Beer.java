package trainingproject.BeerRating.Domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Beer {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long beerId;
	
	@NotNull
	@Size(min=5, max=50)
	private String name;
	
	@NotNull
	@Range(min = 0, max = 20)
	private double alcoholPercentage;
	
	@Size(min=5, max=50)
	private String brewery;
	
	@Size(min=2, max=30)
	private String beerStyle;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "username")
	private User user;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "beer")
	private List<Rating> ratings;

	public Beer() {}

	// Parameterized constructor
	public Beer(String name, double alcoholPercentage, String brewery, String beerStyle, User user) {
		super();
		this.name = name;
		this.alcoholPercentage = alcoholPercentage;
		this.brewery = brewery;
		this.beerStyle = beerStyle;
		this.user = user;

	}

	// Parameterized constructor
		public Beer(String name, double alcoholPercentage, String brewery, String beerStyle) {
			super();
			this.name = name;
			this.alcoholPercentage = alcoholPercentage;
			this.brewery = brewery;
			this.beerStyle = beerStyle;
			
		}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getBeerId() {
		return beerId;
	}

	public void setBeerId(Long beerId) {
		this.beerId = beerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAlcoholPercentage() {
		return alcoholPercentage;
	}

	public void setAlcoholPercentage(double alcoholPercentage) {
		this.alcoholPercentage = alcoholPercentage;
	}

	public String getBrewery() {
		return brewery;
	}

	public void setBrewery(String brewery) {
		this.brewery = brewery;
	}

	public String getBeerStyle() {
		return beerStyle;
	}

	public void setBeerStyle(String beerStyle) {
		this.beerStyle = beerStyle;
	}

	public List<Rating> getRatings() {
		return getRatings();
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	/*
	 * @Override public String toString() { return "Beer [beerId=" + beerId +
	 * ", name=" + name + ", alcoholPercentage=" + alcoholPercentage + ", brewery="
	 * + brewery + ", beerStyle=" + beerStyle + "]"; }
	 */

	@Override
	public String toString() {
		if (this.user != null)
			return "Beer [beerId=" + beerId + ", name=" + name + ", alcoholPercentage=" + alcoholPercentage
					+ ", brewery=" + brewery + ", beerStyle=" + beerStyle + ", user=" + this.getUser() + "]";

		else
			return "Beer [beerId=" + beerId + ", name=" + name + ", alcoholPercentage=" + alcoholPercentage
					+ ", brewery=" + brewery + ", beerStyle=" + beerStyle + "]";
	}

}
