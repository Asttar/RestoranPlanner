package com.restoran.seats_planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/tables")
@CrossOrigin(origins = "http://localhost:8080")
public class TablesController {
    @Autowired
    private TablesService tablesService;

    @GetMapping("/inside")
    public List<TablesInside> getSeatPlanForInsideAndDateAndTime(@RequestParam String date, @RequestParam String time, @RequestParam Integer people, @RequestParam Boolean window, @RequestParam Boolean door, @RequestParam Boolean playground) {
        return tablesService.planTablesForInside(tablesService.getSeatPlanForInsideAndDate(date), LocalTime.parse(time), people, window, door, playground);
    }

    @GetMapping("/inside/view")
    public List<TablesInside> viewSeatPlanForInsideAndDate(@RequestParam String date) {
        return tablesService.getSeatPlanForInsideAndDate(date);
    }

    @GetMapping("/outside")
    public List<TablesOutside> getSeatPlanForOutsideAndDateAndTime(@RequestParam String date, @RequestParam String time, @RequestParam Integer people, @RequestParam Boolean beach, @RequestParam Boolean city, @RequestParam Boolean garden) {
        return tablesService.planTablesForOutside(tablesService.getSeatPlanForOutsideAndDate(date), LocalTime.parse(time), people, beach, city, garden);
    }

    @GetMapping("/outside/view")
    public List<TablesOutside> viewSeatPlanForOutsideAndDate(@RequestParam String date) {
        return tablesService.getSeatPlanForOutsideAndDate(date);
    }

    @GetMapping("/private")
    public List<TablesPrivate> getSeatPlanForPrivateAndDateAndTime(@RequestParam String date, @RequestParam String time, @RequestParam Integer people, @RequestParam Boolean tv, @RequestParam Boolean door, @RequestParam Boolean window) {
        return tablesService.planTablesForPrivate(tablesService.getSeatPlanForPrivateAndDate(date), LocalTime.parse(time), people, tv, door, window);
    }

    @GetMapping("/private/view")
    public List<TablesPrivate> viewSeatPlanForPrivateAndDate(@RequestParam String date) {
        return tablesService.getSeatPlanForPrivateAndDate(date);
    }

    @PutMapping("/inside/book")
    public void bookSeatForInside(@RequestParam Integer id, @RequestParam String time, @RequestParam String name, @RequestParam String phone) {
        tablesService.bookSeatForInside(id, LocalTime.parse(time), name, phone);
    }

    @PutMapping("/outside/book")
    public void bookSeatForOutside(@RequestParam Integer id, @RequestParam String time, @RequestParam String name, @RequestParam String phone) {
        tablesService.bookSeatForOutside(id, LocalTime.parse(time), name, phone);
    }

    @PutMapping("/private/book")
    public void bookSeatForPrivate(@RequestParam Integer id, @RequestParam String time, @RequestParam String name, @RequestParam String phone) {
        tablesService.bookSeatForPrivate(id, LocalTime.parse(time), name, phone);
    }

}
