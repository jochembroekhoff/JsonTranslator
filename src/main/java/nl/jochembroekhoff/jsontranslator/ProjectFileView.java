package nl.jochembroekhoff.jsontranslator;

import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;

public class ProjectFileView extends FileView {

    private Icon icon;

    // Create ImageFileView to serve as a viewer for file icons.
    ProjectFileView() {
        icon = new ImageIcon(getClass().getResource("/logo-mini.png"));
    }

    // Return the icon that associates with the file's type. If null returns, a
    // default icon is used.
    @Override
    public Icon getIcon(File f) {
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if(f.isDirectory()){
            if(new File(f, ".jsontranslator").exists()){
                return icon;
            }
        }

        return null;
    }

    // Return the file's name minus its extension for files with the bmp, gif,
    // jpeg, jpg, or png extensions.
    @Override
    public String getName(File f) {
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            String ext = s.substring(i + 1).toLowerCase();
            if (ext.equals("bmp") || ext.equals("gif")
                    || ext.equals("jpeg") || ext.equals("jpg") || ext.equals("png")) {
                return s.substring(0, i);
            }
        }
        return null;
    }

    // Return an individual file's description.
    @Override
    public String getDescription(File f) {
        // Let the look and feel figure out the description.

        return null;
    }

    // Determine if a directory is traversable.
    @Override
    public Boolean isTraversable(File f) {
        // Let the look and feel determine if the directory is traversable.

        return null;
    }
}
