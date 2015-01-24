/**
 * Created by kate on 06.01.15.
 */

import javafx.util.Pair;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {
    private final int port = 4444;
    String localhost = "localhost";
    private final ServerData serverData;
    private ByteBuffer buffer = ByteBuffer.allocate(256);
    private ExecutorService executorService = Executors.newFixedThreadPool(4);
    private HashMap<SelectionKey, DataPerClient> clientsData = new HashMap<>();


    public Server() {
        serverData = new ServerData();
    }

    private int factorial(int n) {
        if ((n == 0) || (n == 1)) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public void stop() {
        executorService.shutdown();
    }

    public void run() throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress("192.168.1.33", port));
        channel.configureBlocking(false);
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Listening on port " + port);

        while (true) {

            if (selector.select() == 0) {
                continue;
            }

            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey selectionKey = it.next();
                it.remove();

                if (!selectionKey.isValid()) {
                    continue;
                }

                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel clientSocketChannel = serverSocketChannel.accept();
                    if (clientSocketChannel != null) {
                        clientSocketChannel.configureBlocking(false);
                        clientSocketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
                        clientSocketChannel.register(selector, SelectionKey.OP_READ, SelectionKey.OP_WRITE);
                        System.out.println("Client is connected");
                    }

                } else if (selectionKey.isReadable()) {

                    if (!clientsData.containsKey(selectionKey)) {
                        DataPerClient dataPerClient = new DataPerClient();
                        clientsData.put(selectionKey, dataPerClient);
                    }

                    final SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
                    int len = clientChannel.read(buffer);


                    // If no data, close the connection
                    if ((len == 0) || (len < 4)) {
//                        System.out.println("here");
//                        continue;
                        channel.close();
                    }


                    buffer.clear();
                    clientsData.put(selectionKey, clientsData.get(selectionKey).addByteBuffer(buffer, len));


                    while (true) {
                        ByteBuffer current = clientsData.get(selectionKey).bufferData;
                        current.clear();

                        int size = current.getInt();

                        int neededBytes = Integer.SIZE / 8 + size * (Integer.SIZE * 3 / 8);
                        if ((size < 8) || (clientsData.get(selectionKey).currentLength < neededBytes)) {
                            break;
                        }

                        clientsData.get(selectionKey).cutByteBuffer(neededBytes);

                        executorService.execute(() -> {
                            ArrayList<Person> dataMale = new ArrayList<>();
                            ArrayList<Person> dataFemale = new ArrayList<>();


                            for (int i = 1; i < size * 3; i += 3) {
                                Person person = new Person(current.getInt(), current.getInt(), current.getInt());
                                if (person.getMale() == Person.MALE) {
                                    dataMale.add(person);
                                } else {
                                    dataFemale.add(person);
                                }
                            }

//                            System.out.println("malefemale = " + dataMale.size() + " " + dataFemale.size());

                            if ((dataFemale.size() == 0) || (dataMale.size() == 0)) {
                                System.out.println("bad");
//                            break;
                            }

                            Pair<Person, Person> bestCompatibilityPair = new Pair<>(null, null);
                            int bestCompability = 0;
                            //find best pair
                            for (Person male : dataMale) {
                                for (Person female : dataFemale) {
                                    int compatibility = serverData.getCompatibility()[male.getMonth()][female.getMonth()];
//                                    System.out.println("compability = " + factorial(compatibility));
                                    if (compatibility > bestCompability) {
                                        bestCompability = compatibility;
                                        bestCompatibilityPair = new Pair<>(male, female);
                                    }
                                }
                            }

//                            System.out.println(bestCompatibilityPair.getKey().getName() + " " + bestCompatibilityPair.getValue().getName() + " " + bestCompability);

                            ByteBuffer responseBuffer = ByteBuffer.allocate(12);
                            responseBuffer.putInt(bestCompatibilityPair.getKey().getName());
                            responseBuffer.putInt(bestCompatibilityPair.getValue().getName());
                            responseBuffer.putInt(bestCompability);

                            responseBuffer.flip();
                            while (responseBuffer.hasRemaining()) {
                                try {
                                    clientChannel.write(responseBuffer);
                                } catch (IOException e) {
//                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
            server.stop();
        }
    }
}
