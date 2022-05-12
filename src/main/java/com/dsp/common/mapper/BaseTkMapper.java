package com.dsp.common.mapper;

import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * 使用tkmapper并自定义自己的SQL的基类接口
 */

public interface BaseTkMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
