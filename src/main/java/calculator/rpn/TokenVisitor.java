package calculator.rpn;

public interface TokenVisitor {

    void visit(BinaryOperatorToken token);

    void visit(FunctionToken token);

    void visit(UndoToken token);

    void visit(ClearToken token);

    void visit(NumberLiteralToken token);
}
