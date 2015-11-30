/*
 * Copyright (c) 2015 Jochem Broekhoff
 *
 * This software is provided 'as-is', without any express or implied
 * warranty. In no event will the authors be held liable for any damages
 * arising from the use of this software.
 *
 * Permission is granted to anyone to use this software for any purpose,
 * including commercial applications, and to alter it and redistribute it
 * freely, subject to the following restrictions:
 *
 * 1. The origin of this software must not be misrepresented; you must not
 *    claim that you wrote the original software. If you use this software
 *    in a product, an acknowledgement in the product documentation would be
 *    appreciated but is not required.
 * 2. Altered source versions must be plainly marked as such, and must not be
 *    misrepresented as being the original software.
 * 3. This notice may not be removed or altered from any source distribution.
 */
package nl.jochembroekhoff.jsontranslator.settings;

import java.io.File;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import nl.jochembroekhoff.jsontranslator.Main;

/**
 * JsonTranslator settings manager.
 *
 * @author Jochem Broekhoff
 */
public class SettingsManager {

    private final Main parent;
    private final SettingsFrame frame;
    private File settingsFile;
    private Properties props;
    private boolean okay = true;

    public SettingsManager(Main parent) {
        this.parent = parent;
        frame = new SettingsFrame(this);
        settingsFile = new File(System.getProperty("user.home"), ".jsontranslator_props");
        if (!settingsFile.exists()) {
            try {
                settingsFile.createNewFile();
            } catch (Exception e) {
                okay = false;
                SwingUtilities.invokeLater(()->{
                    JOptionPane.showMessageDialog(frame, "Couldn't load properties file!\nDefault settings are loaded now.", "File read error", JOptionPane.ERROR_MESSAGE);
                });
            }
        }
    }

    public void load() {
        if (okay) {

        }
    }

    public void save() {
        if (okay) {

        }
    }

    public JFrame getFrame() {
        return frame;
    }
}
