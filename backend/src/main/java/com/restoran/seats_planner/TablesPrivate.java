package com.restoran.seats_planner;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "tables_private")
public class TablesPrivate extends Tables {
    @JsonProperty("near_tv")
    private Boolean nearTv = false;
    @JsonProperty("near_door")
    private Boolean nearDoor = false;
    @JsonProperty("near_window")
    private Boolean nearWindow = false;

    public TablesPrivate() {

    }

    public TablesPrivate(String date, List<LocalTime> plannedTimes, List<String> plannedNames, List<String> plannedPhones, Integer size, Integer row, Integer column) {
        super(date, plannedTimes, plannedNames, plannedPhones, size, row, column);

    }

    public TablesPrivate(String date, List<LocalTime> plannedTimes, List<String> plannedNames, List<String> plannedPhones, Integer size, Integer row, Integer column, Boolean nearTv, Boolean nearDoor, Boolean nearWindow) {
        super(date, plannedTimes, plannedNames, plannedPhones, size, row, column);
        this.nearTv = nearTv;
        this.nearDoor = nearDoor;
        this.nearWindow = nearWindow;
    }

    public Boolean getNearTv() {
        return nearTv;
    }

    public Boolean getNearDoor() {
        return nearDoor;
    }

    public Boolean getNearWindow() {
        return nearWindow;
    }



}
