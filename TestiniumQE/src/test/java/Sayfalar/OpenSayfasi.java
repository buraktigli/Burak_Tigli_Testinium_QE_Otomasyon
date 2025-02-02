package Sayfalar;

import org.openqa.selenium.WebDriver; //WebDriver kullanabilmek için eklendi.

public class OpenSayfasi extends OrtakKullanim{ //OrtakKullanim class'ının extend'i olarak tanımlandı

    public OpenSayfasi(WebDriver driver) {  //AnaSayfa constructor'ı oluşturuldu.
        super(driver);
    }

    //final key'i ile String'in farklı class'larda değiştirilmesi engellendi.
    public final String openSayfasi = ".r-1i3z66q"; // open sayfası
    public final String openMoneyTransferButton = ".r-z2wwpe"; // open money transfer butonu
    public final String challengeNameYazisi = ".r-1i3z66q > div:nth-of-type(1)"; //open sayfasındaki challenge yazısı

}