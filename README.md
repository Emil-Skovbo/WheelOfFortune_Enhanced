# MeeW WheelOfFortuneProject

I dette projekt har jeg lavet to løsninger til det givet problem i opgaven. Tjek guide afsnittet for forklaring af løsning.
min tanker for den møde jeg har udviklet dette projekt var at oprette en backend med testede metoder. Denne backend har et API som kan kaldes af en frontend som kunne benytte værdien og knytte det til en GUI.

## Teknologier

Java JDK 17 \
Spring Boot v2.5.6 \
junit 5 \
Docker \
MySQL 

docker run -d --name MeeWWheelOfFortuneMySQL -e MYSQL_ROOT_PASSWORD=meew123 -p 3307:3306 mysql
## Guide

### Løsning 1

Kør WOF filen fundet i mappen WheelOfFortuneSolution1

Main metoden vil spinde hjulet 20 gange for at demonstrer at alle resultaterne er mulige at få fra hjulet. Dette kan ses i terminalen.


### Løsning 2

Kør WheelOfFortuneApplication filen fundet i mappen WheelOfFortuneSolution2

Test resultatet på http://localhost:8080/wof/spin eller benyt Postman eller et lignende program til at teste endpointet. 
