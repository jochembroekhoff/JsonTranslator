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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Jochem Broekhoff
 */
public class Translator extends javax.swing.JPanel {

    private final Project project;
    private final String slug;
    private final File file;
    private JSONObject data;
    private boolean isSource = false;

    /**
     * Creates new form Translator
     *
     * @param project project
     * @param translationSlug translation identificator
     */
    public Translator(Project project, String translationSlug) {

        initComponents();
        this.project = project;
        slug = translationSlug;
        file = new File(project.getCwd(), slug + ".json");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Couldn't create the export tranlation file: " + e.getLocalizedMessage(),
                        "Error creating " + slug + ".json",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        try {
            data = (JSONObject) project.j.parse(new FileReader(file));
        } catch (IOException | ParseException e) {
            try {
                file.createNewFile();
                data = new JSONObject();
            } catch (Exception ex) {
                project.closeProject();
                JOptionPane.showMessageDialog(project, "Couldn't read translation file (" + slug + "): " + ex.getLocalizedMessage(), "File read error", JOptionPane.ERROR_MESSAGE);
            }
        }

        update();

        txt_translation.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!isSource) {
                    data.put(project.getActiveKey(), txt_translation.getText());
                } else {
                    txt_translation.setText("");
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        txt_source.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (isSource) {
                    data.put(project.getActiveKey(), txt_source.getText());
                } else {
                    txt_source.setText("");
                }
                project.updateTranslators();
            }

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public final void update() {
        if (project.getSourceTranslation().equals(slug)) {
            isSource = true;
        } else {
            isSource = false;
        }
        txt_source.setEditable(isSource);
        txt_source.setEnabled(isSource);
        txt_translation.setEditable(!isSource);
        txt_translation.setEnabled(!isSource);

        project.keys.stream().forEach((key) -> {
            data.putIfAbsent(key, "");
        });

        loadKey();
    }

    public String getString(String key) {
        if (project.keys.contains(key) && data.containsKey(key)) {
            return (String) data.get(key);
        }

        return "";
    }

    public void save() throws IOException {
        data = new JSONObject(data);
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(data.toJSONString());
        }
    }

    public void loadKey() {
        String key = project.getActiveKey();
        String text = "";
        if (data.containsKey(key)) {
            text = (String) data.get(key);
        }
        if (isSource) {
            txt_source.setText(text);
        } else {
            txt_translation.setText(text);
            String txt = "";
            try {
                txt = project.getSourceTranslator().getString(key);
                if (txt == null) {
                    txt = "";
                }
            } catch (Exception e) {

            }
            txt_source.setText(txt);
        }
    }

    public String getSlug() {
        return slug;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_source = new javax.swing.JLabel();
        lbl_translation = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_translation = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_source = new javax.swing.JTextArea();

        lbl_source.setText("Source:");

        lbl_translation.setText("Translation:");

        txt_translation.setColumns(20);
        txt_translation.setRows(5);
        jScrollPane2.setViewportView(txt_translation);

        txt_source.setColumns(20);
        txt_source.setRows(5);
        jScrollPane3.setViewportView(txt_source);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
                    .addComponent(lbl_source, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_translation, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_source)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_translation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_source;
    private javax.swing.JLabel lbl_translation;
    private javax.swing.JTextArea txt_source;
    private javax.swing.JTextArea txt_translation;
    // End of variables declaration//GEN-END:variables
}
