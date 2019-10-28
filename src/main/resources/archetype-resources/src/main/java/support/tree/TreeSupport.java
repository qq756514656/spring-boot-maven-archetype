package ${package}.support.tree;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author liusy
 */
public class TreeSupport<ID, T extends AbstractTreeModel<ID, T>> {

    private static final String PARENT = "parent";
    private static final String CHILD = "child";
    private static final String ZERO = "0";
    private static final String EMPTY = "";


    public TreeSupport() {
    }

    /**
     * 获取tree型格式数据
     *
     * @return List<T>
     */
    public List<T> getTreeDataList() throws Exception {
        List<T> dataList = setDataList();

        if (dataList == null || dataList.isEmpty()) {
            return null;
        }

        Map<String, Map<ID, T>> map = Maps.newHashMap();
        Map<ID, T> mapPatent = Maps.newHashMap();
        Map<ID, T> mapChild = Maps.newHashMap();

        for (T t : dataList) {
            ID pid = t.getPid();

            if (pid == null || EMPTY.equals((pid)) || ZERO.equals((pid))) {
                mapPatent.put(t.getId(), t);
            } else {
                mapChild.put(t.getId(), t);
            }
        }

        if (mapPatent.isEmpty()) {
            throw new Exception("数据缺少根节点,根节点值为空,或空字符串,或0");
        }

        map.put(PARENT, mapPatent);
        map.put(CHILD, mapChild);
        return getTreeGridModelList(map);
    }

    /**
     * 设置转换后的数据
     *
     * @return
     */
    protected List<T> setDataList() {

        return null;
    }

    private List<T> getTreeGridModelList(Map<String, Map<ID, T>> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        List<T> dodfin = Lists.newArrayList();

        Map<ID, T> parent = map.get(PARENT);
        Map<ID, T> child = map.get(CHILD);

        processSubData(parent, child);
        Collection<T> values = parent.values();

        for (T v : values) {
            dodfin.add(v);
        }

        return dodfin;
    }

    /**
     * 处理子数据
     *
     * @param parent
     * @param subdata
     */
    private void processSubData(Map<ID, T> parent, Map<ID, T> subdata) {
        if (parent != null && parent != null) {

            Map<ID, T> subParent = Maps.newHashMap();
            Map<ID, T> temp = Maps.newHashMap();
            Set<ID> sd = subdata.keySet();

            for (ID id : sd) {
                T childGridModel = subdata.get(id);
                T parentGridModel;
                if (parent.containsKey(childGridModel.getPid())) {
                    parentGridModel = parent.get(childGridModel.getPid());
                    parentGridModel.getChildren().add(childGridModel);
                    temp.put(id, childGridModel);
                    subParent.put(id, childGridModel);
                }
            }

            subdata.values().removeAll(temp.values());
            if (!subdata.isEmpty()) {
                processSubData(subParent, subdata);
            }
        }
    }
}
