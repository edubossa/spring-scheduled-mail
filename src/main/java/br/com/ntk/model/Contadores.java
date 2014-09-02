package br.com.ntk.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Contadores implements Serializable {
	private static final long serialVersionUID = 5659031212935246094L;

	@XmlElement(name = "TransacoesFalhaUltimaHora")
	private int transacoesFalhaUltimaHora;

	@XmlElement(name = "TransacoesFalhaUltimoMinuto")
	private int transacoesFalhaUltimoMinuto;
	
	@XmlElement(name = "TransacoesUltimaHora")
	private int transacoesUltimaHora;

	@XmlElement(name = "TransacoesUltimoMinuto")
	private int transacoesUltimoMinuto;

	public int getTransacoesFalhaUltimaHora() {
		return transacoesFalhaUltimaHora;
	}

	public void setTransacoesFalhaUltimaHora(int transacoesFalhaUltimaHora) {
		this.transacoesFalhaUltimaHora = transacoesFalhaUltimaHora;
	}

	public int getTransacoesFalhaUltimoMinuto() {
		return transacoesFalhaUltimoMinuto;
	}

	public void setTransacoesFalhaUltimoMinuto(
			int transacoesFalhaUltimoMinuto) {
		this.transacoesFalhaUltimoMinuto = transacoesFalhaUltimoMinuto;
	}

	public int getTransacoesUltimaHora() {
		return transacoesUltimaHora;
	}

	public void setTransacoesUltimaHora(int transacoesUltimaHora) {
		this.transacoesUltimaHora = transacoesUltimaHora;
	}

	public int getTransacoesUltimoMinuto() {
		return transacoesUltimoMinuto;
	}

	public void setTransacoesUltimoMinuto(int transacoesUltimoMinuto) {
		this.transacoesUltimoMinuto = transacoesUltimoMinuto;
	}

	@Override
	public String toString() {
		return "Contadores [transacoesFalhaUltimaHora="
				+ transacoesFalhaUltimaHora + ", transacoesFalhaUltimoMinuto="
				+ transacoesFalhaUltimoMinuto + ", transacoesUltimaHora="
				+ transacoesUltimaHora + ", transacoesUltimoMinuto="
				+ transacoesUltimoMinuto + "]";
	}

}