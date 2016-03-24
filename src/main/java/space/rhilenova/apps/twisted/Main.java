package space.rhilenova.apps.twisted;

import space.rhilenova.apps.twisted.gui.DayView;
import space.rhilenova.apps.twisted.todos.SingleTODO;
import space.rhilenova.apps.twisted.todos.TODO;
import space.rhilenova.apps.twisted.todos.TODOGroup;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * TODO: Document
 */
public class Main
{
    public static void main(String args[])
    {
        JFrame test = new JFrame();
        test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
        Date temp = null;
        try
        {
            temp = fmt.parse("03/22/2016");
        }
        catch (ParseException pe)
        {
            pe.printStackTrace();
        }

        List<TODO> required = new ArrayList<>();
        for (int x = 0; x < 10; ++x)
        {
            required.add(new SingleTODO("test" + x, x%3 == 0));
        }
        TODO[] groups = {new TODOGroup("group1")};
        test.add(new DayView(temp, required, Arrays.asList(groups)));

        test.setSize(new Dimension(600, 480));
        test.setLocation(3000, 10);
        test.setVisible(true);
    }
}
