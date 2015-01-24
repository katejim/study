import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by kate on 22.01.15.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("input should contains count of clients and attempt");
            return;
        }


        final ExecutorService executorService = Executors.newFixedThreadPool(8);
        final int clientCount = Integer.valueOf(args[0]);
        final String attempt = args[1];

        try (final Logger logger = new Logger(String.valueOf(clientCount) + attempt +".txt")) {
            ArrayList<Thread> tasks = new ArrayList<>();
            for (int i = 0; i < clientCount; i++) {
                Thread thread = new Thread(new Client(logger));
                tasks.add(thread);
            }
//            long begin = System.currentTimeMillis();
            for (Thread thread : tasks) {
                thread.start();
            }

            for (Thread thread : tasks) {
                thread.join();
            }
//            long end = System.currentTimeMillis();
//            System.out.println((end-begin) + " " + (end-begin)/clientCount);
//            logger.write((end-begin)/clientCount);
//            List<Future<?>> clientFutures = new LinkedList<>();
//            for (int i = 0; i < clientCount; i++) {
//                Client client = new Client(logger);
//                clientFutures.add(executorService.submit(client));
//            }
//
//            for (Future<?> future: clientFutures) {
//                future.get();
//            }
//            executorService.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
