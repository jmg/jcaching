/**
 * JsonProtocol
 *
 * TODO Description if available.
 */

package org.jcaching.protocol.impl;

import org.jcaching.protocol.Message;
import org.jcaching.protocol.Protocol;
import org.jcaching.protocol.action.Action;

/**
 * TODO
 */
public class JsonProtocol implements Protocol {
    
    /**
     * Default constructor.
     */
    public JsonProtocol() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildMessage(String action, String key, String data) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildResponse(Message message) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Message parseMessage(String message) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String parseResponse(String message) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSetAction() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGetAction() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeleteAction() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action getAction(String action) {
        // TODO Auto-generated method stub
        return null;
    }
}
