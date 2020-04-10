package com.javahly.stockservice.mapper;

import com.javahly.stockservice.entity.DispatchEntity;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatchMapper {

    /**
     * 新增派单任务
     */
    @Insert("INSERT into platoon values (null,#{orderId},#{userId});")
    int insertDistribute(DispatchEntity distributeEntity);


}
