package ua.lviv.iot.db4.jdbclab.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Language {
	private Integer id;
    private String name;
}