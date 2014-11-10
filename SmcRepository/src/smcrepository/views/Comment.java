package smcrepository.views;

import java.io.Serializable;

public class Comment implements Serializable {
	private String data;
	private String autore;
	private String testo;

	// costruttore
	public Comment(String data, String autore, String testo) {
		this.data = data;
		this.autore = autore;
		this.testo = testo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

}
