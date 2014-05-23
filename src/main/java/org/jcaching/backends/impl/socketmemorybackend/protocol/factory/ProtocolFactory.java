/**
 * ProtocolFactory
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.socketmemorybackend.protocol.factory;

import org.apache.commons.configuration.Configuration;
import org.jcaching.backends.impl.socketmemorybackend.protocol.Protocol;
import org.jcaching.exception.ImplementationClassLoadException;
import org.jcaching.factory.BaseFactory;

/**
 * TODO
 */
public class ProtocolFactory extends BaseFactory {

    /**
     * TODO
     *
     * @param configuration TODO
     */
    public ProtocolFactory(Configuration configuration) {
        super(configuration);
    }

    public Protocol getProtocolInstance()
        throws ImplementationClassLoadException {

        return (Protocol) this.getObjectInstance("jcaching.backends.socketmemorybackend.protocol");
    }
} 

// vim:ft=java ts=4 tw=80 cc=+1: 
