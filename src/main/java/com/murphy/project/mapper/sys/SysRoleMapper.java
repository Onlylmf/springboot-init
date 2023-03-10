package com.murphy.project.mapper.sys;
import com.murphy.project.model.sys.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @date  2023-03-10 17:19:40
 */

public interface SysRoleMapper extends BaseMapper<SysRole>{

    @Select("select r.* from sys_user_role ru inner join sys_role r on r.id = ru.role_id where ru.user_id = #{userId}")
    List<SysRole> findRolesByUserId(String userId);
}