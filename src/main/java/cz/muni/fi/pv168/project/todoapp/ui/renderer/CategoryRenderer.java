package cz.muni.fi.pv168.project.todoapp.ui.renderer;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import javax.swing.JLabel;

public final class CategoryRenderer extends AbstractRenderer<Category> {

    public CategoryRenderer() {
        super(Category.class);
    }

    @Override
    protected void updateLabel(JLabel label, Category category) {
        if (category != null) {
            label.setText(category.getName());
            label.setBackground(category.getColor().getColor());
        }
    }
}
