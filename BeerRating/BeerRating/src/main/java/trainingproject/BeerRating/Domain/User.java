package trainingproject.BeerRating.Domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	 @Column(name = "id", nullable = false, updatable = false)// ei saa olla tyhjä
    private Long id;
	
	 // Username with unique constraint
    @Column(name = "username", nullable = false, unique = true)// pitää olla uniikki
    private String username;

    @Column(name = "password", nullable = false)
    private String passwordHash;// salasana on cryptattu
    
    
    

    @Column(name = "role", nullable = false)// rooli ei saa olla tyhjä
    private String role;
    
    /*@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Beer> beers;*/
    
    public User() {
    }

	public User(String username, String passwordHash, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		
		this.role = role;
	}

	

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}

