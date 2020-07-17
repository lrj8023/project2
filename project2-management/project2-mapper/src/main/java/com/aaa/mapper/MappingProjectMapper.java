package com.aaa.mapper;

import com.aaa.model.MappingProject;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface MappingProjectMapper extends  Mapper<MappingProjectMapper> {
    //查询所有项目
    List<HashMap>selectAllProject();


    //根据项目类型查询
    List<HashMap>selectName(String name);
    //通过ID查询
    List<HashMap> projectDetail(String id);


    //模糊查询
    List<MappingProject>ProjectLookName(@Param("projectName") String projectName,
                                        @Param("projectType") String projectType,@Param("projectDate")  String projectDate);
    //测绘项目管理，项目名称模糊查询，类型 ，日期精确查
    List<HashMap> projectSelect(MappingProject mappingProject);
}