/**
 * Set
 *
 * TODO Description if available.
 */

package org.jcaching.protocol;

import org.jcaching.server.Storage;

/**
 * TODO description
 */
public class Set implements Action {

    /**
     * Default constructor.
     */
    public Set() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Message message) {

        Storage.getInstance().set(message.getKey(), message.getValue());
        return "ok";
    }

}
