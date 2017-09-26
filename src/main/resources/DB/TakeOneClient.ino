//------------------------------------------------------------------------------------
// Libraries Needed For This Project
//------------------------------------------------------------------------------------
  #include <ESP8266WiFi.h>    // The Basic Function Of The ESP NOD MCU
//------------------------------------------------------------------------------------
// Defining I/O Pins
//------------------------------------------------------------------------------------

  #define       DEVICE_NAME             String("NODE_GAMMA")
  #define       BASE_STATION_SSID_NAME  "BASE_STATION_1"
  #define       BASE_STATION_PASSWORD   ""
  #define       BASE_STATION_IP_ADDRESS 192,168,4,1
  #define       WIFI_SERVER_PORT        9001
  #define       SENSOR_DATA_MESSURING_INTERVAL 20000

String dataMessage;
String temp_sensor_data;
IPAddress TKDServer(BASE_STATION_IP_ADDRESS);
WiFiClient TKDClient;

struct dataStruct{
  char charBuf[50];
}sensor_data;

void setup(){
  Client_Setup();
  pinMode(A0,OUTPUT);
}

//====================================================================================
  
  void loop()
  {
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

//====================================================================================
void Client_Setup(){
  Serial.begin(115200);
  WiFi.mode(WIFI_STA);
  WiFi.begin(BASE_STATION_SSID_NAME,BASE_STATION_PASSWORD);
  while(WiFi.status() != WL_CONNECTED){
    for(int i=0; i < 10; i++){
      delay(500);
    }
  }
  Connect();
}

void Send(){
  Serial.println(dataMessage +" - IS SENT");
  TKDClient.println(dataMessage);
}
void Connect(){
  TKDClient.stop();
  if(TKDClient.connect(TKDServer, WIFI_SERVER_PORT)){
    temp_sensor_data= "<I'M DEVICE :" + DEVICE_NAME;
    temp_sensor_data= temp_sensor_data +" CONNECTED TO SERVER>";
  }        
}
void Disconnect(){
  TKDClient.stop();
}
