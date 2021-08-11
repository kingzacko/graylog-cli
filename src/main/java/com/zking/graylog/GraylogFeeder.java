package com.zking.graylog;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * CLI application for reading Graylog input file and server URL then
 * sending them to a Graylog server
 *
 */
public class GraylogFeeder 
{
    public static final Logger LOGGER = LogManager.getLogger(GraylogFeeder.class);

    public static void main(String[] args) {
        // parse the command line parameters
        CommandLine clInput = parseParams(args);
        if (clInput != null) {
            String serverURL = clInput.getOptionValue("url");
            String inputFile = clInput.getOptionValue("input");
            ObjectMapper mapper = new ObjectMapper();
            List<LogEntry> logEntries = new ArrayList<>();
            try(BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    logEntries.add(mapper.readValue(line, LogEntry.class));
                }
            } catch (IOException e) {
                LOGGER.error("Unable to parse input file: {}", e.toString());
            }
            LOGGER.trace("LogEntry count: {}", logEntries.size());
            HttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(serverURL);
            post.setHeader("Content-Type", "application/json");
            StringEntity gelfMessage;
            HttpResponse response;
            try {
                for (LogEntry log : logEntries) {
                    gelfMessage = new StringEntity(log.toGELF());
                    LOGGER.trace("Sending GELF formatted log to server: {}", log.toGELF());
                    post.setEntity(gelfMessage);
                    response = client.execute(post);
                }
            } catch (UnsupportedEncodingException uee) {
                LOGGER.error("Error encoding log entry: {}", uee.toString());
            } catch (IOException ioe) {
                LOGGER.error("Error sending message to Graylog: {}", ioe.toString());
            }
        }
    }

    private static CommandLine parseParams(String[] args) {
        Options options = new Options();

        Option inputFile = new Option("i", "input", true, "input file path");
        inputFile.setRequired(true);
        
        Option serverURL = new Option("u", "url", true, "Graylog server URL");
        serverURL.setRequired(true);

        options.addOption(inputFile);
        options.addOption(serverURL);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        try {
            return parser.parse(options, args);
        } catch (ParseException e) {
            LOGGER.error("error parsing command line arguments: {}", e.getMessage());
            formatter.printHelp("GraylogFeeder", options);
            return null;
        }
    }
}
