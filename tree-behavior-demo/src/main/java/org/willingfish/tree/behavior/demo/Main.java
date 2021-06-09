package org.willingfish.tree.behavior.demo;

import org.willingfish.tree.behavior.core.Bootstrap;
import org.willingfish.tree.behavior.demo.tree.RBTree;

public class Main {

    public static void main(String[] args){
        Bootstrap bootstrap = Bootstrap.builder().registerTree(RBTree.class).build();
        bootstrap.start();
    }
}
