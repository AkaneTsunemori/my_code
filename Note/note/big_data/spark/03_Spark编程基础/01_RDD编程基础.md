###RDD创建
RDD可以通过两种方式创建：
* 第一种：读取一个外部数据集。比如，从本地文件加载数据集，或者从HDFS文件系统、HBase、Cassandra、Amazon S3等外部数据源中加载数据集。
  Spark可以支持文本文件、SequenceFile文件（Hadoop提供的 SequenceFile是一个由二进制序列化过的key/value的字节流组成的文本存储文件）
  和其他符合Hadoop InputFormat格式的文件。
* 第二种：调用SparkContext的parallelize方法，在Driver中一个已经存在的集合（数组）上创建。

启动Hadoop中的HDFS组件：
```shell
cd  /usr/local/hadoop
./sbin/start-dfs.sh
```
启动spark-shell：
```shell
cd /usr/local/spark
./bin/spark-shell
```

```shell
cd usr/local/spark/mycode/
mkdir rdd
```

新建一个word.txt文件，随便输入几行英文语句用来测试。

###从文件系统中加载数据创建RDD
Spark采用textFile()方法来从文件系统中加载数据创建RDD，该方法把文件的URI作为参数，这个URI可以是本地文件系统的地址，
或者是分布式文件系统HDFS的地址，或者是Amazon S3的地址等等。
```scala
scala> val lines = sc.textFile("file:///usr/local/spark/mycode/rdd/word.txt")
lines: org.apache.spark.rdd.RDD[String] = file:///usr/local/spark/mycode/rdd/word.txt MapPartitionsRDD[12] at textFile at <console>:27
```

从执行结果反馈信息可以看出，lines是一个String类型的RDD，或者我们以后可以简单称为RDD[String]，也就是说，
这个RDD[String]里面的元素都是String类型。

把刚才在本地文件系统中的“/usr/local/spark/mycode/rdd/word.txt” 上传到HDFS文件系统的hadoop用户目录下
（注意：本教程统一使用**hadoop**用户登录Linux系统）。然后，在spark-shell窗口中， 就可以使用下面任意一条命令完成从HDFS文件系统中加载数据： 
以下三条命令是完全等价的命令，只不过使用了不同的目录形式，你可以使用其中任意一条命令完成数据加载操作。
```scala
scala> val lines = sc.textFile("hdfs://localhost:9000/user/hadoop/word.txt")
scala> val lines = sc.textFile("/user/hadoop/word.txt")
scala> val lines = sc.textFile("word.txt")
```

在使用Spark读取文件时，需要说明以下几点：

*   （1）如果使用了本地文件系统的路径，那么，必须要保证在所有的worker节点上，也都能够采用相同的路径访问到该文件，比如，
    可以把该文件拷贝到每个worker节点上，或者也可以使用网络挂载共享文件系统。
*   （2）textFile()方法的输入参数，可以是文件名，也可以是目录，也可以是压缩文件等。比如，textFile("/my/directory"),
    textFile("/my/directory/*.txt"), and textFile("/my/directory/*.gz").
*   （3）textFile()方法也可以接受第2个输入参数（可选），用来指定分区的数目。**默认情况下，Spark会为HDFS的每个block创建一个分区**（
    HDFS中每个block默认是128MB）。你也可以提供一个比block数量更大的值作为分区数目，**但是，你不能提供一个小于block数量的值作为分区数目**。

###通过并行集合（数组）创建RDD
可以调用SparkContext的parallelize方法，在Driver中一个已经存在的集合（数组）上创建。
```scala
scala>val array = Array(1,2,3,4,5)
array: Array[Int] = Array(1, 2, 3, 4, 5)
scala>val rdd = sc.parallelize(array)
rdd: org.apache.spark.rdd.RDD[Int] = ParallelCollectionRDD[13] at parallelize at <console>:29
```

从执行结果信息可以看出，rdd是一个Int类型的RDD。
上面使用数组来创建，或者，也可以从列表中创建：
```scala
scala>val list = List(1,2,3,4,5)
list: List[Int] = List(1, 2, 3, 4, 5)
scala>val rdd = sc.parallelize(list)
rdd: org.apache.spark.rdd.RDD[Int] = ParallelCollectionRDD[14] at parallelize at <console>:29
```
###RDD操作
RDD被创建好以后，在后续使用过程中一般会发生两种操作：
*  转换（Transformation）： 基于现有的数据集创建一个新的数据集。
*  行动（Action）：在数据集上进行运算，返回计算值。

###转换操作
对于RDD而言，每一次转换操作都会产生不同的RDD，供给下一个“转换”使用。转换得到的RDD是惰性求值的，也就是说，整个转换过程只是记录了转换的轨迹，
并不会发生真正的计算，只有遇到行动操作时，才会发生真正的计算，开始从血缘关系源头开始，进行物理的转换操作。
下面列出一些常见的转换操作（Transformation API）：
* filter(func)：筛选出满足函数func的元素，并返回一个新的数据集
* map(func)：将每个元素传递到函数func中，并将结果返回为一个新的数据集
* flatMap(func)：与map()相似，但每个输入元素都可以映射到0或多个输出结果
* groupByKey()：应用于(K,V)键值对的数据集时，返回一个新的(K, Iterable)形式的数据集
* reduceByKey(func)：应用于(K,V)键值对的数据集时，返回一个新的(K, V)形式的数据集，其中的每个值是将每个key传递到函数func中进行聚合

###行动操作
行动操作是真正触发计算的地方。Spark程序执行到行动操作时，才会执行真正的计算，从文件中加载数据，完成一次又一次转换操作，最终，
完成行动操作得到结果。 下面列出一些常见的行动操作（Action API）：
* count() 返回数据集中的元素个数
* collect() 以数组的形式返回数据集中的所有元素
* first() 返回数据集中的第一个元素
* take(n) 以数组的形式返回数据集中的前n个元素
* reduce(func) 通过函数func（输入两个参数并返回一个值）聚合数据集中的元素
* foreach(func) 将数据集中的每个元素传递到函数func中运行*

###惰性机制
这里给出一段简单的代码来解释Spark的惰性机制。
```scala
scala> val lines = sc.textFile("data.txt")
scala> val lineLengths = lines.map(s => s.length)
scala> val totalLength = lineLengths.reduce((a, b) => a + b)
```
上面第一行首先从外部文件data.txt中构建得到一个RDD，名称为lines，但是，由于textFile()方法只是一个转换操作，因此，这行代码执行后，
不会立即把data.txt文件加载到内存中，这时的lines只是一个指向这个文件的指针。 第二行代码用来计算每行的长度（即每行包含多少个单词），
同样，由于map()方法只是一个转换操作，这行代码执行后，不会立即计算每行的长度。 第三行代码的reduce()方法是一个“动作”类型的操作，这时，
就会触发真正的计算。这时，Spark会把计算分解成多个任务在不同的机器上执行，每台机器运行位于属于它自己的map和reduce，
最后把结果返回给Driver Program。

###实例
filter()操作的实例。
```scala
scala> val lines = sc.textFile("file:///usr/local/spark/mycode/rdd/word.txt")
//16表示分区数
lines: org.apache.spark.rdd.RDD[String] = file:///usr/local/spark/mycode/rdd/word.txt MapPartitionsRDD[16] at textFile at <console>:27
scala> lines.filter(line => line.contains("Spark")).count()
res1: Long = 2  //这是执行返回的结果
```

这里再给出另外一个实例，我们要找出文本文件中单行文本所包含的单词数量的最大值，代码如下：
```scala
scala> val lines = sc.textFile("file:///usr/local/spark/mycode/rdd/word.txt")
scala> lines.map(line => line.split(" ").size).reduce((a,b) => if (a>b) a else b)
```
lines.map(line => line.split(" ").size)转换操作得到的RDD，是一个整型RDD，里面每个元素都是整数值（也就是单词的个数）。 最后，
针对这个RDD[Int]，调用reduce()行动操作，完成计算。reduce()操作每次接收两个参数，取出较大者留下，然后再继续比较，例如，
RDD[Int]中包含了1,2,3,4,5，那么，执行reduce操作时，首先取出1和2，把a赋值为1，把b赋值为2，然后，执行大小判断，保留2。下一次，
让保留下来的2赋值给a，再从RDD[Int]中取出下一个元素3，把3赋值给b，然后，对a和b执行大小判断，保留较大者3.依此类推。
最终，reduce()操作会得到最大值是5。

持久化
前面我们已经说过，在Spark中，RDD采用惰性求值的机制，每次遇到行动操作，都会从头开始执行计算。如果整个Spark程序中只有一次行动操作，
这当然不会有什么问题。但是，在一些情形下，我们需要多次调用不同的行动操作，这就意味着，每次调用行动操作，都会触发一次从头开始的计算。
这对于迭代计算而言，代价是很大的，迭代计算经常需要多次重复使用同一组数据。
比如，下面就是多次计算同一个DD的例子：
```scala
scala> val list = List("Hadoop","Spark","Hive")
list: List[String] = List(Hadoop, Spark, Hive)
scala> val rdd = sc.parallelize(list)
rdd: org.apache.spark.rdd.RDD[String] = ParallelCollectionRDD[22] at parallelize at <console>:29
scala> println(rdd.count()) //行动操作，触发一次真正从头到尾的计算
3
scala> println(rdd.collect().mkString(",")) //行动操作，触发一次真正从头到尾的计算
Hadoop,Spark,Hive
```
上面代码执行过程中，前后共触发了两次从头到尾的计算。 实际上，可以通过持久化（缓存）机制避免这种重复计算的开销。
可以使用persist()方法对一个RDD**标记为持久化**，之所以说“标记为持久化”，是因为出现persist()语句的地方，并不会马上计算生成RDD并把它持久化，
而是要等到遇到第一个行动操作触发真正计算以后，才会把计算结果进行持久化，持久化后的RDD将会被保留在计算节点的内存中被后面的行动操作重复使用。
**persist()的圆括号中包含的是持久化级别参数**，比如，persist(MEMORY_ONLY)表示将RDD作为反序列化的对象存储于JVM中，如果内存不足，
就要按照LRU原则替换缓存中的内容。persist(MEMORY_AND_DISK)表示将RDD作为反序列化的对象存储在JVM中，如果内存不足，
超出的分区将会被存放在硬盘上。一般而言，使用cache()方法时，会调用persist(MEMORY_ONLY)。
例子如下：
```scala
scala> val list = List("Hadoop","Spark","Hive")
list: List[String] = List(Hadoop, Spark, Hive)
scala> val rdd = sc.parallelize(list)
rdd: org.apache.spark.rdd.RDD[String] = ParallelCollectionRDD[22] at parallelize at <console>:29
scala> rdd.cache() //会调用persist(MEMORY_ONLY)，但是，语句执行到这里，并不会缓存rdd，这是rdd还没有被计算生成
```
**第一次行动操作，触发一次真正从头到尾的计算，这时才会执行上面的rdd.cache()，把这个rdd放到缓存中**
```scala
scala> println(rdd.count()) 
3
scala> println(rdd.collect().mkString(",")) //第二次行动操作，不需要触发从头到尾的计算，只需要重复使用上面缓存中的rdd
Hadoop,Spark,Hive
```
最后，**可以使用unpersist()方法手动地把持久化的RDD从缓存中移除。**

###分区
RDD是弹性分布式数据集，通常RDD很大，会被分成很多个分区，分别保存在不同的节点上。
RDD分区的一个分区原则是使得分区的个数尽量等于集群中的CPU核心（core）数目。
对于不同的Spark部署模式而言（本地模式、Standalone模式、YARN模式、Mesos模式），都可以通过设置 **spark.default.parallelism** 这个参数的值，
来配置默认的分区数目，一般而言：
*   本地模式：默认为本地机器的CPU数目，若设置了local[N],则默认为N；
*   Apache Mesos：默认的分区数为8；
*   Standalone或YARN：在“集群中所有CPU核心数目总和”和“2”二者中取较大值作为默认值；

因此，对于parallelize而言，如果没有在方法中指定分区数，则默认为spark.default.parallelism，比如：
```scala
scala>val array = Array(1,2,3,4,5)
array: Array[Int] = Array(1, 2, 3, 4, 5)
scala>val rdd = sc.parallelize(array,2) #设置两个分区
rdd: org.apache.spark.rdd.RDD[Int] = ParallelCollectionRDD[13] at parallelize at <console>:29
```
对于textFile而言，如果没有在方法中指定分区数，则默认为min(defaultParallelism,2)，其中，defaultParallelism对应的就是spark.default.parallelism。
**如果是从HDFS中读取文件，则分区数为文件分片数(比如，128MB/片)**。

打印元素
在实际编程中，我们经常需要把RDD中的元素打印输出到屏幕上（标准输出stdout），一般会采用语句rdd.foreach(println)或者rdd.map(println)。
当采用本地模式（local）在单机上执行时，这些语句会打印出一个RDD中的所有元素。但是，当采用集群模式执行时，
在worker节点上执行打印语句是输出到worker节点的stdout中，而不是输出到任务控制节点Driver Program中， 因此，
任务控制节点Driver Program中的stdout是不会显示打印语句的这些输出内容的。为了能够把所有worker节点上的打印输出信息也显示到Driver Program中，
可以使用collect()方法，比如，rdd.collect().foreach(println)，但是，由于collect()方法会把各个worker节点上的所有RDD元素都抓取到
Driver Program中，因此，这可能会导致内存溢出。因此，当你只需要打印RDD的部分元素时，可以采用语句**rdd.take(100).foreach(println)**。

















