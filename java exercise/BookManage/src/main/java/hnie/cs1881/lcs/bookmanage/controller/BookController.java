package hnie.cs1881.lcs.bookmanage.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import hnie.cs1881.lcs.bookmanage.pojo.Student;
import hnie.cs1881.lcs.bookmanage.pojo.book1;
import hnie.cs1881.lcs.bookmanage.pojo.brrowlist;
import hnie.cs1881.lcs.bookmanage.service.BookService;
import hnie.cs1881.lcs.bookmanage.utils.Romdomnumber;
import hnie.cs1881.lcs.bookmanage.utils.SmsUtils;
import hnie.cs1881.lcs.bookmanage.utils.MyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Controller
public class BookController {
    @Autowired
    private BookService bookService;
//    @GetMapping("/index")
//public String index(Model model){
//    model.addAttribute("title","传递过来的title");
//      Integer id=1;
//      model.addAttribute("student",this.thymeleafService.loaddata(id));
//        System.out.println(this.thymeleafService.loaddata(id));
//    return "index";
//}
@GetMapping("/toall")
public  String ToAll(Integer start,Model model){
    System.out.println(start);
    Integer flag = 0;
        if (start == null){
            start=0;
        }
//        if (start < 0){
//            return "tologin";
//        }
        System.out.println(start);
        model.addAttribute("pages",start);
        model.addAttribute("books",this.bookService.selectstudentlimit(start));
        start+=1;
//        System.out.println(this.bookService.selectstudentlimit(start));
        if (this.bookService.selectstudentlimit(start).isEmpty()){
            flag=1;
            model.addAttribute("flag",flag);
    }
        return "/toall";
}
    @GetMapping("/tostudent")
    public  String Tostudent(Integer start,Model model){
        System.out.println(start);
        Integer flag = 0;
        if (start == null){
            start=0;
        }
//        if (start < 0){
//            return "tologin";
//        }
        System.out.println(start);
        model.addAttribute("pages",start);
        model.addAttribute("books",this.bookService.selectstudent(start));
        start+=1;
//        System.out.println(this.bookService.selectstudentlimit(start));
        if (this.bookService.selectstudent(start+3).isEmpty()){
            flag=1;
            model.addAttribute("flag",flag);
        }
        return "/tostudent";
    }
    @GetMapping("/tobrrowlist")
    public  String Tobrrowlist(Integer start,Model model){
        System.out.println(start);
        Integer flag = 0;
        if (start == null){
            start=0;
        }
//        if (start < 0){
//            return "tologin";
//        }
        System.out.println(start);
        model.addAttribute("pages",start);
        model.addAttribute("books",this.bookService.selectbrrowlist(start));
        start+=1;
//        System.out.println(this.bookService.selectstudentlimit(start));
        if (this.bookService.selectbrrowlist(start+3).isEmpty()){
            flag=1;
            model.addAttribute("flag",flag);
        }
        return "/tobrrowlist";
    }
//@RequestMapping("/tofind")
//public  String ToFind(){
//    return "/tofind";
//}
@RequestMapping("/find")
public String ToLike(String name,Integer start,String userid,String token,Model model){
    Integer flag = 0;
    if (start == null){
        start = 0;
    }
//    start =5;
    System.out.println(start);
    List<book1> books = this.bookService.selectstudentlike(name);
    model.addAttribute("userid",userid);
    model.addAttribute("token",token);
    model.addAttribute("books",books);;
    return "/tofind";
}
//@GetMapping("/all")
//public  String All(Integer start,Model model){
//    List<book1> books=this.bookService.selectstudentlimit(start);
//    model.addAttribute("books",books);
//    return "toall";
//}
//@GetMapping("/update/{id}")
//public String update(@PathVariable("id") Integer id,  Model model){
//        //查询
//    Student student = this.thymeleafService.book1loaddata(id);
//    model.addAttribute("student",student);
//    return "update";
//
//}
@GetMapping("/toadd")
public String toadd(){;
        return "addbook";
}

@RequestMapping("/add")
public  String add(book1 book){
        this.bookService.addbook(book);
        return  "redirect:/toall";
}
//@GetMapping("/basicTrain")
//    public String basicTrain(Model model){
//    User user = new User();
//    user.setUsername("lookroot");
//    user.setAge(22);
//    user.setSex(1);
//    user.setVip(true);
//    user.setCreateTime(new Date());
//    user.setTags(Arrays.asList("php","node","java"));
//    model.addAttribute("user",user);
//    return "basic";
//    }
@GetMapping("/delete")
public  String deleteid(Integer id){
        this.bookService.deletebook(id);
        return  "redirect:/toall";
}
@GetMapping("/toedit")
    public String toedit(Integer id ,book1 book,Model model)
{
    book1 bo = this.bookService.book1loaddata(id);
    model.addAttribute("book",bo);
    return "/toedit";
}
@RequestMapping("/tostudentedit")
public String Tostudentedit(String userid,Student student,Model model){
    System.out.println(userid);
    Student st = this.bookService.selecteOneStudent(userid);
    model.addAttribute("student",st);
    System.out.println("st="+st);
    return "/tostudentedit";
}
@RequestMapping("/studentedit")
public  String studentedit(Student student){
    this.bookService.updatestudent(student);
    return "redirect:/tostudent";
}
@RequestMapping("/edit")
    public  String edit(book1 book){
        this.bookService.updatebook(book);
        return "redirect:/toall";
}
@RequestMapping("/tobrrow")
    public  String ToBrrow(Integer id,Model model){
    book1 bo = this.bookService.book1loaddata(id);
    model.addAttribute("book",bo);
    return "/tobrrow";
}
@RequestMapping("/brrow")
    public String Brrow(brrowlist bl,Model model){

    this.bookService.brrow(bl);
    Integer bookid = bl.getBookid();
    book1 book = this.bookService.book1loaddata(bookid);
    if (book.getNumber() == 0){
        model.addAttribute("warning","书已借完");
        return "/tobrrow";
    }
    book.setNumber(book.getNumber()-1);
    this.bookService.updatebook(book);
    return "redirect:/toall";
}
    @RequestMapping("/toreturn")
    public  String ToReturn(Integer id,Model model){
        book1 bo = this.bookService.book1loaddata(id);
        model.addAttribute("book",bo);
        return "/toreturn";
    }
    @RequestMapping("/return")
    public String Retrun(brrowlist bl,Model model){

//
//        Integer bookid = bl.getBookid();

//        if (book.getNumber() == 0){
//            model.addAttribute("warning","书已借完");
//            return "/tobrrow";
//
        Integer bookid = bl.getBookid();
        String userid = bl.getUserid();
        System.out.println("bookid="+bookid);
        System.out.println("userid="+userid);
        this.bookService.ReturnBook(userid,bookid);
        this.bookService.SelectReturnBook(userid,bookid);
        book1 book = this.bookService.book1loaddata(bookid);
        book.setNumber(book.getNumber()+1);
        this.bookService.updatebook(book);
        return "redirect:/toall";
    }
@GetMapping("/tologin")
public String ToLogin(){
    return "tologin";
}
@RequestMapping("/login")
    public String Login(@Valid  String userid ,@Valid String password, Model model){
      Student student = new Student();
    if ((userid.isEmpty() && password.isEmpty()) || userid.isEmpty() || password.isEmpty() ){
        String warning = "请输入密码或者账号";
        model.addAttribute("warning",warning);
        return "/tologin";
    }
    if (this.bookService.selecteOneStudent(userid).getIsroot().compareTo("root")==0){
        return  "redirect:/toall";
    }
    if(this.bookService.selecteOneStudent(userid).getIsroot().compareTo("user")==0){
        Student student2 = this.bookService.selectstudent(userid);
        Date date = new Date();
//        String token = date.toString() + student2.getPassword();
        model.addAttribute("student",student2);
//        model.addAttribute("token",MyToken.MakeToken());
        model.addAttribute("token",date.toString());
        return "/studentindex";
    }
    else{
        model.addAttribute("error","密码或账号错误");
        return "/tologin";
    }
}
@RequestMapping("/toshowbrrow")
    public  String Toshowbrrow(String userid,Integer start,String token,Model model){
    System.out.println(userid);
    if (start == null){
        String userid1 =userid;
        start=0;
    }
    if (token.isEmpty() == true){
        return "/tologin";
    }
    if (this.bookService.selectbrrowlist(userid,start).isEmpty() == true){
        model.addAttribute("warning","没有借过书");
    }
//    if (start<0){
//        return "/tologin";
//    }
    model.addAttribute("pages",start);
    System.out.println(this.bookService.selectbrrowlist(userid, start+3).isEmpty() );
    if (this.bookService.selectbrrowlist(userid, start+3).isEmpty()== true){

        model.addAttribute("flag",1);
    }
    model.addAttribute("brrowlists",this.bookService.selectbrrowlist(userid,start));
    model.addAttribute("token",token);
    model.addAttribute("userid",userid);
    return "toshowbrrow";

}
@RequestMapping("/toregister")
public  String ToRegister(){
    return "/toregister";
}
@RequestMapping("/tosend")
    public String Toregister(String userid,String email,String password,Model model){
    String random = Romdomnumber.numberResult();
    String mytoken = MyToken.MakeToken();
    System.out.println(userid);
    String warning = SmsUtils.sendMail(email,random);
    model.addAttribute("warning",warning);
    model.addAttribute("eamil",email);
    model.addAttribute("password",password);
    model.addAttribute("userid",userid);
    model.addAttribute("random",random);
    model.addAttribute("token",mytoken);
    this.bookService.Insertyzm(random,mytoken);
    return "/toregister";
}
@RequestMapping("/verify")
    public String ToVerify(String userid,@Valid String random,String mytoken,String password,String email,Model model){

    String zt = null;
//    if(Integer.parseInt(random)<10000 || Integer.parseInt(random)>99999 || random.isEmpty()){
//       return "redirect:/toregister";
//    }
    Integer flag = this.bookService.selectyzmordelete(random, mytoken);
//    System.out.println("password="+password.toString());
    System.out.println("verify id="+userid);
//    Integer id;
//    id = Integer.parseInt(userid);
//    System.out.println("id="+id.toString());
    if (flag == 1){
        Student student = new Student();
        student.setUserid(userid);
        student.setEmail(email);
        student.setPassword(password);
        student.setIsroot("user");
        this.bookService.addstudent(student);
        return "/tologin";
    }else {
        zt ="注册失败,再次申请注册码";
        model.addAttribute("zt",zt);
        return "/toregister";
    }

}
}
