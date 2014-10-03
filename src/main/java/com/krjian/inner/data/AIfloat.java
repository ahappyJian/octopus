package com.krjian.inner.data;

/**
 * Created by zhujian on 10/3/14.
 */
public class AIfloat implements AIType{
    private Float value = null;

    public AIfloat(){
        value = new Float(0);
    }
    public AIfloat(float val){
        value = new Float(val);
    }
    public AIfloat(AIfloat val){
        value = new Float(val.value);
    }

    @Override
    public AIint toInt() {
        throw new IllegalArgumentException("Can't cast float to int");
    }
    @Override
    public AIlong toLong(){
        throw new IllegalArgumentException("Can't cast float to long");
    }
    @Override
    public AIfloat toFloat(){
        return new AIfloat(this);
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
            AIfloat toValue = bb.toFloat();
            return new AIfloat(value + toValue.value);
        } catch (IllegalArgumentException e) {
            if(called == true){ throw new RuntimeException("unable to execute the operation+"); }
        }
        return bb.plus(this, true);
    }
    @Override
    public AIType minus(AIType bb, boolean called) throws Exception{
        try{
            AIfloat toValue = bb.toFloat();
            Float res = (called == false) ? (value - toValue.value) : (toValue.value - value);
            return new AIfloat(res);
        } catch (IllegalArgumentException e) {
            if(called == true){ throw new RuntimeException("unable to execute the operation-"); }
        }
        return bb.minus(this, true);
    }
    @Override
    public AIType multiple(AIType bb, boolean called) throws Exception{
        try{
            AIfloat toValue = bb.toFloat();
            return new AIfloat(value * toValue.value);
        } catch (IllegalArgumentException e) {
            if(called == true){ throw new RuntimeException("unable to execute the operation*"); }
        }
        return bb.multiple(this, true);
    }
    @Override
    public AIType divide(AIType bb, boolean called) throws Exception{
        try{
            AIfloat toValue = bb.toFloat();
            Float res = (called == false) ? (value/toValue.value) : (toValue.value/value);
            return new AIfloat(res);
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
