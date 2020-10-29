package org.example.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
    @NotBlank(message = "工号不能为空")
    @Size(min = 10, max = 10, message = "工号长度必须为10位")
    private String workNo;
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "密码必须是6~20位的数字或字母")
    private String password;

    private Integer userType;

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
