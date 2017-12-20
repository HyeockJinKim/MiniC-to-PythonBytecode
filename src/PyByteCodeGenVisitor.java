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
import com.sun.istack.internal.NotNull;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.*;

public class PyByteCodeGenVisitor implements ASTVisitor {


    @Override
    public void visitProgram(Program node) {

    }

    @Override
    public void visitDecl(Declaration node) {

    }

    @Override
    public void visitVar_decl(Variable_Declaration node) {

    }

    @Override
    public void visitVar_decl_array(Variable_Declaration_Array node) {

    }

    @Override
    public void visitVar_decl_assign(Variable_Declaration_Assign node) {

    }

    @Override
    public void visitType_spec(TypeSpecification node) {

    }

    @Override
    public void visitFun_decl(Function_Declaration node) {

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
}
