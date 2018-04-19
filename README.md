# IOTHeartSensor  
Project on a heart sensor for IOT course at ESGI  


### Project Name: HeartQuake  

### Goals  

Use a heart sensor, coupled with a thermometer, to alert the emergency services in the event of a user's cardiac hazard.

The project therefore includes a device that can analyze the user and alert him of a risk via a buzzer, as well as a mobile application that tracks his heart rate and temperature and can also locate the user and alert the emergency services in case of danger.

The device code was developed in C ++, and that of the application in Java Android.  


### Git content  

#### PulseSensorPlayground-master.zip: Heart Sensor Library  
#### TestCardiaque.ino: Arduino code  
#### Android: Android Studio Project for mobile application  


### Components  


#### Wemos D1 Mini  
![Wemos D1 Mini](https://i.ebayimg.com/images/g/d08AAOSwEzxYey3m/s-l300.jpg)  
Architecture: ESP8266  
Use: motherboard of the device  


#### Capteur cardiaque XD58  
![HeartSensor](https://images-eu.ssl-images-amazon.com/images/I/41fRDsDeimL._SY300_QL70_.jpg)  
Library: Pulse Sensor Playground  
Use: allows you to recover heartbeat data to determine a bpm  


#### 5V Active Continous Beeper/Buzzer  
![Buzzer](https://www.picclickimg.com/d/l400/pict/332068484518_/10pcs-5V-Active-Buzzer-Magnetic-Long-Continous-Beep.jpg)  
Use: can alert the user in case of danger  


#### Thermomètre DS18B20  
![Thermometer](http://www.lankatronics.com/pub/media/catalog/product/cache/image/500x500/e9c3970ab036de70892d86c6d221abfe/d/s/ds18b20_1.jpg)  
Library: OneWire, DallasTemperature  
Use: gives additional indications on the health of the user by evaluating its temperature  



### Cable schematics  
![Schéma](https://i.imgur.com/OCIpXDq.png)  

#### Additional cables: 4  



### Contributors  

Samuel BIJOU  
André MOREL  
Loic GOASGUEN  
Azedine NAIDJA  
(All students in the 5th year in computer development science with Software Architecture specialty at ESGI School)
