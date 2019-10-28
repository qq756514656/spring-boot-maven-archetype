package ${package}.support;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * @author liusy
 */

@Data
@Builder
@ApiModel
public class Grid<T> implements Serializable {
    private static final long serialVersionUID = -3991293096532501572L;
    @ApiModelProperty(value = "每页数据")
    @Builder.Default
    private List<T> list = Lists.newArrayList();
    @ApiModelProperty(value = "分页数据对象")
    @Builder.Default
    private Pagination pagination = Pagination.builder().build();

    @Builder
    @Data
    private static class Pagination {
        @ApiModelProperty(value = "数据总数", example = "0")
        @Builder.Default
        private Long total = 0L;
        @ApiModelProperty(value = "每页大小", example = "10")
        @Builder.Default
        private Long pageSize = 10L;
        @ApiModelProperty(value = "当前页", example = "1")
        @Builder.Default
        private Long current = 1L;
    }

    public static <T> Grid<T> of(IPage<T> page) {
        return Grid.<T>builder()
                .list(page.getRecords())
                .pagination(Grid.Pagination.builder()
                        .current(page.getCurrent())
                        .pageSize(page.getSize())
                        .total(page.getTotal())
                        .build())
                .build();
    }
}
