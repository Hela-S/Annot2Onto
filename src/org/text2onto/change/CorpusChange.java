/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.text2onto.change;
import java.util.List;
import java.util.ArrayList;

import org.text2onto.corpus.AbstractDocument;

/**
 * @author Johanna Voelker (jvo@aifb.uni-karlsruhe.de)
 */
public class CorpusChange extends Change { 
  
	public CorpusChange( int iType, Object source, AbstractDocument document ){
		m_iType = iType;
		m_source = source;
		m_object = document; 
	}
    
	public AbstractDocument getDocument(){
		return (AbstractDocument)m_object;
	}
	  
	public String toString(){
		return "CorpusChange( type="+ Type.toString( m_iType ) +", document="+ m_object +" )"; 
	}
}