package com.capitol.hungergames.ui;

import com.capitol.hungergames.model.Event;
import com.capitol.hungergames.model.SponsorGift;
import com.capitol.hungergames.model.Status;
import com.capitol.hungergames.model.Tribute;
import com.capitol.hungergames.repository.ArenaRepository;
import com.capitol.hungergames.service.ArenaService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleUI {
    private final ArenaRepository repository;
    private final ArenaService service;
    private final Scanner scanner;

    private List<Tribute> tributes;
    private List<Event> events;
    private List<SponsorGift> gifts;

    public ConsoleUI() {
        this.repository = new ArenaRepository();
        this.service = new ArenaService();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        loadData();
        executeTask1();
        executeTask2();
        scanner.close();
    }

    private void loadData() {
        tributes = repository.loadTributes("/tributes.json");
        events = repository.loadEvents("/events.json");
        gifts = repository.loadGifts("/gifts.json");
    }

    private void executeTask1() {
        System.out.println("--- Task 1: Load and Display Data ---");
        System.out.println("Tributes loaded: " + tributes.size());
        System.out.println("Events loaded: " + events.size());
        System.out.println("Gifts loaded: " + gifts.size());
        tributes.forEach(System.out::println);
        System.out.println("-------------------------------------\n");
    }

    private void executeTask2() {
        System.out.println("--- Task 2: Filter by District and Status ---");
        System.out.print("Input district: ");
        try {
            int district = scanner.nextInt();
            List<Tribute> filteredTributes = service.filterTributesByDistrictAndStatus(tributes, district, Status.ALIVE);
            filteredTributes.forEach(System.out::println);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next();
        }
        System.out.println("-------------------------------------------\n");
    }
}
