
package com.mysoft.tenantdatabase.dao;

import com.mysoft.tenantdatabase.entity.*;
import com.mysoft.tenantdatabase.mapper.*;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import java.util.*;


/**
 @author warfu on 2018/12/11.
 @version 1.0 */


@Service
public interface TenantConfigDAO
{

    @Select("SELECT count(*) from tenant where tenant_key=#{tenantKey}")
    public Boolean exist(String tenantKey);


    @Select("SELECT * from tenant")
    List<TenantConfigEntity> listAll();
}

