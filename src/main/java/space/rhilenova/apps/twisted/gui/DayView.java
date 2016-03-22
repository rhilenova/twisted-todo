package space.rhilenova.apps.twisted.gui;

import space.rhilenova.apps.twisted.todos.SingleTODO;
import space.rhilenova.apps.twisted.todos.TODOGroup;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Panel for displaying current day's todos.
 */
public class DayView extends JPanel
{
    /** A list of the required (single) todos for this day. */
    private List<SingleTODO> required;
    /** A list of the todo groups for this day. */
    private List<TODOGroup> groups;

    /**
     * Create a new panel for displaying the current day's todo.
     *
     * @param date The date to display for this view.
     * @param required A list of required (single) todos for this day.
     * @param groups A list of the todo groups for this day.
     */
    public DayView(Date date, List<SingleTODO> required, List<TODOGroup> groups)
    {
        this.required = required;
        this.groups = groups;

        this.setLayout(new BorderLayout());
        this.add(new DatePane(date), BorderLayout.NORTH);
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

            SimpleDateFormat fmt = new SimpleDateFormat();
            fmt.applyPattern("MMMM d");
            JLabel mo_day = new JLabel(fmt.format(date));
            mo_day.setFont(new Font("Arial", Font.BOLD, 16));
            fmt.applyPattern("y");
            JLabel year = new JLabel(fmt.format(date));
            year.setFont(new Font("Arial", Font.BOLD, 16));

            this.add(mo_day, BorderLayout.WEST);
            this.add(year, BorderLayout.EAST);
        }
    }
}
