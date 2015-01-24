import java.nio.ByteBuffer;

/**
 * Created by kate on 22.01.15.
 */
public class DataBuffer {
    public final static int MAX_SIZE = 12;
    private ByteBuffer bufferData;

    public int currentLength;

    public DataBuffer() {
        bufferData = ByteBuffer.allocate(MAX_SIZE);
        currentLength = 0;
    }

    //assume that we can't overflow bufferData
    public DataBuffer addByteBuffer(ByteBuffer newData, int lenToAdd) {
        for (int i = 0; i < lenToAdd; i++) {
            bufferData.put(currentLength, newData.get(i));
            currentLength++;
        }
        bufferData.clear();
        return this;
    }
}
