package ua.lviv.iot.db4.jdbclab.dao.impl;

import ua.lviv.iot.db4.jdbclab.dao.LanguageDao;
import ua.lviv.iot.db4.jdbclab.models.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LanguageDaoImpl implements LanguageDao {
    private static final String FIND_ALL = "SELECT * FROM language";
    private static final String CREATE = "INSERT language(name) VALUES (?)";
    private static final String UPDATE = "UPDATE language SET name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM language WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM language WHERE id=?";
    private static final String FIND_BY_LANGUAGE_NAME = "SELECT * FROM language WHERE name=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Language> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Language.class));
    }

    @Override
    public Optional<Language> findById(Integer id) {
        Optional<Language> language;
        try {
        	language = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Language.class), id));
        } catch (EmptyResultDataAccessException e) {
        	language = Optional.empty();
        }
        return language;
    }

    @Override
    public int create(Language language) {
        return jdbcTemplate.update(CREATE, language.getName());
    }

    @Override
    public int update(Integer id, Language language) {
        return jdbcTemplate.update(UPDATE, language.getName(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<Language> findByLanguageName(String languageName) {
        Optional<Language> language;
        try {
        	language = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_LANGUAGE_NAME,
                    BeanPropertyRowMapper.newInstance(Language.class), languageName));
        } catch (EmptyResultDataAccessException e) {
        	language = Optional.empty();
        }
        return language;
    }

}
