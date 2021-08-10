package com.zking.graylog;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

/**
 * CLI application for reading Graylog input file and server URL then
 * sending them to a Graylog server
 *
 */
public class GraylogFeeder 
{
    public static void main(String[] args) {
        // parse the command line parameters
        CommandLine clInput = parseParams(args);
        if (clInput != null) {
            String serverURL = clInput.getOptionValue("url");
            String inputFile = clInput.getOptionValue("input");
            ObjectMapper mapper = new ObjectMapper();
            List<Log> logEntries = new ArrayList<>();
            try(BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    logEntries.add(mapper.readValue(line, Log.class));
                }
            } catch (IOException e) {
                System.out.println(String.format("Unable to parse input file: %s", e));
            }
            System.out.printf("Log entry count: %d\n", logEntries.size());
            HttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(serverURL);
            post.setHeader("Content-Type", "application/json");
            StringEntity gelfMessage;
            HttpResponse response;
            try {
                for (Log log : logEntries) {
                    gelfMessage = new StringEntity(log.toGELF());
                    //System.out.println(log.toGELF());
                    post.setEntity(gelfMessage);
                    response = client.execute(post);
                }
            } catch (Exception e ) {
                System.out.printf("Error sending log entry: %s", e);
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
            System.out.println(e.getMessage());
            formatter.printHelp("GraylogFeeder", options);

            return null;
        }
    }
}