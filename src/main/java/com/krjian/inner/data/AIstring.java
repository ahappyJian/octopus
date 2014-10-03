package com.krjian.inner.data;

/**
 * Created by zhujian on 10/3/14.
 */
public class AIstring implements AIType{
    private String value = null;

    public AIstring(){
        value = new String("");
    }
    public AIstring(String val){
        value = new String(val);
    }
    public AIstring(AIstring val){
        value = new String(val.value);
    }

    @Override
    public AIint toInt() {
        int toValue = 0;
        try{
            toValue = Integer.valueOf(value.trim()).intValue();
        }catch (NumberFormatException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Can't cast string to int");
        }
        return new AIint(toValue);
    }
    @Override
    public AIlong toLong(){
        long toValue = 0;
        try{
            toValue = Long.valueOf(value.trim()).longValue();
        }catch (NumberFormatException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Can't cast string to long");
        }
        return new AIlong(toValue);
    }
    @Override
    public AIfloat toFloat(){
        float toValue = 0;
        try{
            toValue = Float.valueOf(value.trim()).floatValue();
        }catch (NumberFormatException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Can't cast string to float");
        }
        return new AIfloat(toValue);
    }
    @Override
    public AIdouble toDouble(){
        double toValue = 0;
        try{
            toValue = Double.valueOf(value.trim()).doubleValue();
        }catch (NumberFormatException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Can't cast string to float");
        }
        return new AIdouble(toValue);
    }

    @Override
    public AIstring toAIString() throws Exception {
        return new AIstring(this);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    //
    public AIType toNumber(){
        AIType res = null;
        try{
            res = toInt();
            return res;
        }catch (IllegalArgumentException e){}
        try{
            res = toLong();
            return res;
        }catch (IllegalArgumentException e){}
        try{
            res = toFloat();
            return res;
        }catch (IllegalArgumentException e){}
        try{
            res = toDouble();
            return res;
        }catch (IllegalArgumentException e){}
        throw new RuntimeException("Can't cast string:" + value + " to number");

    }
    @Override
    public AIType plus(AIType bb, boolean called) throws Exception{
        AIType local = toNumber();
        return local.plus(bb, false);
    }
    @Override
    public AIType minus(AIType bb, boolean called) throws Exception{
        AIType local = toNumber();
        return local.minus(bb, false);
    }
    @Override
    public AIType multiple(AIType bb, boolean called) throws Exception{
        AIType local = toNumber();
        return local.multiple(bb, false);
    }
    @Override
    public AIType divide(AIType bb, boolean called) throws Exception{
        AIType local = toNumber();
        return local.divide(bb, false);
    }
    @Override
    public AIType concat(AIType bb, boolean called) throws Exception{
        return new AIstring(toString() + bb.toAIString().toString());
    }

}
