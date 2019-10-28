package ${package}.support.tree;

import java.util.List;

/**
 * @author liusy
 */
public abstract class AbstractTreeModel<ID,T> {
     public abstract ID getId();
     public abstract ID getPid();
     public abstract ID getOrder();
     public abstract List<T> getChildren();

}
