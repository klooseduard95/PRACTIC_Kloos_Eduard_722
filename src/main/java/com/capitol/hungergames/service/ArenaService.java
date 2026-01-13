package com.capitol.hungergames.service;

import com.capitol.hungergames.model.*;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArenaService {
    public List<Tribute> filterTributesByDistrictAndStatus(List<Tribute> tributes, int district, Status status) {
        return tributes.stream()
                .filter(t -> t.getDistrict() == district && t.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Tribute> sortTributesBySkillAndName(List<Tribute> tributes) {
        return tributes.stream()
                .sorted(Comparator.comparing(Tribute::getSkillLevel).reversed()
                        .thenComparing(Tribute::getName))
                .collect(Collectors.toList());
    }

    public int calculateComputedPoints(Event event) {
        int points = event.getPoints();
        int day = event.getDay();
        return switch (event.getType()) {
            case FOUND_SUPPLIES -> points + 2 * day;
            case INJURED -> points - day;
            case ATTACK -> points * 2 + day;
            case HELPED_ALLY -> points + 5;
            case SPONSORED -> points + 10;
        };
    }

    public long calculateTotalScoreForTribute(Tribute tribute, List<Event> allEvents, List<SponsorGift> allGifts) {
        long eventsScore = allEvents.stream()
                .filter(event -> event.getTributeId() == tribute.getId())
                .mapToLong(this::calculateComputedPoints)
                .sum();

        long giftsScore = allGifts.stream()
                .filter(gift -> gift.getTributeId() == tribute.getId())
                .mapToLong(SponsorGift::getValue)
                .sum();

        return eventsScore + giftsScore;
    }

    public List<Map.Entry<Tribute, Long>> getTopTributes(List<Tribute> tributes, List<Event> events, List<SponsorGift> gifts, int limit) {
        return tributes.stream()
                .map(tribute -> {
                    long totalScore = calculateTotalScoreForTribute(tribute, events, gifts);
                    return new AbstractMap.SimpleEntry<>(tribute, totalScore);
                })
                .sorted(Map.Entry.<Tribute, Long>comparingByValue().reversed()
                        .thenComparing(entry -> entry.getKey().getName()))
                .limit(limit)
                .collect(Collectors.toList());
    }

}
