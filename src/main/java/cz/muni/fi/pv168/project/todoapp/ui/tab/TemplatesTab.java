package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.ToolBarManager;
import cz.muni.fi.pv168.project.todoapp.ui.action.template.AddTemplate;
import cz.muni.fi.pv168.project.todoapp.ui.action.template.DeleteTemplate;
import cz.muni.fi.pv168.project.todoapp.ui.action.template.EditTemplate;

import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import java.util.function.Supplier;

public class TemplatesTab extends GeneralTab {
    protected static class Builder extends GeneralTab.Builder<Builder> {
        @Override
        public Builder self() {
            return this;
        }
    }

    public TemplatesTab(
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
                        .addAddAction(new AddTemplate(table, tableModelSupplier))
                        .addEditAction(new EditTemplate(table))
                        .addDeleteAction(new DeleteTemplate(table))
        );
    }
}
