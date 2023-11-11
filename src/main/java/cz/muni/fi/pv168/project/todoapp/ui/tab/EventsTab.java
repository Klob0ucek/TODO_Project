package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.action.event.AddEvent;
import cz.muni.fi.pv168.project.todoapp.ui.action.event.DeleteEvent;
import cz.muni.fi.pv168.project.todoapp.ui.action.event.EditEvent;

import javax.swing.*;


public class EventsTab extends GeneralTab {
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
            return new EventsTab(
                    this
                            .addAddAction(new AddEvent(table, frame, crudHolder))
                            .addEditAction(new EditEvent(table, frame, crudHolder))
                            .addDeleteAction(new DeleteEvent(table, frame, crudHolder))
            );
        }
    }

    private EventsTab(
            BuildTemplate buildTemplate
    ) {
        super(buildTemplate);
        super.title = "Events";
    }
}
