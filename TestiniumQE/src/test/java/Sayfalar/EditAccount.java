package Sayfalar;

import org.openqa.selenium.WebDriver; //WebDriver kullanabilmek için eklendi.

public class EditAccount extends OrtakKullanim{ //OrtakKullanim class'ının extend'i olarak tanımlandı

    public EditAccount(WebDriver driver) {  //AnaSayfa constructor'ı oluşturuldu.
        super(driver);
    }

    //final key'i ile String'in farklı class'larda değiştirilmesi engellendi.
    public final String newAccountName = "[placeholder]"; // yeni hesap adı girme alanı
    public final String updateButton = ".r-1pi2tsx.r-150rngu .r-1i6wzkk"; // update butonu
    public final String editAccountPage = ".r-1xfd6ze"; // edit account ekranı

}