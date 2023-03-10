package com.murphy.project.model.sys;

import java.math.BigDecimal;
import java.util.ArrayList;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.murphy.project.constant.CommonConstant;
import com.murphy.project.common.validatgroup.AddGroup;
import com.murphy.project.common.validatgroup.FindGroup;
import com.murphy.project.common.validatgroup.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.murphy.project.common.utils.MyIDGenId;
import tk.mybatis.mapper.annotation.KeySql;
/**
 * @date  2023-03-10 15:44:10
 * 从 sys_menu 表 自动生成的entity.
 */
@Table(name="sys_menu")
@JsonInclude(JsonInclude.Include.NON_NULL)  //加该注解的字段为null,那么就不序列化这个字段了
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class SysMenu implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
     *   主键id
     */
	@Id
	@KeySql(genId = MyIDGenId.class)
	@Null(groups = {AddGroup.class }, message = CommonConstant.ID_NOT_NULL)
	@NotNull(groups = {UpdateGroup.class,FindGroup.class }, message = CommonConstant.ID_NULL)
    @ApiModelProperty(value="主键id  ")
	private String id;
	
	
	/**
     *   父id
     */
	/** @NotNull(groups = {AddGroup.class }, message = "父id 不能为空") */ 
    @ApiModelProperty(value="父id  ")
	private String parentId;
	
	
	/**
     *   菜单标题
     */
	/** @NotNull(groups = {AddGroup.class }, message = "菜单标题 不能为空") */ 
    @ApiModelProperty(value="菜单标题  ")
	private String title;
	
	
	/**
     *   路径
     */
	/** @NotNull(groups = {AddGroup.class }, message = "路径 不能为空") */ 
    @ApiModelProperty(value="路径  ")
	private String url;
	
	
	/**
     *   组件
     */
	/** @NotNull(groups = {AddGroup.class }, message = "组件 不能为空") */ 
    @ApiModelProperty(value="组件  ")
	private String component;
	
	
	/**
     *   组件名字
     */
	/** @NotNull(groups = {AddGroup.class }, message = "组件名字 不能为空") */ 
    @ApiModelProperty(value="组件名字  ")
	private String componentName;
	
	
	/**
     *   菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)
     */
	/** @NotNull(groups = {AddGroup.class }, message = "菜单类型(0:一级菜单; 1:子菜单:2:按钮权限) 不能为空") */ 
    @ApiModelProperty(value="菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)  ")
	private Integer menuType;
	
	
	/**
     *   菜单权限编码
     */
	/** @NotNull(groups = {AddGroup.class }, message = "菜单权限编码 不能为空") */ 
    @ApiModelProperty(value="菜单权限编码  ")
	private String perms;
	
	
	/**
     *   权限策略1显示2禁用
     */
	/** @NotNull(groups = {AddGroup.class }, message = "权限策略1显示2禁用 不能为空") */ 
    @ApiModelProperty(value="权限策略1显示2禁用  ")
	private String permsType;
	
	
	/**
     *   菜单排序
     */
	/** @NotNull(groups = {AddGroup.class }, message = "菜单排序 不能为空") */ 
    @ApiModelProperty(value="菜单排序  ")
	private Integer sortNo;
	
	
	/**
     *   菜单图标
     */
	/** @NotNull(groups = {AddGroup.class }, message = "菜单图标 不能为空") */ 
    @ApiModelProperty(value="菜单图标  ")
	private String icon;
	
	
	/**
     *   是否隐藏路由: 0否,1是
     */
	/** @NotNull(groups = {AddGroup.class }, message = "是否隐藏路由: 0否,1是 不能为空") */ 
    @ApiModelProperty(value="是否隐藏路由: 0否,1是  ")
	private Integer hidden;
	
	
	/**
     *   描述
     */
	/** @NotNull(groups = {AddGroup.class }, message = "描述 不能为空") */ 
    @ApiModelProperty(value="描述  ")
	private String description;
	
	
	/**
     *   创建人
     */
	/** @NotNull(groups = {AddGroup.class }, message = "创建人 不能为空") */ 
    @ApiModelProperty(value="创建人  ")
	private String createBy;
	
	
	/**
     *   创建时间
     */
	/** @NotNull(groups = {AddGroup.class }, message = "创建时间 不能为空") */ 
    @ApiModelProperty(value="创建时间  ")
	private Date createTime;
	
	
	/**
     *   更新人
     */
	/** @NotNull(groups = {AddGroup.class }, message = "更新人 不能为空") */ 
    @ApiModelProperty(value="更新人  ")
	private String updateBy;
	
	
	/**
     *   更新时间
     */
	/** @NotNull(groups = {AddGroup.class }, message = "更新时间 不能为空") */ 
    @ApiModelProperty(value="更新时间  ")
	private Date updateTime;
	
	
	/**
     *   层级
     */
	/** @NotNull(groups = {AddGroup.class }, message = "层级 不能为空") */ 
    @ApiModelProperty(value="层级  ")
	private Integer level;
	
	
	/**
     *   路径
     */
	/** @NotNull(groups = {AddGroup.class }, message = "路径 不能为空") */ 
    @ApiModelProperty(value="路径  ")
	private String paths;
	
	


	
}