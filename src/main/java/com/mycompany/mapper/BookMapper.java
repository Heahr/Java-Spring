package com.mycompany.mapper;
import org.apache.ibatis.annotations.Insert;
import com.mycompany.vo.Book;
public interface BookMapper {
    @Insert("insert into books (title, author, image) values (#{title}, #{author}, #{image})")
    public boolean create(Book book);
}