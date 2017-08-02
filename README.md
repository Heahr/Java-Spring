# Java-Spring

 이번에는 DB에 저장된 데이터를 가져와 페이지로 보여주는 작업을 수행할겁니다.
 
 #맵퍼 메소드 추가
 BOOKS 테이블로부터 Book의 리스트를 만들기 위해 getList() 메소드를 맵퍼에 추가해줍니다.
 
 BookMapper.java
 ```
 package com.mycompany.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import com.mycompany.vo.Book;
public interface BookMapper {
    @Insert("insert into books (title, author, image) values (#{title}, #{author}, #{image})")
    public boolean create(Book book);
    @Select("select * from books")
    public List<Book> getList();
}
```

#데이터를 뷰 페이지로 전달
맵퍼를 사용하여 데이터를 컨트롤러로 가져옵니다.

BooksController.java
```
   @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String index() {
        // 맵퍼로부터 리스트를 받아옴.
        List<Book> books = bookMapper.getList();
        return "books/index";
    }
```

가져온 데이터를 뷰 페이지로 전달하는데 이때 모델을 사용합니다. 

BooksController.java
```
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String index(Model model) {
        List<Book> books = bookMapper.getList();
        // 모델을 통해 뷰페이지로 데이터를 전달
        model.addAttribute("books", books);
        return "books/index";
    }
```

#현재까지의 작업 흐름을 정리하면 DB->Mapper->controller->model->view 순서로 보여지게 됩니다.

#뷰 페이지: foreach를 사용한 데이터 렌더링
DB 데이터를 뷰페이지 까지 가져오게 되었습니다. foreach 태그를 사용하여 가져온 데이터를 화면으로 출력해봅시다.

views/books/index.jsp
```
<body>
    <div class="container">
        <div class="jumbotron">
            <h1>Books INDEX</h1>
            <p>views/books/index.jsp</p>
        </div>
        <div class="row">
            <c:forEach var="book" items="${books}" varStatus="status">
                <div class="col-md-4">
                    <a href="#" class="thumbnail">
                        <img src="${ book.image }" />
                        <h3>${ book.title }</h3>
                    </a>
                </div>
            </c:forEach>
        </div>
        <a href="<c:url value="/books/new" />"
            class="btn btn-lg btn-primary">도서 등록</a>
    </div>
</body>
```

여기까지 하여 DB 데이터를 페이지로 보여주는 작업을 완료하였습니다. 

#데이터 수정하기

저장된 데이터를 수정하여, 다시 DB에 저장해보도록 하겠습니다. 
#수정하기(page)->Router->Controller->Mapper->DB 순서입니다.

#URL 만들기

데이터 수정페이지로 이동할 수 있도록 URL을 만들어 줍니다.
1. 수정페이지 URL : /books/edit/{id}
만들어진 URL로 접속할 수 있도록 index.jsp페이지에 링크를 추가해줍시다.

views/books/index.jsp
```
<div class="row">
    <c:forEach var="book" items="${books}" varStatus="status">
        <div class="col-md-4">
            <div class="thumbnail">
                <img src="${ book.image }" />
                <div class="caption">
                    <h3>${ book.title } <small>${ book.author }</small></h3>
                    <a href="<c:url value='/books/edit/${ book.id }' />" class="btn btn-lg btn-default">수정</a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
```

#수정페이지 만들기

수정하기위핸 페이지인 edit.jsp 파일을 만들어 줍니다.

views/books/edit.jsp
```
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="utf-8" session="false"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
<title>Books EDIT</title>
</head>
<body>
    <div class="container">
        <div class="jumbotron">
            <h1>Books EDIT</h1>
            <p>views/books/edit.jsp</p>
        </div>
    </div>
</body>
</html>
```

#수정페이지와 URL 연결시키기

BooksController의 edit() 메소드를 만들고 URL을 부여 한 뒤, edit.jsp 페이지를 연결시켜줍니다.

BooksController.java
```
@RequestMapping(value = "/books/edit/{id}", method = RequestMethod.GET)
public String edit(@PathVariable int id) {
    return "books/edit";
}
```

#@PathVariable 어노테이션은 URL로부터 변수 값을 받아올 수 있게 합니다.

-->> 연결을 주기적으로 확인해줘야 합니다.(나중에 어디서 에러났는지 모름)

#수정페이지 폼 추가하기

수정페이지(edit.jsp)에 폼 태그를 추가해줍니다. 수정페이지는 생성페이지(new.jsp)와 거의 흡사합니다. 따라서 생성페이지에서 폼태그를 복사해서 붙여넣어 준 뒤, 폼 태그 action 속성의 값을 바꾸어 줍니다.

views/books/edit.jsp
```
<body>
    <div class="container">
        <div class="jumbotron">
            <h1>Books EDIT</h1>
            <p>views/books/edit.jsp</p>
        </div>
        <!-- action 값을 잘 설정해 주세요 -->
        <form action="<c:url value='/books/update' />" method="post">
            <div class="form-group form-group-lg">
                <label class="control-label">도서 제목</label>
                <input name="title" type="text" class="form-control">
            </div>
            <div class="form-group form-group-lg">
                <label class="control-label">저자</label>
                <input name="author" type="text" class="form-control">
            </div>
            <div class="form-group form-group-lg">
                <label class="control-label">이미지</label>
                <input name="image" type="text" class="form-control">
            </div>
            <button type="submit" class="btn btn-lg btn-primary">전송</button>
        </form>
    </div>
</body>
```

추가로 폼 전송버튼위에 히든인풋을 만들어 주어 데이터의 id값을 저장 하도록 합니다. (데이터 등록의 경우, id를 DB에서 자동적으로 만들어 주기때문에 폼 데이터에 id값을 가지고 있지 않음. 하지만 데이터 수정의 경우, id값이 있어야 수정을 할 수 있음)

views/books/edit.jsp
```
<input name="id" type="hidden">
<button type="submit" class="btn btn-lg btn-primary">전송</button>
```

#수정 페이지 값 불러오기

수정페이지에서 이전 데이터를 출력하게 해줍시다. 컨트롤러에서 맵퍼를 사용해 데이터를 가져오게 합니다.

BooksController.java
```
@RequestMapping(value = "/books/edit/{id}", method = RequestMethod.GET)
public String edit(@PathVariable int id) {
    // id 값을 통해 데이터를 가져옴
    Book book = bookMapper.getBook(id);
    return "books/edit";
}
```

BookMapper에 getBook() 메소드를 생성하여 에러를 없애줍니다.

BookMapper.java
```
package com.mycompany.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import com.mycompany.vo.Book;
public interface BookMapper {
    @Insert("insert into books (title, author, image) values (#{title}, #{author}, #{image})")
    public boolean create(Book book);
    @Select("select * from books")
    public List<Book> getList();
    @Select("select * from books where id = #{id}")
    public Book getBook(int id);
}
```

이제 맵퍼를 통해 가져온 데이터를 뷰 페이지로 전달해줍시다.

BooksController.java
```
@RequestMapping(value = "/books/edit/{id}", method = RequestMethod.GET)
public String edit(@PathVariable int id, Model model) {
    Book book = bookMapper.getBook(id);
    // 뷰 페이지로 데이터를 전달(key/value 형식)
    model.addAttribute("book", book);
    return "books/edit";
}
```

가져온 데이터를 수정페이지에서 출력하도록 해줍니다. 각각의 인풋 태그의 value 값을 적어주세요.

views/books/edit.jsp
```
<form action="<c:url value='/books/update' />" method="post">
    <div class="form-group form-group-lg">
        <label class="control-label">도서 제목</label>
        <input name="title" type="text" class="form-control" value="${ book.title }">
    </div>
    <div class="form-group form-group-lg">
        <label class="control-label">저자</label>
        <input name="author" type="text" class="form-control" value="${ book.author }">
    </div>
    <div class="form-group form-group-lg">
        <label class="control-label">이미지</label>
        <input name="image" type="text" class="form-control" value="${ book.image }">
    </div>
    <input name="id" type="hidden" value="${ book.id }">
    <button type="submit" class="btn btn-lg btn-primary">전송</button>
</form>
```

# -->> 잘 수행되는지 확인해 봅시다. (꼭)

#폼데이터 받아오기

수정을 위한 폼 태그는 action의 값에 적혀있는 URL로 데이터를 전송합니다. BooksController에 update() 메소드를 추가하여 데이터를 받아봅시다.

BooksController.java
```
@RequestMapping(value = "/books/update", method = RequestMethod.POST)
public String update(@ModelAttribute Book book) {
    System.out.println(book.toString());
    return "";
}
```

# -->> edit 페이지에서 잘 전달되는지 확인해 봅시다. (아래 상태창에 확인이 됩니다!)

#DB 수정하기

수정할 데이터도 다 받아왔으니, 맵퍼를 사용해 DB를 수정해줍시다.

BooksController.java
```
@RequestMapping(value = "/books/update", method = RequestMethod.POST)
public String update(@ModelAttribute Book book) {
    bookMapper.update(book);
    return "";
}
```

BookMapper 인터페이스에 update() 메소드를 추가합니다.
BookMapper.java
```
@Update("update books set title = #{title}, author = #{author}, image = #{image} where id = #{id}")
public boolean update(Book book);
```

마지막으로 잘 되는지 확인해 봅니다!
