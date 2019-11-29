import java.io._
import java.util.Base64
import java.nio.charset.StandardCharsets.UTF_8

object Serialization
{
  def serialise(value: Any): String = 
  {
    val stream: ByteArrayOutputStream = new ByteArrayOutputStream()
    val oos = new ObjectOutputStream(stream)
    oos.writeObject(value)
    oos.close
    new String(Base64.getEncoder().encode(stream.toByteArray), UTF_8)
  }

  def deserialise(str: String): Any = 
  {
    val bytes = Base64.getDecoder().decode(str.getBytes(UTF_8))
    val ois = new ObjectInputStream(new ByteArrayInputStream(bytes))
    val value = ois.readObject
    ois.close
    value
  }

/*
  println(deserialise(serialise("My Test")))
  println(deserialise(serialise(List(1))))
  println(deserialise(serialise(Map(1 -> 2))))
  println(deserialise(serialise(1)))
*/
}