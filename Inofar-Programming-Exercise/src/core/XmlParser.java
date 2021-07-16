package core;

import java.io.BufferedReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.commons.lang3.StringEscapeUtils;

@SuppressWarnings("deprecation")
public class XmlParser extends AParser{

	
	public XmlParser(IFindAndReplace pIFindAndReplace, IPrinter pPrinter) {
		super(pIFindAndReplace, pPrinter);
		// TODO Auto-generated constructor stub
	}

	// Regarding Memory Complexity : this version is much more efficient than using XmlWriter
	public void parse(BufferedReader pReader, String pTargetString, String pReplacementString) throws Exception {
		
		XMLInputFactory lXmlInputFactory = XMLInputFactory.newInstance();
		XMLEventReader lXmlReader = lXmlInputFactory.createXMLEventReader(pReader);
		
	    final StringBuilder tEventString = new StringBuilder();
		
	    while (lXmlReader.hasNext()) {
			
		    XMLEvent tNextEvent = lXmlReader.nextEvent();

	        tEventString.setLength(0);
		    tEventString.append(tNextEvent.toString());
		    
		    if (tNextEvent.isStartElement() && tNextEvent.toString().contains(pTargetString)) {
		        StartElement startElement = tNextEvent.asStartElement();
		        
		        tEventString.setLength(0);
		        tEventString.append("<");
		        tEventString.append(startElement.getName()+" ");
		        
		        startElement.getAttributes().forEachRemaining(attr->{
		        	
		        	tEventString.append(attr.getName()+"= ");
		        	String tValue = this.cIFindAndReplace.findAndReplace(attr.getValue(), pTargetString, pReplacementString);
		        	
		        	tEventString.append("\""+StringEscapeUtils.escapeXml(tValue)+"\"");
		        });
		        
		        tEventString.append(">");
		    }
		    
		    this.cPrinter.print(tEventString.toString());
		}
	}
}
