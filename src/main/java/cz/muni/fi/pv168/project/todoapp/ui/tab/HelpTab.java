package cz.muni.fi.pv168.project.todoapp.ui.tab;

public class HelpTab extends GeneralTab {
    public static class BuildTemplate extends GeneralTab.BuildTemplate<BuildTemplate> {
        @Override
        public BuildTemplate self() {
            return this;
        }

        @Override
        public GeneralTab build() {
            return new HelpTab(this);
        }
    }

    private HelpTab(
            BuildTemplate buildTemplate
    ) {
        super(buildTemplate);
    }

    @Override
    public void updateToolBar() {
        this.getToolBarManager()
                .reset()
                .saveChanges();
    }
}
