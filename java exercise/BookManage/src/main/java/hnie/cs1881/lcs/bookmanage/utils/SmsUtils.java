package hnie.cs1881.lcs.bookmanage.utils;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component

public class SmsUtils {


    /**
     * 外网邮件发送
     * @param code
     */
    public static String  sendMail(String to, String code) {
        String myEmailAddr = "3270232490@qq.com";

        // Session对象:
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.qq.com"); // 设置主机地址
        // smtp.163.com
        // smtp.qq.com
        // smtp.sina.com
        props.setProperty("mail.smtp.auth", "true");// 认证
        // 2.产生一个用于邮件发送的Session对象
        Session session = Session.getInstance(props);

        // Message对象:
        Message message = new MimeMessage(session);
        // 设置发件人：
        try {
            // 4.设置消息的发送者
            Address fromAddr = new InternetAddress(myEmailAddr);
            message.setFrom(fromAddr);

            // 5.设置消息的接收者 nkpxcloxbtpxdjai
            Address toAddr = new InternetAddress(to);
            // TO 直接发送 CC抄送 BCC密送
            message.setRecipient(MimeMessage.RecipientType.TO, toAddr);

            // 6.设置主题
            message.setSubject("来自图书馆系统的验证码");
            // 7.设置正文
            message.setContent("这里是邮件的正文信息"+"图书馆注册"+"\n\n您的验证码为：" + code, "text/html;charset=UTF-8");

            // 8.准备发送，得到火箭
            Transport transport = session.getTransport("smtp");
            // 9.设置火箭的发射目标
            //transport.connect("smtp.163.com", "发送者@163.com", "biao********");
            transport.connect("smtp.qq.com", myEmailAddr, "wfjrauibwsetchje");//jxzk..这个就是你的授权码
            // 10.发送
            transport.sendMessage(message, message.getAllRecipients());
            return "";
            // Transport对象:
            // Transport.send(message);
        } catch (AddressException e) {
            return "邮箱格式问题，请重新输入";
        } catch (MessagingException e) {
            return "邮箱格式问题，请重新输入";
        }
    }

//    public static void main(String[] args) {
//        sendMail("2464891684@qq.com","1234567");
//    }
}
