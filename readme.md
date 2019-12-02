## Tesztfeladat

Kulcs--érték párok kezelése.  
Kétféle perzisztencia van megvalósítva, amelyet a `DB.tipus` beállításával lehet kiválasztani:  
* `PROBA` egy egyszerű fájlba-perzisztálás, egy neten talált szerializációs eljárással,
* `ORA` mögött Oracle adatbázis van.

Futtatás:

`sbt run parancs [param...]`

vagy, `sbt assembly` után:

`java -jar tesztfeladat.jar parancs [param...]`

parancs ::= {`get|set|remove|list`}   
param... ::= kulcs [érték]...

Teszt:

`sbt test`
