package space.rhilenova.apps.twisted.gui;

import space.rhilenova.apps.twisted.todos.TODO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * JPanel containing a name and a scrollable list of progress items.
 */
class ProgressPane extends JPanel
{
    public enum TYPE
    {
        WEEKLY,
        MONTHLY
    }

    private DayView dayView;

    /**
     * Create a JPanel containing a name and a scrollable list of progress items.
     *
     * @param name The name of the pane.
     * @param todos The list of progress items to draw.
     */
    ProgressPane(String name, List<TODO> todos, TYPE type)
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
            scroll_panel.add(new ProgressPane.JSingleTODOProgress(todo.getName(), 0, 10));
        }
        JScrollPane test = new JScrollPane(scroll_panel);
        this.add(test, BorderLayout.CENTER);
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
