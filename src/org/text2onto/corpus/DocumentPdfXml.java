package org.text2onto.corpus;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.io.File;
import java.io.InputStreamReader;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class DocumentPdfXml extends AbstractDocument {

    protected void setURI( URI XmlUri ) throws ParserConfigurationException, SAXException {
  super.setURIXml( XmlUri );
              
		try {  
         File fXmlFile = new File(XmlUri);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder  dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
         doc.getDocumentElement().normalize();
         super.setXmlContent(doc);
         
		} catch ( IOException e ) {
			e.printStackTrace();
		}

	}

  @Override
protected void setURIPdf( URI PdfUri ) {
		super.setURIPdf( PdfUri );

		Runtime r = Runtime.getRuntime();

		try {
			Process p = r.exec( "3rdparty/pdftotext.exe \""
					+ ( new File( PdfUri ) ).toString() + "\" -" );
			BufferedReader in = new BufferedReader( new InputStreamReader( p.getInputStream() ) );
			String input, content = "";
			while ( ( input = in.readLine() ) != null ) {
				content += input + "\n";
			}
			super.setPdfContent( content );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
}
