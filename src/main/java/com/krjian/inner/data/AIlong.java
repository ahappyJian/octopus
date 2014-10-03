package com.krjian.inner.data;

import java.util.IllegalFormatException;

/**
 * Created by zhujian on 10/3/14.
 */
public class AIlong implements AIType{
    private Long value = null;

    public AIlong(){
        value = new Long(0);
    }
    public AIlong(long val){
        value = new Long(val);
    }
    public AIlong(AIlong val){
        value = new Long(val.value);
    }

    @Override
    public AIint toInt() {
        throw new IllegalArgumentException("Can't cast long to int");
    }
    @Override
    public AIlong toLong(){
        return new AIlong(this);
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
            AIlong toValue = bb.toLong();
            return new AIlong(value + toValue.value);
        } catch (IllegalArgumentException e) {
            if(called == true){ throw new RuntimeException("unable to execute the operation+"); }
        }
        return bb.plus(this, true);
    }
    @Override
    public AIType minus(AIType bb, boolean called) throws Exception{
        try{
            AIlong toValue = bb.toLong();
            Long res = (called == false) ? (value - toValue.value) : (toValue.value - value);
            return new AIlong(res);
        } catch (IllegalArgumentException e) {
            if(called == true){ throw new RuntimeException("unable to execute the operation-"); }
        }
        return bb.minus(this, true);
    }
    @Override
    public AIType multiple(AIType bb, boolean called) throws Exception{
        try{
            AIlong toValue = bb.toLong();
            return new AIlong(value * toValue.value);
        } catch (IllegalArgumentException e) {
            if(called == true){ throw new RuntimeException("unable to execute the operation*"); }
        }
        return bb.multiple(this, true);
    }
    @Override
    public AIType divide(AIType bb, boolean called) throws Exception{
        try{
            AIlong toValue = bb.toLong();
            Long res = (called == false) ? (value/toValue.value) : (toValue.value/value);
            return new AIlong(res);
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
