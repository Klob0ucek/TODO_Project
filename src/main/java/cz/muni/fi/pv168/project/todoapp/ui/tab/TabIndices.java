package cz.muni.fi.pv168.project.todoapp.ui.tab;

public enum TabIndices {
    EVENTS(0),
    CATEGORIES(1),
    TEMPLATES(2),
    INTERVALS(3),
    HELP(4);

    private final Integer index;

    TabIndices(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
