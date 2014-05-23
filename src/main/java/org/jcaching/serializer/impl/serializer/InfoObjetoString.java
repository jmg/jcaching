package org.jcaching.serializer.impl.serializer;

public class InfoObjetoString implements InfoObjeto {
	
	String value;
	
	public InfoObjetoString(String value) {
		this.value = value; 
	}

	@Override
	public String retornarInstancia() {
		return value;
	}
	
		

}
