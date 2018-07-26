package com.fyp.wsn.Entity;

import com.fyp.wsn.DataAccess.SensorDAO;
import com.fyp.wsn.DataAccess.SensorNodeDAO;
import org.apache.catalina.LifecycleState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nadith Premaratne on 23/05/2017.
 */
public class HandleSensorFunctions {


    private HashMap<String,ArrayList<Pair>> new_assigned_pins;
    private HashMap<String,HashMap<Integer,String>> new_edited_functions;
    private HashMap<String,Integer> avalible_pin_number;

    public HandleSensorFunctions(List<String> sensor_id,SensorDAO sensorDAO) {

        avalible_pin_number =new HashMap<>();
        new_assigned_pins=new HashMap<>();
        new_edited_functions=new HashMap<>();

        ArrayList<Pair> temp_list;
        HashMap<Integer,String> temp_hashmap;
        String sensor_funcion=null;
        int function_no=1;

        for(String sensor :sensor_id){
            Sensor temp_sensor = sensorDAO.getSensorById(sensor);
            String  configure_list=temp_sensor.getConfiguration();
            String [] configure_type=configure_list.split(",");
            sensor_funcion=temp_sensor.getCpp_function();
            for(String x :configure_type){
               String [] pair=x.split("-");
               String type_of_pin=pair[0];
               if(avalible_pin_number.containsKey(type_of_pin));
               else{
                   avalible_pin_number.put(type_of_pin,1);
               }

               int num_of_pins=Integer.parseInt(pair[1]);
               for(int i=1;i<= num_of_pins;i++){
                   String pre_pin=type_of_pin+i;
                   int pin_no=avalible_pin_number.get(type_of_pin);
                   String new_pin=type_of_pin+"_NEW_"+ pin_no;
                   Pair temp_map=new Pair(pre_pin,new_pin);
                   sensor_funcion=sensor_funcion.replaceAll(pre_pin,new_pin);
                   if(new_assigned_pins.containsKey(sensor)){
                       temp_list=new_assigned_pins.get(sensor);
                       temp_list.add(temp_map);
                   }
                   else{
                       temp_list =new ArrayList<>();
                       temp_list.add(temp_map);
                       new_assigned_pins.put(sensor,temp_list);

                   }
                   pin_no++;
                   avalible_pin_number.put(type_of_pin,pin_no);
               }
            }
            if(new_edited_functions.containsKey(sensor)){
                temp_hashmap=new_edited_functions.get(sensor);
                String [] function_name=sensor_funcion.split("\\(\\)");
                function_name[0]=function_name[0]+function_no;
                String builder="";
                for(String x: function_name){
                    builder+=x+"()";

                }
                builder=builder.substring(0,builder.length()-2);
                temp_hashmap.put(function_no,builder);
                new_edited_functions.put(sensor,temp_hashmap);
                function_no++;

            }
            else {
                temp_hashmap = new HashMap<>();
                temp_hashmap.put(function_no, sensor_funcion);
                new_edited_functions.put(sensor, temp_hashmap);
                function_no++;
            }
        }

    }

    public String getNewPinName(String sensor_id,String pre_name){

        ArrayList<Pair> temp=new_assigned_pins.get(sensor_id);

        for(Pair x :temp){
            if(x.pre.equals(pre_name)) return x.now;


        }

        return null;
    }


    public HashMap<String, ArrayList<Pair>> getNew_assigned_pins() {
        return new_assigned_pins;
    }

    public void setNew_assigned_pins(HashMap<String, ArrayList<Pair>> new_assigned_pins) {
        this.new_assigned_pins = new_assigned_pins;
    }

    public HashMap<String, HashMap<Integer, String>> getNew_edited_functions() {
        return new_edited_functions;
    }

    public void setNew_edited_functions(HashMap<String, HashMap<Integer, String>> new_edited_functions) {
        this.new_edited_functions = new_edited_functions;
    }

    public HashMap<String, Integer> getAvalible_pin_number() {
        return avalible_pin_number;
    }

    public void setAvalible_pin_number(HashMap<String, Integer> avalible_pin_number) {
        this.avalible_pin_number = avalible_pin_number;
    }
}
