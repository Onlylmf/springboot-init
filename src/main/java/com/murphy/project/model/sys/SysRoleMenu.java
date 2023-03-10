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
 * @date  2023-03-10 15:49:51
 * 从 sys_role_menu 表 自动生成的entity.
 */
@Table(name="sys_role_menu")
@JsonInclude(JsonInclude.Include.NON_NULL)  //加该注解的字段为null,那么就不序列化这个字段了
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class SysRoleMenu implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
     *   编号
     */
	@Id
	@KeySql(genId = MyIDGenId.class)
	@Null(groups = {AddGroup.class }, message = CommonConstant.ID_NOT_NULL)
	@NotNull(groups = {UpdateGroup.class,FindGroup.class }, message = CommonConstant.ID_NULL)
    @ApiModelProperty(value="编号  ")
	private String id;
	
	
	/**
     *   角色ID
     */
	/** @NotNull(groups = {AddGroup.class }, message = "角色ID 不能为空") */ 
    @ApiModelProperty(value="角色ID  ")
	private String roleId;
	
	
	/**
     *   菜单ID
     */
	/** @NotNull(groups = {AddGroup.class }, message = "菜单ID 不能为空") */ 
    @ApiModelProperty(value="菜单ID  ")
	private String menuId;
	
	


	
}