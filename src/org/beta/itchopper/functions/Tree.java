package org.beta.itchopper.functions;

import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Tree {

    private SceneObject tree;
    private int[] id;
    private int log;

    public Tree(int log, int... id) {
        this.id = id;
        this.log = log;
    }

    public SceneObject get() {
        return tree == null ? (tree = SceneEntities.getNearest(id)) : tree;
    }

    public Item[] getItems() {
        return Inventory.getItems(new Filter<Item>() {
            @Override
            public boolean accept(Item i) {
                return i.getId() == log;
            }
        });
    }
}
