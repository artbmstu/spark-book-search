package ru.art.vulk.spark.service;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.art.vulk.spark.config.UserConfig;
import scala.Tuple2;

import java.util.Map;

@Service
public class PopularWordsServiceImpl implements PopularWordsService{
    @Autowired
    UserConfig userConfig;

    @Override
    public Map<String, Integer> topX(JavaRDD<String> lines) {
        JavaPairRDD<String, Integer> output = lines.map(String::toLowerCase)
                .flatMap((String line) -> WordsUtil.getWords(line).iterator())
                .filter(word-> !userConfig.excludeWords.contains(word))
                .mapToPair(word->new Tuple2<>(word,1))
                .reduceByKey((a,b) -> a + b);
        return output.collectAsMap();
    }
}
