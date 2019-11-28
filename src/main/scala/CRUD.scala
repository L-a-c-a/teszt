import scala.util.Using

object CRUD extends App
{
  println(s"helló ${args(0)}")
  def dolog() = "megvan"

  //var db = DB.apply
  Using(DB.apply)
  { db =>
    println(db.list)
  }

}