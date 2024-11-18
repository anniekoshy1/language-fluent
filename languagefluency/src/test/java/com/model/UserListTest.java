package com.model;

import java.util.UUID;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;


public class UserListTest {

    private User user1;
    private User user2;
    private UserList userList;

    @Before
    public void setUp() {
        userList = UserList.getInstance(); // Get the singleton instance of UserList
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        
        // Create test users
        user1 = new User(id1, "user1", "user1@example.com", "password1");
        user2 = new User(id2, "user2", "user2@example.com", "password2");
        
        // Add users to the user list
        userList.addUser(user1);
        userList.addUser(user2);
    }

    @After
public void tearDown() {
    // Reset the user list or remove users if necessary
    userList = UserList.getInstance();
    userList.getUsers().clear(); // Assuming you want to clear users for isolation
}

    @Test
    public void testFindUserById_UserExists() {
        // Test finding a user by their UUID
        User foundUser = userList.findUserById(user1.getId());
        assertEquals(user1, foundUser);
    }

    @Test
    public void testFindUserById_UserDoesNotExist() {
        // Test finding a user with a UUID that doesn't exist
        User foundUser = userList.findUserById(UUID.randomUUID()); // Non-existent UUID
        assertNull(foundUser);
    }

    @Test
    public void testAddUser_ValidUser() {
        // Given a new user
        UUID id3 = UUID.randomUUID();
        User user3 = new User(id3, "user3", "user3@example.com", "password3");
        
        // When adding the user to the list
        userList.addUser(user3);
        
        // Then the user list should contain the new user
        assertEquals(3, userList.getTotalUsers());
        assertNotNull(userList.findUserById(id3)); // Ensure the new user can be found
    }

    @Test //bug -- git issue
    public void testAddUser_UserAlreadyExists() {
        // Attempt to add an existing user again
        userList.addUser(user1);
        
        // Verify that the total number of users remains unchanged
        assertEquals(2, userList.getTotalUsers());
    }

    @Test //bug //git issue
    public void testAddUser_NullUser() {
        int initialSize = userList.getTotalUsers(); // Get initial count
        
        // Attempt to add a null user
        userList.addUser(null);
        
        // Verify that the total number of users is unchanged
        assertEquals(initialSize, userList.getTotalUsers());
    }


}
