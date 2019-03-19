package com.ge.auth.bean;

/**
 * @author: hehui
 * Date: 2019/3/18
 * @Description: 客户端信息
 */
public class AuthClient {

    private String client_id;//客户端编号
    private String client_secret;//客户端明文秘钥
    private String grant_type;  //客户端验证方式
    private int access_token_timeout;
    private int refresh_token_timeout;



    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }


    public int getAccess_token_timeout() {
        return access_token_timeout;
    }

    public void setAccess_token_timeout(int access_token_timeout) {
        this.access_token_timeout = access_token_timeout;
    }

    public int getRefresh_token_timeout() {
        return refresh_token_timeout;
    }

    public void setRefresh_token_timeout(int refresh_token_timeout) {
        this.refresh_token_timeout = refresh_token_timeout;
    }

    @Override
    public String toString() {
        return "AuthClient{" +
                "client_id='" + client_id + '\'' +
                ", client_secret='" + client_secret + '\'' +
                ", grant_type='" + grant_type + '\'' +
                ", access_token_timeout=" + access_token_timeout +
                ", refresh_token_timeout=" + refresh_token_timeout +
                '}';
    }
}
