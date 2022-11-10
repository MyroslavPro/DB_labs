package ua.lviv.iot.lab5backend.domain;


import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Data
@Entity
public class Client {
	@Id 
	@Column (name = "id")
	private Integer id;
	@Column (name = "name", length = 45)
    private String name;
	
	@ManyToOne
	@JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;
	@ManyToOne
	@JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language;
	
    @OneToOne
    @JoinColumn(name="phone_id", referencedColumnName = "id")
    private Phone phone;
    
    @ManyToMany(mappedBy = "clients")
    private List<Trip> trips;
}