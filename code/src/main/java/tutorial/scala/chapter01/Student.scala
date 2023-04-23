package tutorial.scala.chapter01

class Student(name: String, var age: Int) {
  def printInfo(): Unit = {
    Student.school += name
    println(name + " " + age + " " + Student.school)
    Student.main(null)

  }
}

// 引入伴生对象
object Student{

  var school: String = "atguigu"

  def main(args: Array[String]): Unit = {
    val alice = new Student("alice", 20)
    val bob = new Student("bob", 23)

    alice.printInfo()
    bob.printInfo()
  }
}
