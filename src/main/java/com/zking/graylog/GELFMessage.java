package com.zking.graylog;

/**
 * GELFMessage interface for classes that are to be written out in GELF 
 */
public interface GELFMessage {

    public String toGELF();
}