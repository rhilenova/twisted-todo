package space.rhilenova.apps.twisted.gui;

import space.rhilenova.apps.twisted.todos.SingleTODO;
import space.rhilenova.apps.twisted.todos.TODO;
import space.rhilenova.apps.twisted.todos.TODOGroup;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Panel for displaying current day's todos.
 */
public class DayView extends JPanel
{
    /**
     * Create a new panel for displaying the current day's todo.
     *
     * @param date The date to display for this view.
     * @param required A list of required (single) todos for this day.
     * @param groups A list of the todo groups for this day.
     */
    public DayView(Date date, List<TODO> required, List<TODO> groups)
    {
        this.setLayout(new BorderLayout());
        this.add(new DatePane(date), BorderLayout.NORTH);
        this.add(new DayPane(required, groups));
    }

    /**
     * A small panel for displaying the requested day.
     */
    private class DatePane extends JPanel
    {
        /**
         * Create a new panel for displaying the requested date.
         *
         * @param date The date to display.
         */
        private DatePane(Date date)
        {
            this.setLayout(new BorderLayout());
            Integer[] borders = {6};
            PartialLineBorder test = new PartialLineBorder(Arrays.asList(borders), Color.BLACK, 2, 0, 10);
            this.setBorder(test);

            SimpleDateFormat fmt = new SimpleDateFormat();
            fmt.applyPattern("MMMM d");
            JLabel mo_day = new JLabel(fmt.format(date));
            mo_day.setFont(new Font("Arial", Font.BOLD, 20));
            fmt.applyPattern("y");
            JLabel year = new JLabel(fmt.format(date));
            year.setFont(new Font("Arial", Font.BOLD, 20));

            this.add(mo_day, BorderLayout.WEST);
            this.add(year, BorderLayout.EAST);
        }
    }

    private class DayPane extends JPanel
    {
        private DayPane(List<TODO> required, List<TODO> groups)
        {
            this.setLayout(new GridLayout(2, 2));

            TODOPane required_p = new TODOPane("Required", required);
            //JLabel required_l = new JLabel("Required");
            //required_l.setFont(new Font("Arial", Font.BOLD, 16));
            Integer[] required_s = {4, 6, 7};
            PartialLineBorder required_b = new PartialLineBorder(Arrays.asList(required_s), Color.BLACK, 1, 10, 10);
            required_p.setBorder(required_b);
            this.add(required_p);

            JLabel weekly = new JLabel("Weekly Goals");
            weekly.setFont(new Font("Arial", Font.BOLD, 16));
            Integer[] weekly_s = {3, 5, 6};
            PartialLineBorder weekly_b = new PartialLineBorder(Arrays.asList(weekly_s), Color.BLACK, 1, 10, 10);
            weekly.setBorder(weekly_b);
            this.add(weekly);

            JLabel groups_l = new JLabel("Groups");
            groups_l.setFont(new Font("Arial", Font.BOLD, 16));
            Integer[] groups_s = {1, 2, 4};
            PartialLineBorder groups_b = new PartialLineBorder(Arrays.asList(groups_s), Color.BLACK, 1, 10, 10);
            groups_l.setBorder(groups_b);
            this.add(groups_l);

            JLabel monthly = new JLabel("Monthly Goals");
            monthly.setFont(new Font("Arial", Font.BOLD, 16));
            Integer[] monthly_s = {0, 1, 3};
            PartialLineBorder monthly_b = new PartialLineBorder(Arrays.asList(monthly_s), Color.BLACK, 1, 10, 10);
            monthly.setBorder(monthly_b);
            this.add(monthly);
        }
    }

    private class TODOPane extends JPanel
    {
        private TODOPane(String name, List<TODO> todos)
        {
            this.setLayout(new BorderLayout());
            JLabel name_l = new JLabel(name);
            name_l.setFont(new Font("Arial", Font.BOLD, 16));
            this.add(name_l, BorderLayout.NORTH);
            JPanel scroll_panel = new JPanel();
            scroll_panel.setLayout(new BoxLayout(scroll_panel, BoxLayout.Y_AXIS));
            for (TODO todo : todos)
            {
                if (todo instanceof SingleTODO)
                {
                    scroll_panel.add(new JSingleTODO((SingleTODO)todo));
                }
            }
            JScrollPane test = new JScrollPane(scroll_panel);
            this.add(test, BorderLayout.CENTER);
        }
    }

    private class JSingleTODO extends JPanel
    {
        private JSingleTODO(SingleTODO todo)
        {
            this.setLayout(new BorderLayout());
            this.add(new JCheckBox(todo.getName(), todo.isCompleted()));
        }
    }
}
