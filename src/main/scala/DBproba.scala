import scala.collection.mutable.Map
import DBproba._  // hogy db-t ne kelljen kvalifikálni

class DBproba extends DB
{
  override def get(kulcs: String) = db(kulcs)
  override def list = db
  override def set(kulcs: String, ertek: String) = db += kulcs -> Array(ertek)  //nem jó, a tömböt ki kell egészíteni!
  override def remove(kulcs: String) = db -= kulcs
  override def close() = {}
}

object DBproba
{
  var db:Map[String, Array[String]] =  //mutable! ld. import
  Map( "k1" -> Array("v1", "v2", "v3")
     , "k2" -> Array("v21", "v22")
     , "k3" -> Array()
     )

}