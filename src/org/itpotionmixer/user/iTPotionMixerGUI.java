package org.itpotionmixer.user;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class iTPotionMixerGUI extends JFrame {

    //private JButton jButton1;
    private JCheckBox jCheckBox1;
    private JCheckBox jCheckBox2;
    private JComboBox jComboBox1;
    //private JLabel jLabel1;
    //private JLabel jLabel4;
    //private JRadioButton jRadioButton1;
    //private JRadioButton jRadioButton2;

    private static final long serialVersionUID = 1L;

    public iTPotionMixerGUI() {
        super("iTPotionMixer");
        initComponents();
        initComponents();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(getParent());
    }

    private void initComponents() {
        final JButton jButton1 = new JButton("Start");
        final JLabel jLabel1 = new JLabel("Squeal spin ticket options:");
        final JLabel jLabel4 = new JLabel("Select potion:");
        jCheckBox1 = new JCheckBox("Enable antiban?");
        jCheckBox2 = new JCheckBox("Enable paint?");
        //jRadioButton1 = new JRadioButton("Claim ticket");
        //jRadioButton2 = new JRadioButton("Destroy ticket");
        jComboBox1 = new JComboBox(new String[]{"Agility Potion", "Antifire", "Antipoison", "Attack Potion",
                "Combat Potion", "Crafting Potion", "Defence Potion", "Energy Potion", "Fishing Potion", "Fletching Potion",
                "Hunter Potion", "Magic Potion", "Prayer Potion", "Prayer Renewal", "Ranging Potion", "Restore Potion",
                "Saradomin Brew", "Serum 207", "Strength Potion", "Super Antipoison", "Super Attack Potion", "Super Defence",
                "Super Energy Potion", "Super Restore Potion", "Super Strength Potion", "Weapon Poison", "Zamorak Brew",
                "Extreme Attack", "Extreme Strength", "Extreme Defence", "Extreme Ranging", "Extreme Magic", "Overload",
                "Unfinished Avantoe", "Unfinished Cadantine", "Unfinished Dwarf Weed", "Unfinished Fellstalk", "Unfinished Guam",
                "Unfinished Harralander", "Unfinished Irit", "Unfinished Kwuarm", "Unfinished Lantadyme", "Unfinished Marrentill",
                "Unfinished Ranarr", "Unfinished Snapdragon", "Unfinished Spirit Weed", "Unfinished Tarromin", "Unfinished Torstol",
                "Unfinished Wergali"});

        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed();
            }
        });

        jCheckBox2.setSelected(true);

        //jRadioButton1.setSelected(true);
        //jRadioButton1.setEnabled(false);
        //jRadioButton2.setEnabled(false);

        /*jComboBox1.setModel(new DefaultComboBoxModel(new String[]{"Agility Potion", "Antifire", "Antipoison", "Attack Potion",
                "Combat Potion", "Crafting Potion", "Defence Potion", "Energy Potion", "Fishing Potion", "Fletching Potion",
                "Hunter Potion", "Magic Potion", "Prayer Potion", "Prayer Renewal", "Ranging Potion", "Restore Potion",
                "Saradomin Brew", "Serum 207", "Strength Potion", "Super Antipoison", "Super Attack Potion", "Super Defence",
                "Super Energy Potion", "Super Restore Potion", "Super Strength Potion", "Weapon Poison", "Zamorak Brew",
                "Extreme Attack", "Extreme Strength", "Extreme Defence", "Extreme Ranging", "Extreme Magic", "Overload",
                "Unfinished Avantoe", "Unfinished Cadantine", "Unfinished Dwarf Weed", "Unfinished Fellstalk", "Unfinished Guam",
                "Unfinished Harralander", "Unfinished Irit", "Unfinished Kwuarm", "Unfinished Lantadyme", "Unfinished Marrentill",
                "Unfinished Ranarr", "Unfinished Snapdragon", "Unfinished Spirit Weed", "Unfinished Tarromin", "Unfinished Torstol",
                "Unfinished Wergali"})); */

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(
                        layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(
                                                        GroupLayout.Alignment.TRAILING,
                                                        layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 260,
                                                                        GroupLayout.PREFERRED_SIZE))
                                                .addGroup(
                                                        layout.createSequentialGroup()
                                                                .addGroup(
                                                                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel1)
                                                                                        /*.addGroup(
                                                                            layout.createSequentialGroup()
                                                                                    //.addComponent(jRadioButton1)
                                                                                    //.addGap(60, 60, 60)
                                                                                    .addComponent(jRadioButton2))    */
                                                                                .addComponent(jLabel4)
                                                                                .addGroup(
                                                                                        layout.createParallelGroup(
                                                                                                GroupLayout.Alignment.TRAILING, false)
                                                                                                .addComponent(jComboBox1, 0,
                                                                                                        GroupLayout.DEFAULT_SIZE,
                                                                                                        Short.MAX_VALUE)
                                                                                                .addGroup(
                                                                                                        layout.createSequentialGroup()
                                                                                                                .addComponent(
                                                                                                                        jCheckBox1,
                                                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                                                        137,
                                                                                                                        GroupLayout.PREFERRED_SIZE)
                                                                                                                .addPreferredGap(
                                                                                                                        LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                .addComponent(
                                                                                                                        jCheckBox2))))
                                                                .addGap(0, 0, Short.MAX_VALUE))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addGap(1, 1, 1)
                        .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup().addGap(7, 7, 7).addComponent(jCheckBox1))
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jCheckBox2)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                /*.addGroup(
                  layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jRadioButton1)
                          .addComponent(jRadioButton2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)      */
                        .addComponent(jButton1).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
    }

    private void jButton1ActionPerformed() {
        Variables.primary = Functions.getIngredients((String) jComboBox1.getSelectedItem())[0];
        Variables.secondary = Functions.getIngredients((String) jComboBox1.getSelectedItem())[1];
        Variables.abSupport = jCheckBox1.isSelected();
        Variables.paintSupport = jCheckBox2.isSelected();
        Variables.guiInitialized = true;
        Variables.status = "gui done";
        dispose();
    }
}
