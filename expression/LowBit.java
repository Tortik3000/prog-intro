package expression;

import java.math.BigInteger;

public class LowBit extends AbstractUnaryExpression {

    public LowBit(Priority expression){
        super(expression);
    }

    @Override
    public String toString(){
        return toString("t1");
    }

    @Override
    public int evaluate(int x){
        return evaluate(x, "t1");
    }

    @Override
    public int evaluate(int x, int y, int z){
        return evaluate(x, y, z, "t1");
    }

    @Override
    public BigInteger evaluate(BigInteger x){
        throw new UnsupportedOperationException("Unsupported BigInteger");
    }

    @Override
    public String toMiniString(){
        return toMiniString("t1");
    }
}
