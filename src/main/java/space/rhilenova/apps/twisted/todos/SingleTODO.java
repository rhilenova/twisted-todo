package space.rhilenova.apps.twisted.todos;

/**
 * TODO: Document
 */
public class SingleTODO extends TODO
{
    private boolean _completed;

    // TODO implement
    public SingleTODO(String name, boolean completed)
    {
        super(name);
        this._completed = completed;
    }

    public boolean isCompleted()
    {
        return this._completed;
    }
}
