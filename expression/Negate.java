package expression;

import java.math.BigInteger;

public class Negate extends AbstractUnaryExpression{

    public Negate(Priority expression){
        super(expression);
    }

    @Override
    public int evaluate(int x){
        return evaluate(x, "-");
    }

    @Override
    public int evaluate(int x, int y, int z){
        return evaluate(x, y, z, "-");
    }

    @Override
    public BigInteger evaluate(BigInteger x){
        throw new UnsupportedOperationException("Unsupported BigInteger");
    }
    @Override
    public String toString(){
        return toString("-");
    }

    @Override
    public String toMiniString(){
        return toMiniString("-");
    }
}

