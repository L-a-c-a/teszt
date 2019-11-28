import scala.collection.mutable.Map

trait DB extends AutoCloseable
{
  def get (kulcs: String): Array[String]  //nem Option, hanem üres tömb, ha nincs a kulcs
  def set (kulcs: String, ertek: String): Unit
  def remove (kulcs: String): Unit
  def list : Map[String, Array[String]]  //mutable Map!
}

object DB
{
  val tipus = "PROBA"

  def apply =
  {
    tipus match
    {
      case "PROBA" => new DBproba
    }
  }
}