Graylog Feeder CLI Application

Command line application for reading an input file consisting of JSON objects on each line that represent a com.zking.graylog.Log message and sending those messages to a Graylog server as GELF formatted messages. 

Can be run either using the generated FAT jar file directly:
 	- java -jar target/graylog-cli-0.1-jar-with-dependencies.jar -i [input_file] -u [url]
or using the GraylogFeeder.sh script
	- ./GraylogFeeder.sh --input [input_file] --url [url]
Where:
	- i, --input is the path to the input file containing the JSON objects
	- u, --url is the full address of the Graylog server's GELF input

For example,
./GraylogFeeder.sh -i /home/zking/testfile -u http://localhost:12201/gelf

Will parse the file /home/zking/testfile and send each LogEntry to the locally running Graylog server's GELF input listening on port 12201.
