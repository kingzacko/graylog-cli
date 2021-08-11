package com.zking.graylog;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.IOException;
import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * POJO representing a single log entry to be sent to a Graylog server 
 * 
 */
public class LogEntry implements GELFMessage {

    private static final Logger LOGGER = LogManager.getLogger(LogEntry.class);

    private String clientDeviceType;
    private String clientIP;
    private String clientIPClass;
    private int clientStatus;
    private long clientRequestBytes;
    private String clientRequestReferer;
    private String clientRequestURI;
    private String clientRequestUserAgent;
    private int clientSrcPort;
    private String edgeServerIP;
    private long edgeStartTimestamp;
    private String destinationIP;
    private long originResponseBytes;
    private long originResponseTime;

    /**
     * @return String return the clientDeviceType
     */
    @JsonProperty("ClientDeviceType")
    public String getClientDeviceType() {
        return clientDeviceType;
    }

    /**
     * @param clientDeviceType the clientDeviceType to set
     */
    public void setClientDeviceType(String clientDeviceType) {
        this.clientDeviceType = clientDeviceType;
    }

    /**
     * @return String return the clientIP
     */
    @JsonProperty("ClientIP")
    public String getClientIP() {
        return clientIP;
    }

    /**
     * @param clientIP the clientIP to set
     */
    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    /**
     * @return String return the clientIPClass
     */
    @JsonProperty("ClientIPClass")
    public String getClientIPClass() {
        return clientIPClass;
    }

    /**
     * @param clientIPClass the clientIPClass to set
     */
    public void setClientIPClass(String clientIPClass) {
        this.clientIPClass = clientIPClass;
    }

    /**
     * @return int return the clientStatus
     */
    @JsonProperty("ClientStatus")
    public int getClientStatus() {
        return clientStatus;
    }

    /**
     * @param clientStatus the clientStatus to set
     */
    public void setClientStatus(int clientStatus) {
        this.clientStatus = clientStatus;
    }

    /**
     * @return long return the clientRequestBytes
     */
    @JsonProperty("ClientRequestBytes")
    public long getClientRequestBytes() {
        return clientRequestBytes;
    }

    /**
     * @param clientRequestBytes the clientRequestBytes to set
     */
    public void setClientRequestBytes(long clientRequestBytes) {
        this.clientRequestBytes = clientRequestBytes;
    }

    /**
     * @return String return the clientRequestReferrer
     */
    @JsonProperty("ClientRequestReferer")
    public String getClientRequestReferer() {
        return clientRequestReferer;
    }

    /**
     * @param clientRequestReferer the clientRequestReferrer to set
     */
    public void setClientRequestReferer(String clientRequestReferer) {
        this.clientRequestReferer = clientRequestReferer;
    }

    /**
     * @return String return the clientRequestURI
     */
    @JsonProperty("ClientRequestURI")
    public String getClientRequestURI() {
        return clientRequestURI;
    }

    /**
     * @param clientRequestURI the clientRequestURI to set
     */
    public void setClientRequestURI(String clientRequestURI) {
        this.clientRequestURI = clientRequestURI;
    }

    /**
     * @return String return the clientRequestUserAgent
     */
    @JsonProperty("ClientRequestUserAgent")
    public String getClientRequestUserAgent() {
        return clientRequestUserAgent;
    }

    /**
     * @param clientRequestUserAgent the clientRequestUserAgent to set
     */
    public void setClientRequestUserAgent(String clientRequestUserAgent) {
        this.clientRequestUserAgent = clientRequestUserAgent;
    }

    /**
     * @return int return the clientSrcPort
     */
    @JsonProperty("ClientSrcPort")
    public int getClientSrcPort() {
        return clientSrcPort;
    }

    /**
     * @param clientSrcPort the clientSrcPort to set
     */
    public void setClientSrcPort(int clientSrcPort) {
        this.clientSrcPort = clientSrcPort;
    }

    /**
     * @return String return the edgeServerIP
     */
    @JsonProperty("EdgeServerIP")
    public String getEdgeServerIP() {
        return edgeServerIP;
    }

    /**
     * @param edgeServerIP the edgeServerIP to set
     */
    public void setEdgeServerIP(String edgeServerIP) {
        this.edgeServerIP = edgeServerIP;
    }

    /**
     * @return long return the edgeStartTimestamp
     */
    @JsonProperty("EdgeStartTimestamp")
    public long getEdgeStartTimestamp() {
        return edgeStartTimestamp;
    }

    /**
     * @param edgeStartTimestamp the edgeStartTimestamp to set
     */
    public void setEdgeStartTimestamp(long edgeStartTimestamp) {
        this.edgeStartTimestamp = edgeStartTimestamp;
    }

    /**
     * @return String return the destinationIP
     */
    @JsonProperty("DestinationIP")
    public String getDestinationIP() {
        return destinationIP;
    }

    /**
     * @param destinationIP the destinationIP to set
     */
    public void setDestinationIP(String destinationIP) {
        this.destinationIP = destinationIP;
    }

    /**
     * @return long return the originResponseBytes
     */
    @JsonProperty("OriginResponseBytes")
    public long getOriginResponseBytes() {
        return originResponseBytes;
    }

    /**
     * @param originResponseBytes the originResponseBytes to set
     */
    public void setOriginResponseBytes(long originResponseBytes) {
        this.originResponseBytes = originResponseBytes;
    }

    /**
     * @return long return the originResponseeTime
     */
    @JsonProperty("OriginResponseTime")
    public long getOriginResponseTime() {
        return originResponseTime;
    }

    /**
     * @param originResponseTime the originResponseTime to set
     */
    public void setOriginResponseTime(long originResponseTime) {
        this.originResponseTime = originResponseTime;
    }

    @Override
    public String toGELF() {
        JsonObject gelfMessage = Json.createObjectBuilder()
            .add("version", "1.1")
            .add("host", "GraylogFeeder")
            .add("short_message", "HTTP Log")
            .add("timestamp", this.getEdgeStartTimestamp())
            .add("_ClientDeviceType", this.getClientDeviceType())
            .add("_ClientIP", this.getClientIP())
            .add("_ClientIPClass", this.getClientIPClass())
            .add("_ClientStatus", this.getClientStatus())
            .add("_ClientRequestBytes", this.getClientRequestBytes())
            .add("_ClientRequestReferer", this.getClientRequestReferer())
            .add("_ClientRequestURI", this.getClientRequestURI())
            .add("_ClientRequestUserAgent", this.getClientRequestUserAgent())
            .add("_ClientSrcPort", this.getClientSrcPort())
            .add("_EdgeServerIP", this.getEdgeServerIP())
            .add("_EdgeStartTimestamp", this.getEdgeStartTimestamp())
            .add("_DestinationIP", this.getDestinationIP())
            .add("_OriginResponseBytes", this.getOriginResponseBytes())
            .add("_OriginResponseTime", this.getOriginResponseTime())
            .build();
            try (StringWriter stringWriter = new StringWriter();
                JsonWriter writer = Json.createWriter(stringWriter)) {
                writer.writeObject(gelfMessage);
                return stringWriter.getBuffer().toString();
            } catch (IOException ioe) {
                LOGGER.error("Unable to write Log in GELF format: {}", ioe.toString());
                return "";
            }
    }
}
