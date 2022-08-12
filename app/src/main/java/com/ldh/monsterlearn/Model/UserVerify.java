package com.ldh.monsterlearn.Model;

public class UserVerify {
    private String Name,Username,Password,Gmail,Phone,Otp;

    public UserVerify(String name, String username, String password, String email, String phone,String otp) {
        Name = name;
        Username = username;
        Password = password;
        Gmail = email;
        Phone = phone;
        Otp = otp;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getOtp() {
        return Otp;
    }

    public void setOtp(String otp) {
        Otp = otp;
    }

    public void setUserVerify(User user,String Otp){
        this.Name = user.getName();
        this.Username = user.getUsername();
        this.Password = user.getPassword();
        this.Gmail = user.getGmail();
        this.Phone = user.getPhone();
        this.Otp = Otp;
    }
}
