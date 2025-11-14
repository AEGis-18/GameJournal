package agus.gamejournal.authSecurity.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class TokenRefreshResponse {
    private String accessToken;
    //private String refreshToken;
    private String tokenType="Bearer";
    private String username;
    private String email;
    private List<String> roles;

    public TokenRefreshResponse(String accessToken,String username,
                                String email,List<String> roles/*, String refreshToken*/ ) {
        this.accessToken = accessToken;
        this.username = username;
        this.email = email;
        this.roles = roles;
        //this.refreshToken = refreshToken;
    }
}
