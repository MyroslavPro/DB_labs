package ua.lviv.iot.db4.jdbclab.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Guide {
	private Integer id;
    private String name;
    private String surname;
    private String email;
    private Integer countryId;
    private Integer phoneId;
}