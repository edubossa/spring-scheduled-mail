package br.com.ntk.util;

import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBException;

/**
 * Definicao do parse xml, utilizado pela <b>API: JAXB</b>
 *  
 * @author Eduardo Wallace
 * @author NTK - Soluctions
 * @since 06/11/2013
 */
public interface Parser {
	
	/** CHARSET **/
	public static final String CHARSET = "UTF-8";
	
	/** ENCODING **/
	public static final String ENCODING = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
	
	/**
	 * Executa o parse do XML pro Objeto
	 * 
	 * @param c Object.class 
	 * @param xml
	 * @return Objeto parse 
	 */
	public  <T> Object unmarshal(Class<T> c, String xml) throws JAXBException;
	
	/**
	 * Executa o parse do Objeto pro XML 
	 * 
	 * @param c Object.class
	 * @param obj
	 * @return xml
	 */
	public <T> String marshal(Class<T> c, Object jaxbElement) throws JAXBException, UnsupportedEncodingException;

}