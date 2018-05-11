# Vaativuusmäärittely

## Sovelluksen tarkoitus

Sovellus on JavaFX toteutus pelistä 2048. Itse pelin lisäksi sovellus tarjoaa mahdollisuuden tallentaa käyttäjän saamia tuloksia paikallisesti tietokantaan.

## Käyttäjät

Kaikki käyttäjät ovat sovelluksessa normaaleja käyttäjiä. Sovellukseen ei tarvitse kirjautua, mutta käyttäjä voi halutessaan antaa käyttäjänimen huipputulosten tallennuksen yhtydessä.

## Käyttöliittymä

-Aloitusruutu, josta pääsee peliin tai huipputuloksiin

<img src="https://raw.githubusercontent.com/heniko/otm-harjoitustyo/master/dokumentaatio/kuvat/menu.png" width="400">

-Peli

<img src="https://raw.githubusercontent.com/heniko/otm-harjoitustyo/master/dokumentaatio/kuvat/game.png" width="400">

-Huipputulokset

<img src="https://raw.githubusercontent.com/heniko/otm-harjoitustyo/master/dokumentaatio/kuvat/highscores.png" width="400">

-Huipputuloksien lisäys

<img src="https://github.com/heniko/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/addingscore.png" width="400">

## Toiminnallisuus

-Käyttäjä voi aloittaa pelin tai halutessaan tarkastella huipputuloksia

-Käyttäjä voi pelata peliä

-Pelin loputtua käyttäjälle tarjotaan mahdollisuus antaa nimimerkki huipputuloksia varten

-Tulokset tallennetaan tietokantaan

## Jatkokehitysideoita

-Verkossa toimiva tietokanta, jonka avulla käyttäjä voi verrata tuloksiaan muiden saavuttamiin tuloksiin

-Tietokantaan tallennetaan myös tuloksia, jotka eivät kuulu top20. Koska tietokantaan tallennetaan myös aika, jolloin tulokset on saatu voitaisiin tästä datasta näyttää käyttäjälle useanlaisia tilastoja kuten mm. viivakaavio kehityksestä ajan kuluessa tai pylväskaavio keskivertotuloksesta eri kellonaikoina.
