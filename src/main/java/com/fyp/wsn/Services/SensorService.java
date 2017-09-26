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
        String key="";
        String value="" ;



        key="Attached_Time();";
        value="void Attached_Time(){\n" +
                " String JSON_Packet=\"\";\n" +
                " Serial.println(all_data.substring(1,5));\n" +
                " if(all_data.substring(1,5)==\"Data\"){\n" +
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
                "    Server_Setup();          \n" +
                "  }\n" +
                "  else{\n" +
                "  }\n" +
                "  delay(POLLLING_INTERVAL);  \n" +
                "  \n" +
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
                "  memset(packetBuffer, 0, UDP_PACKET_SIZE);\n" +
                "  packetBuffer[0] = 0b11100011;\n" +
                "  packetBuffer[1] = 0;\n" +
                "  packetBuffer[2] = 6;\n" +
                "  packetBuffer[3] = 0xEC;\n" +
                "  packetBuffer[12]  = 49;\n" +
                "  packetBuffer[13]  = 0x4E;\n" +
                "  packetBuffer[14]  = 49;\n" +
                "  packetBuffer[15]  = 52;\n" +
                "  udp.beginPacket(address, 123);\n" +
                "  udp.write(packetBuffer, UDP_PACKET_SIZE);\n" +
                "  udp.endPacket();\n" +
                "}";
        temp.put(key,value);

        key="getTime();";
        value="String getTime(){\n" +
                "  WiFi.hostByName(NTP_SERVER_NAME, timeServerIP); \n" +
                "  sendNTPpacket(timeServerIP);\n" +
                "  delay(2000);\n" +
                "  int cb = udp.parsePacket();\n" +
                "  if (!cb){\n" +
                "    return \"0\";\n" +
                "  }\n" +
                "  else{\n" +
                "    udp.read(packetBuffer, UDP_PACKET_SIZE);\n" +
                "    unsigned long highWord = word(packetBuffer[40], packetBuffer[41]);\n" +
                "    unsigned long lowWord = word(packetBuffer[42], packetBuffer[43]);\n" +
                "    unsigned long secsSince1900 = highWord << 16 | lowWord;\n" +
                "    return String(secsSince1900);\n" +
                "  }\n" +
                "}";
        temp.put(key,value);
        AllFunctions temp_all=new AllFunctions("ccp_base",temp);
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
