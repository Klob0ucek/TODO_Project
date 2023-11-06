package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.ToolBarManager;
import cz.muni.fi.pv168.project.todoapp.ui.action.event.AddEvent;
import cz.muni.fi.pv168.project.todoapp.ui.action.event.DeleteEvent;
import cz.muni.fi.pv168.project.todoapp.ui.action.event.EditEvent;

import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import java.util.function.Supplier;

public class EventsTab extends GeneralTab {
    protected static class Builder extends GeneralTab.Builder<Builder> {
        @Override
        public Builder self() {
            return this;
        }
    }

    public EventsTab(
            String title,
            Icon icon,
            JTable table,
            String tip,
            ToolBarManager toolBarManager,
            Supplier<TableModel> tableModelSupplier
    ) {
        super(
                new Builder()
                        .addTabDetails(title, icon, table, tip)
                        .addToolBarManager(toolBarManager)
                        .addAddAction(new AddEvent(table, tableModelSupplier))
                        .addEditAction(new EditEvent(table))
                        .addDeleteAction(new DeleteEvent(table))
        );
    }
}
