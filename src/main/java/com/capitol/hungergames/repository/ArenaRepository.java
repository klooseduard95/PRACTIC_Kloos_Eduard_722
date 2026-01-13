package com.capitol.hungergames.repository;

import com.capitol.hungergames.model.Event;
import com.capitol.hungergames.model.SponsorGift;
import com.capitol.hungergames.model.Tribute;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ArenaRepository {
    private final ObjectMapper objectMapper;

    public ArenaRepository() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public List<Tribute> loadTributes(String resourcePath) {
        return loadData(resourcePath, new TypeReference<>() {});
    }

    public List<Event> loadEvents(String resourcePath) {
        return loadData(resourcePath, new TypeReference<>() {});
    }

    public List<SponsorGift> loadGifts(String resourcePath) {
        return loadData(resourcePath, new TypeReference<>() {});
    }

    private <T> List<T> loadData(String resourcePath, TypeReference<List<T>> typeReference) {
        try (InputStream inputStream = ArenaRepository.class.getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }
            return objectMapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            System.err.println("Failed to load data from " + resourcePath + ": " + e.getMessage());
            return Collections.emptyList();
        }
    }

}
