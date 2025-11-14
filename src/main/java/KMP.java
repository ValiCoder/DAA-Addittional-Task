public class KMP {

    public static int search(String text, String pattern) {
        if (pattern.length() == 0) return 0;

        int[] LPS = buildLPS(pattern);
        int i = 0;
        int j = 0;

        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;

                if (j == pattern.length()) {
                    return i - j;
                }
            } else {
                if (j != 0) {
                    j = LPS[j - 1];
                } else {
                    i++;
                }
            }
        }
        return -1;
    }

    private static int[] buildLPS(String pattern) {
        int[] LPS = new int[pattern.length()];
        int length = 0;
        int i = 1;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                LPS[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = LPS[length - 1];
                } else {
                    LPS[i] = 0;
                    i++;
                }
            }
        }
        return LPS;
    }
}