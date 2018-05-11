# 2048 käyttöohje

## Pelin käynnistäminen

Pelin voi käynnistää tuplaklikkaamalla .jar-tiedostoa tai komentorivin kautta komennolla:

```
2048-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Main menu
Main menusta pelaaja voi aloittaa joko uuden pelin tai siirtyä tarkastelemaan aikaisempia huipputuloksia.

## Peli
Peli avautuu samaan ikkunaan main menun kanssa. Pelissä liikutaan käyttämällä näppäimistön nuolinäppäimiä. Pelin voi halutessaan keskeyttää painamalla ESC-näppäintä. Kun peli lopetetaan tai mahdollisia liikkeitä ei enään ole, siirrytään takaisin main menuun ja avataan tulosten lisäykseen tarkoitettu ikkuna.

Pelissä on tarkoituksena yhdistellä saman luvun omaavia laattoja liikuttelemalla niitä pelikentällä. Laattojen yhdistyessä pelaaja saa pisteitä sen mukaan, mikä uuden yhdistyneen laatan luku on. Jokaisen siirron jälkeen kentälle lisätään tyhjään paikkaan yksi uusi laatta. Peli loppuu, kun mahdollisia siirtoja ei enään ole olemassa.

## Tulosten lisäys
Tulosten lisäys tapahtuu ikkunasta, joka avautuu automaattisesti pelin päätyttyä. Tulosten lisäystä varten pelaajaa pyydetään syöttämään nimi, joka esitetään huipputuloksissa tuloksen yhteydessä. Nimen tulee olla 1-30 merkkiä pitkä. Mikäli pelaaja ei halua tuloksia tallennettavaksi, voi ikkunan sulkea.

## Huipputulokset
Huipputulokset aukeavat uuteen ikkunaan. Huipputuloksista näkee TOP 20 parhaat pisteet.
