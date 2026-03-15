package com.restoran.seats_planner;

import java.time.LocalTime;
import java.util.List;

public class Booking {
    private List<LocalTime> bookedTimes;
    private List<String> bookedNames;
    private List<String> bookedPhones;

    public Booking(List<LocalTime> bookedTimes, List<String> bookedNames, List<String> bookedPhones) {
        this.bookedTimes = bookedTimes;
        this.bookedNames = bookedNames;
        this.bookedPhones = bookedPhones;
    }

    public List<LocalTime> getBookedTimes() {
        return bookedTimes;
    }

    public List<String> getBookedNames() {
        return bookedNames;
    }

    public List<String> getBookedPhones() {
        return bookedPhones;
    }
}
