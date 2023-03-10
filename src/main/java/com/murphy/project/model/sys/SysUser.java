package com.murphy.project.model.sys;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.murphy.project.constant.CommonConstant;
import com.murphy.project.common.utils.MyIDGenId;
import com.murphy.project.common.validatgroup.AddGroup;
import com.murphy.project.common.validatgroup.FindGroup;
import com.murphy.project.common.validatgroup.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @TableName user
 */
@TableName(value = "sys_user")
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class SysUser implements Serializable {

    /**
     * id
     */
    @Id
    @KeySql(genId = MyIDGenId.class)
    @Null(groups = {AddGroup.class}, message = CommonConstant.ID_NOT_NULL)
    @NotNull(groups = {UpdateGroup.class, FindGroup.class}, message = CommonConstant.ID_NULL)
    @ApiModelProperty(value = "主键id  ")
    private String id;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称  ")
    private String userName;

    /**
     * 账号
     */
    @ApiModelProperty(value = "登录账号  ")
    private String userAccount;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像  ")
    private String userAvatar;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别  ")
    private Integer gender;

    /**
     * 用户角色: user, admin
     */
    @ApiModelProperty(value = "用户角色: user, admin  ")
    private String userRole;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码  ")
    private String userPassword;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间  ")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间  ")
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}