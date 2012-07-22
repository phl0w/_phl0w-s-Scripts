package org.itplanker;

import org.itplanker.constants.Data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class iTPlankerGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JComboBox plankSelection;
    private JCheckBox restCheckBox;
    private JTextField restTime;


    public iTPlankerGUI() {
        super("iTPlanker");
        initComponents();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(getParent());
    }

    private void initComponents() {

        final JLabel label = new JLabel("Select log type:");
        final JButton startButton = new JButton("Start");

        plankSelection = new JComboBox(new String[]{
                "Normal logs -> Normal planks", "Oak logs -> Oak planks",
                "Teak logs -> Teak planks", "Mahogany logs -> Mahogany planks"
        });
        restCheckBox = new JCheckBox("Enable resting at ", true);
        restTime = new JTextField("40");

        restCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        final GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                        .addComponent(restCheckBox)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(restTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE)).addComponent(label)
                                        .addComponent(plankSelection, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(startButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plankSelection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(restCheckBox)
                                        .addComponent(restTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(startButton).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
    }

    private void jRadioButton1ActionPerformed(final ActionEvent evt) {
        restTime.setEnabled(restCheckBox.isSelected());
        restCheckBox.setSelected(restCheckBox.isSelected());
    }

    private void jButton1ActionPerformed(final ActionEvent evt) {
        Variables.guiDone = true;
        Variables.restAt = Integer.parseInt(restTime.getText());
        Variables.restEnabled = restCheckBox.isEnabled();
        Variables.profitPerLog = Data.getProfitPerLog();
        setLogForName(plankSelection.getSelectedItem().toString());
        Variables.startTime = System.currentTimeMillis();
        Variables.status = "gui done";
        dispose();
    }

    public static void setLogForName(final String str) {
        if (str.equalsIgnoreCase("Normal logs -> Normal planks")) {
            Variables.log = Data.NORMAL_LOG;
        } else if (str.equalsIgnoreCase("Oak logs -> Oak planks")) {
            Variables.log = Data.OAK_LOG;
        } else if (str.equalsIgnoreCase("Teak logs -> Teak planks")) {
            Variables.log = Data.TEAK_LOG;
        } else if (str.equalsIgnoreCase("Mahogany logs -> Mahogany planks")) {
            Variables.log = Data.MAHOGANY_LOG;
        }
    }
}
