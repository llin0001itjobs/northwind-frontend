package org.llin.demo.northwind.cache;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AsyncAPICalls {
    private static final ExecutorService executor = Executors.newFixedThreadPool(10); // You can adjust the pool size as needed

    public static void main(String[] args) {
        // List of unique URLs for API calls
        String[] urls = { "https://api.example.com/endpoint1", "https://api.example.com/endpoint2", /* Add more URLs */ };

        for (String url : urls) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                // Make API call using Caffeine or any other HTTP client library
                // Example using Caffeine:
                // Caffeine.cachingHttpClient().get(url);
                System.out.println("Calling URL: " + url);
            }, executor);

            // Handle completion or errors if needed
            future.thenAccept((result) -> System.out.println("Completed URL: " + url))
                    .exceptionally((ex) -> {
                        System.err.println("Error calling URL: " + url + ", " + ex.getMessage());
                        return null;
                    });
        }

        // Shutdown executor after all tasks are completed
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            System.err.println("Executor interrupted while awaiting termination.");
            Thread.currentThread().interrupt();
        }
    }
}