package club.odty.odty.base.Util;

import java.util.UUID;

public class UUIDUtil {
    public static String createUuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
