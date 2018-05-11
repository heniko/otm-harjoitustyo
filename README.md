# Ohjelmistotuotannon menetelmät

## Dokumentaatio

[Käyttöohje](https://github.com/heniko/otm-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaativuusmäärittely](https://github.com/heniko/otm-harjoitustyo/blob/master/dokumentaatio/vaarivuusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/heniko/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/heniko/otm-harjoitustyo/blob/master/dokumentaatio/testaus.md)

[Työaikakirjanpito](https://github.com/heniko/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)


## Releaset



## Komentorivikomennot

### Testaus
```
mvn test
```
### Testikattavuus
```
mvn jacoco:report
```
### Suoritettavan jarin generoiminen
Luo kaksi .jar-tiedostoa, joista 2048-1.0-SNAPSHOT-jar-with-dependencies.jar on toimiva projekti.
```
mvn package
```
### JavaDocin generoiminen
```
mvn javadoc:javadoc
```
### Checkstyle 
```
mvn jxr:jxr checkstyle:checkstyle
```
