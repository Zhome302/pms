package com.ai.zhome.pms.common.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * 账号表I_USER
 * 
 * @author wangzh
 *
 */
public class IUser {

    @NotBlank(message = "账号不能为空！")
    @Size(max = 20, message = "账号长度不能超过20位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号仅支持大小写字母+数字")
    private String userID;

    @NotBlank(message = "用户名称不能为空！")
    @Size(max = 20, message = "用户名称长度不能超过20位")
    private String userName;

    private Integer passwordType;

    private String password;

    private Integer status;

    private String email;

    @NotBlank(message = "联系方式不能为空！")
    @Pattern(regexp = "^1[34578]\\d{9}$", message = "联系方式格式不正确！")
    private String phone;

    private Date createDate;

    private Date modDate;

    private Date modpwdDate;

    private String operator;

    private List<String> roles;

    private String roleList;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(Integer passwordType) {
        this.passwordType = passwordType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public Date getModpwdDate() {
        return modpwdDate;
    }

    public void setModpwdDate(Date modpwdDate) {
        this.modpwdDate = modpwdDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getRoleList() {
        return roleList;
    }

    public void setRoleList(String roleList) {
        this.roleList = roleList;
    }
}
