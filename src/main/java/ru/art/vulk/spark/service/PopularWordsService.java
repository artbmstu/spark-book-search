package ru.art.vulk.spark.service;

import org.apache.spark.api.java.JavaRDD;

import java.io.Serializable;
import java.util.Map;

public interface PopularWordsService extends Serializable {
    public Map<String, Integer> topX(JavaRDD<String> lines);
}
