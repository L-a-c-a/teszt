## Tesztfeladat

Kulcs--érték párok kezelése, egyszerű fájlba-perzisztálással, egy neten talált szerializációs eljárással.  
De bármilyen adatbázis implementálható: meg kell írni, mondjuk, a DBora osztályt (ami `extends DB`), és `DB.tipus`-t átírni.

Futtatás:

`sbt run parancs [param...]`

vagy, `sbt assembly` után:

`java -jar tesztfeladat.jar parancs [param...]`

parancs ::= {`get|set|remove|list`}   
param... ::= kulcs [érték]...

Teszt:

`sbt test`
