package hnie.cs1881.lcs.bookmanage.service;

import hnie.cs1881.lcs.bookmanage.mapper.Book1Mapper;
import hnie.cs1881.lcs.bookmanage.mapper.BrrowlistMapper;
import hnie.cs1881.lcs.bookmanage.mapper.StudentMapper;
import hnie.cs1881.lcs.bookmanage.mapper.YzmMapper;
import hnie.cs1881.lcs.bookmanage.pojo.Student;
import hnie.cs1881.lcs.bookmanage.pojo.book1;
import hnie.cs1881.lcs.bookmanage.pojo.brrowlist;
import hnie.cs1881.lcs.bookmanage.pojo.yzm;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Iterator;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private Book1Mapper book1Mapper;
    @Autowired
    private BrrowlistMapper brrowlistMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private YzmMapper yzmMapper;
//    public Student loaddata(Integer id){
//        return this.thymeleafDemoMapper.selectByPrimaryKey(id);
//    }
    //搜索一个书籍的信息
    public book1 book1loaddata(Integer id){
        return this.book1Mapper.selectByPrimaryKey(id);
    }
//    public List<Student> AllStudent(){
//        return this.thymeleafDemoMapper.selectAll();
//    }
    //搜索所有书籍的信息
    public List<book1> AllBook(){
        return this.book1Mapper.selectAll();
    }
    //
    public Student selecteOneStudent(String userid){
        return this.studentMapper.selectByPrimaryKey(userid);
    }
    public void  updatestudent(Student student){
        this.studentMapper.updateByPrimaryKey(student);
    }
    //插入书籍信息
    public void addbook(book1 book){
        this.book1Mapper.insert(book);
    }
    //删除书籍
    public void deletebook(Integer id){
        this.book1Mapper.deleteByPrimaryKey(id);
    }
    //更新书籍
    public void updatebook(book1 book){

        this.book1Mapper.updateByPrimaryKey(book);
    }
    //借书信息插入
    public void brrow(brrowlist bl){
        this.brrowlistMapper.insert(bl);
    }
    //还书，找到被还的书
    public  void SelectReturnBook(String userid, Integer bookid){
        Example example  = new Example(brrowlist.class);
        example.createCriteria().andLike("userid","%"+userid+"%");
        System.out.println("Returnbook = "+ this.brrowlistMapper.selectByExample(example));
//        return  this.brrowlistMapper.selectOneByExample(example);

    }
    //还书，借书信息删除
    public  void ReturnBook(String userid, Integer bookid){
        Example example = new Example(brrowlist.class);
        example.and().andLike("userid","%"+userid+"%");
        example.and().andLike("bookid","%"+bookid.toString()+"%");
        this.brrowlistMapper.deleteByExample(example);

    }
    //查询单个学生信息
    public Student selectstudent(String userid){
        return this.studentMapper.selectByPrimaryKey(userid);
    }
    public  void addstudent(Student student){
        this.studentMapper.insert(student);
    }
//    //学生靠邮箱登录
//    public  Student selectstudentemail(String email){
//        Example example = new Example(Student.class);
//        example.createCriteria().andLike("email",email);
//        List<Student> student = this.studentMapper.selectByExample(example);
//    }
    //分页
    public List<book1> selectstudentlimit(Integer Start){
        Example example = new Example(book1.class);
        example.orderBy("id");
        example.createCriteria();
        RowBounds rowBounds = new RowBounds(Start,3);
        return this.book1Mapper.selectByExampleAndRowBounds(example,rowBounds);

    }
    //分页
    public List<Student> selectstudent(Integer Start){
        Example example = new Example(Student.class);
        example.orderBy("userid");
        example.createCriteria();
        RowBounds rowBounds = new RowBounds(Start,3);
        return this.studentMapper.selectByExampleAndRowBounds(example,rowBounds);

    }
    //分页
    public List<brrowlist> selectbrrowlist(Integer Start){
        Example example = new Example(brrowlist.class);
        example.orderBy("id");
        example.createCriteria();
        RowBounds rowBounds = new RowBounds(Start,3);
        return this.brrowlistMapper.selectByExampleAndRowBounds(example,rowBounds);

    }
    //模糊查询
    public List<book1> selectstudentlike(String name){
        Example example = new Example(book1.class);
        example.orderBy("id");
        example.createCriteria();
        example.or().andLike("name","%"+name+"%");
        example.or().andLike("tips","%"+name+"%");
        List<book1> books =  this.book1Mapper.selectByExample(example);
        for (Iterator<book1> it = books.iterator(); it.hasNext(); ) {
            book1 book = it.next();
            System.out.println(book.getName());
        }
            return books;
    }

    //根据学生id查询brrowlist的数据
    public List<brrowlist> selectbrrowlist(String userid,Integer start){
        Example example = new Example(brrowlist.class);

        example.orderBy("id");
        example.createCriteria().andLike("userid","%"+userid+"%");
        RowBounds rowBounds = new RowBounds(start,3);
        List<brrowlist> brrowlists = this.brrowlistMapper.selectByExampleAndRowBounds(example,rowBounds);
//        System.out.println(brrowlists);
        for (Iterator<brrowlist> it = brrowlists.iterator(); it.hasNext(); ) {
            brrowlist book = it.next();
            System.out.println(book.getBookname());
        }
        return  brrowlists;
    }
    //新增验证码
    public  void Insertyzm(String yzm,String mytoken){
        yzm yzm1 = new yzm();
        yzm1.setYanzhengma(yzm);
        yzm1.setMytoken(mytoken);
        this.yzmMapper.insert(yzm1);
    }
    //验证码验证
    public Integer selectyzmordelete(String  yzm,String mytoken){
        Integer flag = null;
        Example example = new Example(hnie.cs1881.lcs.bookmanage.pojo.yzm.class);
        Example example1 = new Example(hnie.cs1881.lcs.bookmanage.pojo.yzm.class);
        System.out.println("yzm="+yzm);
        System.out.println("mytoken="+mytoken);
        example.createCriteria().andLike("yanzhengma","%"+yzm+"%");
        example1.createCriteria().andLike("mytoken","%"+mytoken+"%");
        System.out.println(this.yzmMapper.selectByExample(example));
        if (this.yzmMapper.selectByExample(example).isEmpty()){
            this.yzmMapper.deleteByExample(example1);
            flag=0;
        }else {
            this.yzmMapper.deleteByExample(example);
//            Student student = new Student();
//            student.setUserid();
            flag=1;
        }
        return  flag;
    }
    public List<brrowlist> selectbrrowlistAll(){
        return this.brrowlistMapper.selectAll();
    }
    //还书
//    public void Returnbook(Integer id){
//        this.brrowlistMapper.deleteByPrimaryKey(id);
//    }
}
