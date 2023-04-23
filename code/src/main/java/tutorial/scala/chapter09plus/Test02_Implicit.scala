package tutorial.scala.chapter09plus
import scala.util.Random
object Test02_Implicit {
  def main(args: Array[String]): Unit = {

    val new12 = new MyRichInt(12)
    println(new12.myMax(15))

    // 1. 隐式函数
    implicit def convert(num: Int): MyRichInt = new MyRichInt(num)

    println(12.myMax(15))

    println("============================")

    // 2. 隐式类
    implicit class MyRichInt2(val self: Int) {
      // 自定义比较大小的方法
      def myMax2(n: Int): Int = if ( n < self ) self else n
      def myMin2(n: Int): Int = if ( n < self ) n else self
    }

    println(12.myMin2(15))

    println("============================")

    // 3. 隐式参数

    implicit val str: String = "alice"
    //同一个作用域内隐式参数只能有一个
//    implicit val str2: String = "alice2"
    implicit val num: Int = 18

//科里化的写法,第一个括号可以省略,但是使用的时候也需要保持一致
    def sayHello()(implicit name: String): Unit = {
      println("hello, " + name)
    }
    def sayHello2(implicit name: String): Unit = {
      println("hello, " + name)
    }
    def sayHi(implicit name: String = "shiro"): Unit = {
      println("hi, " + name)
    }
    sayHello()
    //需要和上面的定义一致
    sayHello2
    sayHi

    // 简便写法
    def hiAge(): Unit = {
//      想要拿到一个int类型的隐士参数
      println("hi, " + implicitly[Int])
    }
    hiAge()
  }
}

// 自定义类
class MyRichInt(val self: Int) {
  // 自定义比较大小的方法
  def myMax(n: Int): Int = if ( n < self ) self else n
  def myMin(n: Int): Int = if ( n < self ) n else self
}
