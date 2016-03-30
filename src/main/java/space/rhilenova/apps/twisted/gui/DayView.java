package space.rhilenova.apps.twisted.gui;

import org.joda.time.LocalDate;
import space.rhilenova.apps.twisted.todos.SingleTODO;
import space.rhilenova.apps.twisted.todos.TODO;
import space.rhilenova.apps.twisted.todos.TODOGroup;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    public DayView(LocalDate date, List<SingleTODO> required, List<TODOGroup> groups)
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
        private DatePane(LocalDate date)
        {
            this.setLayout(new BorderLayout());
            Integer[] borders = {6};
            PartialLineBorder test = new PartialLineBorder(Arrays.asList(borders), Color.BLACK, 2, 0, 10);
            this.setBorder(test);

            JLabel mo_day = new JLabel(date.toString("MMMM d"));
            mo_day.setFont(new Font("Arial", Font.BOLD, 20));
            JLabel year = new JLabel(date.toString("y"));
            year.setFont(new Font("Arial", Font.BOLD, 20));

            this.add(mo_day, BorderLayout.WEST);
            this.add(year, BorderLayout.EAST);
        }
    }

    /**
     * A JPanel for displaying the todos for the day.
     */
    private class DayPane extends JPanel
    {
        /**
         * Create a JPanel for displaying the todos for the day.
         *
         * @param required A list of required todos.
         * @param groups A list of todo groups.
         */
        private DayPane(List<SingleTODO> required, List<TODOGroup> groups)
        {
            this.setLayout(new GridLayout(2, 2));

            List<TODO> required_todo = new ArrayList<TODO>(required);
            TODOPane required_p = new TODOPane("Required", required_todo);
            Integer[] required_s = {4, 6, 7};
            PartialLineBorder required_b = new PartialLineBorder(Arrays.asList(required_s), Color.BLACK, 1, 10, 10);
            required_p.setBorder(required_b);
            this.add(required_p);

            List<TODO> groups_todo = new ArrayList<TODO>(groups);
            ProgressPane weekly = new ProgressPane("Weekly Goals", groups_todo, ProgressPane.TYPE.WEEKLY);
            Integer[] weekly_s = {3, 5, 6};
            PartialLineBorder weekly_b = new PartialLineBorder(Arrays.asList(weekly_s), Color.BLACK, 1, 10, 10);
            weekly.setBorder(weekly_b);
            this.add(weekly);

            TODOPane groups_p = new TODOPane("Groups", groups_todo);
            Integer[] groups_s = {1, 2, 4};
            PartialLineBorder groups_b = new PartialLineBorder(Arrays.asList(groups_s), Color.BLACK, 1, 10, 10);
            groups_p.setBorder(groups_b);
            this.add(groups_p);

            ProgressPane monthly = new ProgressPane("Monthly Goals", groups_todo, ProgressPane.TYPE.MONTHLY);
            Integer[] monthly_s = {0, 1, 3};
            PartialLineBorder monthly_b = new PartialLineBorder(Arrays.asList(monthly_s), Color.BLACK, 1, 10, 10);
            monthly.setBorder(monthly_b);
            this.add(monthly);
        }
    }

    /**
     * JPanel containing a name and a scrollable list of todo items.
     */
    private class TODOPane extends JPanel
    {
        /**
         * Create a JPanel containing a name and a scrollable list of todo items.
         *
         * @param name The name of the pane.
         * @param todos The list of todo items to draw.
         */
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

    /**
     * JPanel for drawing a single todo item.
     */
    private class JSingleTODO extends JPanel
    {
        /**
         * Create a JPanel for drawing a single todo item.
         *
         * @param todo The todo to draw.
         */
        private JSingleTODO(SingleTODO todo)
        {
            this.setLayout(new BorderLayout());
            this.add(new JCheckBox(todo.getName(), todo.isCompleted()));
        }
    }
}
