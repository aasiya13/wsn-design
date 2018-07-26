package com.fyp.wsn.Entity;

import javax.persistence.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by Asela on 5/3/2017.
 * This class define data entity (data structure) that connect with json objects
 */
@Entity 
//corresponding document in database
@Document(collection = "sensors")
public class Sensor {

    //Id of record
    @Id
    private String id;
    private String display_name;
    private String model_name;
    private String time_required_to_one_sensor_read;
    private String standby_current;
    private String active_current;
    private String voltage;
    private String description;
    private String configuration;
    private String pin_map;
    private String color;
    private String microcontroller_type;
    private String cpp_includes;
    private String cpp_global;
    private String cpp_function;
    private String cpp_initialize;


    public Sensor() {
    }

	public Sensor(String id, String display_name, String model_name, String time_required_to_one_sensor_read,
			String standby_current, String active_current, String voltage, String description, String configuration,
			String pin_map, String color, String microcontroller_type, String cpp_includes, String cpp_global,
			String cpp_function, String cpp_initialize) {
		super();
		this.id = id;
		this.display_name = display_name;
		this.model_name = model_name;
		this.time_required_to_one_sensor_read = time_required_to_one_sensor_read;
		this.standby_current = standby_current;
		this.active_current = active_current;
		this.voltage = voltage;
		this.description = description;
		this.configuration = configuration;
		this.pin_map = pin_map;
		this.color = color;
		this.microcontroller_type = microcontroller_type;
		this.cpp_includes = cpp_includes;
		this.cpp_global = cpp_global;
		this.cpp_function = cpp_function;
		this.cpp_initialize = cpp_initialize;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getTime_required_to_one_sensor_read() {
        return time_required_to_one_sensor_read;
    }

    public void setTime_required_to_one_sensor_read(String time_required_to_one_sensor_read) {
        this.time_required_to_one_sensor_read = time_required_to_one_sensor_read;
    }

    public String getStandby_current() {
        return standby_current;
    }

    public void setStandby_current(String standby_current) {
        this.standby_current = standby_current;
    }

    public String getActive_current() {
        return active_current;
    }

    public void setActive_current(String active_current) {
        this.active_current = active_current;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getDescription() {
        return description;
    }

	public void setDescription(String description) {
        this.description = description;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getPin_map() {
        return pin_map;
    }

    public void setPin_map(String pin_map) {
        this.pin_map = pin_map;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public String getMicrocontroller_type() {
 		return microcontroller_type;
 	}

 	public void setMicrocontroller_type(String microcontroller_type) {
 		this.microcontroller_type = microcontroller_type;
 	}

    public String getCpp_includes() {
        return cpp_includes;
    }

    public void setCpp_includes(String cpp_includes) {
        this.cpp_includes = cpp_includes;
    }

    public String getCpp_global() {
        return cpp_global;
    }

    public void setCpp_global(String cpp_global) {
        this.cpp_global = cpp_global;
    }

    public String getCpp_function() {
        return cpp_function;
    }

    public void setCpp_function(String cpp_function) {
        this.cpp_function = cpp_function;
    }

    public String getCpp_initialize() {
        return cpp_initialize;
    }

    public void setCpp_initialize(String cpp_initialize) {
        this.cpp_initialize = cpp_initialize;
    }
    
}
