import java.nio.ByteBuffer;

/**
 * Created by kate on 08.01.15.
 */
public class DataPerClient {
    public final static int MAX_SIZE = 512;
    public ByteBuffer bufferData;

    public void setCurrentLength(int currentLength) {
        this.currentLength = currentLength;
    }

    public int currentLength;

    public DataPerClient() {
        bufferData = ByteBuffer.allocate(MAX_SIZE);
        currentLength = 0;
    }

    //assume that we can't overflow bufferData
    public DataPerClient addByteBuffer(ByteBuffer newData, int lenToAdd) {
        for (int i = 0; i < lenToAdd; i++) {
            bufferData.put(currentLength, newData.get(i));
            currentLength++;
        }
        bufferData.clear();
        return this;
    }

    public DataPerClient cutByteBuffer(int len) {
        ByteBuffer newBuffer = ByteBuffer.allocate(MAX_SIZE);
        for (int i = len; i < currentLength; i++) {
            newBuffer.put(bufferData.get(i));
        }
        bufferData = newBuffer;
        bufferData.clear();
        currentLength -= len;
        return this;
    }
}
