package space.rhilenova.apps.twisted.todos;

import java.util.List;

/**
 * TODO: Document
 */
public class TODOGroup extends TODO
{
    List<SingleTODO> _todos;

    public TODOGroup(String name, List<SingleTODO> todos)
    {
        super(name);
        this._todos = todos;
    }

    public List<SingleTODO> getTODOs()
    {
        return _todos;
    }

    // TODO implement
}
