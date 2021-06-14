package org.willingfish.tree.behavior.demo.tree1;

import org.willingfish.tree.behavior.core.api.IShowBinNode;

public class RBNodeImpl implements IShowBinNode<Integer> {
    RBNodeImpl parent;
    RBNodeImpl left;
    RBNodeImpl right;
    boolean isRed;
    Integer data;

    public RBNodeImpl(RBNodeImpl parent, Integer data){
        this.parent = parent;
        this.data = data;
        this.isRed = true;
    }

    @Override
    public RBNodeImpl getParent() {
        return parent;
    }

    @Override
    public RBNodeImpl getLeft() {
        return left;
    }

    @Override
    public RBNodeImpl getRight() {
        return right;
    }

    @Override
    public boolean isRed() {
        return isRed;
    }

    @Override
    public Integer getData() {
        return data;
    }
}
