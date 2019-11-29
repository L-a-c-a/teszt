class CRUDTest extends org.scalatest.FunSuite
{
  test("serial")
  {
    assert (Serialization.deserialise(Serialization.serialise("asdasd")) == "asdasd")
  }
}