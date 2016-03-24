package space.rhilenova.apps.twisted.todos;

/**
 * TODO: Document
 */
public abstract class TODO
{
    private String _name;

    public TODO(String name)
    {
        this._name = name;
    }

    public String getName()
    {
        return this._name;
    }
}
