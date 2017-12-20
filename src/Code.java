import java.util.ArrayList;

// flags 가 67이면 global 에 만들어진 함수, 87? 이면 def안에 def, default 로 67로 지정하는게 좋을듯.
/**
 * argCount : parameter 수
 * nLocals : 함수내의 지역변수와 parameter 의 수를 합친 것
 * stackSize : if 나 while 이 있냐 없냐의 차이, 있을 경우 2 없을 경우 1
 * flags : 이 코드가 무엇인지를 알려주는 듯.
 * code : hex 값으로 저장된 실행할 코드 hex 를 String 으로 저장.
 * myConst : 내부의 상수 값의 List
 * names : global 변수, 함수 이름
 * varNames : 지역변수 이름
 * fileName : 파일 이름
 * name : 함수 이름, global 의 경우 '<module>'으로 저장.
 * firstLineNumber : 함수의 시작하는 부분
 * lNoTab : byte code 를 mapping 해주는 debug 용이라는데 잘 모르겠음.
 */
public class Code {
    private int argCount;
//    private int nLocals;  // varNames.size()로 대체 가능.
    private int stackSize; // global 은 1, 함수는 2로 무조건 가능 (def 안의 def가 없어서)
    private int flags;
    private String code;
    private ArrayList<Object> myConst;
    private ArrayList<String> names;
    private ArrayList<String> varNames;
    private String fileName;
    private String name;
    private int firstLineNumber;
    private int lNoTab;

    public Code() {
        myConst = new ArrayList<>();
        names = new ArrayList<>();
        varNames = new ArrayList<>();
    }

    public boolean isContainConst(Object thisConst) {
        return myConst.contains(thisConst);
    }

    public boolean isContainNames(String name) {
        return names.contains(name);
    }

    public boolean isContainVarNames(String varName) {
        return varNames.contains(varName);
    }

    public int getArgCount() {
        return argCount;
    }

    public void setArgCount(int argCount) {
        this.argCount = argCount;
    }

    public int getNLocals() {
        return varNames.size();
    }

//    public void setNLocals(int nLocals) {
//        this.nLocals = nLocals;
//    }

    public int getStackSize() {
        return stackSize;
    }

    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Object> getMyConst() {
        return myConst;
    }

    public void addConst(Object myConst) {
        this.myConst.add(myConst);
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void addNames(String name) {
        this.names.add(name);
    }

    public ArrayList<String> getVarNames() {
        return varNames;
    }

    public void addVarNames(String varNames) {
        this.varNames.add(varNames);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFirstLineNumber() {
        return firstLineNumber;
    }

    public void setFirstLineNumber(int firstLineNumber) {
        this.firstLineNumber = firstLineNumber;
    }

    public int getlNoTab() {
        return lNoTab;
    }

    public void setlNoTab(int lNoTab) {
        this.lNoTab = lNoTab;
    }
}
