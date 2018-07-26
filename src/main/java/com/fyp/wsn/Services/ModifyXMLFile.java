package com.fyp.wsn.Services;

/**
 * Created by Nadith Premaratne on 08/05/2017.
 */
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.fyp.wsn.DataAccess.AllFunctionsDAO;
import com.fyp.wsn.DataAccess.ClientSessionDAO;
import com.fyp.wsn.Entity.AllFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.util.logging.Logger;

public class ModifyXMLFile {

    private Document doc=null;
    String inputfile=null;
    String outputfile=null;
    private Logger LOGGER;

    public ModifyXMLFile(String inputfile,String outputfile) {

        this.inputfile=inputfile;
        this.outputfile=outputfile;
        
        this.LOGGER = Logger.getLogger(this.getClass().getName());
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public  void addElement(String code_tag_part, String tag_name, String value) {
        Node code_part = doc.getElementsByTagName(code_tag_part).item(0);
       // tag_name = "Temperature";
        // append a new node to code part
  //      LOGGER.info("tag_name : "+tag_name);
        Element new_element = doc.createElement(tag_name);
        new_element.appendChild(doc.createTextNode(value));
        code_part.appendChild(new_element);


    }

    public String getText(){

        NodeList list = doc.getChildNodes();
        String code="";
        for (int i = 0; i < list.getLength(); i++) {

            Node node = list.item(i);

            code+=node.getTextContent();
            code+="\n";
        }

        return code;
    }
    public  void deleteElement(String code_tag_part,String tag_name) {

        Node code_part = doc.getElementsByTagName(code_tag_part).item(0);

        NodeList list = code_part.getChildNodes();

        for (int i = 0; i < list.getLength(); i++) {

            Node node = list.item(i);

            if (tag_name.equals(node.getNodeName())) {
                code_part.removeChild(node);
            }

        }


    }

    public org.w3c.dom.Document StringToXML(String xml) throws Exception
    {
        DocumentBuilderFactory fctr = DocumentBuilderFactory.newInstance();
        DocumentBuilder bldr = fctr.newDocumentBuilder();
        InputSource insrc = new InputSource(new StringReader(xml));
        return bldr.parse(insrc);
    }

    public String XMLToString(Document doc){

        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            return writer.getBuffer().toString();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        catch (TransformerException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateElementValue(String code_tag_part,String tag_name,String value) {
        Node code_part = doc.getElementsByTagName(code_tag_part).item(0);
        NodeList list = code_part.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (tag_name.equals(node.getNodeName())) {
                node.setTextContent(value);
            }
        }

    }

    public String toString(String code_tag_part){

        Node staff = doc.getElementsByTagName(code_tag_part).item(0);
        return   staff.getTextContent();

    }

    public void getReady(){

        try{
        String filepath = inputfile;//"d:\\testcode.xml";
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        this.doc = docBuilder.parse(filepath);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        }
    }


    public void finalize_code(){

        try{
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(this.doc);
            StreamResult result = new StreamResult(new File(this.outputfile));
            transformer.transform(source, result);

        } catch (TransformerException tfe){
            tfe.printStackTrace();
        }


    }




    public static void   main(String argv[]) {

        String inputfile="d:\\testcode.xml";
        String outputfile="d:\\testcode.xml";
        ModifyXMLFile obj=new ModifyXMLFile(inputfile,outputfile);

        obj.getReady();


    }
}