import com.ymlinks.cloud.model.Book;
import com.ymlinks.cloud.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by FuZhaohui on 2016/7/29.
 */
@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BookRepository.class})
@EnableAutoConfiguration
@ComponentScan("com.ymlinks")
@EnableElasticsearchRepositories("com.ymlinks.*.repository")
public class BookServiceTest {

    @Autowired
    private BookRepository bookRepository;


    @Test
    public void addUser() {
        Book book = new Book();
        book.setAuthor("test");
        bookRepository.save(book);
    }


}
