package ua.lviv.iot.db4.jdbclab.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Trip {
	private Integer id;
    private Integer tourId;
    private Integer guideId;
    private Date dateStart;
    private Double guideSalary;
}