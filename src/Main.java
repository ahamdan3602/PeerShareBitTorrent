import java.lang.invoke.WrongMethodTypeException;

import com.google.gson.Gson;
import com.dampcake.bencode.Bencode; //- available if you need it!

import java.util.*;


public class Main {
  private static final Gson gson = new Gson();
  // Gson library to convert java objects to JSON and vice versa

  public static void main(String[] args) throws Exception {
    // You can use print statements as follows for debugging, they'll be visible when running tests.
    // System.out.println("Logs from your program will appear here!");
    String command = args[0];
    if("decode".equals(command)) {
        String bencodedValue = args[1];
        Object decoded;
        try {
          decoded = Bencode.decodeBencode(bencodedValue);
        } catch(RuntimeException e) {
          System.out.println(e.getMessage());
          return;
        }
        System.out.println(gson.toJson(decoded));
    } else {
        System.out.println("Unknown command: " + command);
      }

  }

  
}
