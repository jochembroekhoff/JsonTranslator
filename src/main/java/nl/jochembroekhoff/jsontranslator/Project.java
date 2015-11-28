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
package nl.jochembroekhoff.jsontranslator;

import java.awt.Component;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Project class
 *
 * @author Jochem Broekhoff
 */
public class Project extends javax.swing.JFrame {

    private final Main parent;
    /**
     * Current Working Dir
     */
    private File cwd;
    private File projectFile;
    private boolean active = true;
    private JSONObject projectData = new JSONObject();
    public JSONParser j = new JSONParser();
    private boolean initWithoutLoadingExistingProjectFile = false;
    protected List<String> keys = new ArrayList<>();
    private Map<String, String> reverseTranslations = new HashMap<>();

    /**
     * Creates new form Project
     *
     * @param parent parent
     */
    public Project(Main parent) {
        initComponents();
        try {
            setIconImage(ImageIO.read(getClass().getClassLoader().getResource("logo.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.parent = parent;
        projectData.put("translations", new JSONObject());
        projectData.put("keys", new JSONArray());
        projectData.put("sourceTranslation", "");

        cwd = chooseFolder("Select project folder");
        if (cwd == null) {
            closeProject();
        } else {
            projectFile = new File(cwd, ".jsontranslator");
            if (projectFile.exists()) {
                switch (JOptionPane.showConfirmDialog(null, "This folder seems to be a project already. Would you like to load it?")) {
                    case 0:

                        break;
                    case 1:
                        try {
                            initWithoutLoadingExistingProjectFile = true;
                            createNewProjectFile();
                        } catch (IOException e) {
                            closeProject();
                        }
                        break;
                    case 2:
                        closeProject();
                        break;
                }
            } else {
                try {
                    createNewProjectFile();
                } catch (IOException e) {
                    closeProject();
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tab_translations = new javax.swing.JTabbedPane();
        scp_keys = new javax.swing.JScrollPane();
        lst_keys = new javax.swing.JList<>();
        lbl_keys = new javax.swing.JLabel();
        mnubar = new javax.swing.JMenuBar();
        mnu_project = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        mnu_edit = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        mnu_translation = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("New Project - JsonTranslator");

        lst_keys.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "loading keys..." };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lst_keys.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lst_keys.setToolTipText("");
        lst_keys.setDragEnabled(true);
        lst_keys.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lst_keysMouseClicked(evt);
            }
        });
        scp_keys.setViewportView(lst_keys);

        lbl_keys.setText("Keys:");

        mnu_project.setText("Project");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Save");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mnu_project.add(jMenuItem2);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Rename");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        mnu_project.add(jMenuItem7);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Close");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mnu_project.add(jMenuItem1);

        mnubar.add(mnu_project);

        mnu_edit.setText("Edit");

        jMenu3.setText("Add");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Key");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Translation");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        mnu_edit.add(jMenu3);

        mnubar.add(mnu_edit);

        mnu_translation.setText("Translation");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Set source translation");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        mnu_translation.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Remove translation");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        mnu_translation.add(jMenuItem6);

        mnubar.add(mnu_translation);

        setJMenuBar(mnubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scp_keys, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbl_keys)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab_translations, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_keys)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scp_keys, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE))
                    .addComponent(tab_translations)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        closeProject();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        saveProject();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        JCheckBox checkbox = new JCheckBox("Set source translation");
        panel.add(new JLabel("Slug:"));
        panel.add(field1);
        panel.add(new JLabel("Name:"));
        panel.add(field2);
        panel.add(checkbox);
        int result = JOptionPane.showConfirmDialog(null,
                panel,
                "Add Translation",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            addTranslation(field1.getText(), field2.getText());
            if (checkbox.isSelected()) {
                setSourceTranslation(field1.getText());
            }
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        setSourceTranslation(getActiveTranslator().getSlug());
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        projectData.put("translations", getTranslations().remove(getActiveTranslator().getSlug()));
        if (projectData.get("sourceTranslation").equals(getActiveTranslator().getSlug())) {
            projectData.put("srouceTranslation", "");
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        String name = JOptionPane.showInputDialog("Enter new name");
        projectData.put("projectName", name);
        setTitle(name + " - JsonTranslator");
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField field2 = new JTextField();
        panel.add(new JLabel("Key name:"));
        panel.add(field2);
        int result = JOptionPane.showConfirmDialog(null,
                panel,
                "Add Translation",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            keys.add(field2.getText());
            DefaultListModel mdl = new DefaultListModel();
            for (String key : keys) {
                mdl.addElement(key);
            }
            lst_keys.setModel(mdl);
            updateTranslators();
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void lst_keysMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lst_keysMouseClicked
        for (Component c : tab_translations.getComponents()) {
            Translator tr = (Translator) c;
            tr.loadKey();
        }
    }//GEN-LAST:event_lst_keysMouseClicked

    public void addTranslation(String slug, String name) {
        if (!getTranslations().containsKey(slug)) {
            Translator translator = new Translator(this, slug);
            tab_translations.addTab(name, translator);
            JSONObject translations = getTranslations(),
                    thisTransl = new JSONObject();
            thisTransl.put("name", name);
            translations.put(slug, thisTransl);
            JOptionPane.showMessageDialog(null, translations.toJSONString());
            projectData.put("translations", translations);
        }
    }

    public JSONObject getTranslations() {
        return (JSONObject) projectData.get("translations");
    }

    public void setSourceTranslation(String slug) {
        if (getTranslations().containsKey(slug)) {
            projectData.put("sourceTranslation", slug);
        }
        updateTranslators();
    }

    public File getCwd() {
        return cwd;
    }

    private File chooseFolder(String title) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(title);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileView(new ProjectFileView());

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        } else {
            return null;
        }
    }

    public final void closeProject() {
        setVisible(false);
        parent.setVisible(true);
        active = false;
    }

    private void createNewProjectFile() throws IOException {
        projectFile.createNewFile();
        projectData.put("translations", new JSONObject());
        projectData.put("keys", new JSONArray());
        projectData.put("sourceTranslation", "");
    }

    public void initialize() {
        if (active) {
            if (!initWithoutLoadingExistingProjectFile) {
                parent.setVisible(false);
                //load project
                loadProjectFile();
            }
        } else {
            setVisible(false);
            parent.setVisible(true);
        }
    }

    private void loadProjectFile() {
        boolean newfile = false;

        try {
            projectData = (JSONObject) j.parse(new FileReader(projectFile));

            //translations
            if (projectData.containsKey("translations")) {
                for (Iterator iterator = getTranslations().keySet().iterator(); iterator.hasNext();) {
                    String key = (String) iterator.next();

                    String translationName = (String) ((JSONObject) getTranslations().get(key)).get("name");

                    Translator translator = new Translator(this, key);
                    tab_translations.addTab(translationName, translator);

                    reverseTranslations.put(translationName, key);
                }
            } else {
                projectData.put("translations", new JSONObject());
            }

            //keys
            if (projectData.containsKey("keys")) {
                JSONArray keys_ = (JSONArray) projectData.get("keys");
                DefaultListModel mdl = new DefaultListModel();
                for (Object key : keys_) {
                    keys.add((String) key);
                    mdl.addElement(key);
                }
                lst_keys.setModel(mdl);
            }

            //projectName
            if (projectData.containsKey("projectName")) {
                setTitle(projectData.get("projectName") + " - JsonTranslator");
            }
        } catch (IOException | ParseException ex) {
            newfile = true;
        }

        if (newfile) {
            try {
                createNewProjectFile();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error while opening/creating project: " + e.getLocalizedMessage(), "Open/create error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveProject() {
        try {
            //prepare projectData
            if (!((String) projectData.get("sourceTranslation")).equals("")
                    && getTranslations().containsKey(projectData.get("sourceTranslation"))) {
                //save .jsontranslator
                try (FileWriter fw = new FileWriter(projectFile)) {
                    fw.write(projectData.toJSONString());
                }

                projectData.put("keys", keys);

                for (Component c : tab_translations.getComponents()) {
                    Translator tr = (Translator) c;
                    tr.save();
                }

                for (Iterator iterator = getTranslations().keySet().iterator(); iterator.hasNext();) {
                    String key = (String) iterator.next();
                    System.out.println(key + "/" + getTranslations().get(key));
                }

                JOptionPane.showMessageDialog(null, "Project saved");
            } else {
                JOptionPane.showMessageDialog(null, "Save failed: No (or incorrect) source translation", "Save error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Save failed: " + e.getLocalizedMessage(), "Save error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getSourceTranslation() {
        return (String) projectData.get("sourceTranslation");
    }

    public Translator getTranslator(String slug) {
        //return (Translator) tab_translations.getSelectedComponent();
        if (reverseTranslations.containsKey(slug)) {
            return (Translator) tab_translations.getComponentAt(tab_translations.indexOfTab(reverseTranslations.get(slug)));
        }
        return null;
    }

    public void updateTranslators() {
        for (Component c : tab_translations.getComponents()) {
            Translator tr = (Translator) c;
            tr.update();
        }
    }

    public String getActiveKey() {
        if (lst_keys.isSelectionEmpty()) {
            lst_keys.setSelectedIndex(0);
        }
        return lst_keys.getSelectedValue();
    }

    public Translator getActiveTranslator() {
        return (Translator) tab_translations.getSelectedComponent();
    }

    public Translator getSourceTranslator() {
        for (Component c : tab_translations.getComponents()) {
            Translator tr = (Translator) c;
            if (tr.getSlug().equals(getSourceTranslation())) {
                return tr;
            }
        }
        return null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JLabel lbl_keys;
    private javax.swing.JList<String> lst_keys;
    private javax.swing.JMenu mnu_edit;
    private javax.swing.JMenu mnu_project;
    private javax.swing.JMenu mnu_translation;
    private javax.swing.JMenuBar mnubar;
    private javax.swing.JScrollPane scp_keys;
    private javax.swing.JTabbedPane tab_translations;
    // End of variables declaration//GEN-END:variables
}
