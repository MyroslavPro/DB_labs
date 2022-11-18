package ua.lviv.iot.lab5backend.domain;

import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Guide {
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
    private String name;
	@Column(name = "surname")
    private String surname;
	@Column(name = "email")
    private String email;
       
    @ManyToOne
    @JoinColumn(name= "country_id", referencedColumnName = "id")
	private Country country;
	
	@OneToOne
	@JoinColumn(name= "phone_id", referencedColumnName = "id")
    private Phone phone;
	
	@OneToMany(mappedBy = "guide")
	private List<Trip> trips;
}