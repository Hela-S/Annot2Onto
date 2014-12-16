/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.text2onto.util;

/**
 *
 * @author hela
 */
import java.io.Serializable;


public class MyInteger implements Serializable {

	private int m_iValue = 0;


	public MyInteger( int iValue ){
		m_iValue = iValue;
	}

	public void increase(){
		m_iValue++;
	}

	public void decrease(){
		m_iValue--;
	}

	public void setValue( int iValue ){
		m_iValue = iValue;
	}

	public int getValue(){
		return m_iValue;
	}

	public String toString(){
		return String.valueOf( m_iValue );
	}
}
