package ua.lviv.iot.db4.jdbclab.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tour {
	private Integer id;
	private String name;
	private Double price;
	private Integer tourDays;
	private String description;
	private Integer languageId;
    private Integer locationId;
}