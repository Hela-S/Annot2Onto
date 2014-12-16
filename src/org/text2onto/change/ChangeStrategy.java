/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.text2onto.change;
import java.io.Serializable;
import java.util.List;

/**
 * @author Johanna Voelker (jvo@aifb.uni-karlsruhe.de)
 */
public interface ChangeStrategy extends Serializable {

	public List processChanges( List changes );

	public Object clone();
}
