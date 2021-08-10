package com.zking.graylog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zking.graylog.Log;
import java.io.File;
import java.io.IOException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


/**
 * Unit tests for GraylogFeeder application
 */
public class GraylogFeederTest 
{
    /**
     * Test parsing of JSON log message to Log POJO
     */
    @Test
    public void testLogParsing() throws IOException {
        String expectedClientDeviceType = "mobile";
        ObjectMapper mapper = new ObjectMapper();
        Log log = mapper.readValue(new File("src/test/resources/single-log.json"), Log.class);
        assertEquals(String.format("Incorrect ClientDeviceType - expected: '%s', actual: '%s'",
            expectedClientDeviceType, log.getClientDeviceType()), log.getClientDeviceType(), expectedClientDeviceType);
    }
}
