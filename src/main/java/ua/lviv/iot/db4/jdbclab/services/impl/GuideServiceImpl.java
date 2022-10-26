package ua.lviv.iot.db4.jdbclab.services.impl;

import ua.lviv.iot.db4.jdbclab.dao.GuideDao;
import ua.lviv.iot.db4.jdbclab.models.Guide;
import ua.lviv.iot.db4.jdbclab.services.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuideServiceImpl implements GuideService {
    @Autowired
    private GuideDao guideDao;

    @Override
    public List<Guide> findAll() {
        return guideDao.findAll();
    }

    @Override
    public Optional<Guide> findById(Integer id) {
        return guideDao.findById(id);
    }

    @Override
    public int create(Guide client) {
        return guideDao.create(client);
    }

    @Override
    public int update(Integer id, Guide guide) {
        return guideDao.update(id, guide);
    }

    @Override
    public int delete(Integer id) {
        return guideDao.delete(id);
    }

    @Override
    public Optional<Guide> findByGuideName(String guideName) {
        return guideDao.findByGuideName(guideName);
    }
    
    @Override
    public Optional<Guide> findByGuideSurname(String guideSurname) {
        return guideDao.findByGuideSurname(guideSurname);
    }
    
    @Override
    public Optional<Guide> findByGuideEmail(String guideEmail) {
        return guideDao.findByGuideEmail(guideEmail);
    }
}
