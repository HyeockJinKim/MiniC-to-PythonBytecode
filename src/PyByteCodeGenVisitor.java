import Domain.ASTVisitor;
import Domain.Args.Arguments;
import Domain.Decl.*;
import Domain.Expr.*;
import Domain.Param.ArrayParameter;
import Domain.Param.Parameter;
import Domain.Param.Parameters;
import Domain.Program;
import Domain.Stmt.*;
import Domain.Type_spec.TypeSpecification;

public class PyByteCodeGenVisitor implements ASTVisitor {
    private Code pycCode;
    private Code currentCode;
    private int lineNum;

    @Override
    public void visitProgram(Program node) {
        pycCode = new Code();
        lineNum = 1;
        if (node.decls == null) {
            System.out.println("No data! : visitProgram");
            return ;
        }
        for (Declaration declaration : node.decls) {
            declaration.accept(this);
        }
        // TODO : 파이썬으로의 전달 부분

        System.out.println(pycCode.getCode().toString());
    }

    @Override
    public void visitDecl(Declaration node) {}

    @Override
    public void visitVar_decl(Variable_Declaration node) {
        if (!pycCode.addNames(node.lhs.getText())) {
            System.out.println("This value is already exist : visitVar_decl");
            return ;
        }
        // TODO : Global 변수를 0으로 지정해놓을지
    }

    @Override
    public void visitVar_decl_array(Variable_Declaration_Array node) {
        String variableName = node.lhs.getText();
        if (!pycCode.addNames(variableName)) {
            System.out.println("This value is already exist : visitVar_decl_array");
            return ;
        }
        int literal = literalNumber(node.rhs.getText());
        if (literal <= 0) {
            System.out.println("This array number is le than 0 : visitVar_decl_array");
            return ;
        }

        pycCode.addConst(0);
        for (int i = 0; i < literal; ++i) {
            pycCode.appendCode(OpCode.LOAD_CONST.getHexCode());
            pycCode.appendCode(pycCode.indexOfConst(0));
            pycCode.appendCode(OpCode.STOP_CODE.getHexCode());
        }

        pycCode.appendCode(OpCode.BUILD_LIST.getHexCode());
        pycCode.appendCode(String.format("%02x", literal));
        pycCode.appendCode(OpCode.STOP_CODE.getHexCode());

        pycCode.appendCode(OpCode.STORE_NAME.getHexCode());
        pycCode.appendCode(pycCode.indexOfNames(variableName));
        pycCode.appendCode(OpCode.STOP_CODE.getHexCode());

    }

    @Override
    public void visitVar_decl_assign(Variable_Declaration_Assign node) {
        String variableName = node.lhs.getText();
        if (!pycCode.addNames(variableName)) {
            System.out.println("This value is already exist : visitVar_decl_assign");
            return ;
        }
        int literal = literalNumber(node.rhs.getText());

        pycCode.addConst(literal);
        pycCode.appendCode(OpCode.LOAD_CONST.getHexCode());
        pycCode.appendCode(pycCode.indexOfConst(literal));
        pycCode.appendCode(OpCode.STOP_CODE.getHexCode());

        pycCode.appendCode(OpCode.STORE_NAME.getHexCode());
        pycCode.appendCode(pycCode.indexOfNames(variableName));
        pycCode.appendCode(OpCode.STOP_CODE.getHexCode());

    }

    @Override
    public void visitType_spec(TypeSpecification node) {}

    @Override
    public void visitFun_decl(Function_Declaration node) {
        String functionName = node.t_node.getText();
        if (!pycCode.addNames(functionName)) {
            System.out.println("Function name is already exist : visitFun_decl");
            return ;
        }
        currentCode = new Code();

        node.params.accept(this);
        node.compount_stmt.accept(this);

        pycCode.addConst(currentCode);

        pycCode.appendCode(OpCode.LOAD_CONST.getHexCode());
        pycCode.appendCode(pycCode.indexOfConst(currentCode));
        pycCode.appendCode(OpCode.STOP_CODE.getHexCode());

        pycCode.appendCode(OpCode.MAKE_FUNCTION.getHexCode());
        pycCode.appendCode(OpCode.STOP_CODE.getHexCode());
        pycCode.appendCode(OpCode.STOP_CODE.getHexCode());

        pycCode.appendCode(OpCode.STORE_NAME.getHexCode());
        pycCode.appendCode(pycCode.indexOfNames(functionName));
        pycCode.appendCode(OpCode.STOP_CODE.getHexCode());
    }

    @Override
    public void visitParam_array(ArrayParameter node) {

    }

    @Override
    public void visitParams(Parameters node) {

    }

    @Override
    public void visitParam(Parameter node) {

    }

    @Override
    public void visitStmt(Statement node) {

    }

    @Override
    public void visitExpr_stmt(Expression_Statement node) {

    }

    @Override
    public void visitWhile_stmt(While_Statement node) {

    }

    @Override
    public void visitCompound_stmt(Compound_Statement node) {

    }

    @Override
    public void visitLocal_decl(Local_Declaration node) {

    }

    @Override
    public void visitLocal_decl_array(Local_Variable_Declaration_Array node) {

    }

    @Override
    public void visitLocal_decl_assign(Local_Variable_Declaration_Assign node) {

    }

    @Override
    public void visitIf_stmt(If_Statement node) {

    }

    @Override
    public void visitReturn_stmt(Return_Statement node) {

    }

    @Override
    public void visitExpr(Expression node) {

    }

    @Override
    public void visitAref_assign(ArefAssignNode node) {

    }

    @Override
    public void visitAref(ArefNode node) {

    }

    @Override
    public void visitAssign(AssignNode node) {

    }

    @Override
    public void visitBinary_op(BinaryOpNode node) {

    }

    @Override
    public void visitFun_call(FuncallNode node) {

    }

    @Override
    public void visitParen(ParenExpression node) {

    }

    @Override
    public void visitTerminal(TerminalExpression node) {

    }

    @Override
    public void visitUnary_op(UnaryOpNode node) {

    }

    @Override
    public void visitArgs(Arguments node) {

    }



    private int literalNumber(String number) {
        if (number.length() > 2) {
            if (number.charAt(1) == 'x' || number.charAt(1) == 'X') {
                return Integer.parseInt(number.substring(2, number.length()), 16);
            }
        }
        if (number.length() > 1) {
            if (number.charAt(0) == '0') {
                return Integer.parseInt(number, 8);
            }
        }
        return Integer.parseInt(number);
    }


}
