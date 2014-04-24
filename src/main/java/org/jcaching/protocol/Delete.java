/**
 * Delete
 *
 * TODO Description if available.
 */

package org.jcaching.protocol;

import org.jcaching.server.Storage;

/**
 * TODO description
 */
public class Delete extends Action {

    /**
     * Default constructor.
     */
    public Delete() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Message message) {
        
        Storage.delete(message.getKey());
        return "ok";
    }
}
