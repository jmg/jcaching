package org.jcaching.serializer;

public class InfoObjetoInteger implements InfoObjeto {
	
	String value;
	
	public InfoObjetoInteger(String value) {
		this.value = value; 
	}

	@Override
	public Integer retornarInstancia() {
		return Integer.parseInt(value);
	}
		

}
