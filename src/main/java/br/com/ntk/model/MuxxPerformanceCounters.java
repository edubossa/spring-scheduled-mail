package br.com.ntk.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class MuxxPerformanceCounters implements Serializable {
	private static final long serialVersionUID = -1728914775870857010L;

	@XmlElement(name = "Contadores")
	private Contadores contadores;
	
	@XmlElement(name = "MensagemInformativa")
	private String mensagemInformativa;
	
	@XmlElement(name = "Rede")
	private String rede;

	public Contadores getContadores() {
		return contadores;
	}

	public void setContadores(Contadores contadores) {
		this.contadores = contadores;
	}
	
	public String getMensagemInformativa() {
		return mensagemInformativa;
	}

	public void setMensagemInformativa(String mensagemInformativa) {
		this.mensagemInformativa = mensagemInformativa;
	}

	public String getRede() {
		return rede;
	}

	public void setRede(String rede) {
		this.rede = rede;
	}

	@Override
	public String toString() {
		return "MuxxPerformanceCounters [contadores=" + contadores
				+ ", mensagemInformativa=" + mensagemInformativa + ", rede="
				+ rede + "]";
	}

}