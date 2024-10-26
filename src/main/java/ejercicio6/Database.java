package ejercicio6;

import java.util.concurrent.ExecutionException;

public class Database {
    private final Promise promise;

    public Database(Promise promise) {
        this.promise = promise;
    }

    public void insert(String data) {
        promise.set(data);
    }

    public String select() throws ExecutionException, InterruptedException {
        return (String) promise.get();
    }
}
