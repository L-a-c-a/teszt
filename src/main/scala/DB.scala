trait DB extends AutoCloseable
{
  def get (kulcs: String): Set[String]  //nem Option, hanem üres halmaz, ha nincs a kulcs
  def set (kulcs: String, ertek: Set[String]): Unit
  def remove (kulcs: String): Unit
  def remove (kulcs: String, ertek: Set[String]): Unit
  def list : DB.listTip  //Map[String, Array[String]]  //mutable Map!
}

object DB
{
  type listTip = scala.collection.mutable.Map[String, Set[String]]

  val tipus = "PROBA"

  def apply =
  {
    tipus match
    {
      case "PROBA" => new DBproba
    }
  }
}