package com.fyp.wsn.Services;

import com.fyp.wsn.DataAccess.*;
import com.fyp.wsn.Entity.Microcontroller;
import com.fyp.wsn.Entity.Sensor;
import com.fyp.wsn.Entity.SensorNode;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nadith Premaratne on 10/05/2017.
 */
//
public class CodeGenForNetwork {

    @Autowired
    private MicrocontrollerDAO microcontrollerDAO;
    @Autowired
    private SensorNodeDAO sensorNodeDAO;
    @Autowired
    private SensorNetworkDAO sensorNetworkDAO;


    private HashMap<String,ArrayList<String>> adjacency_node;
    private HashMap<String,String> define_tags;
    private HashMap<String,Integer> pin_usage;

    //get the network configurtion fucntion
    public CodeGenForNetwork(String list, SensorNodeDAO sensorNodeDAO, MicrocontrollerDAO microcontrollerDAO, SensorNetworkDAO sensorNetworkDAO) {

        ArrayList<String> temp=new ArrayList<>();
        String [] temp_devices_ids=list.split(",");

        for(int i=1;i<temp_devices_ids.length ;i++){
            temp.add(temp_devices_ids[i]);
        }

        this.adjacency_node=new HashMap<>();
        this.adjacency_node.put(temp_devices_ids[0],temp);
        this.sensorNetworkDAO=sensorNetworkDAO;
        this.microcontrollerDAO=microcontrollerDAO;
        this.sensorNodeDAO=sensorNodeDAO;

    }

    /*  #define       DEVICE_NAME             String("NODE_BETA")
  #define       DEVICE_ID               "343734"
  #define       BASE_STATION_SSID_NAME  "TAKEONE"
  #define       BASE_STATION_IP_ADDRESS 192,168,4,1
  #define       WIFI_SERVER_PORT        9001
  #define       SENSOR_DATA_MESSURING_INTERVAL 20000
  #define     SSID_INTERNET           "AndroidAP"
    #define     PASSWORD_INTERNET       "12345678"
    #define     UDP_TIME_LOCALPORT      2390
    #define     BASE_STATION_SSID_NAME  "TAKEONE"
    #define     BASE_STATION_PASSWORD   ""
    #define     MAXSC                   6           // MAXIMUM NUMBER OF CLIENTS
    #define     WIFI_SERVER_PORT        9001
    #define     POLLLING_INTERVAL       500
    #define     UDP_PACKET_SIZE         48
    #define     NTP_SERVER_NAME         "time.nist.gov"
  */



    private void setDefineFromJSON(){



    }

    private String setSSID_INTERNET(String ssid){

        String temp= "#define SSID_INTERNET \""+ssid+"\"\n";
        return temp;
    }
    private String setPOLLLING_INTERVAL(String poll){

        String temp= "#define POLLLING_INTERVAL "+poll+"\n";
        return temp;
    }

    private String setUDP_PACKET_SIZE(int size){

        String temp= "#define UDP_PACKET_SIZE "+size+"\n";
        return temp;
    }
    private String setNTP_SERVER_NAME(String timeserver){

        String temp= "#define NTP_SERVER_NAME \""+timeserver+"\"\n";
        return temp;
    }






    private String setPASSWORD_INTERNET(String pass){

        String temp= "#define PASSWORD_INTERNET \""+pass+"\"\n";
        return temp;
    }

    private String setUDP_TIME_LOCALPORT(int port){

        String temp= "#define UDP_TIME_LOCALPORT "+port+"\n";
        return temp;
    }
    private String setMAXSC(int maxnode){

        String temp= "#define MAXSC "+maxnode+"\n";
        return temp;
    }






    private String setDEVICE_NAME(String name){

        String temp= "#define DEVICE_NAME \""+name+"\"\n";
        return temp;
    }

    private String setDEVICE_ID(int baseIP,int nodeIP){

        String temp= "#define DEVICE_ID \""+baseIP+"-"+nodeIP+"\"\n";
        return temp;
    }

    private String setBASE_STATION_SSID_NAME(String prefixSSID,int baseIP){

        String temp= "#define BASE_STATION_SSID_NAME \""+prefixSSID+"_"+baseIP+"\"\n";
        return temp;
    }

    private String setBASE_STATION_PASSWORD(String prefixPASS){

        String temp= "#define BASE_STATION_PASSWORD \""+prefixPASS+"\"\n";
        return temp;
    }

    private String setBASE_STATION_IP_ADDRESS(int baseIP){

        String temp= "#define BASE_STATION_IP_ADDRESS 192,168,"+baseIP+",1\n";
        return temp;
    }
    private String setWIFI_SERVER_PORT(int port){

        String temp= "#define WIFI_SERVER_PORT "+port+"\n";
        return temp;
    }

    private String setSENSOR_DATA_MESSURING_INTERVAL(int time){

        String temp= "#define SENSOR_DATA_MESSURING_INTERVAL "+time+"\n";
        return temp;
    }

    private void WriteToFile(String FILENAME,String content){


        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {

            bw.write(content);


        } catch (IOException e) {

            e.printStackTrace();

        }



    }
    public void finalizedNetworkCode(){
        String full_code=null;


        for(Map.Entry<String,ArrayList<String>> entry: adjacency_node.entrySet()) {

            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            int base_station_id=1;
            //get the number of nodes connected to basestation
            int No_of_nodes = value.size();
            SensorNode base_station = sensorNodeDAO.getSensorNodeById(key);

            ModifyXMLFile xml_structure=new ModifyXMLFile("C:\\Users\\Asela\\IdeaProjects\\wsn-design-studio\\src\\main\\resources\\input\\testcodenode.xml","d:\\testcodeoutputnode.xml");
            try {
                xml_structure.setDoc(xml_structure.StringToXML(base_station.getDoc()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            xml_structure.addElement("define_macro","define_Issid"+base_station_id,setSSID_INTERNET(base_station.getInternet_ssid()));
            xml_structure.addElement("define_macro","define_Ipass"+base_station_id,setPASSWORD_INTERNET(base_station.getInternet_password()));
            xml_structure.addElement("define_macro","define_BSssid"+base_station_id,setBASE_STATION_SSID_NAME("BASE_STATION",base_station_id));
            xml_structure.addElement("define_macro","define_BSpass"+base_station_id,setBASE_STATION_PASSWORD(""));
            xml_structure.addElement("define_macro","define_timeserver"+base_station_id,setNTP_SERVER_NAME("time.nist.gov"));
            xml_structure.addElement("define_macro","define_maxc"+base_station_id,setMAXSC(No_of_nodes));
            xml_structure.addElement("define_macro","define_udp_port"+base_station_id,setUDP_TIME_LOCALPORT(2390));
            xml_structure.addElement("define_macro","define_udp_pac_size"+base_station_id,setUDP_PACKET_SIZE(48));
            xml_structure.addElement("define_macro","define_wifi_port"+base_station_id,setWIFI_SERVER_PORT(9001));
            xml_structure.addElement("define_macro","define_poll"+base_station_id,setPOLLLING_INTERVAL(base_station.getInterval()));


            WriteToFile("C:\\Users\\Asela\\IdeaProjects\\wsn-design-studio\\src\\main\\resources\\static\\output\\"+base_station.getName()+"_"+"code"+".ino",xml_structure.getText());

            for(String sensor_node_id :value){

                int node_id=2;

                SensorNode node = sensorNodeDAO.getSensorNodeById(sensor_node_id);

                xml_structure=new ModifyXMLFile("C:\\Users\\Asela\\IdeaProjects\\wsn-design-studio\\src\\main\\resources\\input\\testcodenode.xml","d:\\testcodeoutputnode.xml");
                try {
                    xml_structure.setDoc(xml_structure.StringToXML(node.getDoc()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                xml_structure.addElement("define_macro","define_BSssid"+base_station_id+""+node_id,setBASE_STATION_SSID_NAME("BASE_STATION",base_station_id));
                xml_structure.addElement("define_macro","define_BSpass"+base_station_id+""+node_id,setBASE_STATION_PASSWORD(""));
                xml_structure.addElement("define_macro","define_Device_name"+base_station_id+""+node_id,setDEVICE_NAME(node.getName()));
                xml_structure.addElement("define_macro","define_Device_id"+base_station_id+""+node_id,setDEVICE_ID(base_station_id,node_id));
                xml_structure.addElement("define_macro","define_base_ip"+base_station_id+""+node_id,setBASE_STATION_IP_ADDRESS(base_station_id));
                xml_structure.addElement("define_macro","define_wifi_port"+base_station_id+""+node_id,setWIFI_SERVER_PORT(9001));
                xml_structure.addElement("define_macro","define_poll"+base_station_id+""+node_id,setSENSOR_DATA_MESSURING_INTERVAL(Integer.parseInt(node.getInterval())));

                node_id++;
                WriteToFile("C:\\Users\\Asela\\IdeaProjects\\wsn-design-studio\\src\\main\\resources\\static\\output\\"+node.getName()+"_"+"code"+".ino",xml_structure.getText());

            }
            base_station_id++;
        }

    }
}
