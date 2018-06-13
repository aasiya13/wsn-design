#include <SPI.h>
#include <nRF24L01.h>
#include <RF24.h>

#define DEVICE_ID 2
#define CHANNEL 1 //MAX 127

RF24 radio(17,16);

const uint64_t pipes[2] = { 0xFFFFFFFFFFLL, 0xCCCCCCCCCCLL };

struct dataStruct{
  char charBuf[50];
}sensor_data;

void Server_Setup(){
  yield();
  Serial.begin(115200);
  Serial.println("starting......");
  Connect();                    // turn on and configure radio  
  Serial.println("restarting radio");
  radio.startListening();
  Serial.println("Listening for sensor values...");
  
}
void setup() {  
  Server_Setup();
}

void loop() {
  Read();
}

void Read(){
  if (radio.available()) {
    while (radio.available()) {
      yield();
      radio.read(&sensor_data, sizeof(sensor_data));
      Serial.println("Got message:");
      Serial.println(sensor_data.charBuf);
      Serial.println("-----------");
   }
 }

}

void Connect(){
  yield();
  radio.begin(); // Start up the radio
  radio.setPALevel(RF24_PA_HIGH);
  radio.setDataRate(RF24_250KBPS);  
  //  radio.openReadingPipe(1,pipes[1]); 
  //  radio.openWritingPipe(pipes[0]);
  radio.openReadingPipe(1,pipes[0]);
  radio.openWritingPipe(pipes[1]);  
  radio.stopListening();
}
