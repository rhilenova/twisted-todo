package space.rhilenova.apps.twisted.todos;

import org.joda.time.LocalDate;

/**
 * TODO: Document
 */
public class SingleTODO extends TODO
{
    public enum Repeating
    {
        NONE,
        WEEKLY,
        MONTHLY,
        YEARLY
    }

    private LocalDate _due_date;
    private int _hours_completed;
    private int _weekly_goal;
    private int _monthly_goal;
    private Repeating _repeating;

    public SingleTODO(String name, LocalDate due_date, int hours_completed)
    {
        this(name, due_date, hours_completed, 1, 1, Repeating.NONE);
    }

    public SingleTODO(String name, LocalDate due_date, int hours_completed, Repeating repeats)
    {
        this(name, due_date, hours_completed, 1, 1, repeats);
    }

    public SingleTODO(String name, LocalDate due_date, int hours_completed, int weekly_goal, int monthly_goal)
    {
        this(name, due_date, hours_completed, weekly_goal, monthly_goal, Repeating.NONE);
    }

    // TODO implement
    public SingleTODO(String name, LocalDate due_date, int hours_completed, int weekly_goal, int monthly_goal, Repeating repeats)
    {
        super(name);
        this._due_date = due_date;
        this._hours_completed = hours_completed;
        this._weekly_goal = weekly_goal;
        this._monthly_goal = monthly_goal;
        this._repeating = repeats;
    }

    public LocalDate getDueDate()
    {
        return _due_date;
    }

    public boolean isCompleted()
    {
        return this._hours_completed >= this._monthly_goal;
    }

    public Repeating getRepeats()
    {
        return _repeating;
    }

    public int getHoursCompleted()
    {
        return _hours_completed;
    }

    public int getWeeklyGoal()
    {
        return _weekly_goal;
    }

    public int getMonthlyGoal()
    {
        return _monthly_goal;
    }
}
