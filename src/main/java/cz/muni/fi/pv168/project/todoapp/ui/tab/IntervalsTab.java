package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.ToolBarManager;
import cz.muni.fi.pv168.project.todoapp.ui.action.interval.AddInterval;
import cz.muni.fi.pv168.project.todoapp.ui.action.interval.DeleteInterval;
import cz.muni.fi.pv168.project.todoapp.ui.action.interval.EditInterval;

import javax.swing.Icon;
import javax.swing.JTable;

public class IntervalsTab extends GeneralTab {
    protected static class Builder extends GeneralTab.Builder<Builder> {
        @Override
        public Builder self() {
            return this;
        }
    }

    public IntervalsTab(
            String title,
            Icon icon,
            JTable table,
            String tip,
            ToolBarManager toolBarManager
    ) {
        super(
                new Builder()
                        .addTabDetails(title, icon, table, tip)
                        .addToolBarManager(toolBarManager)
                        .addAddAction(new AddInterval(table))
                        .addEditAction(new EditInterval(table))
                        .addDeleteAction(new DeleteInterval(table))
        );
    }
}
