package Sayfalar;

import org.openqa.selenium.WebDriver; //WebDriver kullanabilmek için eklendi.

public class AnaSayfa extends OrtakKullanim{ //OrtakKullanim class'ının extend'i olarak tanımlandı

    public AnaSayfa(WebDriver driver) {  //AnaSayfa constructor'ı oluşturuldu.
        super(driver);
    }

    //final key'i ile String'in farklı class'larda değiştirilmesi engellendi.
    public final String editAccountButton = ".r-1fvujvx > div:nth-of-type(3)"; // edit account butonu
    public final String addMoneyButton = ".r-1fvujvx > div:nth-of-type(2)"; // add money butonu
    public final String transferMoneyButton = ".r-1fvujvx > div:nth-of-type(1)"; // transfer money butonu
    public final String accountName = ".r-edyy15.r-14lw9ot > div:nth-of-type(1) > .r-yv33h5"; //account name kısmı
    public final String amount = ".r-edyy15.r-14lw9ot > .css-175oi2r > .css-175oi2r"; // amount kısmı

}