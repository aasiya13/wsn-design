package com.fyp.wsn.Entity;

import javax.persistence.*;

@Entity
public class MicrocontrollerType {
	
	private String microcontroller_type;
	private String cpp_includes;
	private String cpp_global;
	private String cpp_function;
	private String cpp_initialize;

	public MicrocontrollerType(String microcontroller_type, String cpp_includes, String cpp_global, String cpp_function,
			String cpp_initialize) {
		super();
		this.microcontroller_type = microcontroller_type;
		this.cpp_includes = cpp_includes;
		this.cpp_global = cpp_global;
		this.cpp_function = cpp_function;
		this.cpp_initialize = cpp_initialize;
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
