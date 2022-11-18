package ua.lviv.iot.lab5backend.domain;


import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Phone {
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "number")
    private String number;
	
	@OneToOne(mappedBy = "phone")
	private Client client;
	
	@OneToOne(mappedBy = "phone")
	private Guide guide;
}