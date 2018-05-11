# Arkkitehtuuri

## Tietojen pysyväistallennus

Tietojen pysyväistallennukseen käytetään sqlite3-tietokantaa, joka luodaan aina tarvittaessa ohjelman käynnistyksen yhteydessä. Tietokannassa on yksi taulu huipputulosten tallentamista varten.

<img src="https://github.com/heniko/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/tietokanta.png" width="400">

## Ohjelman rakenteeseen jääneet heikkoudet

### Käyttöliittymä

Käyttöliittymän koodi on osittain epäselvää. Koodia voisi jakaa useampiin metodeihin, jolloin siitä tulisi selvempää. Käyttöliittymäkomponenttien ulkonäköä olisi voinut myös huomattavasti parannella ja pelin siirtoja olisi voinut animoida.

### Tietokanta

Jos tietokannan käytössä tapahtuu jotain olettamatonta, ohjelma ei osaa luoda uutta kesken ajon. Ongelman voi aiheuttaa esim. Tietokannan poistaminen ajon aikana. Pisteiden lisäysvaiheessa käyttäjälle ilmoitetaan tapahtuneesta virheestä ja highscorejen avaus ei onnistu. Peliä pystyy kuitenkin pelaamaan tällaisen virheen sattuessa.

<img src="https://github.com/heniko/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/addingscore.png" width="400">
