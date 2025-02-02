package Sayfalar;

import org.openqa.selenium.WebDriver; //WebDriver kullanabilmek için eklendi.

public class AddMoney extends OrtakKullanim{ //OrtakKullanim class'ının extend'i olarak tanımlandı

    public AddMoney(WebDriver driver) {  //AnaSayfa constructor'ı oluşturuldu.
        super(driver);
    }

    //final key'i ile String'in farklı class'larda değiştirilmesi engellendi.
    public final String addMoneyPage = ".r-1xfd6ze"; // add money sayfası
    public final String cardNumberPlace = ".r-1mqtkb5";
    public final String cardHolderPlace = ".r-1pi2tsx.r-150rngu div:nth-of-type(4) > [placeholder]";
    public final String cardExpiryDatePlace = "[placeholder='MM/YY']";
    public final String cardCvvPlace = ".r-1pi2tsx.r-150rngu > .css-175oi2r > .css-175oi2r > div:nth-of-type(2) [placeholder]";
    public final String cardAmountPlace = ".r-1pi2tsx.r-150rngu div:nth-of-type(8) > [placeholder]";
    public final String addButton = ".r-1pi2tsx.r-150rngu .r-1i6wzkk";


}