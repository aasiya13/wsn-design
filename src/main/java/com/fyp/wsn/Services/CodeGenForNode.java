package com.fyp.wsn.Services;

import com.fyp.wsn.DataAccess.*;
import com.fyp.wsn.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Nadith Premaratne on 14/05/2017.
 */
public class  CodeGenForNode {

    @Autowired
    private SensorDAO sensorDAO;
    @Autowired
    private SensorNodeDAO sensorNodeDAO;
    @Autowired
    private  MicrocontrollerDAO microcontrollerDAO;
    @Autowired
    private CommunicationDAO communicationDAO;

    @Autowired AllFunctionsDAO allFunctionsDAO;

    private HashMap<String,ArrayList<String>> adjacency_node;
    private HashMap<String,Integer> pin_usage;
    private String Node_name;
    private SensorNode sensorNode;



    //get the network configurtion
    public CodeGenForNode(SensorNode sensorNode,MicrocontrollerDAO microcontrollerDAO,SensorNodeDAO sensorNodeDAO,SensorDAO sensorDAO,AllFunctionsDAO allFunctionsDAO,CommunicationDAO communicationDAO) {

        ArrayList<String> elementsID_of_node=new ArrayList<>();
        String [] temp_devices_ids=sensorNode.getConfiguration().split(",");

        for(int i=1;i<temp_devices_ids.length ;i++){
            elementsID_of_node.add(temp_devices_ids[i]);
        }
        this.sensorNode=sensorNode;
        this.adjacency_node=new HashMap<>();
        this.adjacency_node.put(temp_devices_ids[0],elementsID_of_node);
        this.Node_name=sensorNode.getName();
        this.microcontrollerDAO=microcontrollerDAO;
        this.sensorNodeDAO=sensorNodeDAO;
        this.sensorDAO=sensorDAO;
        this.allFunctionsDAO=allFunctionsDAO;
        this.communicationDAO=communicationDAO;
    }

    public String getNode_name() {
        return Node_name;
    }

    public void setNode_name(String node_name) {
        Node_name = node_name;
    }

    public HashMap<String,String> getCommonFunctionListForNodesCPP(){

        HashMap<String,String> result=new HashMap<>();

        //get common function like communication way,
        return result;
    }


    public HashMap<String,String> getSenosorFunctionList(List<String> Sensor_list){

        HashMap<String,String> result=new HashMap<>();

        for(String temp_sensor :Sensor_list){
            //get the each sensor data messure function and put it the hashmap


        }

        return result;
    }


    public ArrayList<String> getIncludeLibraryListCpp(List<String> Sensor_list){

        ArrayList<String> result=new ArrayList<>();

        for(String temp_sensor_id :Sensor_list){
            //get the each comma seperated library names and put it the arraylist fom the DB
            Sensor sensor_obj=this.sensorDAO.getSensorById(temp_sensor_id);
            String temp_include_list=sensor_obj.getCpp_includes();

            String [] temp_include= temp_include_list.split(",");
            for(String x :temp_include){
                if(result.contains(x));
                else result.add(x);
            }

        }
        if(result.contains("ESP8266WiFi.h"))
            result.add("ESP8266WiFi.h");
        if(result.contains("WiFiUdp.h"))
            result.add("WiFiUdp.h");
        return result;
    }



    public void getSensorFunctionCpp(HandleSensorFunctions handleSensorFunctions,List<String> Sensor_list,ModifyXMLFile xml){

        ArrayList<String> result=new ArrayList<>();
        ArrayList<String> function_already_taken=new ArrayList<>();

        for(Map.Entry<String,HashMap<Integer,String>> entry: handleSensorFunctions.getNew_edited_functions().entrySet()) {

            String key = entry.getKey();
            HashMap<Integer, String> value = entry.getValue();

            for(Map.Entry<Integer,String> entry1: value.entrySet()) {

                int function_no=entry1.getKey();
                String function=entry1.getValue();
                Sensor sensor_obj = this.sensorDAO.getSensorById(key);
                xml.addElement("function_code", sensor_obj.getDisplay_name() + "function"+function_no, function + "\n");

            }

        }

        xml.toString("function_code");

    }

    private String GenerateCppBaseStationCode(String key,ArrayList<String> node){

        int no_of_nodes=node.size();
        ArrayList<String> list_include_libraries= getIncludeLibraryListCpp(node);

        return null;
    }

    private void includeHeadersCpp(ModifyXMLFile xml,ArrayList<String> list){
        Communication communication=this.communicationDAO.getCommunicationById(this.sensorNode.getCommunication_method());

        String [] include_list=communication.getIncludes().split(",");

        int no_of_library=1;
        for(String x : list){
            String include_line="#include<"+x+">\n\t";
            xml.addElement("include_library","include"+no_of_library,include_line);
            no_of_library++;
        }

        for(String x : include_list){
            String include_line="#include<"+x+">\n\t";
            xml.addElement("include_library","include"+no_of_library,include_line);
            no_of_library++;
        }

        xml.toString("include_library");


    }

    private void ccploopBase(ModifyXMLFile xml){

        String temp="   Read();\n" +
                    "   Attached_Time();\n";

        xml.addElement("loop_start","loop_call",temp);

    }

    private void cppSetupBase(ModifyXMLFile xml){
        String temp=" Server_Setup();\n";
        xml.addElement("setup_code","setup_wifi",temp);
    }
    private void ccploopNode(ModifyXMLFile xml){

        for(Map.Entry<String,ArrayList<String>> entry: this.adjacency_node.entrySet()) {
            String Micro = entry.getKey();
            ArrayList<String> sensor_id_list= entry.getValue();

            String value= "   dataMessage= \"Data: \" + DEVICE_NAME;\n" +
                    "   dataMessage= dataMessage + \" : \";\n";
            xml.addElement("loop_middle","Node_name", value);


            for(String x: sensor_id_list){

                Sensor temp_sen=this.sensorDAO.getSensorById(x);
                String function=temp_sen.getCpp_function();

                String [] temp_str=function.split("\\{");
                String [] temp_str1=temp_str[0].split(" ");

                String function_sytax=temp_str1[1]+";";

                String code_pt="temp_sensor_data = " +function_sytax+"\n";

                String code_next="   dataMessage=dataMessage+temp_sensor_data;\n" +
                        "   dataMessage=dataMessage+\",\";";

                code_next=code_pt+code_next;

                xml.addElement("loop_middle","function_"+temp_sen.getDisplay_name(), code_next+"\n");

            }


        }


            String value =  "   int str_len = dataMessage.length() + 1;\n" +
                            "   dataMessage.toCharArray(sensor_data.charBuf,str_len) ;\n"+
                            "   Send();\n" +
                            "   delay(SENSOR_DATA_MESSURING_INTERVAL);\n" +
                            "   Connect();\n";
            xml.addElement("loop_end","finalize_data_dtructure", value);

    }

    private void cppSetupNode(ModifyXMLFile xml){

        String temp_vaue="Client_Setup();\n";
        xml.addElement("setup_code","intial_setup",temp_vaue);
    }

    private void SetGlobalNodeVaribles(ModifyXMLFile xml){

        String value="String dataMessage;\n" +
                "String temp_sensor_data;\n" +
                "IPAddress TKDServer(BASE_STATION_IP_ADDRESS);\n" +
                "WiFiClient TKDClient;\n"+
                "struct dataStruct{\n" +
                "  char charBuf[50];\n" +
                "}sensor_data;\n";

        xml.addElement("global_varible","General_Varible",value);

    }

    private void SetGlobalBaseVaribles(ModifyXMLFile xml){

        String value="String all_data=\"\";\n" +
                "String Client_Message =\"\";\n" +
                "IPAddress timeServerIP;\n" +
                "byte packetBuffer[ UDP_PACKET_SIZE];\n" +
                "WiFiUDP udp;  \n" +
                "WiFiServer TKDServer(WIFI_SERVER_PORT);\n" +
                "WiFiClient TKDClient[MAXSC];\n";

        xml.addElement("global_varible","General_Varible",value);


    }
    //for micro
    private HashMap<String,ArrayList<String> > initiaizePins(String pin_array){//DIGITAL_INPUT-
        HashMap<String,ArrayList<String>> avalible_pin=new HashMap<>();

        ArrayList<String> temp_list;

        String [] pin_mapping =pin_array.split(",");

        for(String x: pin_mapping){

            String [] keyval=x.split("-");

            String pin_number=keyval[0];
            String tag_name=keyval[1];
            if(avalible_pin.containsKey(tag_name)){
                temp_list=avalible_pin.get(tag_name);
                temp_list.add(pin_number);

            }
            else{
                temp_list=new ArrayList<>();
                temp_list.add(pin_number);
                avalible_pin.put(tag_name,temp_list);

            }


        }

        return avalible_pin;
    }
    //for sensor
    public HashMap<String,Integer> getSensorPin(String pin_array){
        HashMap<String,Integer> avalible_pin=new HashMap<>();

        ArrayList<String> temp_list;

        String [] pin_mapping =pin_array.split(",");

        for(String x: pin_mapping) {

            String[] keyval = x.split("-");

            String tag_name = keyval[0];
            String No_of_pins = keyval[1];

            avalible_pin.put(tag_name,Integer.parseInt(No_of_pins));

        }

       return avalible_pin;
    }

    public void getCommunicationFunctionNode(ModifyXMLFile xml){

        Communication communication_method = this.communicationDAO.getCommunicationById(this.sensorNode.getCommunication_method());


        xml.addElement("function_code", "Client_Setup" + "_function", communication_method.getClient_setup() + "\n");
        xml.addElement("function_code", "Send" + "_function", communication_method.getCpp_send() + "\n");
        xml.addElement("function_code", "Connect" + "_function", communication_method.getCpp_connect() + "\n");

    }


    public void getGeneralFunctionBase(ModifyXMLFile xml){

        String Common_function_for_all="void time_setup(){\n" +
                "  Serial.begin(115200);\n" +
                "  WiFi.begin(SSID_INTERNET, PASSWORD_INTERNET);\n" +
                "  while (WiFi.status() != WL_CONNECTED){\n" +
                "    delay(500);\n" +
                "  }\n" +
                "  udp.begin(UDP_TIME_LOCALPORT);\n" +
                "}\n" +
                "\n" +
                "void Attached_Time(){\n" +
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
                "}\n" +
                "\n" +
                "unsigned long sendNTPpacket(IPAddress& address){\n" +
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
                "}\n" +
                "\n" +
                "String getTime(){\n" +
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
                "}\n";
        xml.addElement("function_code",  "common_function", Common_function_for_all);


        Communication communication_method = this.communicationDAO.getCommunicationById(this.sensorNode.getCommunication_method());


        xml.addElement("function_code", "Server_Setup" + "_function", communication_method.getServer_setup() + "\n");
        xml.addElement("function_code", "Send" + "_function", communication_method.getCpp_send() + "\n");
        xml.addElement("function_code", "Connect" + "_function", communication_method.getCpp_connect() + "\n");
        xml.addElement("function_code", "Read" + "_function", communication_method.getCpp_receive() + "\n");



    }

    private void getIncludeLibraryies(ModifyXMLFile xml){

        String temp="#include <ESP8266WiFi.h>\n" +
                "#include <WiFiUdp.h>\n";
        xml.addElement("include_library", "General_include", temp);

        Communication communication=this.communicationDAO.getCommunicationById(this.sensorNode.getCommunication_method());
        String [] include_list=communication.getIncludes().split(",");

        int no_of_library=0;
        for(String x : include_list){
            if(x == "ESP8266WiFi.h") continue;
            String include_line="#include<"+x+">\n\t";
            xml.addElement("include_library","include"+no_of_library,include_line);
            no_of_library++;
        }

    }




    private void WriteToFile(String FILENAME,String content){


        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {

            bw.write(content);


        } catch (IOException e) {

            e.printStackTrace();

        }



    }


    public void CodeforNode(){
        String full_code=null;

        for(Map.Entry<String,ArrayList<String>> entry: adjacency_node.entrySet()){

            String key=entry.getKey();
            ArrayList<String> value=entry.getValue();
            //Microcontroller base_station=microcontrollerDAO.getMicrocontrollerById(key);
            //GenerateCppNode(key,value);
            ModifyXMLFile xml_structure=new ModifyXMLFile("C:\\Users\\noahn\\IdeaProjects\\wsn-design-studio-master\\src\\main\\resources\\input\\testcodenode.xml","C:\\Users\\noahn\\IdeaProjects\\wsn-design-studio-master\\src\\main\\resources\\output\\testcodeoutputnode.xml");
            xml_structure.getReady();

            Microcontroller node_microcontroller=this.microcontrollerDAO.getMicrocontrollerById(key);
            ArrayList<String> sensor_list=value;

            HandleSensorFunctions handleSensorFunctions=new HandleSensorFunctions(sensor_list,this.sensorDAO);
            HashMap<String,ArrayList<String>> avalaible_pins=initiaizePins(node_microcontroller.getPin_map());
            PinAllocation analyzer=new PinAllocation(avalaible_pins);
            HashMap<String,Integer> sensor_pin;
            int no_of_nodes=sensor_list.size();

            //handling include libraries
            ArrayList<String> list_include_libraries= getIncludeLibraryListCpp(sensor_list);
            includeHeadersCpp(xml_structure,list_include_libraries);
            // handling sensor function
            getSensorFunctionCpp(handleSensorFunctions,sensor_list,xml_structure);

            //handling define pins and pinmodes
            for(String sensor_id :sensor_list){

                Sensor temp_sensor=this.sensorDAO.getSensorById(sensor_id);
                String pin_array=temp_sensor.getConfiguration();
                sensor_pin=getSensorPin(pin_array);

                analyzer.handleSensor(sensor_id,handleSensorFunctions,sensor_pin,xml_structure,temp_sensor.getDisplay_name());


            }

            //handing general function in a node
            getCommunicationFunctionNode(xml_structure);
            SetGlobalNodeVaribles(xml_structure);
            cppSetupNode(xml_structure);
            ccploopNode(xml_structure);


            SensorNode sensorNode=this.sensorNodeDAO.getSensorNodeByName(this.getNode_name());
            sensorNode.setDoc(xml_structure.XMLToString(xml_structure.getDoc()));
            this.sensorNodeDAO.updateSensorNodeById(sensorNode);

            try {
                org.w3c.dom.Document xml=xml_structure.StringToXML(sensorNode.getDoc());
                xml_structure.setDoc(xml);
            } catch (Exception e) {
                e.printStackTrace();
            }



        }

    }

    public void CodeforBase(){
        String full_code=null;

        for(Map.Entry<String,ArrayList<String>> entry: adjacency_node.entrySet()){

            String key=entry.getKey();
            ArrayList<String> value=entry.getValue();
            Microcontroller base_station=microcontrollerDAO.getMicrocontrollerById(key);
            ModifyXMLFile xml_structure=new ModifyXMLFile("C:\\Users\\noahn\\IdeaProjects\\wsn-design-studio-master\\src\\main\\resources\\input\\testcodenode.xml","C:\\Users\\noahn\\IdeaProjects\\wsn-design-studio-master\\src\\main\\resources\\output\\testcodeoutputnode.xml");

            xml_structure.getReady();
            Microcontroller node_microcontroller=this.microcontrollerDAO.getMicrocontrollerById(key);


            //handing general function in a node
            getIncludeLibraryies(xml_structure);
            getGeneralFunctionBase(xml_structure);
            SetGlobalBaseVaribles(xml_structure);
            cppSetupBase(xml_structure);
            ccploopBase(xml_structure);


            SensorNode sensorNode=this.sensorNodeDAO.getSensorNodeByName(this.getNode_name());
            sensorNode.setDoc(xml_structure.XMLToString(xml_structure.getDoc()));
            this.sensorNodeDAO.updateSensorNodeById(sensorNode);

            try {
                org.w3c.dom.Document xml=xml_structure.StringToXML(sensorNode.getDoc());
                xml_structure.setDoc(xml);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //   WriteToFile("d:\\"+this.getNode_name()+"_"+"code"+".ino",xml_structure.getText());



        }

    }


}
