/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.text2onto.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import org.text2onto.corpus.AbstractDocument;
import org.text2onto.corpus.Corpus;

/**
 *
 * @author hela
 */
public class JPanelCorpusTest extends javax.swing.JPanel {

    	private DynamicTree m_tree;
        private Object m_node;
     private java.awt.Frame m_frame;
    private JPanelCorpusTest m_corpusPanel;
     private ArrayList list;
      public JPanelCorpusTest() {
        initComponents();    
    }
    public JPanelCorpusTest( java.awt.Frame frame ){

         initComponents();
		m_frame = frame;
		m_corpusPanel = this;
              list = new ArrayList();
		m_tree = new DynamicTree( "Corpus", DynamicTree.ICONS_CORPUS, true );
		this.add( BorderLayout.CENTER, m_tree );
                m_tree.setVisible(true);
		m_itemRemove.setLabel("Remove...");
		m_itemRemove.addActionListener( new ActionListener() {
                   
			public void actionPerformed( ActionEvent e ) {
				doRemove();
                           
			}
		} );
		m_itemAdd.setLabel("Add");
		m_itemAdd.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				doAdd();
                                
			}
		} );
		m_itemShow.setLabel("Show...");
		m_itemShow.addActionListener( new ActionListener() {
			public void actionPerformed( java.awt.event.ActionEvent e ) {
				doShow();
                               
			}
		} );
		m_popup.add( m_itemAdd );
		m_popup.add( m_itemRemove );
		m_popup.add( m_itemShow );
		m_tree.addMouseListener( new PopupListener() );
                this.setVisible(true);
	}

    public void addListener( CorpusListener listener ) {
                list.add(listener);
                DefaultListModel model = new DefaultListModel();
        m_alListeners = new JList(model);
        model.clear();
        for (int i =0; i<list.size(); i++) {
            CorpusListener item = (CorpusListener)list.get(i);
        model.addElement(item);
                }
		
	}

	public void reset() {
		m_tree.clear();
	}

	public void update( Corpus m_corpus ) {
		java.util.List docs = m_corpus.getDocuments();
		for( int i = 0; i < docs.size(); i++ ) {
			AbstractDocument doc = (AbstractDocument)docs.get( i );
			m_tree.addObject( m_tree.getRoot(), doc.getURIXml().toString() );
                        m_tree.addObject( m_tree.getRoot(), doc.getURIPdf().toString() );
		}

                           
	}

	private void doAdd() {
		chooser.setVisible(true);
		chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
		chooser.setMultiSelectionEnabled( true );
		FileFilter filter = new FileFilter() {
			public boolean accept( File file ) {
				return ( file.isDirectory() || file.toString().endsWith( ".xml" ) || file.toString().endsWith( ".pdf" ) );
			}

			public String getDescription() {
				return "Input Files (*.xml, *.pdf)";
			}
		};
		chooser.setFileFilter( filter );
		int iReturn = chooser.showOpenDialog( m_frame );
		if( iReturn == JFileChooser.APPROVE_OPTION ) {
			File[] files = chooser.getSelectedFiles();
			for( int i = 0; i < files.length; i++ ) {
				String sFile = files[i].toString();
				m_tree.addObject( m_tree.getRoot(), sFile );
				notifyListeners( CorpusListener.ADD, sFile );
			}
		}
	}

	private void doRemove() {
		if( m_node == null || m_node.equals( m_tree.getRoot() ) ) {
			return;
		}
		String sFile = (String)m_tree.getSelectedObject();
		m_tree.removeCurrentNode();
		notifyListeners( CorpusListener.REMOVE, sFile );
	}

	private void doShow() {
		if( m_node == null || m_node.equals( m_tree.getRoot() ) ) {
			return;
		}
		String sFile = (String)m_tree.getSelectedObject();
		DocumentDialog dd = new DocumentDialog( m_frame, sFile );
		dd.show();
	}

	private class PopupListener extends MouseAdapter {
		public void mousePressed( MouseEvent e ) {
			maybeShowPopup( e );
		}

		public void mouseReleased( MouseEvent e ) {
			maybeShowPopup( e );
		}

		private void maybeShowPopup( MouseEvent e ) {
			if( e.isPopupTrigger() ) {
				m_node = m_tree.getSelectedNode();
				if( m_node != null ) {
					boolean bRoot = m_node.equals( m_tree.getRoot() );
					m_itemAdd.setEnabled( bRoot );
					m_itemRemove.setEnabled( !bRoot );
					m_itemShow.setEnabled( !bRoot );
					m_popup.show( e.getComponent(), e.getX(), e.getY() );
				}
			}
		}
	}

	private void notifyListeners( int iMessage, String sFile ) {
		Iterator iter = (Iterator)m_alListeners.getCursor();
		while( iter.hasNext() ) {
			( (CorpusListener)iter.next() ).corpusChanged( iMessage, sFile );
		}
	}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        m_itemAdd = new javax.swing.JMenuItem();
        m_itemRemove = new javax.swing.JMenuItem();
        m_itemShow = new javax.swing.JMenuItem();
        m_popup = new javax.swing.JPopupMenu();
        chooser = new javax.swing.JFileChooser();
        paner = new javax.swing.JScrollPane();
        m_alListeners = new javax.swing.JList();

        m_itemAdd.setText("jMenuItem1");

        m_itemRemove.setText("jMenuItem1");

        m_itemShow.setText("jMenuItem1");

        paner.setViewportView(m_alListeners);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(paner, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(315, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(paner, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser chooser;
    private javax.swing.JList m_alListeners;
    private javax.swing.JMenuItem m_itemAdd;
    private javax.swing.JMenuItem m_itemRemove;
    private javax.swing.JMenuItem m_itemShow;
    private javax.swing.JPopupMenu m_popup;
    private javax.swing.JScrollPane paner;
    // End of variables declaration//GEN-END:variables
}
