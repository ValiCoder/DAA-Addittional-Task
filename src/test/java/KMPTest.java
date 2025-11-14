import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KMPTest {

    @Test
    void testShortString() {
        String text = "abcxabcdabcdabcy";
        String pattern = "abcdabcy";
        int result = KMP.search(text, pattern);

        assertEquals(8, result);
    }

    @Test
    void testMediumString() {
        String text = "aaaaaaaaaaaaabaaaaaaabaaaabaa";
        String pattern = "aaabaa";
        int result = KMP.search(text, pattern);

        assertEquals(10, result);
    }

    @Test
    void testLongString() {
        String text = "abacabadabacabaeabacabadabacabafabacabadabacaba";
        String pattern = "abacabadabacaba";
        int result = KMP.search(text, pattern);

        assertEquals(0, result);
    }


    @Test
    void testNoMatch() {
        String text = "abcdefg";
        String pattern = "xyz";
        assertEquals(-1, KMP.search(text, pattern));
    }

    @Test
    void testPatternAtEnd() {
        String text = "hellohellotest";
        String pattern = "test";
        assertEquals(10, KMP.search(text, pattern));
    }

    @Test
    void testRepeatedChars() {
        String text = "aaaaaaaab";
        String pattern = "aaab";
        assertEquals(5, KMP.search(text, pattern));
    }

    @Test
    void testFullMatch() {
        String text = "abcabc";
        String pattern = "abcabc";
        assertEquals(0, KMP.search(text, pattern));
    }
    @Test
    void testKmpPerformance() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5_000_000; i++) {
            sb.append("a");
        }
        sb.append("aaaaab");
        String text = sb.toString();

        String pattern = "aaaaab";

        long start = System.nanoTime();

        int result = KMP.search(text, pattern);

        long end = System.nanoTime();

        long durationNanos = end - start;

        double durationMillis = durationNanos / 1_000_000.0;

        System.out.println("KMP execution time: " + durationMillis + " ms");
        System.out.println("Pattern found at index: " + result);

        assertEquals(5_000_000, result);

        assertTrue(durationMillis < 100, "KMP took too long: " + durationMillis + " ms");
    }
    @Test
    void testKmpPerformanceNoMatch() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5_000_000; i++) {
            sb.append("a");
        }
        String text = sb.toString();

        String pattern = "aaaaab";

        long start = System.nanoTime();

        int result = KMP.search(text, pattern);

        long end = System.nanoTime();

        long durationNanos = end - start;

        double durationMillis = durationNanos / 1_000_000.0;

        System.out.println("KMP execution time (no match): " + durationMillis + " ms");

        assertEquals(-1, result);

        assertTrue(durationMillis < 100, "KMP took too long: " + durationMillis + " ms");
    }
    @Test
    void testKmpPerformanceVariousScenarios() {
        StringBuilder sb1 = new StringBuilder();
        sb1.append("aaaaab");
        for (int i = 0; i < 5_000_000; i++) {
            sb1.append("a");
        }
        String text1 = sb1.toString();

        long start1 = System.nanoTime();

        int result1 = KMP.search(text1, "aaaaab");

        long end1 = System.nanoTime();

        double duration1 = (end1 - start1) / 1_000_000.0;

        System.out.println("Best case (pattern at start): " + duration1 + " ms");
        assertEquals(0, result1);

        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 5_000_000; i++) {
            sb2.append("a");
        }
        sb2.append("aaaaab");
        String text2 = sb2.toString();

        long start2 = System.nanoTime();

        int result2 = KMP.search(text2, "aaaaab");

        long end2 = System.nanoTime();

        double duration2 = (end2 - start2) / 1_000_000.0;

        System.out.println("Worst case (pattern at end): " + duration2 + " ms");
        assertEquals(5_000_000, result2);

        StringBuilder sb3 = new StringBuilder();
        for (int i = 0; i < 5_000_000; i++) {
            sb3.append("a");
        }
        String text3 = sb3.toString();


        long start3 = System.nanoTime();

        int result3 = KMP.search(text3, "aaaaab");

        long end3 = System.nanoTime();

        double duration3 = (end3 - start3) / 1_000_000.0;

        System.out.println("No match: " + duration3 + " ms");
        assertEquals(-1, result3);

        assertTrue(duration1 < 50, "Best case too slow");
        assertTrue(duration2 < 100, "Worst case too slow");
        assertTrue(duration3 < 100, "No match case too slow");
    }
}
