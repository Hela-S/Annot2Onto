/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.text2onto.gui;

/**
 *
 * @author hela
 */
public interface CorpusListener {

	public final static int ADD = 0;
	
	public final static int REMOVE = 1;
	

	public void corpusChanged( int iMessage, String sFile );

}