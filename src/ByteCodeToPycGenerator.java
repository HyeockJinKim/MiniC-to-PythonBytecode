import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ByteCodeToPycGenerator {
    public void compile(Code code) {
        JsonObject jsonCode = convertJsonObject(code, 1);
        sendToPythonCode(jsonCode);
    }

    private JsonObject convertJsonObject(Code code, int stackSize) {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        jsonObjectBuilder.add("argCount", code.getArgCount());
        jsonObjectBuilder.add("nLocals", code.getVarNames().size());
        jsonObjectBuilder.add("stackSize", stackSize);
        jsonObjectBuilder.add("code", code.getCode().toString());

        ArrayList constArray = code.getMyConst();
        for (Object cons : constArray) {
            if (cons instanceof Code)
                jsonArrayBuilder.add(convertJsonObject(code, stackSize + 1));
            else
                jsonArrayBuilder.add((int) cons);
        }
        jsonObjectBuilder.add("myConst", jsonArrayBuilder.build());

        jsonArrayBuilder = Json.createArrayBuilder();
        ArrayList nameArray = code.getNames();
        for (Object name : nameArray)
            jsonArrayBuilder.add((String) name);
        jsonObjectBuilder.add("names", jsonArrayBuilder.build());

        jsonArrayBuilder = Json.createArrayBuilder();
        ArrayList varNameArray = code.getNames();
        for (Object name : varNameArray)
            jsonArrayBuilder.add((String) name);
        jsonObjectBuilder.add("varNames", jsonArrayBuilder.build());

        jsonObjectBuilder.add("fileName", code.getFileName());
        jsonObjectBuilder.add("name", code.getName());
        jsonObjectBuilder.add("firstLineNumber", code.getFirstLineNumber());
        jsonObjectBuilder.add("lNoTab", code.getlNoTab());
        return jsonObjectBuilder.build();
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
