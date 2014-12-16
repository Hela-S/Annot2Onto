/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.text2onto.corpus;
import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
 

public class CorpusFactory { 
    static protected int nbFileXml;
      static  protected int nbFilePdf ;

	

	public static Corpus newCorpus( String sURI ) throws CorpusException {
		URI uri = null;
		try {
			uri =  new URI( sURI );
		} 
		catch( URISyntaxException e ){
			throw new CorpusException( "invalid URI: "+ sURI, e );
		}  
		return newCorpus( uri );
        }
        public static Corpus newCorpus(){
		return new Corpus(); 
	}
	
	private static  AbstractDocument getDocuments( URI dirURI ) throws CorpusException {
             nbFilePdf = 0;
             
		  AbstractDocument doc = null;
		File dir = new File( dirURI );
		if( dir.exists() && dir.isDirectory() )
		{
                    File files[] = dir.listFiles();
                    nbFileXml =0;
                    nbFilePdf = 0;
                    int i = 0;
                    Boolean stop = false;
                    while (i<files.length && !stop)
			{
			 URI uri = files[i].toURI();
                         String suri = uri.toString();
                         if(((suri.endsWith(".xml")) || suri.endsWith(".pdf"))){
                            if(nbFileXml == 0 && nbFilePdf==0){
					 doc = DocumentFactory.newDocument( uri );
                                         if(doc != null){
					if(suri.endsWith(".xml"))
                                            nbFileXml ++;
                                        else
                                            nbFilePdf ++;
                                        i++;
                                        }
                            }
                            else if(nbFileXml == 1 && nbFilePdf==0){
                                if(suri.endsWith(".pdf")){
                                     doc = DocumentFactory.newDocument( uri );
                                      if(doc != null){
                                           nbFilePdf ++;
                                           stop = true;
                                      }  
                                }
                                else i++;
                            }
                             else if(nbFileXml == 0 && nbFilePdf==1){
                                if(suri.endsWith(".xml")){
                                     doc = DocumentFactory.newDocument( dirURI );
                                      if(doc != null){
                                           nbFileXml ++;
                                           stop = true;
                                      }  
                                }
                                else i++;
                            }
				
			}
                         else i++;
		}  
		
	}
                return doc;
        }
        public static Corpus newCorpus( URI dirURI ) throws CorpusException {
		Corpus corpus = new Corpus();  
		AbstractDocument docs = getDocuments( dirURI );
                if(nbFileXml == 1 && nbFilePdf==1)
			corpus.addDocument( docs );
		
		return corpus;
	}
}

