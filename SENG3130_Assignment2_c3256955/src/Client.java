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
    public void getUserInput() throws FileNotFoundException, UnsupportedEncodingException {
        //Creates new Generator Object
        Generator g = new Generator();

        //Generates 50 random strings
        for (int i =0; i < 50; i++)
        {
            //Adds each string (of length 50 characters) to the server
            server.add(g.generateStrings(50));
        }
            //Generates the results
            generateResults();

            //Prints the results
            printLines();

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
            currentLine = currentLine.substring(1, currentLine.length()-1);
            currentLine = removeLinkedListCommas(currentLine);
            results.add(currentLine);
        }
    }

    private String removeLinkedListCommas(String test) {
        boolean commasRemaining = true;
        String result = "";
        String concat = test;

        while (commasRemaining) {
            int index = concat.indexOf(",");

            if ((concat.length()-1 <= index) || (index < 0)) {
                return result+concat;
            } else if (concat.charAt(index+1) == ',') {
                result += concat.substring(0, index+1);
                concat = concat.substring(index+2);
            } else {
                result += concat.substring(0, index);
                concat = concat.substring(index+1);
            }
        }

        return result;
    }
}
