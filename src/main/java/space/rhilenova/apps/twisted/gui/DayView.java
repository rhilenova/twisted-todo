package space.rhilenova.apps.twisted.gui;

import space.rhilenova.apps.twisted.todos.SingleTODO;
import space.rhilenova.apps.twisted.todos.TODO;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        private DayPane(List<TODO> required, List<TODO> groups)
        {
            this.setLayout(new GridLayout(2, 2));

            TODOPane required_p = new TODOPane("Required", required);
            Integer[] required_s = {4, 6, 7};
            PartialLineBorder required_b = new PartialLineBorder(Arrays.asList(required_s), Color.BLACK, 1, 10, 10);
            required_p.setBorder(required_b);
            this.add(required_p);

            // TODO gather weekly goals from groups.
            List<TODO> weekly_todo = new ArrayList<>();
            ProgressPane weekly = new ProgressPane("Weekly Goals", weekly_todo);
            Integer[] weekly_s = {3, 5, 6};
            PartialLineBorder weekly_b = new PartialLineBorder(Arrays.asList(weekly_s), Color.BLACK, 1, 10, 10);
            weekly.setBorder(weekly_b);
            this.add(weekly);

            TODOPane groups_p = new TODOPane("Groups", groups);
            Integer[] groups_s = {1, 2, 4};
            PartialLineBorder groups_b = new PartialLineBorder(Arrays.asList(groups_s), Color.BLACK, 1, 10, 10);
            groups_p.setBorder(groups_b);
            this.add(groups_p);

            // TODO gather monthly goals from groups.
            List<TODO> monthly_todo = new ArrayList<>();
            TODOPane monthly = new TODOPane("Monthly Goals", monthly_todo);
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

    /**
     * JPanel containing a name and a scrollable list of progress items.
     */
    private class ProgressPane extends JPanel
    {
        /**
         * Create a JPanel containing a name and a scrollable list of progress items.
         *
         * @param name The name of the pane.
         * @param todos The list of progress items to draw.
         */
        private ProgressPane(String name, List<TODO> todos)
        {
            this.setLayout(new BorderLayout());
            JLabel name_l = new JLabel(name);
            name_l.setFont(new Font("Arial", Font.BOLD, 16));
            this.add(name_l, BorderLayout.NORTH);
            JPanel scroll_panel = new JPanel();
            scroll_panel.setLayout(new BoxLayout(scroll_panel, BoxLayout.Y_AXIS));
            for (TODO todo : todos)
            {
                // TODO Get progress and total
                scroll_panel.add(new JSingleTODOProgress(todo.getName(), 0, 10));
            }
            JScrollPane test = new JScrollPane(scroll_panel);
            this.add(test, BorderLayout.CENTER);
        }
    }

    /**
     * JPanel for showing a single group item's progress.
     */
    private class JSingleTODOProgress extends JPanel
    {
        /**
         * Create a JPanel for showing a single group item's progress.
         *
         * @param name The name of the item.
         * @param complete How many hours have been worked so far.
         * @param total How many hours are the goal for this item.
         */
        private JSingleTODOProgress(String name, int complete, int total)
        {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JLabel name_l = new JLabel(name);
            name_l.setFont(new Font("Arial", Font.BOLD, 14));
            this.add(name_l);
            JProgressBar progress = new JProgressBar(0, total);
            progress.setValue(complete);
            this.add(progress);
        }
    }
}
