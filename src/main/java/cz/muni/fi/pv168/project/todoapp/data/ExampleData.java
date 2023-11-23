package cz.muni.fi.pv168.project.todoapp.data;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public class ExampleData {
    private static final Category socialCat = new Category("Social", CategoryColor.RED);
    private static final Category sportCat = new Category("Sport", CategoryColor.BLUE);
    private static final Category foodCat = new Category("Food", CategoryColor.PINK);
    private static final Category schoolCat = new Category("School", CategoryColor.YELLOW);

    public static Collection<Event> getEvents() {
        return List.of(
                new Event(false,
                        "Tennis",
                        List.of(sportCat),
                        "Tennis Hall Lužánky",
                        LocalDate.of(1999, 10, 10), LocalTime.of(10, 0), Duration.ofMinutes(45)
                ), new Event(false,
                        "Football",
                        List.of(sportCat),
                        "Tennis Hall Pisarky",
                        LocalDate.of(2020, 11, 1), LocalTime.of(8, 0), Duration.ofMinutes(50)
                ), new Event(true,
                        "Burger King",
                        List.of(foodCat),
                        "Burger King Brno",
                        LocalDate.of(2021, 9, 5), LocalTime.of(12, 0), Duration.ofMinutes(25)
                ), new Event(true,
                        "Seminar JAVA",
                        List.of(schoolCat, socialCat),
                        "FI MUNI b118",
                        LocalDate.of(2022, 10, 1), LocalTime.of(16, 0), Duration.ofMinutes(100)
                ), new Event(false,
                        "Dinner",
                        List.of(foodCat, socialCat),
                        "KFC",
                        LocalDate.of(2023, 12, 12), LocalTime.of(18, 0), Duration.ofMinutes(20)
                ), new Event(false,
                        "Dinner1",
                        List.of(foodCat, socialCat),
                        "KFC",
                        LocalDate.of(2026, 12, 12), LocalTime.of(18, 0), Duration.ofMinutes(20)
                ), new Event(false,
                        "Dinner2",
                        List.of(foodCat, socialCat),
                        "Bageteria",
                        LocalDate.of(2027, 12, 12), LocalTime.of(18, 0), Duration.ofMinutes(20)
                ), new Event(false,
                        "Dinner3",
                        List.of(foodCat, socialCat),
                        "KFC",
                        LocalDate.of(2025, 12, 12), LocalTime.of(18, 0), Duration.ofMinutes(20)
                ), new Event(false,
                        "Dinner4",
                        List.of(foodCat, socialCat),
                        "Mc",
                        LocalDate.of(2024, 12, 12), LocalTime.of(18, 0), Duration.ofMinutes(20)
                )
        );
    }

    public static Collection<Category> getCategories() {
        return List.of(sportCat, foodCat, schoolCat, socialCat);
    }

    public static Collection<Template> getTemplates() {
        return List.of(
                new Template("Social eating",
                        false,
                        null,
                        List.of(socialCat, foodCat),
                        null,
                        null,
                        Duration.ofMinutes(30)),
                new Template("Tennis training template",
                        false,
                        "Tennis training",
                        List.of(sportCat),
                        "Tennis hall",
                        LocalTime.of(15, 0),
                        Duration.ofMinutes(120)),
                new Template("Almost empty template",
                        false,
                        "Sleep",
                        null,
                        null,
                        LocalTime.of(23, 30),
                        null
                )

        );
    }

    public static Collection<Interval> getIntervals() {
        return List.of(new Interval("Lesson", "les", Duration.ofMinutes(45)),
                new Interval("Tennis training", "ten", Duration.ofMinutes(120))
        );
    }
}
