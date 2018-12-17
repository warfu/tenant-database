package com.mysoft.tenantdatabase.controller;

import com.mysoft.tenantdatabase.dao.*;
import com.mysoft.tenantdatabase.entity.*;
import com.mysoft.tenantdatabase.mapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 @author warfu on 2018/12/11.
 @version 1.0 */

@RestController
public class TestController
{
    @Autowired
    TenantConfigDAO tenantConfigDAO;

    @Autowired
    CityMapper cityMapper;

    @RequestMapping(path = "/test",method = RequestMethod.GET)
    public List<TenantConfigEntity> upgrade(){

        System.out.println(tenantConfigDAO.exist("ykf-10001"));
        List<TenantConfigEntity> list= tenantConfigDAO.listAll();
        for(TenantConfigEntity entity : list) {
            System.out.println(entity.getTenantId());
        }

        return  list;

    }

    @RequestMapping(path = "/count",method = RequestMethod.GET)
    public int count(){
        return  cityMapper.getCount();
    }
}
