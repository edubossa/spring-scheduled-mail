package br.com.ntk.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Classe Utilitarea para parsear os Objetos em XML e vice-versa
 * 
 * @author Eduardo Wallace
 * @author NTK - Soluctions
 * @since 06/11/2013
 */
public class XmlParser implements Parser {
	
	private static XmlParser parser;
	
	@SuppressWarnings("rawtypes")
	private static Map<Class, JAXBContext> map = new ConcurrentHashMap<Class, JAXBContext>();
	
	public static XmlParser getInstance() {
		if (parser == null) {
			parser = new XmlParser();
		}
		return parser;
	}
	
	private static <T> JAXBContext getContext(Class<T> c) throws JAXBException {
		
		if (!map.containsKey(c)) {
			System.out.println("-"+c.getSimpleName());
			map.put(c, JAXBContext.newInstance(c));
		}
		
		return (JAXBContext) map.get(c);
	}
	
	public <T> Object unmarshal(Class<T> c, String xml) throws JAXBException {
		Object obj = null;
		try {
			
			obj = getContext(c).createUnmarshaller().unmarshal(new StringReader(xml));
			
		} catch (JAXBException e) {
			throw new JAXBException("Erro ao executar o parse do XML pro Objeto");
		}
		
		return obj;
	}
	
	public <T> String marshal(Class<T> c, Object jaxbElement) throws JAXBException, UnsupportedEncodingException {
			
			String xml = null;
		try {
			StringWriter writer = new StringWriter();
			JAXBContext context = JAXBContext.newInstance(c);
			Marshaller marshaller = context.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(jaxbElement, writer); 
			
			xml = URLDecoder.decode(ENCODING.concat(writer.toString()), CHARSET); 
			
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new JAXBException("Erro ao executar o parse do Objeto pro XML.");
			
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new UnsupportedEncodingException("Erro ao interpretar xml com encoding inválido ou não suportado.");
		}
		
		return xml;
	}
	
}