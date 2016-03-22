package space.rhilenova.apps.twisted;

import space.rhilenova.apps.twisted.gui.DayView;
import space.rhilenova.apps.twisted.todos.SingleTODO;
import space.rhilenova.apps.twisted.todos.TODOGroup;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

        test.add(new DayView(temp, new ArrayList<SingleTODO>(), new ArrayList<TODOGroup>()));

        test.pack();
        test.setVisible(true);
    }
}
