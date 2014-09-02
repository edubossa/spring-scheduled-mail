package br.com.ntk.scheduling;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.ntk.model.ArrayOfMuxxPerformanceCounters;
import br.com.ntk.model.MuxxPerformanceCounters;
import br.com.ntk.util.HttpType;
import br.com.ntk.util.HttpUtil;
import br.com.ntk.util.Parser;
import br.com.ntk.util.XmlParser;

@Service
public class MonitorTransacaoTask {
	
	private Parser parser = XmlParser.getInstance();
	private static final String URL = "http://localhost:8080/ntk-controle-transacoes-client/controle";
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private SimpleMailMessage simpleMailMessage;
	
	
	private Logger log = Logger.getLogger(MonitorTransacaoTask.class.getCanonicalName());
	
	@Scheduled(fixedDelay = 1000*60)
	public void monitor() {
		
		log.info("Iniciando Monitoracao Pay&Go-WEB");
		try {
			String xmlRetorno = HttpUtil.execute(HttpType.GET, URL, 60);
			ArrayOfMuxxPerformanceCounters counters = (ArrayOfMuxxPerformanceCounters) 
					parser.unmarshal(ArrayOfMuxxPerformanceCounters.class, xmlRetorno);
			
			for(MuxxPerformanceCounters mpc : counters.getMuxxPerformanceCounters()) {
				System.out.println(mpc);
			}
			
			MuxxPerformanceCounters mpc  =counters.getMuxxPerformanceCounters().get(0);
			sendEmail(mpc.toString()); 
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage()); 
			e.printStackTrace();
		}
				
		
	}
	
	/**
	 * Envia email
	 * 
	 * @param msg - mensagem a ser enviada por email
	 */
	private void sendEmail(String msg) {
		SimpleMailMessage simpleMessage = new SimpleMailMessage(simpleMailMessage);
		simpleMessage.setText(msg);
		mailSender.send(simpleMessage);
	}
	

}
