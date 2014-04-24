package org.jcaching.protocol;

import server.Storage;

public class Set extends Action {

	@Override
	public String execute(Message message) {

		Storage.set(message.getKey(), message.getValue());
		return "ok";
	}

}
