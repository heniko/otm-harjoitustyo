# Testausdokumentti

Sovelluksen toimintaa on testattu JUnitilla toteutetuilla yksikkö- ja integraatiotesteillä, sekä manuaalisesti.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Sovelluslogiikan tärkeimmän komponentin, eli pakkauksessa logic sijaitsevan luokan Grid testauksessa on käytetty useita JUnit yksikkötestejä.

Pakkauksessa logic oliva luokka GameController toimii pelin ohjaimena ja ohjaajana käyttöliittymän ja luokan Grid välillä, joten sen testaaminen on hoidettu manuaalisesti. Luokan automaattista testausta vaikeuttaisi myös se, että pelissä käytetään paljon satunnaisuutta uusien laattojen luomiseen. Luokan testaaminen testaisi myös paljolti samoja asioita kuin Grid-luokan testaus.

### DAO

Pakkauksen dao luokan HighscoreDao testaus on tapahtunut manuaalisesti muun ohjelman testaamisen yhteydessä.

### Domain

Domain-pakkauksen luokille  highscore ja position on luotu testejä. Database ja direction luokkien testaus on tapahtunut muun testauksen yhteydessä.

## Testikattavuus

Käyttöliittymää lukuunottamatta rivikattavuus on 70% ja haarautumakattavuus 71%.

<img src="https://github.com/heniko/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/testikattavuus.png" width="600">

## Järjestelmätestaus

### Asennus

Sovelluksen testaus on tapahtunut pääasiassa Windows-ympäristössä.

Sovellusta on testattu tilanteissa joissa:
-Tietokanta joudutaan luomaan kokonaan uudelleen
-Tietokanta on olemassa, mutta tarvittavat taulut puuttuvat
-Tietokanta ja taulut ovat olemassa
-Tietokanta ja taulut ovat olemassa ja tietokantaan on tallennettuna tietoa

### Toiminnallisuudet

Määrittelydokumentissa ja käyttöohjeessa listatut toiminnallisuudet on käyty läpi. Huipputulosten lisäykseen tarkoitetetussa ikkunassa olevan kentän toiminnallisuutta on testattu myös tyhjällä ja yli 30 merkkiä pitkällä nimimerkillä.

## Sovellukseen jääneet laatuongelmat

Tietokannan käytössä tapahtuvan virheen sattuessa käyttäjälle ei ilmoiteta asiasta hänen avatessan huipputulokset. Virheilmoitus kuitenkin tulostetaan, jos käyttäjä yrittää lisätä uutta huipputulosta. Virheen voi aiheuttaa esimerkiksi tietokannan poistaminen kesken ohjelman ajon. Tällöin uusi tiedosto tietokannalle luodaan ajon aikana, mutta tarvittavia tietokantatauluja ei yritetä luoda muulloin kuin käynnistämisen yhteydessä.
