package org.jcaching.protocol;

import server.Storage;

public class Delete extends Action {

	@Override
	public String execute(Message message) {
		
		Storage.delete(message.getKey());
		return "ok";
	}

}
