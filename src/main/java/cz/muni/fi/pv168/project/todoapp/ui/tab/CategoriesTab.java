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
            return new CategoriesTab(
                    this
                            .addAddAction(new AddCategory(table))
                            .addEditAction(new EditCategory(table))
                            .addDeleteAction(new DeleteCategory(table))
            );
        }
    }

    private CategoriesTab(
            BuildTemplate buildTemplate
    ) {
        super(buildTemplate);
    }
}
