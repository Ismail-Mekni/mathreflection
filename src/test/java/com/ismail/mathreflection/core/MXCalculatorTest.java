package com.ismail.mathreflection.core;

import com.ismail.mathreflection.core.impl.MXCalculator;
import com.ismail.mathreflection.exceptions.UnparseableFieldException;
import com.ismail.mathreflection.factory.MXFactory;
import com.ismail.mathreflection.models.Formula;
import com.ismail.mathreflection.utilities.BeanTest;
import com.ismail.mathreflection.utilities.BeanTestParser;
import com.ismail.mathreflection.utilities.BeanTestUnparseableField;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MXCalculatorTest {

    private MXCalculator calculator;

    @Before
    public void setUp() {

    }

    @Test
    public void calculateInitializerTest() {

        calculator = MXFactory.createCalculator(BeanTest.class);

        assertNotNull(calculator.getFieldOrder());

        assertNotNull(calculator.getFieldOrder().getOrderedFields());

        assertEquals(2, calculator.getFieldOrder().getOrderedFields().size());

        assertEquals("field3", ((Formula) calculator.getFieldOrder().getOrderedFields().poll()).getFieldName());

        assertEquals("field4", ((Formula) calculator.getFieldOrder().getOrderedFields().poll()).getFieldName());

    }

    @Test
    public void calculateSimpleBeanWithPublicAndDoubleFieldsTest() {
        calculator = MXFactory.createCalculator(BeanTest.class);

        BeanTest beanTest = new BeanTest();

        beanTest.field1 = 5;

        beanTest.field2 = 6;

        calculator.calculate(beanTest);

        assertEquals(-1.0, beanTest.field3, 0.01);

        assertEquals(10.0, beanTest.field4, 0.01);
    }

    @Test
    public void calculateBeanWithReadParserTest() {

        calculator = MXFactory.createCalculator(BeanTestParser.class);

        BeanTestParser beanTest = new BeanTestParser();

        beanTest.field1 = "5";

        beanTest.field2 = 6;

        calculator.calculate(beanTest);

        assertEquals(-1.0, beanTest.field3, 0.01);

        assertEquals(10.0, beanTest.field4, 0.01);
    }

    @Test(expected = UnparseableFieldException.class)
    public void calculateThrowUparseableFiledExceptionTest() {
        calculator = MXFactory.createCalculator(BeanTestUnparseableField.class);

        BeanTestUnparseableField beanTest = new BeanTestUnparseableField();

        beanTest.field1 = new HashMap();

        beanTest.field2 = 6;

        calculator.calculate(beanTest);
    }

}
