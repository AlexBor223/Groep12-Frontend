import dao.AbbreviationDao;
import models.Abbreviation;
import org.junit.Test;
import services.LoginService;

import static org.junit.Assert.*;

public class AlexUnitTests {
    @Test
    public void should_returnNull_when_login_unsuccessfull() throws Exception {
        // Arrange=
        LoginService loginService = LoginService.getInstance();
        String username = "Leonard";
        String password = "1245";

        // Act
        loginService.login(username, password);
        String JWT = loginService.getAccessToken();

        // Assert
        assertNull(JWT, null);
    }

}
