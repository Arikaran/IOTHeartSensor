# IOTHeartSensor  
Projet sur un capteur cardiaque pour le cours d'IOT à l'ESGI  


### Nom du projet : HeartQuake  

### Objectifs  

Utiliser un capteur cardiaque, couplé à un thermomètre, afin d'alerter les secours en cas de danger cardiaque d'un l'utilisateur. 

Le projet comporte donc un appareil qui peut analyser l'utilisateur et l'alerter d'un risque via un buzzer, ainsi qu'une application mobile qui permet de suivre l'évolution de son rythme cardiaque et de sa température et qui peut également localiser l'utilisateur et alerter les secours en cas de danger.  

Le code de l'appareil a été développé en C++, et celle de l'application en Java Android.  


### Contenu du Git  

#### PulseSensorPlayground-master.zip : bibliothèque du capteur cardiaque  
#### TestCardiaque.ino : code de l'Arduino  
#### Android : Projet android studio pour l'application mobile


### Composants  

![Wemos D1 Mini](https://i.ebayimg.com/images/g/d08AAOSwEzxYey3m/s-l300.jpg)  
#### Wemos D1 Mini  
Architecture : ESP8266  
Utilisation : carte mère de l'appareil  

![HeartSensor](https://images-eu.ssl-images-amazon.com/images/I/41fRDsDeimL._SY300_QL70_.jpg)  
#### Capteur cardiaque XD58  
Bibliothèque : Pulse Sensor Playground  
Utilisation : permet de récupérer les données des pulsations cardiaques de façon à déterminer un bpm  

![Buzzer](https://www.picclickimg.com/d/l400/pict/332068484518_/10pcs-5V-Active-Buzzer-Magnetic-Long-Continous-Beep.jpg)  
#### 5V Active Continous Beeper/Buzzer  
Utilisation : peut alerter l'utilisateur en cas de danger  

![Thermometer](http://www.lankatronics.com/pub/media/catalog/product/cache/image/500x500/e9c3970ab036de70892d86c6d221abfe/d/s/ds18b20_1.jpg)  
#### Thermomètre DS18B20  
Bibliothèque : ? (à modifier)  
Utilisation : donne des indications supplémentaires sur le santé de l'utilisateur en évaluant sa température  

#### Nombre de cables : 2 (à modifier)  



### Contributeurs  

Samuel BIJOU  
André MOREL  
Loic GOASGUEN  
Azedine NAIDJA  
(Tous élèves en 5ème année dans la filière développement de l'ESGI en spécialité Architecture Logicielle)
