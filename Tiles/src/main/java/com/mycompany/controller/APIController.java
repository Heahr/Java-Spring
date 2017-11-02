package com.mycompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.mapper.BookMapper;
import com.mycompany.vo.Book;

@Controller
public class APIController {
	@Autowired
	BookMapper bookMapper;
	
    @RequestMapping(value = "/api/books/search", method = RequestMethod.GET)
    public ModelAndView searchBook(String term) {
        List<Book> bookList = bookMapper.search(term);
        ModelAndView mv = new ModelAndView();
        mv.addObject("bookList", bookList);
        mv.setViewName("jsonView");
        return mv;
    }
	
}