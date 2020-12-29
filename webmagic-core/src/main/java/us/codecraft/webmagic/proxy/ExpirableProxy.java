package us.codecraft.webmagic.proxy;

import lombok.Getter;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author yaoqiang
 *
 * 可以过期的代理
 */
@Contract(threading = ThreadingBehavior.IMMUTABLE_CONDITIONAL)
public class ExpirableProxy extends Proxy {
    @Getter
    private final int ttl;
    private final LocalDateTime expireTime;


    public ExpirableProxy(String host, int port, int ttl, ChronoUnit chronoUnit) {
        super(host, port);
        this.ttl = ttl;
        this.expireTime = LocalDateTime.now().plus(ttl, chronoUnit);

    }

    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }
    public LocalDateTime getExpireTime(){
        return expireTime;
    }

}
