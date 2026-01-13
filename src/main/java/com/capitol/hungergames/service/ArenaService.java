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

}
