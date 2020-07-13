package com.ws.ldy.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ws.ldy.admin.model.entity.DictionaryAdmin;
import com.ws.ldy.admin.model.vo.DictionaryAdminVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DictionaryAdminMapper extends BaseMapper<DictionaryAdmin> {


    /***
     * TODO  查询字典表 数据，基本最多两层，  A --> B,C  -->  B1,B2,B3,C1,C2  = 返回list数据
     * @param code
     * @date 2020/7/12 0012 19:41
     * @return java.util.List<com.ws.ldy.admin.model.entity.DictionaryAdmin>
     */
    @Select("  select * from t_admin_dictionary " +
            "    where code =#{code} " +
            "    or pid=(select id from t_admin_dictionary where code =#{code}) " +
            "    or pid in(select id from t_admin_dictionary where pid=(select id from t_admin_dictionary where code=#{code}))")
    List<DictionaryAdminVo> findCode(@Param("code") String code);
}
