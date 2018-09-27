package com.zxy.mytsfqxproject.mvp.entity;

public class UserBean {

    /**
     * code : 200
     * errmsg : 登录成功
     * result : {"staff_id":96,"username":"钛师傅96","phone":"18030839218","store_id":9,"store_name":"","is_login_allow":1,"photo":"http://v3.taishifu.com.cn/store/data/upload//store/05888877796158241.png","acctoken":"f4S7s4B2c2aDd6Rihnt-YIu4vNKRZ82bgoaeaoCfr5WVmpdrlKCEbJmgg6mboZSblris0Z59o50","first_guide":true,"rongCloud":"olsoteRFd3TR42h5XmQheTUZ3QGwe+UTFO/diiQ4vgrum3QEKIbMd7IzNKNLzbMagMh0Zh1G3tg="}
     */

    private int code;
    private String errmsg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * staff_id : 96
         * username : 钛师傅96
         * phone : 18030839218
         * store_id : 9
         * store_name :
         * is_login_allow : 1
         * photo : http://v3.taishifu.com.cn/store/data/upload//store/05888877796158241.png
         * acctoken : f4S7s4B2c2aDd6Rihnt-YIu4vNKRZ82bgoaeaoCfr5WVmpdrlKCEbJmgg6mboZSblris0Z59o50
         * first_guide : true
         * rongCloud : olsoteRFd3TR42h5XmQheTUZ3QGwe+UTFO/diiQ4vgrum3QEKIbMd7IzNKNLzbMagMh0Zh1G3tg=
         */

        private int staff_id;
        private String username;
        private String phone;
        private int store_id;
        private String store_name;
        private int is_login_allow;
        private String photo;
        private String acctoken;
        private boolean first_guide;
        private String rongCloud;

        public int getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(int staff_id) {
            this.staff_id = staff_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public int getIs_login_allow() {
            return is_login_allow;
        }

        public void setIs_login_allow(int is_login_allow) {
            this.is_login_allow = is_login_allow;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getAcctoken() {
            return acctoken;
        }

        public void setAcctoken(String acctoken) {
            this.acctoken = acctoken;
        }

        public boolean isFirst_guide() {
            return first_guide;
        }

        public void setFirst_guide(boolean first_guide) {
            this.first_guide = first_guide;
        }

        public String getRongCloud() {
            return rongCloud;
        }

        public void setRongCloud(String rongCloud) {
            this.rongCloud = rongCloud;
        }
    }
}
