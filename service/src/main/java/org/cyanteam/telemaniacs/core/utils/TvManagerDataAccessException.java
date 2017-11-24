package org.cyanteam.telemaniacs.core.utils;

/**
 *
 * @author Miroslav Kubus
 */
public class TvManagerDataAccessException extends Exception {

    /**
     * Creates a new instance of <code>TvManagerDataAccessException</code>
     * without detail message.
     */
    public TvManagerDataAccessException() {
    }

    /**
     * Constructs an instance of <code>TvManagerDataAccessException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public TvManagerDataAccessException(String msg) {
        super(msg);
    }
}
