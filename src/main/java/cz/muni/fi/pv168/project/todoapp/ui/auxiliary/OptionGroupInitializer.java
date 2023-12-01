package cz.muni.fi.pv168.project.todoapp.ui.auxiliary;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.util.List;
import java.util.function.Function;

public final class OptionGroupInitializer {
    private OptionGroupInitializer() {
        // private constructor to ensure that the class cannot be instantiated
    }

    public static <T extends JMenuItem> void initializer(
            String name,
            Function<String, T> optionConstructor,
            List<String> optionValues,
            JMenuBar optionMenuBar,
            OptionGroup<T> optionGroup
    ) {
        var optionsMenu = new JMenu(name);
        T option;

        for (var optVal : optionValues) {
            option = optionConstructor.apply(optVal);
            optionsMenu.add(option);
            optionGroup.add(option);
        }

        optionMenuBar.add(optionsMenu);
    }
}
