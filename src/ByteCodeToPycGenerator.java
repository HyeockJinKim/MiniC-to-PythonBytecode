import javax.json.JsonObject;
import java.util.concurrent.TimeUnit;

public class ByteCodeToPycGenerator {
    public void compile(Code code) {
        JsonObject jsonCode = convertJsonObject(code);
        sendToPythonCode(jsonCode);
    }

    private JsonObject convertJsonObject(Code code) {
        return null;
    }

    private void sendToPythonCode(JsonObject object) {
        String path = "python ./src/PycGenerator.py ";
        String param = object.toString();
        Process ps = null;

        try {
            ps = Runtime.getRuntime().exec(path + param);

            if (!ps.waitFor(3, TimeUnit.MINUTES)) {
                ps.destroyForcibly();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            if (ps != null) {
                ps.destroy();
            }

        }
    }
}
