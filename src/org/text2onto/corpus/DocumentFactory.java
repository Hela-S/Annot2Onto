/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.text2onto.corpus;

import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;
public final class DocumentFactory {

	public static AbstractDocument newDocument( URI uri ) throws CorpusException {
		String sURI = uri.toString();
		if( sURI.endsWith( ".xml")){
			return newDocumentXml( DocumentPdfXml.class, uri );       
		} 
                else if( sURI.endsWith( ".pdf")){
			return newDocumentPdf( DocumentPdfXml.class, uri );
                }
                return null;
                
	}
 
	public static AbstractDocument newDocumentXml( Class c, URI uri ) throws CorpusException {
		AbstractDocument doc = null;
		try { 
			doc = (AbstractDocument)c.newInstance();
		} 
		catch ( Exception e ){
			throw new CorpusException( "class not found: " + c );
		}
		doc.setURIXml( uri );
		return doc;
	}
        	public static AbstractDocument newDocumentPdf( Class c, URI uri ) throws CorpusException {
		AbstractDocument doc = null;
		try { 
			doc = (AbstractDocument)c.newInstance();
		} 
		catch ( Exception e ){
			throw new CorpusException( "class not found: " + c );
		}
		doc.setURIPdf( uri );
		return doc;
	}
 
	
}
