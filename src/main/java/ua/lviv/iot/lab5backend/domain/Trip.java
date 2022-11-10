package ua.lviv.iot.lab5backend.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Trip {
	@Id
	
	@Column(name = "id")
	private Integer id;
    
    @Column(name = "date_start")
    private Date dateStart;
    
    @Column(name = "guide_salary")
    private Double guideSalary;
    
    
    @ManyToOne
	@JoinColumn(name = "tour_id", referencedColumnName = "id")
    private Tour tour;
    
    @ManyToOne
    @JoinColumn(name = "guide_id", referencedColumnName = "id")
    private Guide guide;
    
    @ManyToMany
    @JoinTable(
      name = "client_has_tour_with_guide", 
      joinColumns = @JoinColumn(name = "trip_id", referencedColumnName = "id", nullable = false), 
      inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false))
	private List<Client> clients;
}