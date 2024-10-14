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
          decoded = decodeBencode(bencodedValue);
        } catch(RuntimeException e) {
          System.out.println(e.getMessage());
          return;
        }
        System.out.println(gson.toJson(decoded));
    } else {
        System.out.println("Unknown command: " + command);
      }

  }
  static Object decodeBencode(String bencodedString) {
    // 3:cat
    if (Character.isDigit(bencodedString.charAt(0))) { //checks if first character is a digit
      //first character in bencode usually tells us the length of the string
      int firstColonIndex = 0;
      for(int i = 0; i < bencodedString.length(); i++) { 
        if(bencodedString.charAt(i) == ':') {
          firstColonIndex = i; //find the colon that seperates the number from the actual string.
          break;
        }
      }
      int length = Integer.parseInt(bencodedString.substring(0, firstColonIndex)); //extract length of string from the bencode
      //ex: 3:cat, we get length = 3;
      return bencodedString.substring(firstColonIndex+1, firstColonIndex+1+length);
      //returns the decoded bencode string, which would be in this case cat.
    } else if (bencodedString.startsWith("i")) {
      return Long.parseLong(bencodedString.substring(1, bencodedString.indexOf("e")));
    } else if (bencodedString.startsWith("l")) {
        ArrayList<Object> arr = new ArrayList<>();

        for (int i = 1; i < bencodedString.length();i++) {
          if (Character.isDigit(bencodedString.charAt(i))) {
            int length = Integer.valueOf(i);
            int sidx = i + 2; //starting index
            int eidx = sidx + length; //ending index

            String word = bencodedString.substring(sidx, eidx);
            arr.add(word);
          } else if (bencodedString.charAt(i) == 'i') {
            arr.add(bencodedString.substring(i+1, bencodedString.indexOf('e', i)));

          } else if (bencodedString.charAt(i) == 'l') {

          } else if (bencodedString.charAt(i) == 'd') {

          } 
        } 
        return arr;
    } else {
      throw new RuntimeException("Only strings are supported at the moment");
    }
  }
  
}
