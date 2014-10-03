package com.krjian.inner.data;

/**
 * Created by zhujian on 10/3/14.
 */
public class AIint implements AIType{
    private Integer value = null;

    public AIint(){
        value = new Integer(0);
    }
    public AIint(int val){
        value = new Integer(val);
    }
    public AIint(AIint val){
        value = new Integer(val.value);
    }

    @Override
    public AIint toInt() {
        return new AIint(this);
    }
    @Override
    public AIlong toLong(){
        return new AIlong(value.longValue());
    }
    @Override
    public AIfloat toFloat(){
        return new AIfloat(value.floatValue());
    }
    @Override
    public AIdouble toDouble(){
        return new AIdouble(value.doubleValue());
    }

    @Override
    public AIstring toAIString() throws Exception {
        return new AIstring(value.toString());
    }

    @Override
    public AIType plus(AIType bb, boolean called) throws Exception{
        try{
            AIint toValue = bb.toInt();
            return new AIint(value + toValue.value);
        } catch (IllegalArgumentException e) {
            if(called == true){ throw new RuntimeException("unable to execute the operation+"); }
        }
        return bb.plus(this, true);
    }
    @Override
    public AIType minus(AIType bb, boolean called) throws Exception{
        try{
            AIint toValue = bb.toInt();
            Integer res = (called == false) ? (value - toValue.value) : (toValue.value - value);
            return new AIint(res);
        } catch (IllegalArgumentException e) {
            if(called == true){ throw new RuntimeException("unable to execute the operation-"); }
        }
        return bb.minus(this, true);
    }
    @Override
    public AIType multiple(AIType bb, boolean called) throws Exception{
        try{
            AIint toValue = bb.toInt();
            return new AIint(value * toValue.value);
        } catch (IllegalArgumentException e) {
            if(called == true){ throw new RuntimeException("unable to execute the operation*"); }
        }
        return bb.multiple(this, true);
    }
    @Override
    public AIType divide(AIType bb, boolean called) throws Exception{
        try{
            AIint toValue = bb.toInt();
            Integer res = (called == false) ? (value/toValue.value) : (toValue.value/value);
            return new AIint(res);
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
