package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.action.category.AddCategory;
import cz.muni.fi.pv168.project.todoapp.ui.action.category.DeleteCategory;
import cz.muni.fi.pv168.project.todoapp.ui.action.category.EditCategory;

import javax.swing.JTable;

public class CategoriesTab extends GeneralTab {
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
            var refresher = this.getRefresher();
            return new CategoriesTab(
                    this
                            .addAddAction(new AddCategory(table, frame, crudHolder))
                            .addEditAction(new EditCategory(table, frame, crudHolder, refresher))
                            .addDeleteAction(new DeleteCategory(table, frame, crudHolder))
                            .addPopupMenu(table)
            );
        }
    }

    private CategoriesTab(
            BuildTemplate buildTemplate
    ) {
        super(buildTemplate);
    }
}
