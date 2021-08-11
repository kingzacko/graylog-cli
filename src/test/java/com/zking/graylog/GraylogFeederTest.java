package com.zking.graylog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zking.graylog.LogEntry;
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
     * Test parsing of JSON log message to LogEntry POJO
     */
    @Test
    public void testLogParsing() throws IOException {
        String expectedClientDeviceType = "mobile";
        String expectedClientIP = "192.168.239.245";
        String expectedClientIPClass = "noRecord";
        int expectedClientStatus = 200;
        long expectedClientRequestBytes = 632;
        String expectedClientRequestReferer = "graylog.org";
        String expectedClientRequestURI = "/search";
        String expectedClientRequestUserAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_4) AppleWebKit/600.7.12 (KHTML, like Gecko) Version/8.0.7 Safari/600.7.12";
        int expectedClientSrcPort = 264;
        String expectedEdgeServerIP = "10.0.130.188";
        long expectedEdgeStartTimestamp = 1576929197;
        String expectedDestinationIP = "172.16.122.29";
        long expectedOriginResponseBytes = 616;
        long expectedOriginResponseTime = 435000000;
        ObjectMapper mapper = new ObjectMapper();
        LogEntry log = mapper.readValue(new File("src/test/resources/single-log.json"), LogEntry.class);
        assertEquals(String.format("Incorrect ClientDeviceType - expected: '%s', actual: '%s'",
            expectedClientDeviceType, log.getClientDeviceType()), log.getClientDeviceType(), expectedClientDeviceType);
        assertEquals(String.format("Incorrect ClientIP - expected: '%s', actual: '%s'",
            expectedClientIP, log.getClientIP()), log.getClientIP(), expectedClientIP);
        assertEquals(String.format("Incorrect ClientIPClass - expected: '%s', actual: '%s'",
            expectedClientIPClass, log.getClientIPClass()), log.getClientIPClass(), expectedClientIPClass);
        assertEquals(String.format("Incorrect ClientStatus - expected: '%s', actual: '%s'",
            expectedClientStatus, log.getClientStatus()), log.getClientStatus(), expectedClientStatus);
        assertEquals(String.format("Incorrect ClientRequestBytes - expected: '%s', actual: '%s'",
            expectedClientRequestBytes, log.getClientRequestBytes()), log.getClientRequestBytes(), expectedClientRequestBytes);
        assertEquals(String.format("Incorrect ClientRequestReferer - expected: '%s', actual: '%s'",
            expectedClientRequestReferer, log.getClientRequestReferer()), log.getClientRequestReferer(), expectedClientRequestReferer);
        assertEquals(String.format("Incorrect ClientRequestURI - expected: '%s', actual: '%s'",
            expectedClientRequestURI, log.getClientRequestURI()), log.getClientRequestURI(), expectedClientRequestURI);
        assertEquals(String.format("Incorrect ClientRequestUserAgent - expected: '%s', actual: '%s'",
            expectedClientRequestUserAgent, log.getClientRequestUserAgent()), log.getClientRequestUserAgent(), expectedClientRequestUserAgent);
        assertEquals(String.format("Incorrect ClientSrcPort - expected: '%s', actual: '%s'",
            expectedClientSrcPort, log.getClientSrcPort()), log.getClientSrcPort(), expectedClientSrcPort);
        assertEquals(String.format("Incorrect EdgeServerIP - expected: '%s', actual: '%s'",
            expectedEdgeServerIP, log.getEdgeServerIP()), log.getEdgeServerIP(), expectedEdgeServerIP);
        assertEquals(String.format("Incorrect EdgeStartTimestamp - expected: '%s', actual: '%s'",
            expectedEdgeStartTimestamp, log.getEdgeStartTimestamp()), log.getEdgeStartTimestamp(), expectedEdgeStartTimestamp);
        assertEquals(String.format("Incorrect DestinationIP - expected: '%s', actual: '%s'",
            expectedDestinationIP, log.getDestinationIP()), log.getDestinationIP(), expectedDestinationIP);
        assertEquals(String.format("Incorrect OriginResponseBytes - expected: '%s', actual: '%s'",
            expectedOriginResponseBytes, log.getOriginResponseBytes()), log.getOriginResponseBytes(), expectedOriginResponseBytes);
        assertEquals(String.format("Incorrect OriginResponseTime - expected: '%s', actual: '%s'",
            expectedOriginResponseTime, log.getOriginResponseTime()), log.getOriginResponseTime(), expectedOriginResponseTime);
    }
}
