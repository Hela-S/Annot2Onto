package org.text2onto.corpus;
import java.io.Serializable;
import java.net.URI;
import org.w3c.dom.Document;

public abstract class AbstractDocument implements Serializable {

	protected URI xml_uri;
        protected URI pdf_uri;
        
        protected String[]columnNames={"XML","PDF"}; 
    protected Object[][] m_sContent = new Object[1][2]; 



	protected void setURIXml( URI Xmluri ) {
		xml_uri = Xmluri;
	}
        protected void setURIPdf( URI Pdfuri ) {
		pdf_uri = Pdfuri;
	}

	public URI getURIXml() {
		return xml_uri;
	}
        public URI getURIPdf() {
		return pdf_uri;
	}

	public String toStringXml() {
		return xml_uri.toString();
	}
        public String toStringPdf() {
		return pdf_uri.toString();
	}
	 
	public int hashCodeXml() {
		return xml_uri.toString().hashCode();
	}
        public int hashCodePdf() {
		return pdf_uri.toString().hashCode();
	}
	public void setPdfContent( String sContent ) {
                m_sContent[1][2] = sContent;
	}
public void setXmlContent( Document sContent ) {
                m_sContent[1][1] = sContent;
	}
	public Document getContentXml() {
	 return (Document)m_sContent[1][1];
	}
        public String getContentPdf() {
		return (String) m_sContent[1][2];
	}
        public Object[][] getContent() {
		return m_sContent;
	}

	public boolean equalsXml( Object object ) {
		if( object == null || !( object instanceof AbstractDocument ) ){
			return false;
		}
		AbstractDocument doc = (AbstractDocument) object;
		if( doc.getURIXml() == null || this.getURIXml() == null )
		{
			if( this.getURIXml() != null ){
                          return false;
			} 
			else if( doc.getContentXml().equals( this.getContentXml() ) ) {
				return true;
			}
			else {
				return false;
			}
		}
		if ( doc.getURIXml().equals( xml_uri ) ){
			return true;
		}
		return false;
	}
        public boolean equalsPdf( Object object ) {
		if( object == null || !( object instanceof AbstractDocument ) ){
			return false;
		}
		AbstractDocument doc = (AbstractDocument) object;
		if( doc.getURIPdf() == null || this.getURIPdf() == null )
		{
			if( this.getURIPdf() != null ){
                          return false;
			} 
			else if( doc.getContentPdf().equals( this.getContentPdf() ) ) {
				return true;
			}
			else {
				return false;
			}
		}
		if ( doc.getURIPdf().equals( pdf_uri ) ){
			return true;
		}
		return false;
	}
}

