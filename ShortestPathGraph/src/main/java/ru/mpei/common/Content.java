package ru.mpei.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.List;

@Data
public class Content implements Comparable<Content> {
    private static ObjectMapper objectMapper = new ObjectMapper();

    private String target;
    private int weight;
    private List<String> visitedElements = new LinkedList<>();

    @SneakyThrows
    public String convertToString() {
        return objectMapper.writeValueAsString(this);
    }

    @SneakyThrows
    public static Content convertToContent(String s) {
        return objectMapper.readValue(s, Content.class);
    }

    public void increaseWeight(int x) {
        this.weight += x;
    }

    @Override
    public int compareTo(Content o) {
        return this.weight - o.weight;
    }
}
