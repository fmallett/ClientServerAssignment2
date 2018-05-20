import java.util.LinkedList;
import java.util.Random;

public class Generator {

    private LinkedList<String> randomTitles = new LinkedList<>();

    public void generateStrings(int count)
    {
        String bigString = " abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789 !@#$%^&*()<>?/ .,`~+_=-|[]{}; ";
        Random rand = new Random();
        int value = rand.nextInt(50);

        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*bigString.length());
            builder.append(bigString.charAt(character));
        }

        randomTitles.add(builder.toString());
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


