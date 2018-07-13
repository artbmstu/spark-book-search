# spark-book-search
<pre>1. Приложение работает по принципу REST сервис с запросами вида localhost:8080/{book} , где book - это 
передаваемый аргумент названия текста
из папки scr/main/resources/books
2. Для обработки запросов используется класс PopularWordsServiceImpl.</pre>
```JavaPairRDD<String, Integer> output = lines.map(String::toLowerCase)
                .flatMap((String line) -> WordsUtil.getWords(line).iterator())
                .filter(word-> !userConfig.excludeWords.contains(word))
                .mapToPair(word->new Tuple2<>(word,1))
                .reduceByKey((a,b) -> a + b);
        return output.collectAsMap();
```
<pre>Здесь привожу строки к нижнему регистру, разбиваю строки на отдельные слова при помощи WordsUtil, исключаю лишние
слова с помощью .filter (слова располагаются в user.properties) и далее кладу слова в Tuple и считаю их количество.
3.На контроллер данные возвращаются в виде Map<String,Integer>. Сортирую значения и отправляю клиенту в виде json.

Пример запроса:
{"что":7797,"он":7491,"на":6780,"с":5932,"как":4112,"его":3955,"к":3469,"я":3103,"но":2777,"она":2752,"это":2567,
"было":2519,"то":2309,"а":2113,"сказал":2016,"так":1990,"о":1889,"же":1820,"за":1818,"все":1801,"по":1792,
"от":1773,"ему":1764,"ее":1700,"из":1695,"только":1616,"был":1584,...}</pre>
