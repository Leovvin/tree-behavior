package org.willingfish.tree.behavior.core;

import org.willingfish.tree.behavior.core.api.IShowTree;
import org.willingfish.tree.behavior.core.ui.SwingContext;

public class Bootstrap {

    private Class<? extends IShowTree> treeClass;

    Bootstrap(){}

    public void start(){
        try {
            new SwingContext(treeClass).init();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static BootstrapBuilder builder(){
        return new BootstrapBuilder();
    }

    void setTree(Class<? extends IShowTree> treeClass){
        this.treeClass = treeClass;
    }
}
