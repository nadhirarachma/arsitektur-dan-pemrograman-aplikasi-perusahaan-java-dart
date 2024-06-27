package apap.ta.rumahsehat.setting;

public class Setting {

    private Setting() {}

    public static final String CLIENT_BASE_URL = "http://apap-087.cs.ui.ac.id";
  
    public static final String CLIENT_LOGIN = CLIENT_BASE_URL + "/validate-ticket";

    public static final String CLIENT_LOGOUT = CLIENT_BASE_URL + "/logout";


    public static final String SERVER_BASE_URL = "https://sso.ui.ac.id/cas2";

    public static final String SERVER_LOGIN = SERVER_BASE_URL + "/login?service=";

    public static final String SERVER_LOGOUT = SERVER_BASE_URL + "/logout?url=";

    public static final String SERVER_VALIDATE_TICKET
            = SERVER_BASE_URL + "/serviceValidate?ticket=%s&service=%s";
}
