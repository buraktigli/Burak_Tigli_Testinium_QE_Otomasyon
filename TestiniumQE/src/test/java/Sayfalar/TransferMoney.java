package Sayfalar;

import org.openqa.selenium.WebDriver; //WebDriver kullanabilmek için eklendi.

public class TransferMoney extends OrtakKullanim{ //OrtakKullanim class'ının extend'i olarak tanımlandı

    public TransferMoney(WebDriver driver) {  //AnaSayfa constructor'ı oluşturuldu.
        super(driver);
    }

    //final key'i ile String'in farklı class'larda değiştirilmesi engellendi.
    public final String transferMoneyPage = ".r-1xfd6ze"; // transfer money sayfası
    public final String receiveraccount = ".r-1pi2tsx.r-150rngu div:nth-of-type(7) > select"; // receiver account seçilen combo box
    public final String transferAmount = "[placeholder]"; //transfer edilcek amount girilir.
    public final String transferSendButton = ".r-1pi2tsx.r-150rngu .r-1i6wzkk"; //transfer için send butonu
}