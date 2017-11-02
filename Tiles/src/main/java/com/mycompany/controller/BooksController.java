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
		// ���� ���� ���������� �����͸� ����
		model.addAttribute("books", books);
		return "books/index";
	}

	// ---����� ������ value ������ ���� �޾� �ְڴ�.
	@RequestMapping(value = "/books/new", method = RequestMethod.GET)
	public String newBook() {
		return "books/new";
	}

	// get=������ ���� �ϴ½� post=������ �Ǽ� ������ �ӵ��� ������.
	// @ModelAttribute �����ִ� ���� book ������.
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
		// �� �������� �����͸� ����(key/value ����)
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
		// ��ϵ� �����
		List<Review> reviews = reviewMapper.getReviews(id);
		model.addAttribute("reviews", reviews);
		// ���ο� ���� ���
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