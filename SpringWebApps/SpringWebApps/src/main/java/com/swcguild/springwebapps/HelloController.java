package com.swcguild.springwebapps;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller  //this declares it as a controller
@RequestMapping({"/hello"})  //route at controller level is optional
//the route on the individual methods is not optional, they must have routes to run
public class HelloController {
        
    public HelloController() {
    }
    
    @RequestMapping(value="/sayhi", method=RequestMethod.GET)
    public String sayHi(Map<String, Object> model) {
        model.put("message", "Hello from the controller" );
        return "hello";
    }
}
