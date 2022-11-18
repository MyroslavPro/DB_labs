package ua.lviv.iot.lab5backend.domain;


import java.util.List;

import javax.persistence.*;

import lombok.Data;


@Data
@Entity
public class Tour {
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "name", length = 60)
	private String name;
	@Column(name = "price")
	private Double price;
	@Column(name = "tour_days")
	private Integer tourDays;
	@Column(name = "description", length = 700)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language;
	
	@ManyToOne
	@JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;
	
	@OneToMany(mappedBy = "tour")
	private List<Trip> trips;
	
	
}