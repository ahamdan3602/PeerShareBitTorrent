import java.util.*;

public class BencodeTest {
    public static void main(String[] args) {
        decodesString();
        decodesInteger();
        decodesListWithStringsAndIntegers();
        decodesNestedList();

        System.out.println("All Bencode tests passed.");
    }

    private static void decodesString() {
        Object decoded = Bencode.decodeBencode("5:hello");

        assertEquals("hello", decoded, "decode string");
    }

    private static void decodesInteger() {
        Object decoded = Bencode.decodeBencode("i52e");

        assertEquals(52, decoded, "decode integer");
    }

    private static void decodesListWithStringsAndIntegers() {
        Object decoded = Bencode.decodeBencode("l5:helloi52ee");

        List<Object> expected = new ArrayList<>();
        expected.add("hello");
        expected.add(52);

        assertEquals(expected, decoded, "decode list with string and integer");
    }

    private static void decodesNestedList() {
        Object decoded = Bencode.decodeBencode("ll5:helloi52eee");

        List<Object> inner = new ArrayList<>();
        inner.add("hello");
        inner.add(52);

        List<Object> expected = new ArrayList<>();
        expected.add(inner);

        assertEquals(expected, decoded, "decode nested list");
    }

    private static void assertEquals(Object expected, Object actual, String testName) {
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError(
                    testName + " failed. Expected: " + expected + ", actual: " + actual);
        }
    }
}
