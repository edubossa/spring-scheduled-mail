package br.com.ntk.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import br.com.ntk.model.ArrayOfMuxxPerformanceCounters;
import br.com.ntk.model.MuxxPerformanceCounters;
import br.com.ntk.util.HttpType;
import br.com.ntk.util.HttpUtil;
import br.com.ntk.util.Parser;
import br.com.ntk.util.XmlParser;

public class ParseXMLTest {
	
	Parser parser;
	
	@Before
	public void init() {
		parser = XmlParser.getInstance();
	}
	
	
	@Test
	public void testParseXML() {
		
		try {
			
			String xmlRetorno = HttpUtil.execute(HttpType.GET, "http://localhost:8080/ntk-controle-transacoes-client/controle", 20);
			
			ArrayOfMuxxPerformanceCounters counters = (ArrayOfMuxxPerformanceCounters) 
					parser.unmarshal(ArrayOfMuxxPerformanceCounters.class, xmlRetorno);
			
			System.out.println("QTDE: " + counters.getMuxxPerformanceCounters().size());
			for(MuxxPerformanceCounters mpc : counters.getMuxxPerformanceCounters()) {
				System.out.println(mpc);
			}
			
			assertNotNull(counters);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
