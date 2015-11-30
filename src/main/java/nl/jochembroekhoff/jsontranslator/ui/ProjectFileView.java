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
package nl.jochembroekhoff.jsontranslator.ui;

import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;

public class ProjectFileView extends FileView {

    private final Icon icon;

    // Create ImageFileView to serve as a viewer for file icons.
    public ProjectFileView() {
        icon = new ImageIcon(getClass().getResource("/logo-mini.png"));
    }

    // Return the icon that associates with the file's type. If null returns, a
    // default icon is used.
    @Override
    public Icon getIcon(File f) {
        if (f.isDirectory()) {
            if (new File(f, ".jsontranslator").exists()) {
                return icon;
            }
        }

        return null;
    }
}
