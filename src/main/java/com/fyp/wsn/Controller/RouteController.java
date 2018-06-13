package com.fyp.wsn.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Asela on 5/3/2017.
 */

@RestController
public class RouteController {

    @RequestMapping("/create/network")
    public String CreateNetwork(){
        return "createNetwork";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }
    
    @RequestMapping("/create/sensor")
    public String addSensor(){
        return "addSensor";
    }

    @RequestMapping("/create/microcontroller")
    public String addMicrocontroller(){
        return "addMicrocontroller";
    }

    @RequestMapping(value="/code/{nodes}" ,method = RequestMethod.GET)
    public String code(@PathVariable("nodes") String[] nodes, Model model){
        model.addAttribute(nodes);
        return "code";
    }

}
