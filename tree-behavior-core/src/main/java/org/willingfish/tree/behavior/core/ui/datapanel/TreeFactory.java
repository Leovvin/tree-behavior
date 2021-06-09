package org.willingfish.tree.behavior.core.ui.datapanel;

import org.willingfish.tree.behavior.core.api.IShowTree;

public class TreeFactory {
    InternalTree generate(IShowTree<Integer> tree) {
        InternalTree internalTree = new InternalTree();
        tree.traversal(node -> internalTree.addNode(node));
        return internalTree;
    }


}
