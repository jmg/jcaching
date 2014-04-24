/**
 * Message
 *
 * TODO Description if available.
 */

package org.jcaching.protocol;

/**
 * TODO
 */
public class Message {
        
    private Action action;
    private String key;
    private String value;   
    
    /**
     * Constructor.
     *
     * @param action TODO
     * @param key TODO
     * @param value TODO
     */
    public Message(String action, String key, String value) {
        super();
                
        this.key = key;
        this.value = value;
        this.action = this.setAction(action);
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public String getKey() {
        return key;
    }

    /**
     * TODO
     *
     * @param key TODO
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public String getValue() {
        return value;
    }

    /**
     * TODO
     *
     * @param value TODO
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public Action getAction() {
        return action;
    }

    /**
     * TODO
     *
     * @param action TODO
     * @return TODO
     */
    public Action setAction(String action) {
        
        if (action.equals("GET")) {
            return new Get();
        } else if (action.equals("SET")) {
            return new Set();
        } else if (action.equals("DELETE")) {
            return new Delete();
        }
        return null;
    }
    
}
