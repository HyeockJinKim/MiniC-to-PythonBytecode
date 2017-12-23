import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ByteCodeToPycGenerator {
    public void compile(Code code) {
        JsonObject jsonCode = convertJsonObject(code);
        sendToPythonCode(jsonCode);
    }

    private JsonObject convertJsonObject(Code code) {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        jsonObjectBuilder.add("\"argCount\"", code.getArgCount());
        jsonObjectBuilder.add("\"nLocals\"", code.getNLocals());
        jsonObjectBuilder.add("\"stackSize\"", code.getStackSize());
        jsonObjectBuilder.add("\"flags\"", code.getFlags());
        jsonObjectBuilder.add("\"code\"", '\"'+code.getCode().toString()+'\"');

        ArrayList constArray = code.getMyConst();
        for (Object cons : constArray) {
            if (cons instanceof Code)
                jsonArrayBuilder.add(convertJsonObject((Code) cons));
            else if (cons instanceof String)
                jsonArrayBuilder.add('\"'+(String) cons+'\"');
            else
                jsonArrayBuilder.add((int) cons);
        }
        jsonObjectBuilder.add("\"myConst\"", jsonArrayBuilder.build());

        jsonArrayBuilder = Json.createArrayBuilder();
        ArrayList nameArray = code.getNames();
        for (Object name : nameArray)
            jsonArrayBuilder.add('\"'+(String) name+'\"');
        jsonObjectBuilder.add("\"names\"", jsonArrayBuilder.build());

        jsonArrayBuilder = Json.createArrayBuilder();
        ArrayList varNameArray = code.getVarNames();
        for (Object name : varNameArray)
            jsonArrayBuilder.add('\"'+(String) name+'\"');
        jsonObjectBuilder.add("\"varNames\"", jsonArrayBuilder.build());

        jsonObjectBuilder.add("\"fileName\"", '\"'+code.getFileName()+'\"');
        jsonObjectBuilder.add("\"name\"", '\"'+code.getName()+'\"');
        jsonObjectBuilder.add("\"firstLineNumber\"", code.getFirstLineNumber());
        jsonObjectBuilder.add("\"lNoTab\"", '\"'+code.getlNoTab()+'\"');
        return jsonObjectBuilder.build();
    }

    private void sendToPythonCode(JsonObject object) {
        String path = "./src/PycGenerator.py";
        String param = object.toString();
        String result = "";
        Process ps = null;
        BufferedReader br;
        List<String> process_args = new ArrayList<>(Arrays.asList("python", path, param));

        try {
            ps = Runtime.getRuntime().exec(process_args.toArray(new String[]{}));
            br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            if (!ps.waitFor(3, TimeUnit.MINUTES)) {
                ps.destroyForcibly();
            }
            String tmpStr;
            while ((tmpStr = br.readLine()) != null) {
                result = result + tmpStr + "\n";
            }
            System.out.println(result);
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            if (ps != null) {
                ps.destroy();
            }

        }
    }
}

// V5eXJleWR0Q0RycVkzc3NxM3NuYlFoSVNFaA==N0tHdzZyV1FJT3VwamV5eXJleWR0T3Vwam
