package com.chen.demo.event;

import org.springframework.context.ApplicationEvent;

/**
 * 
 * @ClassName:  UserRegisterEvent   
 * @Description:事件定义，定义了一个注册事件，然后等着别人处理 
 * @author: jim
 * @date:   2020年12月22日 上午11:49:17      
 * @Copyright:
 */
@SuppressWarnings("serial")
public class UserRegisterEvent extends ApplicationEvent {
    
    private String username;

    public UserRegisterEvent(Object source) {
        super(source);
    }

    public UserRegisterEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
