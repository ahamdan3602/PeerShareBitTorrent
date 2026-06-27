import java.lang.invoke.WrongMethodTypeException;

import com.google.gson.Gson;
import com.dampcake.bencode.Bencode; //- available if you need it!

import java.util.*;


public class Bencode {

    public static Object decodeBencode(bencodedString) { 
        
        // Decoding bencoded strings
        // Byte strings are encoded as follows:
        // <string length encoded in base ten ASCII>:<string data>
        if (Character.isDigit(bencodedString.charAt(0))) {
            int colon_index = 0;
            for (int i = 0; i < bencodedString.length();i++) {
                if (bencodedString.charAt(i)== ':') {
                    colon_index = i;
                    break;
                }
            }
            int length = Integer.parseInt(bencodedString.substring(0, colon_index));
            return bencodedString.substring(colon_index, colon_index+1+length);
        } 
        // Decoding bencoded integers
        // Integers are encoded as follows:
        // i<integer encoded in base ten ASCII>e
        else if (bencodedString.startsWith("i")) {
            return Long.parseLong(bencodedString.substring(1, bencodedString.indexOf('e')));
        } 
        // Decoding bencoded lists
        //Lists are encoded as follows: 
        // l<bencoded values>e
        else if (bencodedString.startsWith("l")) {
            List<Object> arr = new ArrayList<>(); 
            for (int i = 0; i < bencodedString.length();i++) {
                // handle string in list
                if (Character.isDigit(bencodedString.charAt(i))) {
                    int colonIndex = bencodedString.indexOf(':', i);
                    int length = Integer.parseInt(bencodedString.substring(i, colon_index));
                    arr.add(bencodedString.substring(colon_index+1, (colon_index+1) + length));
                    i = ((colonIndex + 1) + length) - 1;
                } 
                // handle int in list
                else if (bencodedString.charAt(i) == 'i') {}
            }
        }




    }




}