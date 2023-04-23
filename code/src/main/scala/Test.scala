import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper

import java.util
import scala.collection.JavaConverters.mapAsJavaMapConverter
import scala.collection.mutable.Map

object TestScala {

  def main(args: Array[String]): Unit = {
    val testMap: Map[String,String] = Map()

    println(testMap.getClass)
    val mapper = new ObjectMapper()
    testMap.put("sdfs","cxzfvs")

    println(String.valueOf(testMap))
    println(mapper.writeValueAsString(testMap.asJava))
    val map: util.HashMap[String, String] = new util.HashMap[String, String]


    val javaMap = mapper.readValue(mapper.writeValueAsString(testMap.asJava), new TypeReference[util.HashMap[String, String]]() {})

    println(mapper.writeValueAsString(javaMap))

  }
}
