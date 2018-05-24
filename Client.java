package main.ClientServerAssignment2;

import java.io.*;
import java.util.LinkedList;

/**
 * Client Class which processes the file input and generates results with the Server
 */
public class Client {

    //LinkedList to hold the results
    private LinkedList<String> results = new LinkedList<>();

    public LinkedList<String> getResults() {
		return results;
	}

	//New server object
    private Server server = new Server();

    public Server getServer() {
		return server;
	}

	//Retrieves the user input from the text file
    public void getUserInput()
    {
        //Creates a new BufferedReader object to read the input file
       try(BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {

           //Gets the first line of the input file
            String line = br.readLine();

            //Reads the file line by line
            while (line != null) {
                server.add(line);
                line = br.readLine();
            }

            //Generates the results
            generateResults();

            //Prints the results
            printLines();

        } catch (IOException e) {
           //Exception for Input/Output
            e.printStackTrace();
        }

    }

    //Prints the lines to an external file called "output.txt"
    public void printLines() throws FileNotFoundException, UnsupportedEncodingException {

        //Generates a new file called "output.txt"
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");

        //Writes the results to the file
        for (String currentLine : results) {
            writer.println(currentLine);
        }
        writer.close();
    }

    //Generates the results from the server. Removes the LinkedList attributes
    public void generateResults()
    {
        LinkedList<String> temp = server.getResults();

        //Iterates through the results given by the server
        for (String currentLine : temp) {
            currentLine = currentLine.substring(1, currentLine.length()-2);
            //currentLine = currentLine.replace("[", "");
            //currentLine = currentLine.replace("]", "");
            currentLine = currentLine.replace(",", "");
            results.add(currentLine);
        }
    }
}
