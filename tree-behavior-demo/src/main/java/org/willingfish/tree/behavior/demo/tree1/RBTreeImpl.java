package org.willingfish.tree.behavior.demo.tree1;

import org.willingfish.tree.behavior.core.api.IShowBinNode;
import org.willingfish.tree.behavior.core.api.IShowTree;

import java.util.Stack;
import java.util.function.Consumer;

public class RBTreeImpl implements IShowTree<Integer> {

    RBNodeImpl root;
    RBNodeImpl hot;

    @Override
    public RBNodeImpl insert(Integer value) {
        RBNodeImpl node;
        if ((node = search(value)) != null) {
            return node;
        }
        if (root == null) {
            root = node = new RBNodeImpl(null, value);
        } else {
            node = new RBNodeImpl(hot, value);
            if (value < hot.getData()) {
                hot.left = node;
            } else {
                hot.right = node;
            }
        }

        balanceInsert(node);
        return node;
    }

    @Override
    public RBNodeImpl delete(Integer value) {
        RBNodeImpl node;
        if ((node = search(value)) == null) {
            return null;
        }
        if (node.getLeft() != null && node.getRight() != null) {
            RBNodeImpl succ = successor(node);
            node.data = succ.getData();
            node = succ;
        }
        RBNodeImpl replacement = node.getLeft() != null ? node.getLeft() : node.getRight();

        if (replacement!=null){
            replacement.parent = node.getParent();
            if (node == root){
                root = replacement;
            }else if (node.getParent().getLeft() == node){
                node.getParent().left = replacement;
            }else {
                node.getParent().right = replacement;
            }
            if (!node.isRed){
                balanceDelete(replacement);
            }
            node.parent = node.left = node.right = null;
        }else if (node == root){
            root = null;
        }else {
            if (!node.isRed){
                balanceDelete(node);
            }
            if (node.getParent()!=null){
                if (node.getParent().getLeft() == node){
                    node.getParent().left = null;
                }else {
                    node.getParent().right = null;
                }
                node.parent = null;
            }
        }


        return node;
    }

    RBNodeImpl successor(RBNodeImpl node) {
        if (node.getRight() != null) {
            RBNodeImpl next = node.getRight();
            while (next.getLeft() != null) {
                next = next.getLeft();
            }
            return next;
        } else {
            return node;
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public RBNodeImpl getRoot() {
        return root;
    }

    @Override
    public void traversal(Consumer<IShowBinNode> consumer) {
        if (root == null) {
            return;
        }
        Stack<IShowBinNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            IShowBinNode node = stack.pop();
            consumer.accept(node);
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
    }

    public RBNodeImpl search(Integer data) {
        if (root == null) {
            return null;
        }
        RBNodeImpl result = hot = root;
        while (result != null) {
            if (result.getData().equals(data)) {
                return result;
            } else if (result.getData() > data) {
                hot = result;
                result = result.getLeft();
            } else {
                hot = result;
                result = result.getRight();
            }
        }
        return null;
    }

    void balanceInsert(RBNodeImpl node) {
        RBNodeImpl x, xp, xpp;
        x = node;
        while (true) {
            if (x == root) {
                x.isRed = false;
                return;
            }
            if (!isRed(xp = x.getParent())) {
                return;
            }
            if ((xpp = xp.getParent()).getLeft() == xp) {
                if (isRed(xpp.getRight())) {
                    xpp.getRight().isRed = false;
                    xp.isRed = false;
                    xpp.isRed = true;
                    x = xpp;
                    continue;
                }
                if (xp.getRight() == x) {
                    rotateLeft(x = xp);
                }
                xp = x.getParent();
                xp.isRed = false;
                xpp.isRed = true;
                rotateRight(xpp);
                return;
            } else {
                if (isRed(xpp.getLeft())) {
                    xpp.getLeft().isRed = false;
                    xp.isRed = false;
                    xpp.isRed = true;
                    x = xpp;
                    continue;
                }
                if (xp.getLeft() == x) {
                    rotateRight(x = xp);
                }
                xp = x.getParent();
                xp.isRed = false;
                xpp.isRed = true;
                rotateLeft(xpp);
                return;
            }
        }
    }

    void balanceDelete(RBNodeImpl node) {
        if (node.isRed) {
            return;
        }

    }

    void rotateLeft(RBNodeImpl p) {
        RBNodeImpl r, pp, rl;
        if (p != null && (r = p.getRight()) != null) {
            if ((rl = p.right = r.getLeft()) != null) {
                rl.parent = p;
            }
            if ((pp = r.parent = p.getParent()) == null) {
                (root = r).isRed = false;
            } else if (pp.getLeft() == p) {
                pp.left = r;
            } else {
                pp.right = r;
            }
            r.left = p;
            p.parent = r;
        }
    }

    void rotateRight(RBNodeImpl node) {
        RBNodeImpl l, pp;
        l = node.getLeft();
        pp = node.getParent();

        if (l.getRight() != null) {
            l.getRight().parent = node;
        }
        node.left = l.getRight();
        node.parent = l;
        l.right = node;
        l.parent = pp;

        if (node != root) {
            if (pp.getLeft() == node) {
                pp.left = l;
            } else {
                pp.right = l;
            }
        } else {
            root = l;
        }
    }

    boolean isRed(RBNodeImpl node) {
        if (node == null) {
            return false;
        }
        return node.isRed();
    }
}
