import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 *@Name Fiona Mallett
 *@Course Computer Science
 *@StudentNumber 3289339
 */

public class TestClient {

	String input = "";
	ArrayList <String> output = new ArrayList<String>();
	Client client = new Client();
	Server server = new Server(); 


	@Test 
	public void testOutputIsProvidedWithValidInput() throws IOException{ 
		BufferedReader br = new BufferedReader(new FileReader("output.txt"));

		input = "Star wars\nThe Simpsons";
		client.getServer().add(input);
		client.generateResults();
		client.printLines();


		//Checks where the output.txt file contains information
		assertNotNull(br.readLine());
		br.close();
	}

	@Test 
	public void testNoWordInput(){ 
		input = "";
		server.add(input);
		server.getResults();
		client.generateResults();
		assertTrue(client.getResults().isEmpty());
	}

	@Test 
	public void testOneWordInput() throws IOException{ 
		BufferedReader br = new BufferedReader(new FileReader("output.txt"));

		input = "Science";
		client.getServer().add(input);
		client.generateResults();
		client.printLines();

		String line = br.readLine();

		//Checks if the input matches the first line from the output file
		assertEquals(input, line);

		//Checks that the second line in the output file is empty as there
		//was only one word provided
		assertNull(br.readLine());
	}


	@Test 
	public void testCommasAreIgnored() throws IOException{ 
		BufferedReader br = new BufferedReader(new FileReader("output.txt"));

		input = "Ah, Wilderness!”";
		client.getServer().add(input);
		client.generateResults();
		client.printLines();

		String line = br.readLine();

		//Ensures an output is provided
		assertNotNull(line);
		//Checks if the output file contains a comma
		assertFalse(line.contains(","));
	}

	@Test 
	public void testUpperCaseTitlesAppearBeforeLowerCaseTitles() throws IOException{ 
		BufferedReader br = new BufferedReader(new FileReader("output.txt"));

		input = "Zebra\napple";
		client.getServer().add(input);
		client.generateResults();
		client.printLines();

		String line = br.readLine();

		//Ensures an output is provided
		assertNotNull(line);

		//Tests whether the capital "Z" appears before lower case "a"
		assertTrue(line.contentEquals("Zebra"));
		line = br.readLine();
		assertTrue(line.contentEquals("apple"));
	}

	@Test 
	public void testRepeatingWords() throws IOException{ 
		BufferedReader br = new BufferedReader(new FileReader("output.txt"));

		input = "Mirror Mirror";
		client.getServer().add(input);
		client.generateResults();
		client.printLines();

		String line = br.readLine();

		//Add each line of output to an arrayList which will be used later to check if duplicate lines appear
		while(line!=null) {
			output.add(line);
			line = br.readLine();
		}

		//Test will fail if book titles with repeating words appear more than once in the output
		assertTrue(containsOnce(input, output));

		//Can change the title to "Mirror" to see the test pass
	}


	//Loops through an output array list for book titles
	//returns true if a book title appears once in the list, false otherwise
	public static boolean containsOnce(final String input, final ArrayList output) {
		int numberOfMatches = 0;
		for (int i = 0; i < output.size(); i++) {
			if(output.contains(input)) {
				numberOfMatches++;
			}
		}
		if(numberOfMatches == 1) {
			return true;
		}
		else return false;
	}
}
