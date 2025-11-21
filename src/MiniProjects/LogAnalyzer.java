package MiniProjects;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class LogAnalyzer {
    private static final int THREAD_POOL_SIZE = 4;
    private static final String[] KEYWORDS = {"ERROR", "WARN", "INFO", "DEBUG"};

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java LogAnalyzer <folder_path>");
            return;
        }
        String folderPath = args[0];
        Path path = Paths.get(folderPath);
        if (!Files.isDirectory(path)) {
            System.out.println("Invalid folder path.");
            return;
        }

        try {
            List<Path> logFiles = Files.list(path)
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".txt"))
                    .collect(Collectors.toList());

            if (logFiles.isEmpty()) {
                System.out.println("No log files found.");
                return;
            }

            long startTime = System.nanoTime();
            ConcurrentHashMap<String, Integer> concurrentResults = analyzeConcurrently(logFiles);
            long concurrentTime = System.nanoTime() - startTime;

            startTime = System.nanoTime();
            Map<String, Integer> sequentialResults = analyzeSequentially(logFiles);
            long sequentialTime = System.nanoTime() - startTime;

            outputResults(concurrentResults, concurrentTime, sequentialTime);

        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static ConcurrentHashMap<String, Integer> analyzeConcurrently(List<Path> logFiles) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        List<Future<Map<String, Integer>>> futures = new ArrayList<>();

        for (Path file : logFiles) {
            futures.add(executor.submit(new LogAnalyzerTask(file)));
        }

        ConcurrentHashMap<String, Integer> aggregatedCounts = new ConcurrentHashMap<>();
        for (Future<Map<String, Integer>> future : futures) {
            Map<String, Integer> result = future.get();
            result.forEach((key, value) -> aggregatedCounts.merge(key, value, Integer::sum));
        }

        executor.shutdown();
        return aggregatedCounts;
    }

    private static Map<String, Integer> analyzeSequentially(List<Path> logFiles) throws IOException {
        Map<String, Integer> aggregatedCounts = new HashMap<>();
        for (Path file : logFiles) {
            Map<String, Integer> result = analyzeFile(file);
            result.forEach((key, value) -> aggregatedCounts.merge(key, value, Integer::sum));
        }
        return aggregatedCounts;
    }

    private static Map<String, Integer> analyzeFile(Path file) throws IOException {
        Map<String, Integer> counts = new HashMap<>();
        List<String> lines = Files.readAllLines(file);
        for (String line : lines) {
            String[] words = line.split("\\s+");
            for (String word : words) {
                if (Arrays.asList(KEYWORDS).contains(word.toUpperCase())) {
                    counts.merge(word.toUpperCase(), 1, Integer::sum);
                }
            }
        }
        return counts;
    }

    private static void outputResults(ConcurrentHashMap<String, Integer> results, long concurrentTime, long sequentialTime) {
        System.out.println("Concurrent Execution Time: " + concurrentTime / 1_000_000 + " ms");
        System.out.println("Sequential Execution Time: " + sequentialTime / 1_000_000 + " ms");
        System.out.println("Keyword Counts:");
        results.forEach((key, value) -> System.out.println(key + ": " + value));

        try (PrintWriter writer = new PrintWriter(new FileWriter("analysis_results.txt"))) {
            writer.println("Concurrent Execution Time: " + concurrentTime / 1_000_000 + " ms");
            writer.println("Sequential Execution Time: " + sequentialTime / 1_000_000 + " ms");
            writer.println("Keyword Counts:");
            results.forEach((key, value) -> writer.println(key + ": " + value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class LogAnalyzerTask implements Callable<Map<String, Integer>> {
        private final Path file;

        public LogAnalyzerTask(Path file) {
            this.file = file;
        }

        @Override
        public Map<String, Integer> call() throws Exception {
            return analyzeFile(file);
        }
    }
}

