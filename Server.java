import java.util.LinkedList;
import java.util.*;
/**
 * Server class which processes the KWIC system
 */
public class Server {

    //LinkedList of results
    private  LinkedList<String> results = new LinkedList<>();

    //Results are sorted and returned to the client
    public LinkedList<String> getResults() {
        //Sort results
        Collections.sort(results);
        return results;
    }

    //Strings are added to the server and modified
    public void add(String input)
    {
        //All the words are split into individual parts of an array
        LinkedList<String> inputWords = new LinkedList<>();

        String[] allWords = input.split(" ");

        //This array is turned into a LinkedList
        for (int k = 0; k < allWords.length; k++)
        {
            inputWords.add(allWords[k]);
        }

        //The LinkedList is iterated
        for (int z = 0; z < inputWords.size(); z++)
        {
            //Results are generated
            results.add(inputWords.toString());
            //The last result is moved to be the first
            inputWords.addLast(inputWords.getFirst());
            //The first result is removed
            inputWords.remove(0);
        }
    }
}
