package com.restoran.seats_planner;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "tables_inside")
public class TablesInside extends Tables {

    @JsonProperty("near_window")
    private Boolean nearWindow = false;
    @JsonProperty("near_bathroom")
    private Boolean nearBathroom = false;
    @JsonProperty("near_playground")
    private Boolean nearPlayground = false;

    public TablesInside() {

    }

    public TablesInside(String date, List<LocalTime> plannedTimes, List<String> plannedNames, List<String> plannedPhones, Integer size, Integer row, Integer column) {
        super(date, plannedTimes, plannedNames, plannedPhones, size, row, column);

    }

    public TablesInside(String date, List<LocalTime> plannedTimes, List<String> plannedNames, List<String> plannedPhones, Integer size, Integer row, Integer column, Boolean nearWindow, Boolean nearBathroom, Boolean nearPlayground) {
        super(date, plannedTimes, plannedNames, plannedPhones, size, row, column);
        this.nearWindow = nearWindow;
        this.nearBathroom = nearBathroom;
        this.nearPlayground = nearPlayground;
    }

    public Boolean getNearWindow() {
        return nearWindow;
    }

    public Boolean getNearBathroom() {
        return nearBathroom;
    }

    public Boolean getNearPlayground() {
        return nearPlayground;
    }
}
