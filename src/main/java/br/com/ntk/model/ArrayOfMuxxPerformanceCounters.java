package br.com.ntk.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ArrayOfMuxxPerformanceCounters") //namespace ="http://schemas.datacontract.org/2004/07/Muxx.Application.MainContext.Monitoring.ValueObjects"
@XmlAccessorType(XmlAccessType.FIELD)
public class ArrayOfMuxxPerformanceCounters implements Serializable{
	private static final long serialVersionUID = -7533778491284086858L;
	
	@XmlElement(name = "MuxxPerformanceCounters")
	private List<MuxxPerformanceCounters> muxxPerformanceCounters;

	public List<MuxxPerformanceCounters> getMuxxPerformanceCounters() {
		return muxxPerformanceCounters;
	}

	public void setMuxxPerformanceCounters(
			List<MuxxPerformanceCounters> muxxPerformanceCounters) {
		this.muxxPerformanceCounters = muxxPerformanceCounters;
	}

}