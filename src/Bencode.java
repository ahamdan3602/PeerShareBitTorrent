import java.lang.invoke.WrongMethodTypeException;

import java.util.*;


public class Bencode {
    private final String input;
    private int pos = 0;

    private Bencode(String input) {
        this.input = input;
    }

 
    private Object decodeNext() {
        char current = input.charAt(pos);

        if (Character.isDigit(current)) {
            return decodeString();
        }

        if (current == 'i') {
            return decodeInteger();
        }

        if (current == 'l') {
            return decodeList();
        }

        throw new RuntimeException("Unsupported bencode value");
    }

    // Decoding bencoded integers
    // Integers are encoded as follows:
    // i<integer encoded in base ten ASCII>e
    private Object decodeInteger() {
        int end_index = input.indexOf('e', pos);
        int value = Integer.parseInt(input.substring(pos + 1, end_index));

        pos = end_index + 1;

        return value;
    }

    // Decoding bencoded strings
    // Byte strings are encoded as follows:
    // <string length encoded in base ten ASCII>:<string data>
    private Object decodeString() {
       int colon_index = input.indexOf(':', pos);

        int length = Integer.parseInt(input.substring(pos, colon_index));

        int start = colon_index + 1;
        int end = start + length;

        pos = end;
        
        return input.substring(colon_index+1, colon_index + 1 + length);
    }

    // Decoding bencoded lists
    // Lists are encoded as follows:
    // l<bencoded values>e
    private Object decodeList() {
        List<Object> arr = new ArrayList<>();
        pos++;

        while (input.charAt(pos) != 'e') {
            arr.add(decodeNext());
        }

        pos++;
        return arr;
    }

    public static Object decodeBencode(String bencodedString) {

        Bencode parser = new Bencode(bencodedString);
        return parser.decodeNext();
    }
}
