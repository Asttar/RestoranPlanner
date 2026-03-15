package com.restoran.seats_planner;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "restaurant_tables")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("date")
    private String date;

    @ElementCollection
    @Column(name = "planned_time")
    @CollectionTable(name = "tables_planned_times", joinColumns = @JoinColumn(name = "table_id"))
    @JsonProperty("planned_times")
    private List<LocalTime> plannedTimes;
    @ElementCollection
    @Column(name = "planned_names")
    @CollectionTable(name = "tables_planned_names", joinColumns = @JoinColumn(name = "table_id"))
    @JsonProperty("planned_names")
    private List<String> plannedNames;
    @ElementCollection
    @Column(name = "planned_phones")
    @CollectionTable(name = "tables_planned_phones", joinColumns = @JoinColumn(name = "table_id"))
    @JsonProperty("planned_phones")
    private List<String> plannedPhones;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("row_table")
    private Integer rowTable;
    @JsonProperty("column_table")
    private Integer columnTable;

    @JsonProperty("chosen")
    private Boolean chosen = false;

    @JsonProperty("availability")
    private String availability;


    public Tables() {

    }

    public void setChosen(Boolean chosen) {
        this.chosen = chosen;
    }

    public Integer getSize() {
        return size;
    }

    public String isAvailableAtTime(LocalTime time) {
        if (plannedTimes.contains(time)) {
            return "unavailable";
        }
        if (plannedTimes.contains(time.plusMinutes(30)) || plannedTimes.contains(time.plusMinutes(60)) || plannedTimes.contains(time.plusMinutes(90)) || plannedTimes.contains(time.plusMinutes(120))) {
            return "unavailable-in-next-2-hours";
        }
        return "available";
    }

    public void setPlannedTimes(List<LocalTime> plannedTimes) {
        this.plannedTimes = plannedTimes;
    }

    public void setPlannedNames(List<String> plannedNames) {
        this.plannedNames = plannedNames;
    }

    public void setPlannedPhones(List<String> plannedPhones) {
        this.plannedPhones = plannedPhones;
    }

    public List<LocalTime> getPlannedTimes() {
        return plannedTimes;
    }

    public List<String> getPlannedNames() {
        return plannedNames;
    }

    public List<String> getPlannedPhones() {
        return plannedPhones;
    }

    public Tables(String date, List<LocalTime> plannedTimes, List<String> plannedNames, List<String> plannedPhones, Integer size, Integer row, Integer column) {
        this.date = date;
        this.plannedTimes = plannedTimes;
        this.plannedNames = plannedNames;
        this.plannedPhones = plannedPhones;
        this.size = size;
        this.rowTable = row;
        this.columnTable = column;
    }

    public boolean getAvailable() {
        return Objects.equals(availability, "available");
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
