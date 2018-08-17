public interface NodeVisitor {
    void visitBinOp(BinOpNode binOpNode);

    void visitUnaryOp(UnaryOpNode unaryOpNode);


    void visitUndo(MisOpNode misOpNode);

    void visitClear(MisOpNode misOpNode);

    void visitLiteral(LiteralNode numberASTNode);
}
