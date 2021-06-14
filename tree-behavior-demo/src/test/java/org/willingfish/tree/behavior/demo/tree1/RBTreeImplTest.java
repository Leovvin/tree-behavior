package org.willingfish.tree.behavior.demo.tree1;

import static org.junit.jupiter.api.Assertions.*;

class RBTreeImplTest {

    @org.junit.jupiter.api.Test
    void insert() {
        RBTreeImpl tree = new RBTreeImpl();

        tree.insert(50);
        tree.insert(25);
        tree.insert(75);
        tree.insert(13);
        tree.insert(37);
        tree.insert(63);
        tree.insert(87);


    }
}