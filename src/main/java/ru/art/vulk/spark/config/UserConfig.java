package ru.art.vulk.spark.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Component
public class UserConfig implements Serializable {

    public List<String> excludeWords;

    @Value("${excludeWords}")
    private void setExcludeWords(String[] excludeWords) {
        this.excludeWords = Arrays.asList(excludeWords);
    }
}
