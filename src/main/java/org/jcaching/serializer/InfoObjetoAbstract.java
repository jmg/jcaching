package org.jcaching.serializer;
import java.lang.reflect.Field;
import java.util.HashMap;

public class InfoObjetoAbstract implements InfoObjeto{
	
	Class<?> clas;
	HashMap<Field, InfoObjeto> attrs;
	
	public InfoObjetoAbstract(String clas) throws ClassNotFoundException {
		this.clas = Class.forName(clas);
		this.attrs = new HashMap<Field, InfoObjeto>();
	}
	
	public void addAttr(String attr, String object) throws Exception{

		Field field = clas.getField(attr);
		InfoObjeto value = null;
		
		
		if(field.getType().getName() == "java.lang.String"){
			value = new InfoObjetoString(object);
		}else if(field.getType().getName() == "java.lang.Integer"){
			value = new InfoObjetoInteger(object);
		}else if(field.getType().getName() == "java.lang.Boolean"){
			value = new InfoObjetoBoolean(object);
		}
		
		attrs.put(field, value);
	}
	
	@Override
	public Object retornarInstancia() {
		// TODO Auto-generated method stub
		return null;
	}

}
