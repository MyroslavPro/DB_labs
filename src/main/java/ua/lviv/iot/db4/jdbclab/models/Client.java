package ua.lviv.iot.db4.jdbclab.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {
	private Integer id;
    private String name;
    private Integer countryId;
    private Integer languageId;
    private Integer phoneId;
}