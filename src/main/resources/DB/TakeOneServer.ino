 //-- Libraries Included --------------------------------------------------------------
#include <ESP8266WiFi.h>
#include <WiFiUdp.h>




#define     SSID_INTERNET           "AndroidAP"
#define     PASSWORD_INTERNET       "12345678"
#define     UDP_TIME_LOCALPORT      2390
#define     BASE_STATION_SSID_NAME  "BASE_STATION_1"
#define     BASE_STATION_PASSWORD   ""
#define     MAXSC                   6           // MAXIMUM NUMBER OF CLIENTS
#define     WIFI_SERVER_PORT        9001
#define     POLLLING_INTERVAL       500
#define     UDP_PACKET_SIZE         48
#define     NTP_SERVER_NAME         "time.nist.gov"
//------------------------------------------------------------------------------------
  // Authentication Variables

String all_data="";
String Client_Message ="";

IPAddress timeServerIP;
byte packetBuffer[ UDP_PACKET_SIZE];
WiFiUDP udp;  

WiFiServer TKDServer(WIFI_SERVER_PORT);
WiFiClient TKDClient[MAXSC];

  void setup()
  {
    Server_Setup();
  }

//====================================================================================
  
  void loop()
  {  
    Read();
    Attached_Time();
  }
void Read(){
    if (TKDServer.hasClient()){
    for(uint8_t i = 0; i < MAXSC; i++){
      if (!TKDClient[i] || !TKDClient[i].connected()){
        if(TKDClient[i]){
          TKDClient[i].stop();
        }
        if(TKDClient[i] = TKDServer.available()){
        }
        continue;
      }
    }
    WiFiClient TKDClient = TKDServer.available();
    TKDClient.stop();
  }
  else{
  }
  all_data= "[";
    for(uint8_t i = 0; i < MAXSC; i++){
      if (TKDClient[i] && TKDClient[i].connected() && TKDClient[i].available()){
        while(TKDClient[i].available()){
          Serial.print(".");
          Client_Message="";
          Client_Message = TKDClient[i].readStringUntil('\r');
          TKDClient[i].flush();
          all_data=all_data+ Client_Message;
          all_data=all_data+ "+";
        }
      }
    }
    all_data=all_data.substring(0,all_data.length()-1);
    all_data=all_data+ "]";
}

void Server_Setup(){
  Serial.begin(115200);
  WiFi.disconnect();
  WiFi.mode(WIFI_AP_STA);  
  WiFi.softAP(BASE_STATION_SSID_NAME, BASE_STATION_PASSWORD);
  delay(1000); 
  IPAddress IP = WiFi.softAPIP();   
  TKDServer.begin();
  TKDServer.setNoDelay(true);
}

void time_setup(){
  Serial.begin(115200);
  WiFi.begin(SSID_INTERNET, PASSWORD_INTERNET);
  while (WiFi.status() != WL_CONNECTED){
    delay(500);
  }
  udp.begin(UDP_TIME_LOCALPORT);
}

void Attached_Time(){
 String JSON_Packet="";
 Serial.println(all_data.substring(1,5));
 if(all_data.substring(1,5)=="Data"){
    WiFi.disconnect();
    time_setup();
    String time_now=getTime();
    JSON_Packet="{\"TIME:\":\"";
    JSON_Packet=JSON_Packet+time_now;
    JSON_Packet=JSON_Packet+"\",\"DATA\":";
    JSON_Packet=JSON_Packet+all_data;
    JSON_Packet=JSON_Packet+"}";
    Serial.println(JSON_Packet);
    JSON_Packet="";
    WiFi.disconnect();
    Server_Setup();          
  }
  else{
  }
  delay(POLLLING_INTERVAL);  
  
}

unsigned long sendNTPpacket(IPAddress& address){
  memset(packetBuffer, 0, UDP_PACKET_SIZE);
  packetBuffer[0] = 0b11100011;
  packetBuffer[1] = 0;
  packetBuffer[2] = 6;
  packetBuffer[3] = 0xEC;
  packetBuffer[12]  = 49;
  packetBuffer[13]  = 0x4E;
  packetBuffer[14]  = 49;
  packetBuffer[15]  = 52;
  udp.beginPacket(address, 123);
  udp.write(packetBuffer, UDP_PACKET_SIZE);
  udp.endPacket();
}

String getTime(){
  WiFi.hostByName(NTP_SERVER_NAME, timeServerIP); 
  sendNTPpacket(timeServerIP);
  delay(2000);
  int cb = udp.parsePacket();
  if (!cb){
    return "0";
  }
  else{
    udp.read(packetBuffer, UDP_PACKET_SIZE);
    unsigned long highWord = word(packetBuffer[40], packetBuffer[41]);
    unsigned long lowWord = word(packetBuffer[42], packetBuffer[43]);
    unsigned long secsSince1900 = highWord << 16 | lowWord;
    return String(secsSince1900);
  }
}
