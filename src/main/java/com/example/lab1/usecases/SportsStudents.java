package com.example.lab1.usecases;

import com.example.lab1.entities.Sport;
import com.example.lab1.persistence.SportsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;

@Model
public class SportsStudents {
    @Inject
    private SportsDAO sportsDAO;

    @Getter @Setter
    private Sport sport;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long sportId = Long.parseLong(requestParameters.get("sportId"));
        this.sport = sportsDAO.findById(sportId);
    }
}
