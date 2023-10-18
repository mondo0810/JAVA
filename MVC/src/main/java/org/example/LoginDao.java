package org.example;

public interface LoginDao {
    public String checkLoginStatement(Users user);
    public String checkLoginPreparedStatement(Users user);
}
