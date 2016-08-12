package Phase2;

import java.util.ArrayList;

import java.util.List;



public class Regle {

	
	private List<String> m_left;
	private List<String> m_right;
	private Double m_conf;
	private Double m_freq;
	
	public Regle(List<String> left, List<String> right, double conf, double freq) {
		m_left = left;
		m_right = right;
		// on arrondi les valeurs de conf et de freq
		m_conf =  (double) (Math.round(conf * 100) / 100);
		m_freq = (double) Math.round(conf * 100) / 100;
	}
	
	
	public String toString() {
		// on converti la règle d'asso  en string
		return ExtractAsso.SepareElements(m_left, ";") + " -> " +
			   ExtractAsso.SepareElements(m_right, ";") + " : " + m_conf + ", " + m_freq;
	}
	
	
	// Getter
	public List<String> getLeft() {
		return m_left;
	}
	
	public List<String> getRight() {
		return m_right;
	}
	
	
	public Double getConf() {
		return m_conf;
	}
	
	public Double getFreq() {
		return m_freq;
	}

	

}
