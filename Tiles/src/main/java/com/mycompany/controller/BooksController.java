package com.mycompany.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.helper.FileHelper;
import com.mycompany.mapper.BookMapper;
import com.mycompany.mapper.ReviewMapper;
import com.mycompany.vo.Book;
import com.mycompany.vo.Review;

@Controller
public class BooksController {
	@Autowired
	private BookMapper bookMapper;
	@Autowired
	private ReviewMapper reviewMapper;

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String index(Model model) {
		List<Book> books = bookMapper.getList();
		// 모델을 통해 뷰페이지로 데이터를 전달
		model.addAttribute("books", books);
		return "books/index";
	}

	// ---명령이 들어오면 value 값으로 값을 받아 주겠다.
	@RequestMapping(value = "/books/new", method = RequestMethod.GET)
	public String newBook() {
		return "books/new";
	}

	// get=데이터 노출 하는식 post=보안이 되서 오지만 속도가 느리다.
	// @ModelAttribute 폼에있는 값을 book 보낸다.
	@RequestMapping(value = "/books", method = RequestMethod.POST)
	public String create(@ModelAttribute Book book, @RequestParam MultipartFile file, HttpServletRequest request) {
		String fileUrl = FileHelper.upload("/uploads", file, request);
		book.setImage(fileUrl);
		System.out.println(book.toString());
		bookMapper.create(book);
		return "redirect:/books";
	}

	@RequestMapping(value = "/books/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable int id, Model model) {
		Book book = bookMapper.getBook(id);
		// 뷰 페이지로 데이터를 전달(key/value 형식)
		model.addAttribute("book", book);
		return "books/edit";
	}

	@RequestMapping(value = "/books/update", method = RequestMethod.POST)
	public String update(@ModelAttribute Book book) {
		bookMapper.update(book);
		return "redirect:/books";
	}

	@RequestMapping(value = "/books/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		bookMapper.delete(id);
		return "redirect:/books";
	}

	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
	public String show(@PathVariable int id, Model model) {
		Book book = bookMapper.getBook(id);
		model.addAttribute("book", book);
		// 등록된 리뷰들
		List<Review> reviews = reviewMapper.getReviews(id);
		model.addAttribute("reviews", reviews);
		// 새로운 리뷰 등록
		Review review = new Review();
		review.setBookId(id);
		model.addAttribute("review", review);
		return "books/show";
	}
	
	@RequestMapping(value = "/books/search", method = RequestMethod.GET)
	public String searchBook(@RequestParam String query, Model model) {
		List<Book> books = bookMapper.search(query);
		System.out.println(query);
		model.addAttribute("books",books);
		return "books/index";
	}
}