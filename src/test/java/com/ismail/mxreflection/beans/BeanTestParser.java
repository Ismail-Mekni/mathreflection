package com.ismail.mxreflection.beans;

import com.ismail.mxreflection.annotations.Expression;
import com.ismail.mxreflection.annotations.Arg;

public class BeanTestParser {


    @Arg("f1")
    public String field1;

    @Arg("f2")
    public int field2;

    @Expression("f1 - f2")
    public double field3;

    @Expression("f1 + f2 + field3")
    public Double field4;
}
