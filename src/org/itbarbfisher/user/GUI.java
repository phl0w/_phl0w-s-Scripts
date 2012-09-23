package org.itbarbfisher.user;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton2;
    private JRadioButton jRadioButton3;
    private JRadioButton jRadioButton4;
    private JComboBox<String> jComboBox1;

    public GUI() {
        super("iTBarbFisher");
        initComponents();
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {

        jRadioButton1 = new JRadioButton("Fast dropping");
        jRadioButton2 = new JRadioButton("Slow dropping");
        jRadioButton3 = new JRadioButton("Enabled");
        jRadioButton4 = new JRadioButton("Disabled");
        jComboBox1 = new JComboBox<String>();
        final JLabel jLabel1 = new JLabel("Dropping");
        final JLabel jLabel2 = new JLabel("Summoning");
        final JLabel jLabel3 = new JLabel("Have summong/super restore potion");
        final JLabel jLabel4 = new JLabel("if summoning is enabled!");
        final JLabel jLabel5 = new JLabel("Script made by _phl0w on PB");
        final JLabel jLabel6 = new JLabel("Decorated fishing urn");
        final JButton jButton1 = new JButton("Start!");
        jComboBox1.setModel(new DefaultComboBoxModel<String>(new String[]{"None", "Granite crab (16 summoning)", "Ibis (56 summoning)",
                "Granite lobster (74 summoning)"}));


        jRadioButton1.setSelected(true);
        jRadioButton4.setSelected(true);

        jRadioButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jRadioButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jRadioButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jRadioButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                        .addGroup(
                                                                layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jRadioButton2)
                                                                        .addComponent(jRadioButton1)
                                                                        .addComponent(jLabel1)
                                                                        .addComponent(jLabel2)
                                                                        .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jLabel4)
                                                                        .addComponent(jButton1, GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addContainerGap())
                                        .addGroup(
                                                GroupLayout.Alignment.TRAILING,
                                                layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(jLabel5)
                                                        .addGap(29, 29, 29))
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                        .addGroup(
                                                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jRadioButton4).addComponent(jLabel6)
                                                                        .addComponent(jRadioButton3)).addGap(0, 0, Short.MAX_VALUE)))));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addComponent(jLabel1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jRadioButton1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jRadioButton2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel6)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jRadioButton3)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jRadioButton4)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel3)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel4)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jButton1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel5).addContainerGap()));
        pack();
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        Variables.urns = jRadioButton3.isSelected();
        Variables.quickDrop = jRadioButton1.isSelected();
        Variables.pouch = Utilities.getPouch((String) jComboBox1.getSelectedItem());
        Variables.guiInitialized = true;
        dispose();
    }

    private void jRadioButton1ActionPerformed(ActionEvent evt) {
        jRadioButton1.setSelected(true);
        jRadioButton2.setSelected(false);
    }

    private void jRadioButton2ActionPerformed(ActionEvent evt) {
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(true);
    }

    private void jRadioButton3ActionPerformed(ActionEvent evt) {
        jRadioButton3.setSelected(true);
        jRadioButton4.setSelected(false);
    }

    private void jRadioButton4ActionPerformed(ActionEvent evt) {
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(true);
    }
}
