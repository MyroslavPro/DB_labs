package ua.lviv.iot.lab6.domain;


import lombok.Data;
import java.util.List;
import javax.persistence.*;


@Data
@Entity
public class Language {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
    private String name;
	
	@OneToMany(mappedBy = "language")
	private List<Client> clients;
	
	@OneToMany(mappedBy = "language")
	private List<Tour> tours;
}