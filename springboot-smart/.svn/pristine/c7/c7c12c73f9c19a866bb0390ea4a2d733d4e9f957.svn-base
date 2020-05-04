package com.cignacmb.smart.base.utils.mail;

import com.cignacmb.smart.base.utils.basic.StringUtils;
import com.cignacmb.smart.base.utils.exception.BusinessException;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import javax.mail.internet.MimeUtility;
import java.io.File;

public class MailUtils {

    public static void send(SmartMailInfo mailInfo) throws Exception{

        if (StringUtils.isEmpty(mailInfo.getMailType())){
            throw new BusinessException("邮件类型参数缺失");
        }

        MultiPartEmail email = new MultiPartEmail();

        if (mailInfo.getFiles() != null && mailInfo.getFiles().length > 0){
            for (File file : mailInfo.getFiles()){
                EmailAttachment attachment = new EmailAttachment();
                attachment.setPath(file.getAbsolutePath());
                attachment.setDisposition(EmailAttachment.ATTACHMENT);
                attachment.setDescription(file.getName());
                attachment.setName(MimeUtility.encodeText(file.getName())); //显示的文件名
                email.attach(attachment);
            }
        }

        //smtp host
        email.setHostName(mailInfo.getSmtpHost());
        //登陆邮件服务器的用户名和密码
        email.setAuthentication(mailInfo.getMailFromUser(), mailInfo.getMailFromPwd());
        //接收人
        makeToAndCc(email, mailInfo);
        //发送人
        email.setFrom(mailInfo.getMailFromUrl(), mailInfo.getMailFromUser());
        //标题,增加环境标识
        String subject = (StringUtils.isEmpty(mailInfo.getTheinv()) ? "系统邮件_" : mailInfo.getTheinv() + "_系统邮件_") + mailInfo.getMailTitle();
        email.setSubject(subject);
        //邮件内容
        email.setMsg(mailInfo.getMailMsg());

        email.send();

    }

    /**
     * 解析接收人和抄送人
     * @param email
     * @param mailInfo
     * @throws Exception
     */
    private static void makeToAndCc(MultiPartEmail email, SmartMailInfo mailInfo) throws Exception {
        String[] tos = mailInfo.getMailTo().split(",");

        for (String to: tos){
            email.addTo(to.trim(), to);
        }

        if (!StringUtils.isEmpty(mailInfo.getMailCc())){
            String[] ccs = mailInfo.getMailCc().split(",");

            for (String cc: ccs){
                email.addCc(cc.trim(), cc);
            }
        }
    }

}