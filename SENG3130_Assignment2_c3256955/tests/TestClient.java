import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

    //Junit Test cases for random strings
    public class TestClient {
        String input = "";
        ArrayList<String> output = new ArrayList<String>();
        Client client = new Client();
        Server server = new Server();

        @Test
        public void testOutputIsProvidedWithValidInput() throws IOException {
            BufferedReader br = new BufferedReader(new FileReader("output.txt"));

            //Creates new Generator Object
            Generator g = new Generator();
            //Adds each string (of length 50 characters) to the server
            client.getServer().add(g.generateStrings(50));
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

            //Creates new Generator Object
            Generator g = new Generator();

            input = g.generateStringsNoSpaces(50);

            //Adds each string (of length 50 characters) to the server
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
        public void testPunctuationMarksAreAllowed() throws IOException{
            BufferedReader br = new BufferedReader(new FileReader("output.txt"));

            //Creates new Generator Object
            Generator g = new Generator();

            input = g.generateStringsWithComma(50);
            client.getServer().add(input);

            client.generateResults();
            client.printLines();

            String line = br.readLine();
            //Ensures an output is provided
            assertNotNull(line);
            //Checks if the output file contains a comma
            assertTrue(line.contains(","));

            //Ensure no duplicate lines
            assertNull(br.readLine());
        }

        @Test
        public void testUpperCaseTitlesAppearBeforeLowerCaseTitles() throws IOException{
            BufferedReader br = new BufferedReader(new FileReader("output.txt"));

            //Creates new Generator Object
            Generator g = new Generator();

            String lowercase =  g.generateLowerCase(50);
            String uppercase = g.generateUpperCase(50);

            client.getServer().add(lowercase);
            client.getServer().add(uppercase);
            client.generateResults();
            client.printLines();

            String line = br.readLine();
            //Ensures an output is provided
            assertNotNull(line);

            //Tests whether the uppercase String appears before the  lower case  string
            assertTrue(line.contentEquals(uppercase));
            line = br.readLine();
            assertTrue(line.contentEquals(lowercase));
        }

        @Test
        public void testRepeatingWords() throws IOException{
            BufferedReader br = new BufferedReader(new FileReader("output.txt"));

            //Creates new Generator Object
            Generator g = new Generator();
            input = g.generateStringsNoSpaces(50);

            input = input + " " + input;

            client.getServer().add(input);
            client.generateResults();
            client.printLines();

            String line = br.readLine();

            //Add each line of output to an arrayList which will be used later to check if duplicate lines appear
            while(line!=null) {
                output.add(line);
                line = br.readLine();
            }
            assertTrue(containsOnce(input, output));
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

            Generator g = new Generator();
            input = g.generateStringsNoSpaces(1);

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
        public void testDuplicateTitles() throws IOException{
            BufferedReader br = new BufferedReader(new FileReader("output.txt"));
            String line = System.getProperty("line.separator");

            Generator g = new Generator();
            input = g.generateStringsNoSpaces(20);

            client.getServer().add(input);
            client.getServer().add(g.generateStringsNoSpaces(20));
            client.getServer().add(input); //Here is the duplicate input

            client.generateResults();
            client.printLines();

            String readLine = br.readLine();

            //Button Moon is first in alphabet so read the first line and verify that it is there
            assertTrue(input.equals(br.readLine()));

            //read all other lines and ensure "Button Moon" does not appear
            while(readLine != null) {
                assertFalse(input.equals(readLine));
                readLine = br.readLine();
            }
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