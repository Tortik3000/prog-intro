package expression;

public interface Priority extends BigIntegerExpression, TripleExpression, Expression{
    int getPriority();

    boolean isSim();
}
