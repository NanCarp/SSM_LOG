package com.github.nancarp.mvc.controller;

import com.github.nancarp.domain.ResponseObj;
import com.github.nancarp.domain.User;
import com.github.nancarp.service.UserService;
import com.github.nancarp.utils.GsonUtils;
import com.github.nancarp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by nanca on 8/16/2017.
 */
@Controller
@RequestMapping("/userAction")
public class UserController {

    @Autowired
    private UserService userService;
    private ResponseObj responseObj;

    @RequestMapping(value = "/reg"
            , method = RequestMethod.POST
            )
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

    @RequestMapping(value = "/login"
            , method = RequestMethod.POST
            , produces = {
            "application/json; charset=utf-8"
    })
    @ResponseBody
    public Object login(HttpServletRequest req, User user) {
        Object result;

        if (null == user) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("登录信息不能为空");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }

        //查找用户
        User user1 = userService.findUser(user);
        if (null == user1) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("未找到该用户");
            result =  new GsonUtils().toJson(responseObj);
        } else {
            if (user.getPwd().equals(user1.getPwd())) {
                responseObj = new ResponseObj<User>();
                responseObj.setCode(ResponseObj.OK);
                responseObj.setMsg(ResponseObj.OK_STR);
                result =  new GsonUtils().toJson(responseObj);
            } else {
                responseObj = new ResponseObj<User>();
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg("ResponseObj.FAILED");
                result =  new GsonUtils().toJson(responseObj);
            }
        }
        return result;
    }

    @RequestMapping(value = "/uploadHeadPic"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object uploadHeadPic(@RequestParam(required = false) MultipartFile file, HttpServletRequest request) {
        if (null == file || file.isEmpty()) {
            responseObj = new ResponseObj();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("文件不能为空");
            return new GsonUtils().toJson(responseObj);
        }
        responseObj = new ResponseObj();
        responseObj.setCode(ResponseObj.OK);
        responseObj.setMsg("文件长度为：" + file.getSize());
        return new GsonUtils().toJson(responseObj);
    }
}
