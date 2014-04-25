package org.jcaching.settings;

import org.jcaching.protocol.Protocol;
import org.jcaching.protocol.SimpleProtocol;

public class MemoryProcessSettings {
		
	public static Protocol getProtocol() {
		
		return new SimpleProtocol();
	}
}
