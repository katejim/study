import javax.lang.model.element.NestingKind;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by kate on 22.01.15.
 */
public class Logger implements AutoCloseable {

    private final BufferedWriter bw;

    public Logger(String filePath) throws IOException {
        bw = new BufferedWriter(new FileWriter(filePath));
    }

    public void write(int value) throws IOException {
        bw.write(String.valueOf(value) + "\n");
    }

    public void close() throws IOException {
        bw.close();
    }
}
