package hackerrank.non_divisible_subset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/non-divisible-subset
 */
public final class Solution {
    private Solution() {
    }

    private static Reader inOverride = null;
    private static Writer outOverride = null;

    public static int[][] prepare(final Reader override) throws IOException {
        Reader reader = override == null ? new InputStreamReader(System.in, "UTF-8") : override;
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<int[]> prepared = new LinkedList<>();
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            int[] entry = Arrays.stream(line.strip().split(" ")).mapToInt(Integer::parseInt).toArray();
            prepared.add(entry);
        }
        bufferedReader.close();
        return prepared.toArray(new int[0][]);
    }

    public static void main(final String[] args) {
        if (null == inOverride) {
            inOverride = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        }
        if (null == outOverride) {
            outOverride = new OutputStreamWriter(System.out, StandardCharsets.UTF_8);
        }
        try {
            int[][] input = prepare(inOverride);
            int n = input[0][0];
            int k = input[0][1];
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = input[1][i];
            }
            outOverride.write(String.valueOf(SolutionCore.countMaximumSubset(a, k)));
            outOverride.write('\n');
            outOverride.flush();
            outOverride.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
