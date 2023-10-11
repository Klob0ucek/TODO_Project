package cz.muni.fi.pv168.project.ui;

public enum Tab {
    EVENTS, CATEGORIES, TEMPLATES, INTERVALS, HELP;

    public String getStringValue() {
        return switch (this) {
            case EVENTS -> "Events";
            case CATEGORIES -> "Categories";
            case TEMPLATES -> "Templates";
            case INTERVALS -> "Intervals";
            case HELP -> "Help";
        };
    }

    public String getTabTip() {
        return ""; // TODO
    }
}
