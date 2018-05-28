/*
This is the ClientServer implementation of the KWIC system. Please start here
 */

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class ClientServer {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        //Creating a new client object
       Client c = new Client();
       c.getUserInput();
    }
}
