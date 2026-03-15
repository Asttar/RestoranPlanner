package com.restoran.seats_planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TablesService {
    @Autowired
    private TablesInsideRepository tablesInsideRepository;
    @Autowired
    private TablesOutsideRepository tablesOutsideRepository;
    @Autowired
    private TablesPrivateRepository tablesPrivateRepository;

    public List<TablesInside> getSeatPlanForInsideAndDate(String date) {
        return tablesInsideRepository.findByDate(date);
    }

    public List<TablesOutside> getSeatPlanForOutsideAndDate(String date) {
        return tablesOutsideRepository.findByDate(date);
    }

    public List<TablesPrivate> getSeatPlanForPrivateAndDate(String date) {
        return tablesPrivateRepository.findByDate(date);
    }

    public <T extends Tables> List<T> filterAvailableTables(List<T> tables) {
        List<T> availableTables = new ArrayList<>();
        for (T table : tables) {
            if (table.getAvailable()) {
                availableTables.add(table);
            }
        }
        return availableTables;
    }

    public <T extends Tables> List<T> planTablesGeneral(List<T> tables, Integer people) {
        List<T> availableTables = filterAvailableTables(tables);
        int starting_table_size = people;
        if (people % 2 != 0) {
            starting_table_size += 1;
        }
        while (starting_table_size <= 10) {
            for (T table : availableTables) {
                if (table.getSize() == starting_table_size) {
                    table.setChosen(true);
                    return tables;
                }
            }
            starting_table_size += 2;
        }
        return tables;
    }


    public List<TablesInside> planTablesForInside(List<TablesInside> tables, LocalTime time, Integer people, Boolean window, Boolean bathroom, Boolean playground) {
        setUpAvailability(tables, time);
        if (!window && !bathroom && !playground) {
            return planTablesGeneral(tables, people);
        }
        List<TablesInside> tables_available = filterAvailableTables(tables);
        if (window) {
            tables_available = filterWindowTables(tables_available);
        }
        if (bathroom) {
            tables_available = filterBathroomTables(tables_available);
        }
        if (playground) {
            tables_available = filterPlaygroundTables(tables_available);
        }

        planTablesGeneral(tables_available, people);
        return tables;
    }

    public List<TablesOutside> planTablesForOutside(List<TablesOutside> tables, LocalTime time, Integer people, Boolean beach, Boolean city, Boolean garden) {
        setUpAvailability(tables, time);
        if (!beach && !city && !garden) {
            return planTablesGeneral(tables, people);
        }
        List<TablesOutside> tables_available = filterAvailableTables(tables);
        if (beach) {
            tables_available = filterBeachViewTables(tables_available);
        }
        if (city) {
            tables_available = filterCityViewTables(tables_available);
        }
        if (garden) {
            tables_available = filterGardenViewTables(tables_available);
        }

        planTablesGeneral(tables_available, people);
        return tables;
    }

    public List<TablesPrivate> planTablesForPrivate(List<TablesPrivate> tables, LocalTime time, Integer people, Boolean tv, Boolean door, Boolean window) {
        setUpAvailability(tables, time);
        if (!tv && !door && !window) {
            return planTablesGeneral(tables, people);
        }
        List<TablesPrivate> tables_available = filterAvailableTables(tables);
        if (tv) {
            tables_available = filterTvTablesPrivate(tables_available);
        }
        if (door) {
            tables_available = filterDoorTablesPrivate(tables_available);
        }
        if (window) {
            tables_available = filterWindowTablesPrivate(tables_available);
        }

        planTablesGeneral(tables_available, people);
        return tables;
    }

    private <T extends Tables> void setUpAvailability(List<T> tables, LocalTime time) {
        for (Tables table : tables) {
            table.setAvailability(table.isAvailableAtTime(time));
        }
    }

    private List<TablesInside> filterWindowTables(List<TablesInside> tablesAvailable) {
        List<TablesInside> windowTables = new ArrayList<>();
        for (TablesInside table : tablesAvailable) {
            if (table.getNearWindow()) {
                windowTables.add(table);
            }
        }
        return windowTables;
    }

    private List<TablesInside> filterBathroomTables(List<TablesInside> tablesAvailable) {
        List<TablesInside> doorTables = new ArrayList<>();
        for (TablesInside table : tablesAvailable) {
            if (table.getNearBathroom()) {
                doorTables.add(table);
            }
        }
        return doorTables;
    }

    private List<TablesInside> filterPlaygroundTables(List<TablesInside> tablesAvailable) {
        List<TablesInside> playgroundTables = new ArrayList<>();
        for (TablesInside table : tablesAvailable) {
            if (table.getNearPlayground()) {
                playgroundTables.add(table);
            }
        }
        return playgroundTables;
    }

    private List<TablesOutside> filterBeachViewTables(List<TablesOutside> tablesAvailable) {
        List<TablesOutside> beachViewTables = new ArrayList<>();
        for (TablesOutside table : tablesAvailable) {
            if (table.getBeachView()) {
                beachViewTables.add(table);
            }
        }
        return beachViewTables;
    }

    private List<TablesOutside> filterCityViewTables(List<TablesOutside> tablesAvailable) {
        List<TablesOutside> cityViewTables = new ArrayList<>();
        for (TablesOutside table : tablesAvailable) {
            if (table.getCityView()) {
                cityViewTables.add(table);
            }
        }
        return cityViewTables;
    }

    private List<TablesOutside> filterGardenViewTables(List<TablesOutside> tablesAvailable) {
        List<TablesOutside> gardenViewTables = new ArrayList<>();
        for (TablesOutside table : tablesAvailable) {
            if (table.getGardenView()) {
                gardenViewTables.add(table);
            }
        }
        return gardenViewTables;
    }

    private List<TablesPrivate> filterWindowTablesPrivate(List<TablesPrivate> tablesAvailable) {
        List<TablesPrivate> windowTables = new ArrayList<>();
        for (TablesPrivate table : tablesAvailable) {
            if (table.getNearWindow()) {
                windowTables.add(table);
            }
        }
        return windowTables;
    }

    private List<TablesPrivate> filterDoorTablesPrivate(List<TablesPrivate> tablesAvailable) {
        List<TablesPrivate> doorTables = new ArrayList<>();
        for (TablesPrivate table : tablesAvailable) {
            if (table.getNearDoor()) {
                doorTables.add(table);
            }
        }
        return doorTables;
    }

    private List<TablesPrivate> filterTvTablesPrivate(List<TablesPrivate> tablesAvailable) {
        List<TablesPrivate> tvTables = new ArrayList<>();
        for (TablesPrivate table : tablesAvailable) {
            if (table.getNearTv()) {
                tvTables.add(table);
            }
        }
        return tvTables;
    }

    public void bookSeatForInside(Integer id, LocalTime time, String name, String phone) {
        Optional<TablesInside> tableById = tablesInsideRepository.findById(Long.valueOf(id));
        if (tableById.isPresent()) {
            TablesInside table  = tableById.get();

            List<LocalTime> plannedTimes = table.getPlannedTimes();
            plannedTimes.add(time);
            plannedTimes.add(time.plusMinutes(30));
            plannedTimes.add(time.plusMinutes(60));
            plannedTimes.add(time.plusMinutes(90));
            table.setPlannedTimes(plannedTimes);

            List<String> plannedNames = table.getPlannedNames();
            plannedNames.add(name);
            plannedNames.add(name);
            plannedNames.add(name);
            plannedNames.add(name);
            table.setPlannedNames(plannedNames);

            List<String> plannedPhones = table.getPlannedPhones();
            plannedPhones.add(phone);
            plannedPhones.add(phone);
            plannedPhones.add(phone);
            plannedPhones.add(phone);
            table.setPlannedPhones(plannedPhones);

            tablesInsideRepository.save(table);
        }

    }

    public void bookSeatForOutside(Integer id, LocalTime time, String name, String phone) {
        Optional<TablesOutside> tableById = tablesOutsideRepository.findById(Long.valueOf(id));
        if (tableById.isPresent()) {
            TablesOutside table  = tableById.get();

            List<LocalTime> plannedTimes = table.getPlannedTimes();
            plannedTimes.add(time);
            plannedTimes.add(time.plusMinutes(30));
            plannedTimes.add(time.plusMinutes(60));
            plannedTimes.add(time.plusMinutes(90));
            table.setPlannedTimes(plannedTimes);

            List<String> plannedNames = table.getPlannedNames();
            plannedNames.add(name);
            plannedNames.add(name);
            plannedNames.add(name);
            plannedNames.add(name);
            table.setPlannedNames(plannedNames);

            List<String> plannedPhones = table.getPlannedPhones();
            plannedPhones.add(phone);
            plannedPhones.add(phone);
            plannedPhones.add(phone);
            plannedPhones.add(phone);
            table.setPlannedPhones(plannedPhones);

            tablesOutsideRepository.save(table);
        }

    }

    public void bookSeatForPrivate(Integer id, LocalTime time, String name, String phone) {
        Optional<TablesPrivate> tableById = tablesPrivateRepository.findById(Long.valueOf(id));
        if (tableById.isPresent()) {
            TablesPrivate table  = tableById.get();

            List<LocalTime> plannedTimes = table.getPlannedTimes();
            plannedTimes.add(time);
            plannedTimes.add(time.plusMinutes(30));
            plannedTimes.add(time.plusMinutes(60));
            plannedTimes.add(time.plusMinutes(90));
            table.setPlannedTimes(plannedTimes);

            List<String> plannedNames = table.getPlannedNames();
            plannedNames.add(name);
            plannedNames.add(name);
            plannedNames.add(name);
            plannedNames.add(name);
            table.setPlannedNames(plannedNames);

            List<String> plannedPhones = table.getPlannedPhones();
            plannedPhones.add(phone);
            plannedPhones.add(phone);
            plannedPhones.add(phone);
            plannedPhones.add(phone);
            table.setPlannedPhones(plannedPhones);

            tablesPrivateRepository.save(table);
        }

    }
}
