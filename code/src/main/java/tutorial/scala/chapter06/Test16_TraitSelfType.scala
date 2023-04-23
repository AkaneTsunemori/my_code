package tutorial.scala.chapter06

object Test16_TraitSelfType {
  def main(args: Array[String]): Unit = {
    val user = new RegisterUser("alice", "123456")
    user.insert()
    val value: Class[RegisterUser] = classOf[RegisterUser]
    println(value)
  }
}

// 用户类
case class User( name: String,  password: String)

class Student1x private(val name: String, val age: Int){
  def printInfo(){
    println(s"student: name = ${name}, age = $age, school = ${Student11.school}")
  }
}

trait UserDao {
  _: User =>
  // 向数据库插入数据
  def insert(): Unit = {
    println(s"insert into db: ${this.name},${this.password}")
  }



}

// 定义注册用户类
class RegisterUser(name: String, password: String) extends User(name, password) with UserDao