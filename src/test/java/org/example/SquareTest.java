package org.example;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;


public class SquareTest {


    private static Logger strLogger = Logger.getLogger(String.class);


    @Test(groups = "smoke", testName = "test_square")
    public void testArea() {
        Square square = new Square(5);
        Assert.assertEquals(square.getSquareArea(),  25.0);

        strLogger.info("<<< Test method: " + this.getClass().getMethods()[0].toString() + " passed >>>");

    }



    @AfterClass
    private  void closer() {
        strLogger.info("<<< Test class " + (SquareTest.class.getSimpleName()) + " passed >>>");
    }

}
