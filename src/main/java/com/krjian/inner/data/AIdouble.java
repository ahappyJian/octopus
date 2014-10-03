package com.krjian.inner.data;

/**
 * Created by zhujian on 10/3/14.
 */
public class AIdouble implements AIType{
    private Double value = null;

    public AIdouble() {
        value = new Double(0);
    }

    public AIdouble(double val) {
        value = new Double(val);
    }

    public AIdouble(AIdouble val) {
        value = new Double(val.value);
    }

    @Override
    public AIint toInt() {
        throw new IllegalArgumentException("Can't cast double to int");
    }

    @Override
    public AIlong toLong() {
        throw new IllegalArgumentException("Can't cast double to long");
    }

    @Override
    public AIfloat toFloat() {
        throw new IllegalArgumentException("Can't cast double to float");
    }

    @Override
    public AIdouble toDouble() {
        return new AIdouble(this);
    }

    @Override
    public AIstring toAIString() throws Exception {
        return new AIstring(value.toString());
    }

    @Override
    public AIType plus(AIType bb, boolean called) throws Exception{
        try{
            AIdouble toValue = bb.toDouble();
            return new AIdouble(value + toValue.value);
        } catch (IllegalArgumentException e) {
            if(called == true){ throw new RuntimeException("unable to execute the operation+"); }
        }
        return bb.plus(this, true);
    }
    @Override
    public AIType minus(AIType bb, boolean called) throws Exception{
        try{
            AIdouble toValue = bb.toDouble();
            Double res = (called == false) ? (value - toValue.value) : (toValue.value - value);
            return new AIdouble(res);
        } catch (IllegalArgumentException e) {
            if(called == true){ throw new RuntimeException("unable to execute the operation-"); }
        }
        return bb.minus(this, true);
    }
    @Override
    public AIType multiple(AIType bb, boolean called) throws Exception{
        try{
            AIdouble toValue = bb.toDouble();
            return new AIdouble(value * toValue.value);
        } catch (IllegalArgumentException e) {
            if(called == true){ throw new RuntimeException("unable to execute the operation*"); }
        }
        return bb.multiple(this, true);
    }
    @Override
    public AIType divide(AIType bb, boolean called) throws Exception{
        try{
            AIdouble toValue = bb.toDouble();
            Double res = (called == false) ? (value/toValue.value) : (toValue.value/value);
            return new AIdouble(res);
        } catch (IllegalArgumentException e) {
            if(called == true){ throw new RuntimeException("unable to execute the operation/"); }
        }
        return bb.divide(this, true);
    }
    @Override
    public AIType concat(AIType bb, boolean called) throws Exception{
        return new AIstring(this.toAIString().toAIString() + bb.toAIString().toString());
    }
}