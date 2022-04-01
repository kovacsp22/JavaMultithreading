# JavaMultithreading (HF6)

Írj egy programot, amelyben bankszámlák közötti utalásokat szimulálsz.

Ehhez szükséges létrehoznod egy **bankszámla osztályt**. Ennek az osztálynak a példányai fognak egymásnak pénzt küldeni.

Hozzatok létre egy **banki utalás osztályt** is, ennek a segítségével kell elvégezni az utalásokat több szálon *(thread vagy runnable)*

Az utalások a következőképpen történnek: 
 - Először a banki utalás osztály meghívja a küldő bankszámla azon metódusát, ami levonja a küldött összeget az egyenlegből

 - Majd a banki utalás osztály meghívja a fogadó bankszámla azon metódusát, ami hozzáadja a fogadott összeget az egyenleghez

 - Szóval, ha az összeg levonása megtörtént, átadhatjuk a számlát egy másik szálnak, nem kell megvárnunk, amíg a fogadó számla is végzett *(feltételezzük, hogy nem tud a két utalás között eltűnni az összeg)*.

 - Amikor befejeződtek az utalás különböző lépései, írjátok ki a konzolra, hogy melyik bankszámlával és mi történt

Tegyük fel, hogy az **utalások feldolgozása lassú**, ezért a utaláskor az egyenleg csökkentése vagy növelése 1 másodpercig tart egy bankszámlán *(Thread.sleep())*

**Minden pénzügyi műveletnek szálbiztosnak kell lennie**. Tehát, amíg nem fejeződött be az összeg levonása vagy hozzáadása az egyenlegekhez, addig az adott bankszámlán nem végezhetünk más műveletet.

**Ellenőrizzétek, hogy van-e elegendő pénz a számlákon** az utalásokhoz, ha nem, akkor jelezzétek a hibát, de **NE** terminálódjon a program.

Az utaláson kívül még szálbiztosan **implementáljatok egy plusz pénzügyi műveletet a bankszámla osztályban**, mint pl. bankkártya díjának levonása, kamat kifizetése stb. Erre műveletre természetesen ugyanazok a szabályok vonatkoznak, mint az utalás műveleteire.

**Demózzátok** több féle egymás utáni pénzügyi művelettel, egyenleggel, különböző számú bankszámlákkal a működést!

Ez egy egyszerűsített példa, az órán tanultak elegendőek a megoldásához.

Ne feledjétek a **Javadoc**-kal való dokumentálást!
