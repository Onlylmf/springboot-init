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
 * @date  2023-03-10 15:39:51
 * 从 sys_role 表 自动生成的entity.
 */
@Table(name="sys_role")
@JsonInclude(JsonInclude.Include.NON_NULL)  //加该注解的字段为null,那么就不序列化这个字段了
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class SysRole implements Serializable {
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
     *   角色名称
     */
	/** @NotNull(groups = {AddGroup.class }, message = "角色名称 不能为空") */ 
    @ApiModelProperty(value="角色名称  ")
	private String name;
	
	
	/**
     *   如果有特殊角色硬编码用的这个字段，慢慢加
     */
	/** @NotNull(groups = {AddGroup.class }, message = "如果有特殊角色硬编码用的这个字段，慢慢加 不能为空") */ 
    @ApiModelProperty(value="如果有特殊角色硬编码用的这个字段，慢慢加  ")
	private Integer type;
	
	
	/**
     *   状态 0不启用 1启用
     */
	/** @NotNull(groups = {AddGroup.class }, message = "状态 0不启用 1启用 不能为空") */ 
    @ApiModelProperty(value="状态 0不启用 1启用  ")
	private Integer state;
	
	
	/**
     *   创建者
     */
	/** @NotNull(groups = {AddGroup.class }, message = "创建者 不能为空") */ 
    @ApiModelProperty(value="创建者  ")
	private String createBy;
	
	
	/**
     *   创建时间
     */
	/** @NotNull(groups = {AddGroup.class }, message = "创建时间 不能为空") */ 
    @ApiModelProperty(value="创建时间  ")
	private Date createDate;
	
	
	/**
     *   备注信息
     */
	/** @NotNull(groups = {AddGroup.class }, message = "备注信息 不能为空") */ 
    @ApiModelProperty(value="备注信息  ")
	private String remarks;
	
	
	/**
     *   
     */
	/** @NotNull(groups = {AddGroup.class }, message = " 不能为空") */ 
    @ApiModelProperty(value="  ")
	private Date updateTime;
	
	


	
}