package com.murphy.project.service.sys;


import com.baomidou.mybatisplus.extension.service.IService;
import com.murphy.project.model.sys.SysUser;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
 * @author murphy
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    String userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    SysUser userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    SysUser getLoginUser(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 登录时通过用户名查询用户
     * @param username
     * @return
     */
    SysUser findByUsername(String username);
}
