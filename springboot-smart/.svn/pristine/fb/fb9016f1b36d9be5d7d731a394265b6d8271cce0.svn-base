package com.cignacmb.smart.base.utils.mail;

import java.io.File;

public class SmartMailInfo {
    /**
     * 邮件类型
     */
    private String mailType;
    /**
     * 邮件标题
     */
    private String mailTitle;
    /**
     * 邮件内容
     */
    private String mailMsg;
    /**
     * 收件人
     */
    private String mailTo;
    /**
     * 抄送人
     */
    private String mailCc;
    /**
     * 发件人
     */
    private String mailFromUrl;
    /**
     * 发件人邮箱登录名
     */
    private String mailFromUser;
    /**
     * 发件人邮箱密码
     */
    private String mailFromPwd;
    /**
     * 邮件发送服务器
     */
    private String smtpHost;
    /**
     * 附件
     */
    private File[] files;

    /**
     * 系统标识
     */
    private String theinv;

    public static final class Builder {
        private String mailType;
        private String mailTitle;
        private String mailMsg;
        private String mailTo;
        private String mailCc;
        private String mailFromUrl;
        private String mailFromUser;
        private String mailFromPwd;
        private String smtpHost;
        private File[] files;
        private String theinv;

        private Builder() {
        }

        public static Builder aSmartMailInfo() {
            return new Builder();
        }

        public Builder withMailType(String mailType) {
            this.mailType = mailType;
            return this;
        }

        public Builder withMailTitle(String mailTitle) {
            this.mailTitle = mailTitle;
            return this;
        }

        public Builder withMailMsg(String mailMsg) {
            this.mailMsg = mailMsg;
            return this;
        }

        public Builder withMailTo(String mailTo) {
            this.mailTo = mailTo;
            return this;
        }

        public Builder withMailCc(String mailCc) {
            this.mailCc = mailCc;
            return this;
        }

        public Builder withMailFromUrl(String mailFromUrl) {
            this.mailFromUrl = mailFromUrl;
            return this;
        }

        public Builder withMailFromUser(String mailFromUser) {
            this.mailFromUser = mailFromUser;
            return this;
        }

        public Builder withMailFromPwd(String mailFromPwd) {
            this.mailFromPwd = mailFromPwd;
            return this;
        }

        public Builder withSmtpHost(String smtpHost) {
            this.smtpHost = smtpHost;
            return this;
        }

        public Builder withFiles(File[] files) {
            this.files = files;
            return this;
        }

        public Builder withTheinv(String theinv) {
            this.theinv = theinv;
            return this;
        }

        public SmartMailInfo build() {
            SmartMailInfo smartMailInfo = new SmartMailInfo();
            smartMailInfo.mailFromUser = this.mailFromUser;
            smartMailInfo.mailFromPwd = this.mailFromPwd;
            smartMailInfo.mailTo = this.mailTo;
            smartMailInfo.mailFromUrl = this.mailFromUrl;
            smartMailInfo.smtpHost = this.smtpHost;
            smartMailInfo.mailCc = this.mailCc;
            smartMailInfo.mailType = this.mailType;
            smartMailInfo.mailTitle = this.mailTitle;
            smartMailInfo.mailMsg = this.mailMsg;
            smartMailInfo.files = this.files;
            smartMailInfo.theinv = this.theinv;
            return smartMailInfo;
        }
    }

    public String getMailType() {
        return mailType;
    }

    public String getMailTitle() {
        return mailTitle;
    }

    public String getMailMsg() {
        return mailMsg;
    }

    public String getMailTo() {
        return mailTo;
    }

    public String getMailCc() {
        return mailCc;
    }

    public String getMailFromUrl() {
        return mailFromUrl;
    }

    public String getMailFromUser() {
        return mailFromUser;
    }

    public String getMailFromPwd() {
        return mailFromPwd;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public File[] getFiles() {
        return files;
    }

    public String getTheinv() {
        return theinv;
    }
}
