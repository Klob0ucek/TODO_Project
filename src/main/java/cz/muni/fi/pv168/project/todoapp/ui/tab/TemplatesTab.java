package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.action.template.AddTemplate;
import cz.muni.fi.pv168.project.todoapp.ui.action.template.DeleteTemplate;
import cz.muni.fi.pv168.project.todoapp.ui.action.template.EditTemplate;

import javax.swing.JTable;

public class TemplatesTab extends GeneralTab {
    public static class BuildTemplate extends GeneralTab.BuildTemplate<BuildTemplate> {
        @Override
        public BuildTemplate self() {
            return this;
        }

        @Override
        public GeneralTab build() {
            var table = (JTable) this.getComponent();
            var frame = this.getFrame();
            var crudHolder = this.getCrudHolder();
            return new TemplatesTab(
                    this
                            .addAddAction(new AddTemplate(table, frame, crudHolder))
                            .addEditAction(new EditTemplate(table, frame, crudHolder))
                            .addDeleteAction(new DeleteTemplate(table, frame, crudHolder))
            );
        }
    }

    private TemplatesTab(
            BuildTemplate buildTemplate
    ) {
        super(buildTemplate);
    }
}
