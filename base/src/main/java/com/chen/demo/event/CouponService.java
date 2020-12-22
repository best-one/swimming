package com.chen.demo.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 注解方式 @EventListener
 * @author Summerday
 */
@Service
@Slf4j
public class CouponService {
    /**
     * 监听用户注册事件,执行发放优惠券逻辑
     */
    @EventListener
    public void addCoupon(UserRegisterEvent event) {
        log.info("给用户[{}]发放优惠券", event.getUsername());
    }
}
