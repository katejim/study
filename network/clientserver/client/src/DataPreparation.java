import javafx.util.Pair;

import java.nio.ByteBuffer;

/**
 * Created by kate on 06.01.15.
 */
public class DataPreparation {
    ClientData data = new ClientData();
    private final int WRITE_SIZE;

    public DataPreparation() {
        WRITE_SIZE = Integer.SIZE / 8 + data.getParams().size() * (Integer.SIZE * 3 / 8);

    }

    public byte[] getDataBytes(){
        byte[] result = new byte[WRITE_SIZE];
        int position = 0;
        byte[] size = getByteArray(data.getParams().size());
        for (int i = 0; i < size.length; i++){
            result[position] = size[i];
            position++;
        }
        for (ClientData.Person elem : data.getParams()){
            size  = getByteArray(elem.getMonth());
            for (int i = 0; i < size.length; i++){
                result[position] = size[i];
                position++;
            }
            size = getByteArray(elem.getMale());
            for (int i = 0; i < size.length; i++){
                result[position] = size[i];
                position++;
            }
            size = getByteArray(elem.getName());
            for (int i = 0; i < size.length; i++){
                result[position] = size[i];
                position++;
            }
        }
        return result;
    }

    private byte[] getByteArray(int value){
        return ByteBuffer.allocate(4).putInt(value).array();
    }
}
