//import org.scalatest.FunSuite
import org.scalatest.FunSpec
import scala.util.Using

class CRUDTest extends FunSpec
{
  describe("szerializáció")
  {
    it("oda-vissza szerializálás ugyanazt adja vissza")
    {
      assert (Serialization.deserialise(Serialization.serialise("asdasd")) == "asdasd")
      assert (Serialization.deserialise(Serialization.serialise(List(1,2,3))) == List(1,2,3))
    }
  }

  Using.resource(DB.apply)
  { db =>
    describe ("list funkció:")
    {
      it("Map-ot ad vissza") 
      { 
        assert (db.list.getClass.toString.contains("Map")) 
      }
    }

    describe("get funkció:")
    {
      it("nem létező kulcsra üres halmazt ad")
      {
        val getValasz = db.get("qqqq")
        assert (getValasz.getClass.toString.contains("Set") && getValasz.size == 0)
      }
      it("létezőre pedig annak az értékeit")
      {
        assert (db.get("k1") == Set("v1", "v2", "v3"))
      }
    }

    describe("set funkció:")
    {
      it("kezdeti állapot: kUj kulcs nincs")
      {
        assert(db.get("kUj") == Set[String]())
      }
      it("ha még nem létezik a kulcs, beírja az érték(ek)et")
      {
        db.set("kUj", Set("v1", "v2", "v3"))
        assert (db.get("kUj") == Set("v1", "v2", "v3"))
      }
      it("ha már létezik, az érték(ek)et hozzáírja az eddigi értékekhez")
      {
        db.set("kUj", Set("v4"))
        assert (db.get("kUj") == Set("v1", "v2", "v3", "v4"))
      }
      it("többet is, de többedszer nem írja be ugyanazt az értéket")
      {
        db.set("kUj", Set("v4", "v5"))
        assert (db.get("kUj") == Set("v1", "v2", "v3", "v4", "v5"))
      }
      it("tesztadat törlés")
      {
        db.remove("kUj")
        assert(db.get("kUj") == Set[String]())
      }
    }

    describe("remove funkció:")
    {
      it("kezdeti állapot: kUj és nemlétezőkulcs kulcs nincs")
      {
        assert(db.get("kUj") == Set[String]())
        assert(db.get("nemlétezőkulcs") == Set[String]())
      }
      it("tesztadat")
      {
        db.set("kUj", Set("v1", "v2", "v3", "v4", "v5"))
        assert (db.get("kUj") == Set("v1", "v2", "v3", "v4", "v5"))
      }
      it("érték-paraméter nélkül: az egész kulcsot törli (már ha létezett)")
      {
        db.remove("kUj")
        assert(db.get("kUj") == Set[String]())
        db.remove("nemlétezőkulcs")
        assert(db.get("nemlétezőkulcs") == Set[String]())
      }
      it("tesztadat újra")
      {
        db.set("kUj", Set("v1", "v2", "v3", "v4", "v5"))
        assert (db.get("kUj") == Set("v1", "v2", "v3", "v4", "v5"))
      }
      it("paraméterekkel")
      {
        db.remove("nemlétezőkulcs", Set("nincs", "sincs"))
        assert(db.get("nemlétezőkulcs") == Set[String]())
        db.remove("kUj", Set("v2", "v3"))
        assert(db.get("kUj") == Set("v1", "v4", "v5"))
        db.set("kUj", Set("v2", "v3"))
        db.remove("kUj", Set("v2", "vNincs"))
        assert(db.get("kUj") == Set("v1", "v3", "v4", "v5"))
      }
    }

  }
}