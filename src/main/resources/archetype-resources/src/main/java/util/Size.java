package ${package}.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author liusy
 */
@Slf4j
public final class Size {

    protected Size() {
    }

    private static final int INT = 1024;

    public static String of(long size) {
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        double value = (double) size;
        if (value < INT) {
            return value + "B";
        } else {
            value = BigDecimal.valueOf(value / INT).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (value < INT) {
            return value + "KB";
        } else {
            value = BigDecimal.valueOf(value / INT).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        }
        if (value < INT) {
            return value + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            value = BigDecimal.valueOf(value / INT).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            return value + "GB";
        }
    }
}
