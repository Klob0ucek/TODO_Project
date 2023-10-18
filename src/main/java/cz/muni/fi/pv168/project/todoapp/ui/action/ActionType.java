package cz.muni.fi.pv168.project.todoapp.ui.action;

public enum ActionType {
    ADD,
    DELETE,
    EDIT,
    EXPORT,
    FILTER,
    IMPORT;

    public static ActionType[] all() {
        return ActionType.values();
    }

    public static ActionType[] basic() {
        return new ActionType[]{ADD, DELETE, EDIT};
    }
}
