/**
 * Delete
 *
 * TODO Description if available.
 */

package org.jcaching.protocol.action.impl;

import org.jcaching.protocol.Message;
import org.jcaching.protocol.action.Action;
import org.jcaching.server.Storage;

/**
 * TODO description
 */
public class Delete implements Action {

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
        
        Storage.getInstance().delete(message.getKey());
        return "ok";
    }
}
