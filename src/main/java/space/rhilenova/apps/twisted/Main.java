package space.rhilenova.apps.twisted;

import space.rhilenova.apps.twisted.gui.DayView;
import space.rhilenova.apps.twisted.todos.SingleTODO;
import space.rhilenova.apps.twisted.todos.TODOGroup;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;

/**
 * TODO: Document
 */
public class Main
{
    private List<SingleTODO> testRequired()
    {
        ArrayList<SingleTODO> required = new ArrayList<>();

        // Get current date
        LocalDate today = new LocalDate();

        // Create a required todo for yesterday (complete), today (incomplete), and tomorrow (repeating weekly, incomplete)
        required.add(new SingleTODO("yesterday", today.minusDays(1), 1));
        required.add(new SingleTODO("today", today, 0));
        required.add(new SingleTODO("tomorrow", today.plusDays(1), 0, SingleTODO.Repeating.WEEKLY));

        return required;
    }

    private List<TODOGroup> testGroups()
    {
        ArrayList<TODOGroup> groups = new ArrayList<>();

        for (int x = 0; x < 5; ++x)
        {
            ArrayList<SingleTODO> things = new ArrayList<>();
            SingleTODO thing1 = new SingleTODO(Integer.toString(x) + "thing1", new LocalDate(), x, 10, 20);
            things.add(thing1);
            SingleTODO thing2 = new SingleTODO(Integer.toString(x) + "thing2", new LocalDate(), (3 * x) % 18, 8, 18);
            things.add(thing2);
            groups.add(new TODOGroup("Things " + Integer.toString(x), things));
        }

        return groups;
    }

    public static void main(String args[])
    {
        // TODO replace with selection.
        LocalDate cur_selected_date = new LocalDate();

        // TODO replace with database access.
        Main main = new Main();
        List<SingleTODO> required_all = main.testRequired();
        List<SingleTODO> required_today = new ArrayList<>();
        for (SingleTODO todo : required_all)
        {
            if (todo.getDueDate().equals(cur_selected_date))
            {
                required_today.add(todo);
            }
        }
        List<TODOGroup> groups = main.testGroups();

        JFrame test = new JFrame();
        test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        test.add(new DayView(new LocalDate(), required_today, groups));

        test.setSize(new Dimension(600, 480));
        test.setLocation(3000, 10);
        test.setVisible(true);
    }
}
