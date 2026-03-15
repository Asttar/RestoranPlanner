package com.restoran.seats_planner;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "tables_outside")
public class TablesOutside extends Tables {
    @JsonProperty("beach_view")
    private Boolean beachView = false;
    @JsonProperty("city_view")
    private Boolean cityView = false;
    @JsonProperty("garden_view")
    private Boolean gardenView = false;

    public TablesOutside() {

    }

    public TablesOutside(String date, List<LocalTime> plannedTimes, List<String> plannedNames, List<String> plannedPhones, Integer size, Integer row, Integer column) {
        super(date, plannedTimes, plannedNames, plannedPhones, size, row, column);

    }

    public Boolean getBeachView() {
        return beachView;
    }

    public Boolean getCityView() {
        return cityView;
    }

    public Boolean getGardenView() {
        return gardenView;
    }

    public TablesOutside(String date, List<LocalTime> plannedTimes, List<String> plannedNames, List<String> plannedPhones, Integer size, Integer row, Integer column, Boolean beachView, Boolean cityView, Boolean gardenView) {
        super(date, plannedTimes, plannedNames, plannedPhones, size, row, column);
        this.beachView = beachView;
        this.cityView = cityView;
        this.gardenView = gardenView;
    }
}
