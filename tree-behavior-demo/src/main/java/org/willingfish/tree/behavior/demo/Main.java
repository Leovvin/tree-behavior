package org.willingfish.tree.behavior.demo;

import org.willingfish.tree.behavior.core.Bootstrap;
import org.willingfish.tree.behavior.demo.tree1.RBTreeImpl;

public class Main {

    public static void main(String[] args){
        Bootstrap bootstrap = Bootstrap.builder().registerTree(RBTreeImpl.class).build();
        bootstrap.start();
    }
}
