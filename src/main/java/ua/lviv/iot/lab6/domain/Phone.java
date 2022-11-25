package ua.lviv.iot.lab6.domain;


import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Phone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "number")
    private String number;
	
	@OneToOne(mappedBy = "phone")
	private Client client;
	
	@OneToOne(mappedBy = "phone")
	private Guide guide;
}