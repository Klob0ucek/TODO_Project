package cz.muni.fi.pv168.project.ui.model;

import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class HelpTab extends JLabel {
    static final String content = """
            <html>
            <head>
            <title>TODO List app</title>
            </head>
            <body>

            <h2>TODO List</h2>
            <p>Version: Alpha</p>
            <p>Contributors:</p>
            <ul>
              <li>Patrik Rosecky</li>
              <li>Jakub Nezval</li>
              <li>Jan Polacek</li>
              <li>Radim Rychlik</li>
            </ul>
            <p>Contact us:<p>
            <a href="https://gitlab.fi.muni.cz/xroseck2/pv168-project-template/-/issues">   GitLab issues</a>
            </body>
            </html>""";
    public HelpTab() {
        super(HelpTab.content,SwingConstants.CENTER);
    }
}
