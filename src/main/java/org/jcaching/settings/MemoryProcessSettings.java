/**
 * MemoryProcessSettings
 *
 * TODO Description if available.
 */

package org.jcaching.settings;

import org.jcaching.protocol.Protocol;
import org.jcaching.protocol.impl.SimpleProtocol;

/**
 * TODO
 */
public class MemoryProcessSettings {
        
    /**
     * TODO
     *
     * @return TODO
     */
    public static Protocol getProtocol() {
        
        return new SimpleProtocol();
    }
}
