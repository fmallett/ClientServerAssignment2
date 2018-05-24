import org.junit.Test;

import static org.junit.Assert.*;
import java.io.BufferedReader;
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

		input = "Star wars\nThe Simpsons\nA Series Of Unfortunate Events";
		client.getServer().add(input);
		client.generateResults();
		client.printLines();


		//Checks whether the output.txt file contains information
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

		input = "Ah, Wilderness!";
		client.getServer().add(input);
		client.generateResults();
		client.printLines();

		String line = br.readLine();
		//Ensures an output is provided
		assertNotNull(line);
		//Checks if the output file contains a comma
		assertFalse(line.contains(","));
		//checks the output lines are in alphabetical order
		assertEquals("Ah Wilderness!", line);
		assertEquals("Wilderness! Ah", br.readLine());
		//Ensure no duplicate lines
		assertNull(br.readLine());
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

	@Test
	public void testSymbolsUsedInBookTitles() throws IOException{ 
		BufferedReader br = new BufferedReader(new FileReader("output.txt"));
		//[] square brackets make the test fail
		input = "Star Wars[]!$%^&*()";
		client.getServer().add(input);
		client.generateResults();
		client.printLines();

		String line = br.readLine();
		System.out.println(line);
		//Ensures an output is provided
		assertNotNull(line);
		//Checks if the output file matches the input
		assertTrue(line.contains("[]"));
		assertTrue(line.contentEquals(input));
	}

	@Test
	public void testSingleCharacter() throws IOException{ 
		BufferedReader br = new BufferedReader(new FileReader("output.txt"));
		input = "f";
		client.getServer().add(input);
		client.generateResults();
		client.printLines();

		String line = br.readLine();
		System.out.println(line);
		//Ensures an output is provided
		assertNotNull(line);
		//Checks if the output file matches the input
		assertTrue(line.contentEquals(input));
		//Ensure there are no other lines outputted
		assertNull(br.readLine());
	}
	
	@Test
	public void testAnotherLanguge() throws IOException{ 
		BufferedReader br = new BufferedReader(new FileReader("output.txt"));
		input = "böse"; //German word
		client.getServer().add(input);
		client.generateResults();
		client.printLines();

		String line = br.readLine();
		System.out.println(line);
		//Ensures an output is provided
		assertNotNull(line);
		//Checks if the output file matches the input
		assertTrue(line.contentEquals(input));
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
