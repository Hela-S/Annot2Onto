/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.text2onto.gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.util.List; 
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

import org.text2onto.change.Change;

public class DocumentDialog extends javax.swing.JDialog {

  private JButton m_buttonClose; 
	
	private JTextArea m_textArea;
        
    public DocumentDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
  public DocumentDialog(java.awt.Frame owner, String sFile) {
        super(owner, sFile, true);
        
        initComponents();
        setSize( 700, 600 );
		Dimension dim = getToolkit().getScreenSize();
		setLocation( (int)( ( dim.getWidth() - this.getWidth() ) / 2 ), 
						(int)( ( dim.getHeight() - this.getHeight() ) / 2 ) );
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout( new BorderLayout() );
		textPanel.setBorder( new EmptyBorder( 10, 10, 0, 10 ) ); 
		String sText = null;
		try {
			sText = getText( sFile );
		}
		catch( Exception e ){
			sText = e.toString();
		}
		m_textArea = new JTextArea( sText );
		m_textArea.setEditable( false ); 
		m_textArea.setLineWrap( true );
		m_textArea.setWrapStyleWord( false ); 
		textPanel.add( BorderLayout.CENTER, new JScrollPane( m_textArea ) );
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
		buttonPanel.setLayout( new FlowLayout( FlowLayout.CENTER ) );
		 				
		m_buttonClose = new JButton( "Ok" );
		m_buttonClose.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent e ){ 
				dispose();
			}
		}); 
		buttonPanel.add( m_buttonClose );
		
		Container cp = getContentPane();
		cp.setLayout( new BorderLayout() );
		cp.add( BorderLayout.CENTER, textPanel );
		cp.add( BorderLayout.SOUTH, buttonPanel );
    }

   private String getText( String sFile ) throws Exception {
		StringBuffer sb = new StringBuffer();
		File file = new File( sFile );
		BufferedReader reader = new BufferedReader( new FileReader( file ) );
		String sLine = null;
		while( ( sLine = reader.readLine() ) != null ){
			sb.append( sLine +"\n" );
		}
		return sb.toString();
	}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DocumentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DocumentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DocumentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DocumentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DocumentDialog dialog = new DocumentDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
