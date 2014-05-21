package org.jcaching.serializer;

public class InfoObjetoBoolean implements InfoObjeto {
	
	String value;
	
	public InfoObjetoBoolean(String value) {
		this.value = value; 
	}

	@Override
	public Boolean retornarInstancia() {
		return Boolean.valueOf(value);
	}
	
		

}
