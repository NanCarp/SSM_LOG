package com.github.nancarp.service;

import com.github.nancarp.domain.User;

/**
 * Created by NanCarp on 2017/8/3.
 */
public interface UserService extends BaseService<User> {
    void add(User user) throws Exception;
}
