class Book: 
  title = "자바의 정식"
  author = "남궁성"

  def display_info(self):
    print(f'제목 : " {self.title} ,  저자 : {self.author}')

class EBook(Book):
  title = "스프링 부트 3 백과사전"
  author = "김영한"
  file_size = 20.5

  def display_info(self):
    print(f'제목 : " {self.title} ,  저자 : {self.author}, 파일 크기 : {self.file_size}')

  
book = Book()
book.display_info()
ebook = EBook()
ebook.display_info()
    
