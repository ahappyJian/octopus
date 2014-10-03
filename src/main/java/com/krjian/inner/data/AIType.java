package com.krjian.inner.data;

/**
 * Created by zhujian on 10/3/14.
 */
public interface AIType {
    public AIint toInt() throws Exception;
    public AIlong toLong() throws Exception;
    public AIfloat toFloat() throws Exception;
    public AIdouble toDouble() throws Exception;
    public AIstring toAIString() throws Exception;

    public AIType plus(AIType bb, boolean called) throws Exception;
    public AIType minus(AIType bb, boolean called) throws Exception;
    public AIType multiple(AIType bb, boolean called) throws Exception;
    public AIType divide(AIType bb, boolean called) throws Exception;
    public AIType concat(AIType bb, boolean called) throws Exception;
}
