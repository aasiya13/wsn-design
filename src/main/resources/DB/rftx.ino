#include <SPI.h>
#include <nRF24L01.h>
#include <RF24.h>


  #define       DEVICE_NAME             String("NODE_GAMMA")
  #define       BASE_STATION_SSID_NAME  "BASE_STATION_1"
  #define       BASE_STATION_PASSWORD   ""
  #define       BASE_STATION_IP_ADDRESS 192,168,4,1
  #define       WIFI_SERVER_PORT        9001
  #define       SENSOR_DATA_MESSURING_INTERVAL 20000

String dataMessage;
String temp_sensor_data;

RF24 radio(17,16);
 
const uint64_t pipes[2] = { 0xFFFFFFFFFFLL, 0xCCCCCCCCCCLL };

struct dataStruct{
  char charBuf[50];
}sensor_data;
 
void setup() {
  Client_Setup();
}
 
void loop() {  
       
   dataMessage= "Data: " + DEVICE_NAME;
   dataMessage= dataMessage + " : ";
   temp_sensor_data="TEMP\\27-";
   dataMessage=dataMessage+temp_sensor_data;
   dataMessage=dataMessage+",";
   temp_sensor_data="HUMID\\68-";
   dataMessage=dataMessage+temp_sensor_data;  
   
   int str_len = dataMessage.length() + 1;
   dataMessage.toCharArray(sensor_data.charBuf,str_len) ;
   
   Send();

   delay(SENSOR_DATA_MESSURING_INTERVAL);
   Connect();
}



void Send(){

    radio.stopListening();    
    Serial.println("Now sending");
    if(!radio.write(&sensor_data, sizeof(sensor_data))){
      Serial.println("failed...");
    }
    else Serial.println("Sent...");
    radio.powerDown();      

}
 
void Client_Setup(){
  Serial.begin(115200);
  Connect();
}
void Connect() {
  Serial.println("Sleeping...");
  radio.begin(); // Start up the radio
  radio.setPALevel(RF24_PA_HIGH);
  radio.setDataRate(RF24_250KBPS);
  radio.openReadingPipe(1, pipes[1]);
  radio.openWritingPipe(pipes[0]);
  //  radio.openReadingPipe(1,pipes[0]);
  //  radio.openWritingPipe(pipes[1]);
  radio.stopListening();
}
 
