package com.restoran.seats_planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private TablesInsideRepository tablesInsideRepository;
    @Autowired
    private TablesOutsideRepository tablesOutsideRepository;
    @Autowired
    private TablesPrivateRepository tablesPrivateRepository;


    @Override
    public void run(String... args) throws Exception {
        //tablesInsideRepository.deleteAll();
        //tablesOutsideRepository.deleteAll();
        //tablesPrivateRepository.deleteAll();
        if (tablesInsideRepository.count() != 0 && tablesOutsideRepository.count() != 0 && tablesPrivateRepository.count() != 0) {
            return;
        }

        List<TablesInside> tablesInsideList = new ArrayList<>();
        List<TablesOutside> tablesOutsideList = new ArrayList<>();
        List<TablesPrivate> tablesPrivateList = new ArrayList<>();
        LocalDate date = LocalDate.now();
        for (int i = 0; i < 30; i++) {

            Booking booking1 = randomlyBooked(date);
            TablesInside table1 = new TablesInside(date.toString(), booking1.getBookedTimes(), booking1.getBookedNames(), booking1.getBookedPhones(), 2, 1, 1, true, false, false);
            Booking booking2 = randomlyBooked(date);
            TablesInside table2 = new TablesInside(date.toString(), booking2.getBookedTimes(), booking2.getBookedNames(), booking2.getBookedPhones(), 2, 2, 1, true, false, false);
            Booking booking3 = randomlyBooked(date);
            TablesInside table3 = new TablesInside(date.toString(), booking3.getBookedTimes(), booking3.getBookedNames(), booking3.getBookedPhones(), 4, 3, 1, true, false, false);
            Booking booking4 = randomlyBooked(date);
            TablesInside table4 = new TablesInside(date.toString(), booking4.getBookedTimes(), booking4.getBookedNames(), booking4.getBookedPhones(), 4, 4, 1, true, false, false);
            Booking booking5 = randomlyBooked(date);
            TablesInside table5 = new TablesInside(date.toString(), booking5.getBookedTimes(), booking5.getBookedNames(), booking5.getBookedPhones(), 2, 5, 1, true, false, false);
            Booking booking6 = randomlyBooked(date);
            TablesInside table6 = new TablesInside(date.toString(), booking6.getBookedTimes(), booking6.getBookedNames(), booking6.getBookedPhones(), 2, 6, 1, true, false, false);
            Booking booking7 = randomlyBooked(date);
            TablesInside table7 = new TablesInside(date.toString(), booking7.getBookedTimes(), booking7.getBookedNames(), booking7.getBookedPhones(), 4, 1, 3, false, true, false);
            Booking booking8 = randomlyBooked(date);
            TablesInside table8 = new TablesInside(date.toString(), booking8.getBookedTimes(), booking8.getBookedNames(), booking8.getBookedPhones(), 4, 2, 3, false, true, false);
            Booking booking9 = randomlyBooked(date);
            TablesInside table9 = new TablesInside(date.toString(), booking9.getBookedTimes(), booking9.getBookedNames(), booking9.getBookedPhones(), 6, 3, 3);
            Booking booking10 = randomlyBooked(date);
            TablesInside table10 = new TablesInside(date.toString(), booking10.getBookedTimes(), booking10.getBookedNames(), booking10.getBookedPhones(), 6, 4, 3);
            Booking booking11 = randomlyBooked(date);
            TablesInside table11 = new TablesInside(date.toString(), booking11.getBookedTimes(), booking11.getBookedNames(), booking11.getBookedPhones(), 4, 5, 3);
            Booking booking12 = randomlyBooked(date);
            TablesInside table12 = new TablesInside(date.toString(), booking12.getBookedTimes(), booking12.getBookedNames(), booking12.getBookedPhones(), 8, 6, 3);
            Booking booking13 = randomlyBooked(date);
            TablesInside table13 = new TablesInside(date.toString(), booking13.getBookedTimes(), booking13.getBookedNames(), booking13.getBookedPhones(), 4, 1, 5, false, true, false);
            Booking booking14 = randomlyBooked(date);
            TablesInside table14 = new TablesInside(date.toString(), booking14.getBookedTimes(), booking14.getBookedNames(), booking14.getBookedPhones(), 4, 2, 5, false, true, false);
            Booking booking15 = randomlyBooked(date);
            TablesInside table15 = new TablesInside(date.toString(), booking15.getBookedTimes(), booking15.getBookedNames(), booking15.getBookedPhones(), 2, 3, 5);
            Booking booking16 = randomlyBooked(date);
            TablesInside table16 = new TablesInside(date.toString(), booking16.getBookedTimes(), booking16.getBookedNames(), booking16.getBookedPhones(), 8, 4, 5);
            Booking booking17 = randomlyBooked(date);
            TablesInside table17 = new TablesInside(date.toString(), booking17.getBookedTimes(), booking17.getBookedNames(), booking17.getBookedPhones(), 2, 5, 5);
            Booking booking18 = randomlyBooked(date);
            TablesInside table18 = new TablesInside(date.toString(), booking18.getBookedTimes(), booking18.getBookedNames(), booking18.getBookedPhones(), 4, 6, 5);
            Booking booking19 = randomlyBooked(date);
            TablesInside table19 = new TablesInside(date.toString(), booking19.getBookedTimes(), booking19.getBookedNames(), booking19.getBookedPhones(), 2, 1, 7);
            Booking booking20 = randomlyBooked(date);
            TablesInside table20 = new TablesInside(date.toString(), booking20.getBookedTimes(), booking20.getBookedNames(), booking20.getBookedPhones(), 4, 2, 7);
            Booking booking21 = randomlyBooked(date);
            TablesInside table21 = new TablesInside(date.toString(), booking21.getBookedTimes(), booking21.getBookedNames(), booking21.getBookedPhones(), 4, 3, 7);
            Booking booking22 = randomlyBooked(date);
            TablesInside table22 = new TablesInside(date.toString(), booking22.getBookedTimes(), booking22.getBookedNames(), booking22.getBookedPhones(), 4, 4, 7);
            Booking booking23 = randomlyBooked(date);
            TablesInside table23 = new TablesInside(date.toString(), booking23.getBookedTimes(), booking23.getBookedNames(), booking23.getBookedPhones(), 4, 5, 7);
            Booking booking24 = randomlyBooked(date);
            TablesInside table24 = new TablesInside(date.toString(), booking24.getBookedTimes(), booking24.getBookedNames(), booking24.getBookedPhones(), 2, 6, 7);
            Booking booking25 = randomlyBooked(date);
            TablesInside table25 = new TablesInside(date.toString(), booking25.getBookedTimes(), booking25.getBookedNames(), booking25.getBookedPhones(), 4, 1, 8, false, false, true);
            Booking booking26 = randomlyBooked(date);
            TablesInside table26 = new TablesInside(date.toString(), booking26.getBookedTimes(), booking26.getBookedNames(), booking26.getBookedPhones(), 6, 2, 8, false, false, true);
            Booking booking27 = randomlyBooked(date);
            TablesInside table27 = new TablesInside(date.toString(), booking27.getBookedTimes(), booking27.getBookedNames(), booking27.getBookedPhones(), 6, 3, 8, false, false, true);
            Booking booking28 = randomlyBooked(date);
            TablesInside table28 = new TablesInside(date.toString(), booking28.getBookedTimes(), booking28.getBookedNames(), booking28.getBookedPhones(), 6, 3, 9, false, false, true);
            Booking booking29 = randomlyBooked(date);
            TablesInside table29 = new TablesInside(date.toString(), booking29.getBookedTimes(), booking29.getBookedNames(), booking29.getBookedPhones(), 4, 3, 10, false, false, true);

            tablesInsideList.add(table1);
            tablesInsideList.add(table2);
            tablesInsideList.add(table3);
            tablesInsideList.add(table4);
            tablesInsideList.add(table5);
            tablesInsideList.add(table6);
            tablesInsideList.add(table7);
            tablesInsideList.add(table8);
            tablesInsideList.add(table9);
            tablesInsideList.add(table10);
            tablesInsideList.add(table11);
            tablesInsideList.add(table12);
            tablesInsideList.add(table13);
            tablesInsideList.add(table14);
            tablesInsideList.add(table15);
            tablesInsideList.add(table16);
            tablesInsideList.add(table17);
            tablesInsideList.add(table18);
            tablesInsideList.add(table19);
            tablesInsideList.add(table20);
            tablesInsideList.add(table21);
            tablesInsideList.add(table22);
            tablesInsideList.add(table23);
            tablesInsideList.add(table24);
            tablesInsideList.add(table25);
            tablesInsideList.add(table26);
            tablesInsideList.add(table27);
            tablesInsideList.add(table28);
            tablesInsideList.add(table29);

            Booking booking30 = randomlyBooked(date);
            TablesOutside table30 = new TablesOutside(date.toString(), booking30.getBookedTimes(), booking30.getBookedNames(), booking30.getBookedPhones(), 2, 1, 1, true, false, false);
            Booking booking31 = randomlyBooked(date);
            TablesOutside table31 = new TablesOutside(date.toString(), booking31.getBookedTimes(), booking31.getBookedNames(), booking31.getBookedPhones(), 4, 2, 1);
            Booking booking32 = randomlyBooked(date);
            TablesOutside table32 = new TablesOutside(date.toString(), booking32.getBookedTimes(), booking32.getBookedNames(), booking32.getBookedPhones(), 4, 3, 1);
            Booking booking33 = randomlyBooked(date);
            TablesOutside table33 = new TablesOutside(date.toString(), booking33.getBookedTimes(), booking33.getBookedNames(), booking33.getBookedPhones(), 2, 4, 1);
            Booking booking34 = randomlyBooked(date);
            TablesOutside table34 = new TablesOutside(date.toString(), booking34.getBookedTimes(), booking34.getBookedNames(), booking34.getBookedPhones(), 2, 5, 1);
            Booking booking35 = randomlyBooked(date);
            TablesOutside table35 = new TablesOutside(date.toString(), booking35.getBookedTimes(), booking35.getBookedNames(), booking35.getBookedPhones(), 4, 6, 1, false, false, true);

            Booking booking36 = randomlyBooked(date);
            TablesOutside table36 = new TablesOutside(date.toString(), booking36.getBookedTimes(), booking36.getBookedNames(), booking36.getBookedPhones(), 8, 1, 3, true, false, false);
            Booking booking37 = randomlyBooked(date);
            TablesOutside table37 = new TablesOutside(date.toString(), booking37.getBookedTimes(), booking37.getBookedNames(), booking37.getBookedPhones(), 4, 2, 3);
            Booking booking38 = randomlyBooked(date);
            TablesOutside table38 = new TablesOutside(date.toString(), booking38.getBookedTimes(), booking38.getBookedNames(), booking38.getBookedPhones(), 2, 3, 3);
            Booking booking39 = randomlyBooked(date);
            TablesOutside table39 = new TablesOutside(date.toString(), booking39.getBookedTimes(), booking39.getBookedNames(), booking39.getBookedPhones(), 6, 4, 3);
            Booking booking40 = randomlyBooked(date);
            TablesOutside table40 = new TablesOutside(date.toString(), booking40.getBookedTimes(), booking40.getBookedNames(), booking40.getBookedPhones(), 2, 5, 3);
            Booking booking41 = randomlyBooked(date);
            TablesOutside table41 = new TablesOutside(date.toString(), booking41.getBookedTimes(), booking41.getBookedNames(), booking41.getBookedPhones(), 6, 6, 3, false, false, true);

            Booking booking42 = randomlyBooked(date);
            TablesOutside table42 = new TablesOutside(date.toString(), booking42.getBookedTimes(), booking42.getBookedNames(), booking42.getBookedPhones(), 6, 1, 5, true, true, false);
            Booking booking43 = randomlyBooked(date);
            TablesOutside table43 = new TablesOutside(date.toString(), booking43.getBookedTimes(), booking43.getBookedNames(), booking43.getBookedPhones(), 4, 2, 5, false, true, false);
            Booking booking44 = randomlyBooked(date);
            TablesOutside table44 = new TablesOutside(date.toString(), booking44.getBookedTimes(), booking44.getBookedNames(), booking44.getBookedPhones(), 2, 3, 5, false, true, false);
            Booking booking45 = randomlyBooked(date);
            TablesOutside table45 = new TablesOutside(date.toString(), booking45.getBookedTimes(), booking45.getBookedNames(), booking45.getBookedPhones(), 4, 4, 5, false, true, false);
            Booking booking46 = randomlyBooked(date);
            TablesOutside table46 = new TablesOutside(date.toString(), booking46.getBookedTimes(), booking46.getBookedNames(), booking46.getBookedPhones(), 2, 5, 5, false, true, false);
            Booking booking47 = randomlyBooked(date);
            TablesOutside table47 = new TablesOutside(date.toString(), booking47.getBookedTimes(), booking47.getBookedNames(), booking47.getBookedPhones(), 8, 6, 5, false, true, true);

            tablesOutsideList.add(table30);
            tablesOutsideList.add(table31);
            tablesOutsideList.add(table32);
            tablesOutsideList.add(table33);
            tablesOutsideList.add(table34);
            tablesOutsideList.add(table35);
            tablesOutsideList.add(table36);
            tablesOutsideList.add(table37);
            tablesOutsideList.add(table38);
            tablesOutsideList.add(table39);
            tablesOutsideList.add(table40);
            tablesOutsideList.add(table41);
            tablesOutsideList.add(table42);
            tablesOutsideList.add(table43);
            tablesOutsideList.add(table44);
            tablesOutsideList.add(table45);
            tablesOutsideList.add(table46);
            tablesOutsideList.add(table47);

            Booking booking48 = randomlyBooked(date);
            TablesPrivate table48 = new TablesPrivate(date.toString(), booking48.getBookedTimes(), booking48.getBookedNames(), booking48.getBookedPhones(), 8, 1, 1, false, false, true);
            Booking booking49 = randomlyBooked(date);
            TablesPrivate table49 = new TablesPrivate(date.toString(), booking49.getBookedTimes(), booking49.getBookedNames(), booking49.getBookedPhones(), 2, 2, 1, false, false, true);
            Booking booking50 = randomlyBooked(date);
            TablesPrivate table50 = new TablesPrivate(date.toString(), booking50.getBookedTimes(), booking50.getBookedNames(), booking50.getBookedPhones(), 8, 3, 1, false, false, true);
            Booking booking51 = randomlyBooked(date);
            TablesPrivate table51 = new TablesPrivate(date.toString(), booking51.getBookedTimes(), booking51.getBookedNames(), booking51.getBookedPhones(), 2, 4, 1, false, false, true);
            Booking booking52 = randomlyBooked(date);
            TablesPrivate table52 = new TablesPrivate(date.toString(), booking52.getBookedTimes(), booking52.getBookedNames(), booking52.getBookedPhones(), 2, 5, 1, false, true, true);
            Booking booking53 = randomlyBooked(date);
            TablesPrivate table53 = new TablesPrivate(date.toString(), booking53.getBookedTimes(), booking53.getBookedNames(), booking53.getBookedPhones(), 6, 6, 1, false, true, true);

            Booking booking54 = randomlyBooked(date);
            TablesPrivate table54 = new TablesPrivate(date.toString(), booking54.getBookedTimes(), booking54.getBookedNames(), booking54.getBookedPhones(), 6, 1, 3);
            Booking booking55 = randomlyBooked(date);
            TablesPrivate table55 = new TablesPrivate(date.toString(), booking55.getBookedTimes(), booking55.getBookedNames(), booking55.getBookedPhones(), 4, 2, 3, true, false, false);
            Booking booking56 = randomlyBooked(date);
            TablesPrivate table56 = new TablesPrivate(date.toString(), booking56.getBookedTimes(), booking56.getBookedNames(), booking56.getBookedPhones(), 2, 3, 3, true, false, false);
            Booking booking57 = randomlyBooked(date);
            TablesPrivate table57 = new TablesPrivate(date.toString(), booking57.getBookedTimes(), booking57.getBookedNames(), booking57.getBookedPhones(), 8, 4, 3, true, false, false);
            Booking booking58 = randomlyBooked(date);
            TablesPrivate table58 = new TablesPrivate(date.toString(), booking58.getBookedTimes(), booking58.getBookedNames(), booking58.getBookedPhones(), 2, 5, 3, false, true, false);
            Booking booking59 = randomlyBooked(date);
            TablesPrivate table59 = new TablesPrivate(date.toString(), booking59.getBookedTimes(), booking59.getBookedNames(), booking59.getBookedPhones(), 4, 6, 3, false, true, false);

            tablesPrivateList.add(table48);
            tablesPrivateList.add(table49);
            tablesPrivateList.add(table50);
            tablesPrivateList.add(table51);
            tablesPrivateList.add(table52);
            tablesPrivateList.add(table53);
            tablesPrivateList.add(table54);
            tablesPrivateList.add(table55);
            tablesPrivateList.add(table56);
            tablesPrivateList.add(table57);
            tablesPrivateList.add(table58);
            tablesPrivateList.add(table59);

            date = date.plusDays(1);
        }


        tablesInsideRepository.saveAll(tablesInsideList);
        tablesOutsideRepository.saveAll(tablesOutsideList);
        tablesPrivateRepository.saveAll(tablesPrivateList);
    }

    private Booking randomlyBooked(LocalDate date) {
        List<LocalTime> bookedTimes = new ArrayList<>();
        List<String> bookedNames = new ArrayList<>();
        List<String> bookedPhones = new ArrayList<>();
        LocalTime[] times = {LocalTime.parse("09:00"), LocalTime.parse("09:30"), LocalTime.parse("10:00"),
                LocalTime.parse("10:30"), LocalTime.parse("11:00"), LocalTime.parse("11:30"),
                LocalTime.parse("12:00"), LocalTime.parse("12:30"), LocalTime.parse("13:00"),
                LocalTime.parse("13:30"), LocalTime.parse("14:00"), LocalTime.parse("14:30"),
                LocalTime.parse("15:00"), LocalTime.parse("15:30"), LocalTime.parse("16:00"),
                LocalTime.parse("16:30"), LocalTime.parse("17:00"), LocalTime.parse("17:30"),
                LocalTime.parse("18:00"), LocalTime.parse("18:30"), LocalTime.parse("19:00"),
                LocalTime.parse("19:30"), LocalTime.parse("20:00"), LocalTime.parse("20:30"),
                LocalTime.parse("21:00"), LocalTime.parse("21:30"), LocalTime.parse("22:00"),
                LocalTime.parse("22:30"), LocalTime.parse("23:00")};

        String[][] namesAndPhones = {{"Alice Smith", "5055 1234"}, {"Brian Potter", "5079 2431"}, {"John Walker", "5127 3712"},
                {"Jack Sparrow", "5401 6195"}, {"Jonathan Astmae", "5067 6767"}, {"Mia Brown", "5653 6405"},
                {"Bill White", "5514 4139"}, {"Harry Black", "5581 9417"}, {"Alastor Banner", "5653 6405"},
                {"Walter Jones", "5401 6195"}, {"Jesse Johnson", "5067 6767"}, {"Charlie Parker", "5653 6405"},};

        boolean isWeekend = date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7;

        if (isWeekend) {
            for (int i = 4; i < times.length - 8; i++) {
                double random = Math.random();
                if (random <= 0.1) {
                    bookedTimes.add(times[i]);
                    bookedTimes.add(times[i + 1]);
                    bookedTimes.add(times[i + 2]);
                    bookedTimes.add(times[i + 3]);

                    int randomNameAndPhoneIndex = (int) (Math.random() * namesAndPhones.length);
                    bookedNames.add(namesAndPhones[randomNameAndPhoneIndex][0]);
                    bookedNames.add(namesAndPhones[randomNameAndPhoneIndex][0]);
                    bookedNames.add(namesAndPhones[randomNameAndPhoneIndex][0]);
                    bookedNames.add(namesAndPhones[randomNameAndPhoneIndex][0]);

                    bookedPhones.add(namesAndPhones[randomNameAndPhoneIndex][1]);
                    bookedPhones.add(namesAndPhones[randomNameAndPhoneIndex][1]);
                    bookedPhones.add(namesAndPhones[randomNameAndPhoneIndex][1]);
                    bookedPhones.add(namesAndPhones[randomNameAndPhoneIndex][1]);

                    i += 3;
                }
            }
        } else {
            for (int i = 0; i < times.length - 4; i++) {
                double random = Math.random();
                if (random <= 0.1) {
                    bookedTimes.add(times[i]);
                    bookedTimes.add(times[i + 1]);
                    bookedTimes.add(times[i + 2]);
                    bookedTimes.add(times[i + 3]);

                    int randomNameAndPhoneIndex = (int) (Math.random() * namesAndPhones.length);
                    bookedNames.add(namesAndPhones[randomNameAndPhoneIndex][0]);
                    bookedNames.add(namesAndPhones[randomNameAndPhoneIndex][0]);
                    bookedNames.add(namesAndPhones[randomNameAndPhoneIndex][0]);
                    bookedNames.add(namesAndPhones[randomNameAndPhoneIndex][0]);

                    bookedPhones.add(namesAndPhones[randomNameAndPhoneIndex][1]);
                    bookedPhones.add(namesAndPhones[randomNameAndPhoneIndex][1]);
                    bookedPhones.add(namesAndPhones[randomNameAndPhoneIndex][1]);
                    bookedPhones.add(namesAndPhones[randomNameAndPhoneIndex][1]);

                    i += 3;
                }
            }
        }
        Booking booking = new Booking(bookedTimes, bookedNames, bookedPhones);
        return booking;
    }
}
