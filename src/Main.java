import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] patternCounts = {10, 100, 1000};
        int[] textLengths = {1000, 10000, 100000};
        int runs = 5;

        Random random = new Random(42);

        System.out.println("Patterns\tText Length\tAvg Time (ms)");
        System.out.println("----------------------------------------------");

        for (int pCount : patternCounts) {
            List<String> patterns = new ArrayList<>();
            for (int i = 0; i < pCount; i++) {
                StringBuilder sb = new StringBuilder();
                int len = 4 + random.nextInt(4);
                for (int j = 0; j < len; j++) {
                    sb.append((char) ('a' + random.nextInt(5)));
                }
                patterns.add(sb.toString());
            }

            for (int tLen : textLengths) {
                StringBuilder textSb = new StringBuilder();
                for (int i = 0; i < tLen; i++) {
                    textSb.append((char) ('a' + random.nextInt(5)));
                }
                String text = textSb.toString();

                long totalTimeNano = 0;

                for (int run = 0; run < runs; run++) {
                    long start = System.nanoTime();

                    AhoCorasick ac = new AhoCorasick();
                    for (String pat : patterns) {
                        ac.insert(pat);
                    }
                    ac.buildFailureLinks();
                    ac.search(text);

                    long end = System.nanoTime();
                    totalTimeNano += (end - start);
                }

                double avgTimeMs = (totalTimeNano / (double) runs) / 1_000_000.0;
                System.out.printf("%d\t\t%d\t\t%.4f ms\n", pCount, tLen, avgTimeMs);
            }
        }
    }
}