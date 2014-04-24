package org.jcaching.protocol;

import server.Storage;

public class Get extends Action {

	@Override
	public String execute(Message message) {
		
		String response = Storage.get(message.getKey());
		if (response == null) {
			response = "null";
		}
		return response;
	}

}
