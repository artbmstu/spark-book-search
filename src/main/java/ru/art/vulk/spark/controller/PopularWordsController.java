package ru.art.vulk.spark.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.art.vulk.spark.config.WordsConfig;
import ru.art.vulk.spark.service.PopularWordsService;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class PopularWordsController {
    @Autowired
    PopularWordsService popularWordsService;
    @Autowired
    WordsConfig config;

    @GetMapping("/{path}")
    public String popularWords(@PathVariable String path){
        Map<String, Integer> result = popularWordsService.topX(config.conf().textFile("src/main/resources/books/" + path + ".txt"));
        result = result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        String json = new Gson().toJson(result);
        return json;
    }
}
