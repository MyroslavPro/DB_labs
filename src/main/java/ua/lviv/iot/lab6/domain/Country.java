package ua.lviv.iot.lab6.domain;

import lombok.Data;

import java.util.List;

import javax.persistence.*;


@Data
@Entity
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Integer id;
	@Column (name = "name", length = 60)
    private String name;
	
	@OneToMany(mappedBy = "country")
	private List<Client> clients;
	
	@OneToMany(mappedBy = "country")
	private List<Guide> guides;
	
	@OneToMany(mappedBy = "country")
	private List<Location> locations;
}