package Sayfalar;

import org.openqa.selenium.WebDriver; //WebDriver kullanabilmek için eklendi.

public class LoginSayfasi extends OrtakKullanim{ //OrtakKullanim class'ının extend'i olarak tanımlandı

    public LoginSayfasi(WebDriver driver) {  //AnaSayfa constructor'ı oluşturuldu.
        super(driver);
    }

    //final key'i ile String'in farklı class'larda değiştirilmesi engellendi.
    public final String username = "[placeholder='Username']"; // username girilen kısım
    public final String password = "[placeholder='Password']"; // password girilen kısım
    public final String loginButton = ".r-1i6wzkk"; // login butonu

}
