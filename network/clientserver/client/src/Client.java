/**
 * Created by kate on 06.01.15.
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client implements Runnable {

    private final Logger logger;
    public static final int RESPONSE_COUNT = 1;
    long begin, end;


    public Client(Logger logger) throws IOException {
        this.logger = logger;
        begin = 0;
        end = 0;
    }


    public void readOneResponse(SocketChannel channel) throws IOException {
        boolean allRead = false;
        ByteBuffer bufferA = ByteBuffer.allocate(12);
        int count = 0;
        DataBuffer dataBuffer = new DataBuffer();

        while (!allRead) {
            count = channel.read(bufferA);
            dataBuffer.addByteBuffer(bufferA, count);

            if (count == 12) {
                allRead = true;
                end = System.currentTimeMillis();
                int rez = (int) (end - begin);
//                System.out.println(String.valueOf(rez));
                logger.write(rez);


//                System.out.println("best pair is number = " + dataBuffer.getInt() + " and number = " + dataBuffer.getInt() +
//                        " with compability = " + dataBuffer.getInt() + "%");
                dataBuffer.currentLength = 0;
            }
        }

    }

    public void run() {
        try {
            int port = 4444;
            SocketChannel channel = SocketChannel.open();
            long time = 0;

            channel.configureBlocking(false);
            channel.connect(new InetSocketAddress("192.168.1.51", port));

            while (!channel.finishConnect()) {
                // System.out.println("still connecting");
            }


            //send data
            DataPreparation dataPreparation = new DataPreparation();
            byte[] responseBuffer = dataPreparation.getDataBytes();

            begin = System.currentTimeMillis();

            ByteBuffer buffer = ByteBuffer.wrap(responseBuffer);
            while (buffer.hasRemaining()) {
                channel.write(buffer);
            }

            //receive data
            readOneResponse(channel);
        } catch (Exception ex) {
        }
    }


}
