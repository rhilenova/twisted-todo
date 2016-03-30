package space.rhilenova.apps.twisted.gui;

import space.rhilenova.apps.twisted.todos.SingleTODO;
import space.rhilenova.apps.twisted.todos.TODO;
import space.rhilenova.apps.twisted.todos.TODOGroup;

import javax.swing.*;
import javax.swing.border.TitledBorder;
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

    private TYPE _type;

    /**
     * Create a JPanel containing a name and a scrollable list of progress items.
     *
     * @param name The name of the pane.
     * @param todos The list of progress items to draw.
     * @param type The type of data to display in this pane.
     */
    ProgressPane(String name, List<TODOGroup> todos, TYPE type)
    {
        this._type = type;

        this.setLayout(new BorderLayout());
        JLabel name_l = new JLabel(name);
        name_l.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(name_l, BorderLayout.NORTH);
        JPanel scroll_panel = new JPanel();
        scroll_panel.setLayout(new BoxLayout(scroll_panel, BoxLayout.Y_AXIS));
        for (TODOGroup todo : todos)
        {
            scroll_panel.add(new ProgressPane.JTODOGroupProgress(todo, this._type));
        }
        JScrollPane test = new JScrollPane(scroll_panel);
        this.add(test, BorderLayout.CENTER);
    }

    /**
     * JPanel for showing a single group item's progress.
     */
    private class JTODOGroupProgress extends JPanel
    {
        /**
         * Create a JPanel for showing a single group item's progress.
         *
         * @param todo The TODOGroup to display progress for.
         */
        private JTODOGroupProgress(TODOGroup todo, ProgressPane.TYPE type)
        {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.setBorder(new TitledBorder(todo.getName()));
            for (SingleTODO s_todo : todo.getTODOs())
            {
                int completed = s_todo.getHoursCompleted();
                int goal = 0;
                if (type == TYPE.WEEKLY)
                {
                    goal = s_todo.getWeeklyGoal();
                } else if (type == TYPE.MONTHLY)
                {
                    goal = s_todo.getMonthlyGoal();
                }

                JSingleTODOProgress progress = new JSingleTODOProgress(s_todo.getName(), completed, goal);
                this.add(progress);

            }
        }
    }

    private class JSingleTODOProgress extends JProgressBar
    {
        private String _name;

        private JSingleTODOProgress(String name, int value, int total)
        {
            super(0, total);
            this.setValue(value);

            this._name = name;

            this.setStringPainted(true);
        }

        @Override
        public String getString()
        {
            return (this._name + " " + Integer.toString(this.getValue()) + "/" + Integer.toString(this.getMaximum()));
        }
    }
}
