###键值对RDD的创建

####第一种创建方式：从文件中加载
我们可以采用多种方式创建键值对RDD，其中一种主要方式是使用**map()函数**来实现，如下：
```scala
scala>  val lines = sc.textFile("file:///usr/local/spark/mycode/pairrdd/word.txt")
lines: org.apache.spark.rdd.RDD[String] = file:///usr/local/spark/mycode/pairrdd/word.txt MapPartitionsRDD[1] at textFile at <console>:27
scala> val pairRDD = lines.flatMap(line => line.split(" ")).map(word => (word,1))
pairRDD: org.apache.spark.rdd.RDD[(String, Int)] = MapPartitionsRDD[3] at map at <console>:29
scala> pairRDD.foreach(println)
(i,1)
(love,1)
(hadoop,1)
(i,1)
(love,1)
(Spark,1)
(Spark,1)
(is,1)
(fast,1)
(than,1)
(hadoop,1)
```
我们之前在“第一个Spark应用程序:WordCount”章节已经详细解释过类似代码，所以，上面代码不再做细节分析。从代码执行返回信息：
pairRDD: org.apache.spark.rdd.RDD[(String, Int)] = MapPartitionsRDD[3] at map at :29，可以看出，返回的结果是键值对类型的RDD，
即RDD[(String, Int)]。从pairRDD.foreach(println)执行的打印输出结果也可以看到，都是由(单词,1)这种形式的键值对。
可以通过reduceByKey((a,b)=>a+b).collect()统计词频

####第二种创建方式：通过并行集合（数组）创建RDD
```scala
scala> val list = List("Hadoop","Spark","Hive","Spark")
list: List[String] = List(Hadoop, Spark, Hive, Spark)

scala> val rdd = sc.parallelize(list)
rdd: org.apache.spark.rdd.RDD[String] = ParallelCollectionRDD[11] at parallelize at <console>:29

scala> val pairRDD = rdd.map(word => (word,1))
pairRDD: org.apache.spark.rdd.RDD[(String, Int)] = MapPartitionsRDD[12] at map at <console>:31

scala> pairRDD.foreach(println)
(Hadoop,1)
(Spark,1)
(Hive,1)
(Spark,1)
```
我们下面实例都是采用这种方式得到的pairRDD作为基础。

常用的键值对转换操作
常用的键值对转换操作包括reduceByKey()、groupByKey()、sortByKey()、join()、cogroup()等，下面我们通过实例来介绍。

######reduceByKey(func)
reduceByKey(func)的功能是，使用func函数合并具有相同键的值。比如，reduceByKey((a,b) => a+b)，有四个键值对("spark",1)、("spark",2)、
("hadoop",3)和("hadoop",5)，对具有相同key的键值对进行合并后的结果就是：("spark",3)、("hadoop",8)。
可以看出，(a,b) => a+b这个Lamda表达式中，a和b都是指value，比如，对于两个具有相同key的键值对("spark",1)、("spark",2)，a就是1，b就是2。
我们对上面第二种方式创建得到的pairRDD进行reduceByKey()操作，代码如下：
```scala
scala> pairRDD.reduceByKey((a,b)=>a+b).foreach(println)
(Spark,2)
(Hive,1)
(Hadoop,1)
scala
```
######groupByKey()
*   groupByKey()的功能是，对具有相同键的值进行分组。比如，对四个键值对("spark",1)、("spark",2)、("hadoop",3)和("hadoop",5)， 
    采用groupByKey()后得到的结果是：("spark",(1,2))和("hadoop",(3,5))。分组后，**value被保存到Iterable[Int]中**
    我们对上面第二种方式创建得到的pairRDD进行groupByKey()操作，代码如下：
```scala
scala> pairRDD.groupByKey()
res15: org.apache.spark.rdd.RDD[(String, Iterable[Int])] = ShuffledRDD[15] at groupByKey at <console>:34
scala> pairRDD.groupByKey().foreach(println)
(Spark,CompactBuffer(1, 1))
(Hive,CompactBuffer(1))
(Hadoop,CompactBuffer(1))
```
*   keys只会把键值对RDD中的key返回形成一个新的RDD。比如，对四个键值对("spark",1)、("spark",2)、("hadoop",3)和("hadoop",5)
    构成的RDD，采用keys后得到的结果是一个RDD[Int]，内容是{"spark","spark","hadoop","hadoop"}。 
    我们对上面第二种方式创建得到的pairRDD进行keys操作，代码如下：
```scala
scala> pairRDD.keys
res17: org.apache.spark.rdd.RDD[String] = MapPartitionsRDD[17] at keys at <console>:34
scala> pairRDD.keys.foreach(println)
Hadoop
Spark
Hive
Spark
```
*   values只会把键值对RDD中的value返回形成一个新的RDD。比如，对四个键值对("spark",1)、("spark",2)、("hadoop",3)和("hadoop",5)
    构成的RDD，采用keys后得到的结果是一个RDD[Int]，内容是{1,2,3,5}。 我们对上面第二种方式创建得到的pairRDD进行values操作，代码如下：
```scala
scala> pairRDD.values
res0: org.apache.spark.rdd.RDD[Int] = MapPartitionsRDD[2] at values at <console>:34
scala> pairRDD.values.foreach(println)
1
1
1
1
```

*   sortByKey()的功能是返回一个根据键排序的RDD。 我们对上面第二种方式创建得到的pairRDD进行keys操作，代码如下：
```scala
scala> pairRDD.sortByKey()
res0: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[2] at sortByKey at <console>:34
scala> pairRDD.sortByKey().foreach(println)
(Hadoop,1)
(Hive,1)
(Spark,1)
(Spark,1)
```
*   mapValues(func)**只对键值对RDD的value部分进行处理**，而不是同时对key和value进行处理。对于这种情形，
    Spark提供了mapValues(func)，它的功能是，对键值对RDD中的每个value都应用一个函数，但是，key不会发生变化。比如，
    对四个键值对("spark",1)、("spark",2)、("hadoop",3)和("hadoop",5)构成的pairRDD，如果执行pairRDD.mapValues(x => x+1)，
    就会得到一个新的键值对RDD，它包含下面四个键值对("spark",2)、("spark",3)、("hadoop",4)和("hadoop",6)。 
    我们对上面第二种方式创建得到的pairRDD进行keys操作，代码如下：
```scala
scala> pairRDD.mapValues(x => x+1)
res2: org.apache.spark.rdd.RDD[(String, Int)] = MapPartitionsRDD[4] at mapValues at <console>:34
scala> pairRDD.mapValues(x => x+1).foreach(println)
(Hadoop,2)
(Spark,2)
(Hive,2)
(Spark,2)
```
*   join(连接)操作是键值对常用的操作。注意本章是键值对操作, join操作的结果是对键进行join,值会合在一起成为一个元组?
    “连接”(join)这个概念来自于关系数据库领域，因此，join的类型也和关系数据库中的join一样，
    包括内连接(join)、左外连接(leftOuterJoin)、右外连接(rightOuterJoin)等。最常用的情形是内连接，所以，join就表示内连接。 对于内连接，
    对于给定的两个输入数据集(K,V1)和(K,V2)，只有在两个数据集中都存在的key才会被输出，最终得到一个(K,(V1,V2))类型的数据集。   
    eg:pairRDD1是一个键值对集合{("spark",1)、("spark",2)、("hadoop",3)和("hadoop",5)}，
    pairRDD2是一个键值对集合{("spark","fast")}，那么，pairRDD1.join(pairRDD2)的结果就是一个新的RDD，
    这个新的RDD是键值对集合{("spark",1,"fast"),("spark",2,"fast")}。对于这个实例，我们下面在spark-shell中运行一下：
```scala
scala> val pairRDD1 = sc.parallelize(Array(("spark",1),("spark",2),("hadoop",3),("hadoop",5)))
pairRDD1: org.apache.spark.rdd.RDD[(String, Int)] = ParallelCollectionRDD[24] at parallelize at <console>:27

scala> val pairRDD2 = sc.parallelize(Array(("spark","fast")))
pairRDD2: org.apache.spark.rdd.RDD[(String, String)] = ParallelCollectionRDD[25] at parallelize at <console>:27

scala> pairRDD1.join(pairRDD2)
res9: org.apache.spark.rdd.RDD[(String, (Int, String))] = MapPartitionsRDD[28] at join at <console>:32

scala> pairRDD1.join(pairRDD2).foreach(println)
(spark,(1,fast))
(spark,(2,fast))
```
####一个综合实例
题目：给定一组键值对("spark",2),("hadoop",6),("hadoop",4),("spark",6)，键值对的key表示图书名称，value表示某天图书销量，
请计算每个键对应的平均值，也就是计算每种图书的每天平均销量。 很显然，对于上面的题目，结果是很显然的，("spark",4),("hadoop",5)。
下面，我们在spark-shell中演示代码执行过程： reduceByKey里面传递的匿名函数是对当前和下一个元素的value进行操作
```scala
scala> val rdd = sc.parallelize(Array(("spark",2),("hadoop",6),("hadoop",4),("spark",6)))
rdd: org.apache.spark.rdd.RDD[(String, Int)] = ParallelCollectionRDD[38] at parallelize at <console>:27

scala> rdd.mapValues(x => (x,1)).reduceByKey((x,y) => (x._1+y._1,x._2 + y._2)).mapValues(x => (x._1 / x._2)).collect()
res22: Array[(String, Int)] = Array((spark,4), (hadoop,5))
```