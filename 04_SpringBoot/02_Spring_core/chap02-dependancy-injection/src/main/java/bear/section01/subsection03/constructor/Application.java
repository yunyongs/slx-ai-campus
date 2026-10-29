package bear.section01.subsection03.constructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext("bear.section01");

        BookService bookService =
                context.getBean("bookServiceConstructor", BookService.class);


        bookService.selectAllBooks().forEach(System.out::println);
    }
}
