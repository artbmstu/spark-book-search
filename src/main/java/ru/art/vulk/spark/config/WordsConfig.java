package ru.art.vulk.spark.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "ru.art.vulk.spark")
@PropertySource("classpath:user.properties")
public class WordsConfig {
    @Bean
    public JavaSparkContext conf(){
        SparkConf conf = new SparkConf().setMaster("local").setAppName("Count App");
        return new JavaSparkContext(conf);
    }
}
