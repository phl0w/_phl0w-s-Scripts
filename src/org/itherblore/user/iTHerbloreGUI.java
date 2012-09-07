package org.itherblore.user;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class iTHerbloreGUI extends JFrame {

    private JCheckBox jCheckBox1;
    private JCheckBox jCheckBox2;
    private JCheckBox jCheckBox3;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton2;
    private JComboBox<String> jComboBox1;

    public iTHerbloreGUI() {
        super("iTHerbloreGUI");
        initComponents();
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {

        final JLabel jLabel1 = new JLabel("Type:");
        final JLabel jLabel2 = new JLabel("Please select your potion:");
        final JButton jButton1 = new JButton("Start");
        jRadioButton1 = new JRadioButton("Potion mixing");
        jRadioButton2 = new JRadioButton("Herb cleaning");
        jComboBox1 = new JComboBox<String>();
        jCheckBox1 = new JCheckBox("Faster mouse click?"); //need: 17, cur:
        jCheckBox2 = new JCheckBox("Antiban?");
        jCheckBox3 = new JCheckBox("Paint?");
        jComboBox1.setModel(new DefaultComboBoxModel<String>(new String[]{"Agility Potion", "Antifire", "Antipoison", "Attack Potion", "Combat Potion", "Crafting Potion", "Defence Potion", "Energy Potion", "Extreme Attack", "Extreme Defence", "Extreme Magic", "Extreme Ranging", "Extreme Strength", "Fishing Potion", "Fletching Potion", "Hunter Potion", "Magic Potion", "Overload", "Prayer Potion", "Prayer Renewal", "Ranging Potion", "Recover Special", "Restore Potion", "Saradomin Brew", "Serum 207", "Strength Potion", "Summoning Potion", "Super Antifire", "Super Antipoison", "Super Attack", "Super Defence", "Super Energy Potion", "Super Restore Potion", "Super Strength Potion", "Weapon Poison", "Zamorak Brew", "Unfinished Avantoe", "Unfinished Cadantine", "Unfinished Dwarf Weed", "Unfinished Fellstalk", "Unfinished Guam", "Unfinished Harralander", "Unfinished Irit", "Unfinished Kwuarm", "Unfinished Lantadyme", "Unfinished Marrentill", "Unfinished Ranarr", "Unfinished Snapdragon", "Unfinished Spirit Weed", "Unfinished Tarromin", "Unfinished Toadflax", "Unfinished Torstol", "Unfinished Wergali"}));


        jRadioButton1.setSelected(true);
        jCheckBox2.setSelected(true);
        jCheckBox3.setSelected(true);

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

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(jLabel1)
                                                                .addGap(170, 170, 170))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                                .addComponent(jRadioButton1)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jRadioButton2)))
                                                                .addGap(16, 16, 16))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(jComboBox1, 0, 0, Short.MAX_VALUE)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(jCheckBox1)
                                                                        .addGap(7, 7, 7))
                                                                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                        .addComponent(jCheckBox2)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(jCheckBox3)))
                                                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jRadioButton1)
                                        .addComponent(jRadioButton2))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jCheckBox2)
                                        .addComponent(jCheckBox3))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void jRadioButton1ActionPerformed(ActionEvent evt) {
        if (jRadioButton2.isSelected()) {
            jRadioButton1.setSelected(true);
            jRadioButton2.setSelected(false);
        } else {
            jRadioButton2.setSelected(true);
            jRadioButton2.setSelected(false);
        }
        jComboBox1.setModel(new DefaultComboBoxModel<String>(new String[]{"Agility Potion", "Antifire", "Antipoison", "Attack Potion", "Combat Potion", "Crafting Potion", "Defence Potion", "Energy Potion", "Extreme Attack", "Extreme Defence", "Extreme Magic", "Extreme Ranging", "Extreme Strength", "Fishing Potion", "Fletching Potion", "Hunter Potion", "Magic Potion", "Overload", "Prayer Potion", "Prayer Renewal", "Ranging Potion", "Recover Special", "Restore Potion", "Saradomin Brew", "Serum 207", "Strength Potion", "Summoning Potion", "Super Antifire", "Super Antipoison", "Super Attack", "Super Defence", "Super Energy Potion", "Super Restore Potion", "Super Strength Potion", "Weapon Poison", "Zamorak Brew", "Unfinished Avantoe", "Unfinished Cadantine", "Unfinished Dwarf Weed", "Unfinished Fellstalk", "Unfinished Guam", "Unfinished Harralander", "Unfinished Irit", "Unfinished Kwuarm", "Unfinished Lantadyme", "Unfinished Marrentill", "Unfinished Ranarr", "Unfinished Snapdragon", "Unfinished Spirit Weed", "Unfinished Tarromin", "Unfinished Toadflax", "Unfinished Torstol", "Unfinished Wergali"}));
    }

    private void jRadioButton2ActionPerformed(ActionEvent evt) {
        if (jRadioButton1.isSelected()) {
            jRadioButton2.setSelected(true);
            jRadioButton1.setSelected(false);
        } else {
            jRadioButton2.setSelected(false);
            jRadioButton1.setSelected(true);
        }
        jComboBox1.setModel(new DefaultComboBoxModel<String>(new String[]{"Guam", "Marrentil", "Tarromin", "Harralander", "Ranarr", "Toadflax", "Spirit Weed", "Irit", "Wergali", "Avantoe", "Kwuarm", "Snapdragon", "Cadantine", "Lantadyme", "Dwarf Weed", "Torstol", "Fellstalk"}));
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        Variables.abSupport = jCheckBox2.isSelected();
        Variables.paintSupport = jCheckBox3.isSelected();
        Variables.quick = jCheckBox1.isSelected();
        Variables.pots = jRadioButton1.isSelected();
        if (jRadioButton1.isSelected()) {
            int[] ingredients = Utilities.getIngredients((String) jComboBox1.getSelectedItem());
            Variables.primary = ingredients[0];
            Variables.secondary = ingredients[1];
        } else {
            Variables.primary = Utilities.getHerb((String) jComboBox1.getSelectedItem());
        }
        Variables.status = "gui done";
        Variables.guiInitialized = true;
        dispose();
    }


}


