package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.action.category.AddCategory;
import cz.muni.fi.pv168.project.todoapp.ui.action.category.DeleteCategory;
import cz.muni.fi.pv168.project.todoapp.ui.action.category.EditCategory;

import javax.swing.*;

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
            return new CategoriesTab(
                    this
                            .addAddAction(new AddCategory(table, frame))
                            .addEditAction(new EditCategory(table, frame))
                            .addDeleteAction(new DeleteCategory(table, frame))
            );
        }
    }

    private CategoriesTab(
            BuildTemplate buildTemplate
    ) {
        super(buildTemplate);
    }
}
