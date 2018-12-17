package com.mysoft.tenantdatabase.entity;

import lombok.*;
import lombok.experimental.*;

@EqualsAndHashCode(callSuper = false)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TenantConfigEntity {
    /**
     * 租户id
     **/
    Integer tenantId;
    /**
     * 租户名称
     **/
    String tenantName;
    /**
     * 租户名称key
     **/
    String tenantKey;
    /**
     * 数据库url
     **/
    String dbUrl;
    /**
     * 数据库用户名
     **/
    String dbUser;
    /**
     * 数据库密码
     **/
    String dbPassword;
    /**
     * 数据库public_key
     **/
    String dbPublicKey;
}

