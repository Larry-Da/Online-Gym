package org.qmbupt.grp105.Controller;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.qmbupt.grp105.UI.UIStyle;

public class ToolboxTest extends TestCase {




    @Test
    public void testIsDateForm1() {
        String input = "1999-06-24";
        assertTrue(Toolbox.isDateForm1(input));

    }

    @Test
    public void testIsDateForm2() {
        String input =  "1999-06-24 11:00:00";
        assertTrue(Toolbox.isDateForm2(input));
    }

    @Test
    public void testIsEmail() {
        String input = "2018213144@bupt.edu.cn";
        assertTrue(Toolbox.isEmail(input));
    }

    @Test
    public void testIsPassword() {
        String input = "123abcd!";
        assertTrue(Toolbox.isPassword(input));
    }

    @Test
    public void testIsPicture()
    {
        String input = "/Users/daliangrun/weight loss5.jpg";
        assertTrue(Toolbox.isPicture(input));
    }

}