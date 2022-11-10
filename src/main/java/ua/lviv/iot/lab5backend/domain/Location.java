package ua.lviv.iot.lab5backend.domain;

import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Location {
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;
    
    @OneToMany(mappedBy = "location")
    private List<Tour> tours;
}