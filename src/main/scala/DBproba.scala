import DBproba._  // hogy db-t ne kelljen kvalifikálni

class DBproba extends DB
{
  override def get(kulcs: String) = db(kulcs)
  override def list = db
  override def set(kulcs: String, ertek: String) = db += kulcs -> Set(ertek)  //nem jó, a tömböt^^^halmazt ki kell egészíteni!
  override def remove(kulcs: String) = db -= kulcs
  override def close() = { println ("DBproba csukva")}
}

object DBproba
{
  var db:DB.listTip =
  scala.collection.mutable.
  Map( "k1" -> Set("v1", "v2", "v3")
     , "k2" -> Set("v21", "v22")
     , "k3" -> Set()
     )

}