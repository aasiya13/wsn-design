package com.fyp.wsn.Services;

import com.fyp.wsn.DataAccess.AllFunctionsDAO;
import com.fyp.wsn.DataAccess.SensorDAO;
import com.fyp.wsn.Entity.AllFunctions;
import com.fyp.wsn.Entity.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Asela on 4/30/2017.
 */

//This annotation is to indicate this is service class
@Service
public class SensorService {

    // connect with data access class
    @Autowired
    private SensorDAO sensorDAO;

    @Autowired

    private AllFunctionsDAO allFunctionsDAO;



    private void insertfunction(){
        HashMap<String,String> temp=new HashMap<>();
        String key="client_setup();";
        String value="void client_setup(){\n" +
                "  Serial.begin(115200);\n" +
                "  WiFi.mode(WIFI_STA);\n" +
                "  WiFi.begin(BASE_STATION_SSID_NAME,\"\");\n" +
                "  CheckWiFiConnectivity();\n" +
                "  TKDRequest();\n" +
                "}";
        temp.put(key,value);

        key="SetJSON();";
        value="void SetJSON(){\n" +
                "  dataMessage=\"{\";\n" +
                "  dataMessage=dataMessage+\"\\\"DEVICE NAME\\\":\\\"\";\n" +
                "  dataMessage=dataMessage+ DEVICE_NAME;\n" +
                "  dataMessage=dataMessage+ \"\\\",\";\n" +
                "  dataMessage=dataMessage+\"\\\"DEVICE ID\\\":\\\"\";\n" +
                "  dataMessage=dataMessage+ DEVICE_ID;\n" +
                "  dataMessage=dataMessage+ \"\\\",\";\n" +
                "  dataMessage=dataMessage+\"\\\"COUNT\\\":\\\"\";\n" +
                "  dataMessage=dataMessage+Count;\n" +
                "  dataMessage=dataMessage+ \"\\\",\";\n" +
                "  dataMessage=dataMessage+\"\\\"SENSOR VALUES\\\":[\";\n" +
                "}";
        temp.put(key,value);

        key="CheckWiFiConnectivity();";
        value="void CheckWiFiConnectivity(){\n" +
                "  while(WiFi.status() != WL_CONNECTED){\n" +
                "    for(int i=0; i < 10; i++){\n" +
                "      delay(500);\n" +
                "    }\n" +
                "  }\n" +
                "}";
        temp.put(key,value);

        key="SendMessage();";
        value="void SendMessage(){\n" +
                "  dataMessage=dataMessage+\"]}\";\n" +
                "  Serial.println(dataMessage +\" - IS SENT\");\n" +
                "  TKDClient.println(dataMessage);\n" +
                "}";
        temp.put(key,value);

        key="TKDRequest();";
        value="void TKDRequest(){\n" +
                "  // First Make Sure You Got Disconnected\n" +
                "  TKDClient.stop();\n" +
                "  // If Sucessfully Connected Send Connection Message\n" +
                "  if(TKDClient.connect(TKDServer, TKDServerPort)){\n" +
                "    temp_sensor_data=\"<I'M DEVICE :\" + DEVICE_NAME;\n" +
                "    temp_sensor_data= temp_sensor_data +\" CONNECTED TO SERVER>\";\n" +
                "  }        \n" +
                "}";
        temp.put(key,value);

        AllFunctions temp_all=new AllFunctions("ccp_node",temp);
        allFunctionsDAO.insertAllFunctions(temp_all);


//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        temp=new HashMap<>();
        key ="CollectData();";
        value="String CollectData(){\n" +
                "  AvailableClients();\n" +
                "  all_data=AvailableMessage();    \n" +
                "  if(all_data.substring(3,9)==\"DEVICE\"){\n" +
                "    WiFi.disconnect();\n" +
                "    time_setup();\n" +
                "    String time_now=getTime();\n" +
                "    JSON_Packet=\"{\\\"TIME:\\\":\\\"\";\n" +
                "    JSON_Packet=JSON_Packet+time_now;\n" +
                "    JSON_Packet=JSON_Packet+\"\\\",\\\"DATA\\\":\";\n" +
                "    JSON_Packet=JSON_Packet+all_data;\n" +
                "    JSON_Packet=JSON_Packet+\"}\";\n" +
                "    Serial.println(JSON_Packet);\n" +
                "    JSON_Packet=\"\";\n" +
                "    WiFi.disconnect();\n" +
                "    SetWifi();          \n" +
                "  }\n" +
                "  else{\n" +
                "  }\n" +
                "  delay(POLLLING_INTERVAL);\n" +
                "  return JSON_Packet;\n" +
                "}";
        temp.put(key,value);

        key="SetWifi();";
        value="void SetWifi(){\n" +
                "  WiFi.disconnect();\n" +
                "  WiFi.mode(WIFI_AP_STA);\n" +
                "  TKDssid      = BASE_STATION_SSID_NAME;\n" +
                "  TKDpassword  = BASE_STATION_PASSWORD;   \n" +
                "  WiFi.softAP(BASE_STATION_SSID_NAME, BASE_STATION_PASSWORD);\n" +
                "  delay(1000); \n" +
                "  IPAddress IP = WiFi.softAPIP();   \n" +
                "  TKDServer.begin();\n" +
                "  TKDServer.setNoDelay(true);\n" +
                "}";
        temp.put(key,value);

        key="AvailableClients();";
        value="void AvailableClients(){   \n" +
                "  if (TKDServer.hasClient()){\n" +
                "    for(uint8_t i = 0; i < MAXSC; i++){\n" +
                "      if (!TKDClient[i] || !TKDClient[i].connected()){\n" +
                "        if(TKDClient[i]){\n" +
                "          TKDClient[i].stop();\n" +
                "        }\n" +
                "        if(TKDClient[i] = TKDServer.available()){\n" +
                "        }\n" +
                "        continue;\n" +
                "      }\n" +
                "    }\n" +
                "    WiFiClient TKDClient = TKDServer.available();\n" +
                "    TKDClient.stop();\n" +
                "  }\n" +
                "  else{\n" +
                "  }\n" +
                "}";
        temp.put(key,value);

        key="AvailableMessage();";
        value="String AvailableMessage(){\n" +
                "  all_data= \"[\";\n" +
                "  for(uint8_t i = 0; i < MAXSC; i++){\n" +
                "    if (TKDClient[i] && TKDClient[i].connected() && TKDClient[i].available()){\n" +
                "      while(TKDClient[i].available()){\n" +
                "        Client_Message=\"\";\n" +
                "        Client_Message = TKDClient[i].readStringUntil('\\r');\n" +
                "        TKDClient[i].flush();\n" +
                "        all_data=all_data+ Client_Message;\n" +
                "        all_data=all_data+ \",\";\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "  all_data=all_data.substring(0,all_data.length()-1);\n" +
                "  all_data=all_data+ \"]\";\n" +
                "  return all_data;\n" +
                "}";
        temp.put(key,value);

        key="time_setup();";
        value="void time_setup(){\n" +
                "  Serial.begin(115200);\n" +
                "  WiFi.begin(SSID_INTERNET, PASSWORD_INTERNET);\n" +
                "  while (WiFi.status() != WL_CONNECTED){\n" +
                "    delay(500);\n" +
                "  }\n" +
                "  udp.begin(UDP_TIME_LOCALPORT);\n" +
                "}";
        temp.put(key,value);

        key="sendNTPpacket(IPAddress& address);";
        value="unsigned long sendNTPpacket(IPAddress& address){\n" +
                "  memset(packetBuffer, 0, NTP_PACKET_SIZE);\n" +
                "  packetBuffer[0] = 0b11100011;\n" +
                "  packetBuffer[1] = 0;\n" +
                "  packetBuffer[2] = 6;\n" +
                "  packetBuffer[3] = 0xEC;\n" +
                "  packetBuffer[12]  = 49;\n" +
                "  packetBuffer[13]  = 0x4E;\n" +
                "  packetBuffer[14]  = 49;\n" +
                "  packetBuffer[15]  = 52;\n" +
                "  udp.beginPacket(address, 123);\n" +
                "  udp.write(packetBuffer, NTP_PACKET_SIZE);\n" +
                "  udp.endPacket();\n" +
                "}";
        temp.put(key,value);

        key="getTime();";
        value="String getTime(){\n" +
                "  WiFi.hostByName(ntpServerName, timeServerIP); \n" +
                "  sendNTPpacket(timeServerIP);\n" +
                "  delay(2000);\n" +
                "  int cb = udp.parsePacket();\n" +
                "  if (!cb){\n" +
                "    return \"0\";\n" +
                "  }\n" +
                "  else{\n" +
                "    udp.read(packetBuffer, NTP_PACKET_SIZE);\n" +
                "    unsigned long highWord = word(packetBuffer[40], packetBuffer[41]);\n" +
                "    unsigned long lowWord = word(packetBuffer[42], packetBuffer[43]);\n" +
                "    unsigned long secsSince1900 = highWord << 16 | lowWord;\n" +
                "    return String(secsSince1900);\n" +
                "  }\n" +
                "}";
        temp.put(key,value);
        temp_all=new AllFunctions("ccp_base",temp);
        allFunctionsDAO.insertAllFunctions(temp_all);

    }
    public Collection<Sensor> getAllSensors(){

//        insertfunction();
        return this.sensorDAO.getAllSensors();
    }

    public Sensor getSensorById(String id){

        return this.sensorDAO.getSensorById(id);

    }

    public void removeSensorById(String id) {

        this.sensorDAO.removeSensorById(id);
    }

    public void updateSensorById(Sensor sensor){

        this.sensorDAO.updateSensorById(sensor);
    }

    public void insertSensor(Sensor sensor) {

        this.sensorDAO.insertSensor(sensor);
    }
}
