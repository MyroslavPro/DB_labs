package ua.lviv.iot.db4.jdbclab.controllers.impl;

import ua.lviv.iot.db4.jdbclab.controllers.GuideController;
import ua.lviv.iot.db4.jdbclab.models.Guide;
import ua.lviv.iot.db4.jdbclab.services.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GuideControllerImpl implements GuideController{
    @Autowired
    private GuideService guideService;

    @Override
    public List<Guide> findAll() {
        return guideService.findAll();
    }

    @Override
    public Optional<Guide> findById(Integer id) {
        return guideService.findById(id);
    }

    @Override
    public int create(Guide client) {
        return guideService.create(client);
    }

    @Override
    public int update(Integer id, Guide guide) {
        return guideService.update(id, guide);
    }

    @Override
    public int delete(Integer id) {
        return guideService.delete(id);
    }

    @Override
    public Optional<Guide> findByGuideName(String guideName) {
        return guideService.findByGuideName(guideName);
    }
    
    @Override
    public Optional<Guide> findByGuideSurname(String guideSurname) {
        return guideService.findByGuideSurname(guideSurname);
    }
    
    @Override
    public Optional<Guide> findByGuideEmail(String guideEmail) {
        return guideService.findByGuideEmail(guideEmail);
    }
}
