package ua.lviv.iot.lab6.domain;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Sight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
    private String name;
	@Column(name = "country_id")
    private Integer countryId;
}