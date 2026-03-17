package br.com.aal.java21.virtualtheads.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.StructuredTaskScope;

@Service
public class ProductService {

    public ProductView getProduct(String id) {
        //try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {

            StructuredTaskScope.Subtask<Product> product =
                    //executor.submit(
                    scope.fork(() -> fetchProduct(id));

            StructuredTaskScope.Subtask<Inventory> inventory =
                    //executor.submit(
                    scope.fork(() -> fetchInventory(id));

            StructuredTaskScope.Subtask<List<Review>> reviews =
                    //executor.submit(
                    scope.fork(() -> fetchReviews(id));

            scope.join();
            scope.throwIfFailed();

            return new ProductView(
                    product.get(),
                    inventory.get(),
                    reviews.get()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Product fetchProduct(String id) throws Exception {
        Thread.sleep(200);
        return new Product(id, "Laptop");
    }

    private Inventory fetchInventory(String id) throws Exception {
        Thread.sleep(300);
        return new Inventory(id, 43);
    }

    private List<Review> fetchReviews(String id) throws Exception {
        Thread.sleep(250);
        return List.of(new Review("Great product"));
    }
}
