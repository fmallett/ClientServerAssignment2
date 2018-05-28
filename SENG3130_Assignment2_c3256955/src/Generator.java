import java.util.LinkedList;
import java.util.Random;

//Public class generator which creates sample random data
public class Generator {
    private LinkedList<String> randomTitles = new LinkedList<>();

    public LinkedList<String> getRandomTitles() {
        return randomTitles;
    }

    //Generates a set of random strings
    public String generateStrings(int count)
    {
        //Characters to select from
        String bigString = " abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ α,β,γ,δ,ε 0123456789 NEIN !@#$%^&*()<>?/  ";
        //Generates a random string to the length given.
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            //Gets a random character
            int character = (int)(Math.random()*bigString.length());
            //Appends it to the string
            builder.append(bigString.charAt(character));
        }
        //Returns the result
        return (builder.toString());
    }

    public String generateStringsNoSpaces(int count)
    {
        //Characters to select from
        String bigString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZαβγδε0123456789!@#$%^&*()<>?/";

        //Generates a random string to the length given.
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            //Gets a random character
            int character = (int)(Math.random()*bigString.length());
            //Appends it to the string
            builder.append(bigString.charAt(character));
        }
        //Returns the result
        return (builder.toString());
    }

    public String generateStringsWithComma(int count)
    {
        String bigString = ",.';:?/><~`";

        //Generates a random string to the length given.
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            //Gets a random character
            int character = (int)(Math.random()*bigString.length());
            //Appends it to the string
            builder.append(bigString.charAt(character));
        }
        //Returns the result
        return "," + (builder.toString()) + ",";
    }

    public String generateUpperCase(int count)
    {
        String bigString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        //Generates a random string to the length given.
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            //Gets a random character
            int character = (int)(Math.random()*bigString.length());
            //Appends it to the string
            builder.append(bigString.charAt(character));
        }
        //Returns the result
        return (builder.toString());
    }

    public String generateLowerCase(int count)
    {
        String bigString = "abcdefghijklmnopqrstuvwxyz";

        //Generates a random string to the length given.
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            //Gets a random character
            int character = (int)(Math.random()*bigString.length());
            //Appends it to the string
            builder.append(bigString.charAt(character));
        }
        //Returns the result
        return (builder.toString());
    }

    public String toString() {
        String output = "";
        for (String str: randomTitles)
        {
            output = output + str;
            System.out.println(str);
        }

        return output;
    }

}


