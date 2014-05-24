/**
 * ProtocolFactory
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.socketmemorybackend.protocol.factory;

import org.apache.commons.configuration.Configuration;
import org.jcaching.backends.impl.socketmemorybackend.protocol.Protocol;
import org.jcaching.factory.BaseFactory;

/**
 * TODO
 */
public class ProtocolFactory<T extends Protocol> extends BaseFactory<T> {

    /**
     * TODO
     *
     * @param configuration TODO
     */
    public ProtocolFactory(Configuration configuration) {
        super(configuration);
    }

	@Override
	protected String getConfigurationKey() {
 
		return "jcaching.backends.socketmemorybackend.protocol";
	}	
    
} 

// vim:ft=java ts=4 tw=80 cc=+1: 
