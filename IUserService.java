/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buyingandselling;

import java.util.List;

/**
 *
 * @author Abel
 */
public interface IUserService {
    void registerUser(String type, String username, String password, String phoneNumber);
    User authenticateUser(String username, String password);
    List<User> getAllUsers();
}
