package com.fyp.wsn.Controller;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

@RestController

/**
 * Created by Sithara on 8/27/2018.
 */

// This is how you map your end points to entire class
@RequestMapping("/Firmware")
@CrossOrigin()
//This annotation for auto generate API documentation
@Api(
        name = "Data download API",
        description = "The data file is downloaded"
)

public class FirmwareController {
	
	//private static final String INTERNAL_FILE = "D:/Computer Engineering/FYP/wsn-design-studio-master/src/main/resources/DB/rftx.ino";
	private static final String INTERNAL_FILE = "D:/Computer Engineering/FYP/wsn-design-studio-master/src/main/java/com/fyp/wsn/Controller/abc.txt";
	@RequestMapping(method = RequestMethod.GET)
    @ApiMethod(description = "Get specific sensor details by Id ")
	
	public void downloadFile(HttpServletResponse response) throws IOException{
		File file = null; 
//            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//            file = new File(classloader.getResource(INTERNAL_FILE).getFile());
		
            file = new File(INTERNAL_FILE);
            if(!file.exists()){
                String errorMessage = "Sorry. The file you are looking for does not exist";
                System.out.println(errorMessage);
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
                outputStream.close();
                return;
            }
            
            String mimeType= URLConnection.guessContentTypeFromName(file.getName());
            if(mimeType==null){
                System.out.println("mimetype is not detectable, will take default");
                mimeType = "application/octet-stream";
            }
             
            System.out.println("mimetype : "+mimeType);
             
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
            
            response.setContentLength((int)file.length());
            
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
   //     return "Hello All Good";
    }
}
