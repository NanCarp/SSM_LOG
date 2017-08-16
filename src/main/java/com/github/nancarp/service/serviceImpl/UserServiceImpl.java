package com.github.nancarp.service.serviceImpl;

import com.github.nancarp.dao.UserDao;
import com.github.nancarp.domain.User;
import com.github.nancarp.exception.OtherThingsException;
import com.github.nancarp.exception.UserCanNotBeNullException;
import com.github.nancarp.exception.UserNameCanNotBeNullException;
import com.github.nancarp.exception.UserPwdCanNotBeNullException;
import com.github.nancarp.service.UserService;
import com.github.nancarp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by NanCarp on 2017/8/3.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public void add(User user) throws Exception {
        if (null == user) {
            throw new UserCanNotBeNullException("User can not be Null");
        }
        //用户名不能为空检查
        if (StringUtils.isEmpty(user.getLoginId())) {
            //抛出用户名为空的自定义异常
            throw new UserNameCanNotBeNullException("User name can not be Null");
        }
        //用户密码不能为空检查
        if (StringUtils.isEmpty(user.getPwd())) {
            //抛出用户密码为空的自定义异常
            throw new UserPwdCanNotBeNullException("User name can not be Null");
        }
        //由于我这个是仓库管理系统，根据业务需求来说，我们的用户基本信息都是不能为空的
        //基本信息包括：姓名、年龄、用户名、密码、性别、手机号，年龄大于18
        if (StringUtils.isEmpty(user.getDuty())
                || StringUtils.isEmpty(user.getSex())
                || user.getAge() < 18
                || StringUtils.isEmpty(user.getCellNumber())) {
            //其他综合异常
            throw new OtherThingsException("Some use's base info can not be null");
        }
        int result = 0;
        try {
            result = userDao.add(user);
        } catch (Exception e) {
            System.out.println("添加用户失败,用户已经存在");
            //其他用户添加失败异常
            throw new OtherThingsException(e);
        }
        if (result > 0) {
            System.out.println("添加用户成功");
        }

    }

    public User findUser(User user) {
        return userDao.findOneById(user.getLoginId());
    }

}
