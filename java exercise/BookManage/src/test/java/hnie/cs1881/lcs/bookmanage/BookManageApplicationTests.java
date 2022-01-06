package hnie.cs1881.lcs.bookmanage;

import hnie.cs1881.lcs.bookmanage.mapper.YzmMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookManageApplicationTests {
@Autowired
private YzmMapper yzmMapper;
    @Test
    void contextLoads() {
        System.out.println(this.yzmMapper.selectByPrimaryKey(56));
    }

}
