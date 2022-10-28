package ua.lviv.iot.db4.jdbclab.dao.impl;

import ua.lviv.iot.db4.jdbclab.dao.GuideDao;
import ua.lviv.iot.db4.jdbclab.models.Guide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GuideDaoImpl implements GuideDao {
    private static final String FIND_ALL = "SELECT * FROM guide";
    private static final String CREATE = "INSERT guide(name, surname, email, country_id, phone_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE guide SET name=?, surname=?, email=?, country_id=?, phone_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM guide WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM guide WHERE id=?";
    private static final String FIND_BY_GUIDE_NAME = "SELECT * FROM guide WHERE name=?";
    private static final String FIND_BY_GUIDE_SURNAME = "SELECT * FROM guide WHERE surname=?";
    private static final String FIND_BY_GUIDE_EMAIL = "SELECT * FROM guide WHERE email=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Guide> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Guide.class));
    }

    @Override
    public Optional<Guide> findById(Integer id) {
        Optional<Guide> guide;
        try {
        	guide = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Guide.class), id));
        } catch (EmptyResultDataAccessException e) {
        	guide = Optional.empty();
        }
        return guide;
    }

    @Override
    public int create(Guide guide) {
        return jdbcTemplate.update(CREATE, guide.getName(), guide.getSurname(), guide.getEmail(), guide.getCountryId(), guide.getPhoneId());
    }

    @Override
    public int update(Integer id, Guide guide) {
        return jdbcTemplate.update(UPDATE, guide.getName(), guide.getSurname(), guide.getEmail(), guide.getCountryId(), guide.getPhoneId(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    
    @Override
    public Optional<Guide> findByGuideName(String guideName) {
        Optional<Guide> guide;
        try {
        	guide = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_GUIDE_NAME,
                    BeanPropertyRowMapper.newInstance(Guide.class), guideName));
        } catch (EmptyResultDataAccessException e) {
        	guide = Optional.empty();
        }
        return guide;
    }
    
    @Override
    public Optional<Guide> findByGuideSurname(String guideSurname) {
        Optional<Guide> guide;
        try {
        	guide = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_GUIDE_SURNAME,
                    BeanPropertyRowMapper.newInstance(Guide.class), guideSurname));
        } catch (EmptyResultDataAccessException e) {
        	guide = Optional.empty();
        }
        return guide;
    }
    
    @Override
    public Optional<Guide> findByGuideEmail(String guideEmail) {
        Optional<Guide> guide;
        try {
        	guide = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_GUIDE_EMAIL,
                    BeanPropertyRowMapper.newInstance(Guide.class), guideEmail));
        } catch (EmptyResultDataAccessException e) {
        	guide = Optional.empty();
        }
        return guide;
    }

}
