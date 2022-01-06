package decagon.week8.chatapp.services;


import decagon.week8.chatapp.model.User;
import decagon.week8.chatapp.repository.UserRepository;
import decagon.week8.chatapp.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceTest {
UserService userService = null;
UserRepository repository = Mockito.mock(UserRepository.class);
User user;


@BeforeEach
public void setup(){
    userService = new UserService(repository);

    user= new User();
    user.setFirstName("Odochi");
    user.setLastName("Okara");
    user.setEmail("okaraodochi@gmail.com");
    user.setPassword("1234");
    user.setDateOfBirth("03-05-2007");
}

@Test
public void getPersonByEmail(){
    when(repository.getUserByEmail(anyString())).thenReturn(user);
    User getPerson = userService.getUserByEmail("okaraodochi@gmail.com");
    assertNotNull(getPerson);
    assertEquals(user.getEmail(), getPerson.getEmail());
    verify(repository, times(1)).getUserByEmail(anyString());
}

@Test
public void saveRegisteredUsers(){
    when(repository.save(any(User.class))).thenReturn(user);
    User newPerson = new User(1L,"ODOCHI","OKARA","okara@gmail","1234","Female","03/04/90");
    assertNotNull(userService.addUser(newPerson));
    verify(repository, times(1)).save(any(User.class));
}


}