package com.mycompany.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mycompany.vo.Daily;

public interface DailyMapper {

    @Insert("insert into daily (title, author) values (#{title}, #{author})")
    public boolean create(Daily daily);

    @Select("select * from daily")
    public List<Daily> getList();

    @Select("select * from daily where id = #{id}")
    public Daily getdaily(int id);
    
    @Update("update daily set title = #{title}, author = #{author} where id = #{id}")
    public boolean update(Daily daily);
    
    @Delete("delete from daily where id = #{id}")
    public boolean delete(int id);
}