package com.mysoft.tenantdatabase.mapper;

import com.mysoft.tenantdatabase.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.*;
import java.util.*;


/**
 * @author warfu on 2018/11/16.
 * @version 1.0
 * 获取配置信息
 */
@Repository
public interface CityMapper
{

    @Select("SELECT count(id) from city")
    Integer getCount();
}
