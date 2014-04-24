/**
 * Get
 *
 * TODO Description if available.
 */

package org.jcaching.protocol;

import org.jcaching.server.Storage;

/**
 * TODO description
 */
public class Get extends Action {

    /**
     * Default constructor.
     */
    public Get() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Message message) {
        
        String response = Storage.get(message.getKey());
        if (response == null) {
            response = "null";
        }
        return response;
    }
}
