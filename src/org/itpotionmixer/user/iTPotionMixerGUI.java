package org.itpotionmixer.user;

import org.powerbot.game.bot.Context;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class iTPotionMixerGUI extends JFrame {

    private JCheckBox jCheckBox1;
    private JCheckBox jCheckBox2;
    private JComboBox<String> jComboBox1;

    public iTPotionMixerGUI() {
        super("iTPotionMixer");
        initComponents();
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {

        final JButton jButton1 = new JButton("Start");
        final JLabel jLabel1 = new JLabel("Select potion:");

        jComboBox1 = new JComboBox<String>();
        jCheckBox1 = new JCheckBox("Antiban?");
        jCheckBox2 = new JCheckBox("Paint?");
        jComboBox1.setModel(new DefaultComboBoxModel<String>(new String[]{"Agility Potion", "Antifire", "Antipoison", "Attack Potion", "Combat Potion", "Crafting Potion", "Defence Potion", "Energy Potion", "Fishing Potion", "Fletching Potion", "Hunter Potion", "Summoning Potion", "Magic Potion", "Prayer Potion", "Prayer Renewal", "Ranging Potion", "Restore Potion", "Saradomin Brew", "Serum 207", "Recover Special", "Strength Potion", "Super Antipoison", "Super Attack", "Super Defence", "Super Energy Potion", "Super Restore Potion", "Super Strength Potion", "Weapon Poison", "Zamorak Brew", "Extreme Attack", "Extreme Strength", "Extreme Defence", "Extreme Ranging", "Extreme Magic", "Overload", "Unfinished Avantoe", "Unfinished Cadantine", "Unfinished Dwarf Weed", "Unfinished Fellstalk", "Unfinished Guam", "Unfinished Harralander", "Unfinished Irit ", "Unfinished Kwuarm", "Unfinished Lantadyme", "Unfinished Marrentill", "Unfinished Ranarr", "Unfinished Snapdragon", "Unfinished Spirit Weed", "Unfinished Tarromin", "Unfinished Torstol", "Unfinished Wergali"}));

        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed();
            }
        });

        jCheckBox1.setSelected(true);
        jCheckBox2.setSelected(true);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel1)
                                        .addComponent(jComboBox1, 0, 156, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jCheckBox1)
                                                .addGap(18, 18, 18)
                                                .addComponent(jCheckBox2))
                                        .addComponent(jButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jCheckBox1)
                                        .addComponent(jCheckBox2))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButton1ActionPerformed() {
        System.out.println(jComboBox1.getSelectedItem().toString());
        Variables.primary = Utilities.getIngredients((String) jComboBox1.getSelectedItem())[0];
        Variables.secondary = Utilities.getIngredients((String) jComboBox1.getSelectedItem())[1];
        System.out.println(Variables.primary + ":" + Variables.secondary);
        Variables.abSupport = jCheckBox1.isSelected();
        if (!jCheckBox1.isSelected()) {
            Context.get().b(2000);
        }
        Variables.paintSupport = jCheckBox2.isSelected();
        Variables.guiInitialized = true;
        Variables.status = "gui done";
        dispose();
    }
}