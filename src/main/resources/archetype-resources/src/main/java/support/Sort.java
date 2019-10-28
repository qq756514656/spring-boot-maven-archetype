package ${package}.support;

import ${package}.util.StringTool;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;

/**
 * 处理排序工具类
 */
public final class Sort {
    protected Sort() {
    }

    public static final String DEFAULT_SORT = "id";
    public static final String DEFAULT_ORDER = "descend";

    /**
     * 处理排序（分页情况下） for mybatis-plus
     *
     * @param pageVo           request
     * @param page              Page
     * @param defaultSort       默认排序的字段
     * @param defaultOrder      默认排序规则
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void bind(PageVo pageVo, Page page, String defaultSort, String defaultOrder, boolean camelToUnderscore) {
        page.setCurrent(pageVo.getCurrentPage());
        page.setSize(pageVo.getPageSize());
        String sorter = pageVo.getSorter();
        if (StringUtils.isBlank(sorter)) {
            if (StringUtils.isNotBlank(defaultSort)) {
                defaultSort = StringTool.camelToUnderscore(defaultSort);
                if (StringUtils.equals(defaultOrder, "descend")) {
                    page.setDesc(defaultSort);
                } else {
                    page.setAsc(defaultSort);
                }
            }
            return;
        }
        String[] s = sorter.split(StringPool.UNDERSCORE);
        String sortField = s[0];
        String sortOrder = s[1];
        if (camelToUnderscore) {
            sortField = StringTool.camelToUnderscore(sortField);
        }
        if (StringUtils.isNotBlank(sortField)
                && StringUtils.isNotBlank(sortOrder)
                && !StringUtils.equalsIgnoreCase(sortField, "undefined")
                && !StringUtils.equalsIgnoreCase(sortOrder, "undefined")) {
            if (StringUtils.equals(sortOrder, "descend")) {
                page.setDesc(sortField);
            } else {
                page.setAsc(sortField);
            }
        }
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param pageVo QueryReq
     * @param page    Page
     */
    public static void bind(PageVo pageVo, Page page) {
        bind(pageVo, page, null, null, false);
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param pageVo           QueryReq
     * @param page              Page
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void bind(PageVo pageVo, Page page, boolean camelToUnderscore) {
        bind(pageVo, page, DEFAULT_SORT, DEFAULT_ORDER, camelToUnderscore);
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param pageVo           QueryReq
     * @param wrapper           wrapper
     * @param defaultSort       默认排序的字段
     * @param defaultOrder      默认排序规则
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void handleWrapperSort(PageVo pageVo, QueryWrapper wrapper, String defaultSort, String defaultOrder, boolean camelToUnderscore) {
        String sorter = pageVo.getSorter();
        if (StringUtils.isBlank(sorter)) {
            if (StringUtils.isNotBlank(defaultSort)) {
                defaultSort = StringTool.camelToUnderscore(defaultSort);
                if (StringUtils.equals(defaultOrder, "descend")) {
                    wrapper.orderByDesc(defaultSort);
                } else {
                    wrapper.orderByAsc(defaultSort);
                }
            }
            return;
        }
        String[] s = sorter.split(StringPool.UNDERSCORE);
        String sortField = s[0];
        String sortOrder = s[1];
        if (camelToUnderscore) {
            sortField = StringTool.camelToUnderscore(sortField);
        }
        if (StringUtils.isNotBlank(sortField)
                && StringUtils.isNotBlank(sortOrder)
                && !StringUtils.equalsIgnoreCase(sortField, "undefined")
                && !StringUtils.equalsIgnoreCase(sortOrder, "undefined")) {
            if (StringUtils.equals(sortOrder, "descend")) {
                wrapper.orderByDesc(sortField);
            } else {
                wrapper.orderByAsc(sortField);
            }
        }
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param pageVo QueryReq
     * @param wrapper wrapper
     */
    public static void handleWrapperSort(PageVo pageVo, QueryWrapper wrapper) {
        handleWrapperSort(pageVo, wrapper, null, null, false);
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param pageVo           QueryReq
     * @param wrapper           wrapper
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void handleWrapperSort(PageVo pageVo, QueryWrapper wrapper, boolean camelToUnderscore) {
        handleWrapperSort(pageVo, wrapper, null, null, camelToUnderscore);
    }
}
