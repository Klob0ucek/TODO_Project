package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.action.interval.AddInterval;
import cz.muni.fi.pv168.project.todoapp.ui.action.interval.DeleteInterval;
import cz.muni.fi.pv168.project.todoapp.ui.action.interval.EditInterval;

import javax.swing.JTable;

public class IntervalsTab extends GeneralTab {
    public static class BuildTemplate extends GeneralTab.BuildTemplate<BuildTemplate> {
        @Override
        public BuildTemplate self() {
            return this;
        }

        @Override
        public GeneralTab build() {
            var table = (JTable) this.getComponent();
            return new IntervalsTab(
                    this
                            .addAddAction(new AddInterval(table))
                            .addEditAction(new EditInterval(table))
                            .addDeleteAction(new DeleteInterval(table))
            );
        }
    }

    private IntervalsTab(
            BuildTemplate buildTemplate
    ) {
        super(buildTemplate);
    }
}
