package com.fyp.wsn.Entity;

import com.fyp.wsn.Services.ModifyXMLFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nadith Premaratne on 15/05/2017.
 */
public class PinAllocation {

    private HashMap<String,ArrayList<String>> avalibale_pins;

    public PinAllocation(HashMap<String, ArrayList<String>> avalibale_pins) {
        this.avalibale_pins = avalibale_pins;
    }

    public HashMap<String, ArrayList<String>> getAvalibale_pins() {
        return avalibale_pins;
    }

    public void setAvalibale_pins(HashMap<String, ArrayList<String>> avalibale_pins) {
        this.avalibale_pins = avalibale_pins;
    }

    public void handleSensor(String sensor_id,HandleSensorFunctions handleSensorFunctions, HashMap<String,Integer> sensor,ModifyXMLFile xml,String sensor_name){


        for(Map.Entry<String,Integer> entry: sensor.entrySet()) {

            String key = entry.getKey();
            int value = entry.getValue();

            for (int i = 1; i <= value; i++) {
                String assign_pin = this.getAvaliblePin(key);
                String overide_pin_name=handleSensorFunctions.getNewPinName(sensor_id,key+i);
                setPinMode(xml, sensor_name, overide_pin_name, assign_pin, "INPUT");
            }
        }
    }

    public String getAvaliblePin(String pintype){

        ArrayList<String> avalible_pins_list=this.getAvalibale_pins().get(pintype);
        if(avalible_pins_list.size()==0) return "No Pins Avalible";
        else {
            String pin_number=avalible_pins_list.get(0);
            avalible_pins_list.remove(0);
            avalibale_pins.put(pintype,avalible_pins_list);
            return pin_number;
        }
    }

    public void setPinMode(ModifyXMLFile xml,String sensor_name,String tag, String pin_no, String mode){
        xml.addElement("setup_code",sensor_name+"_pin_mode","pinMode("+pin_no+","+mode+");\n\t");
        xml.addElement("define_macro",sensor_name+"_define_pin","#define "+tag+ " "+pin_no+"\n\t");
        xml.toString("define_macro");
        xml.toString("setup_code");
    }
}
