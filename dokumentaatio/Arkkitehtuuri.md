# Arkkitehtuuri

## Tietojen pysyväistallennus

Tietojen pysyväistallennukseen käytetään sqlite3-tietokantaa, joka luodaan projektin ensimmäisen käynnistyskerran yhteydessä.

## Ohjelman rakenteeseen jääneet heikkoudet

Käyttöliittymän koodi on osittain epäselvää. Koodia voisi jakaa useampiin metodeihin, jolloin siitä tulisi selvempää. Käyttöliittymäkomponenttien ulkonäköä olisi voinut myös huomattavasti parannella ja pelin siirtoja olisi voinut animoida.

Jos tietokannan käytössä tapahtuu jotain olettamatonta, ohjelma ei osaa luoda uutta kesken ajon. Ongelman voi aiheuttaa esim. Tietokannan poistaminen ajon aikana. Pisteiden lisäysvaiheessa käyttäjälle ilmoitetaan tapahtuneesta virheestä ja highscorejen avaus ei onnistu. Peliä pystyy kuitenkin pelaamaan tällaisen virheen sattuessa.
