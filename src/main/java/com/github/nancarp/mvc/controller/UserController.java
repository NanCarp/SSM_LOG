package com.github.nancarp.mvc.controller;

import com.github.nancarp.domain.ResponseObject;
import com.github.nancarp.domain.User;
import com.github.nancarp.service.serviceImpl.UserServiceImpl;
import com.github.nancarp.utils.GsonUtils;
import com.github.nancarp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by nanca on 8/16/2017.
 */
@Controller
@RequestMapping("/userAction")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    private ResponseObject responseObject;

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public ModelAndView reg(HttpServletRequest req, User user) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        if (null == user) {
            mav.addObject("message", "用户信息不能为空！");
            return mav;
        }
        if (StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPwd())) {
            mav.addObject("message", "用户名或密码不能为空！");
        }
        if (null != userService.findUser(user)) {
            mav.addObject("message", "用户已经存在！");
        }
        try {
            userService.add(user);
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("message", "错误：用户其他信息错误");
            return mav;
        }
        mav.addObject("code", 110);
        mav.addObject("message", "恭喜。注册成功");
        req.getSession().setAttribute("user", user);

        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8"
    })
    @ResponseBody
    public ModelAndView login(HttpServletRequest req, User user) {
        ModelAndView mav = new ModelAndView("home");
        String result;

        if (null == user) {
            responseObject = new ResponseObject<User>();
            responseObject.setCode(ResponseObject.EMPTY);
            responseObject.setMsg("登录信息不能为空");
            result = GsonUtils.gson.toJson(responseObject);
            mav.addObject("result", result);
            return mav;
        }

        //查找用户
        User user1 = userService.findUser(user);
        if (null == user1) {
            responseObject = new ResponseObject<User>();
            responseObject.setCode(ResponseObject.EMPTY);
            responseObject.setMsg("未找到该用户");
            result = GsonUtils.gson.toJson(responseObject);
        } else {
            if (user.getPwd().equals(user1.getPwd())) {
                responseObject = new ResponseObject<User>();
                responseObject.setCode(ResponseObject.OK);
                responseObject.setMsg(ResponseObject.OK_STR);
                result = GsonUtils.gson.toJson(responseObject);
            } else {
                responseObject = new ResponseObject<User>();
                responseObject.setCode(ResponseObject.FAILED);
                responseObject.setMsg("ResponseObj.FAILED");
                result = GsonUtils.gson.toJson(responseObject);
            }
        }
        mav.addObject("result", result);
        return mav;
    }
}
