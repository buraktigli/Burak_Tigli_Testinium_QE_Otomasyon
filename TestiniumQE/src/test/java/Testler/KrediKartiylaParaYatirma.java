package Testler;

import Sayfalar.*;
import org.junit.jupiter.api.*;
import org.testng.annotations.*;
import org.junit.jupiter.api.AfterEach; //AfterEach annotation kullanimi icin "junit-jupiter-api" isimli Maven dependency pom.xml'e eklendi
import org.junit.jupiter.api.BeforeEach; //BeforeEach annotation kullanimi icin "junit-jupiter-api" isimli Maven dependency pom.xml'e eklendi
import org.junit.jupiter.api.Test; //Test annotation kullanimi icin "junit-jupiter-api" isimli Maven dependency pom.xml'e eklendi
//import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.*; // WebDriver kullanmak icin
import org.openqa.selenium.chrome.ChromeDriver; // chrome driver kullanabilmek icin "selenium-chrome-driver" isimli Maven dependency pom.xml'e eklendi
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait; // WebDriverWait kullanabilmek icin "selenium-support" isimli Maven dependency pom.xml'e eklendi.
import org.openqa.selenium.support.ui.ExpectedConditions; // ExpectedConditions kullanabilmek icin "selenium-support" isimli Maven dependency pom.xml'e eklendi.
//import org.testng.annotations.Test;
//import java.io.IOException;
import javax.swing.plaf.TableHeaderUI;
import java.time.Duration; //dinamic wait methodunu kullanabilmek için eklendi.

public class KrediKartiylaParaYatirma {


    WebDriver driver;

    private LoginSayfasi loginSayfasi;
    private OpenSayfasi openSayfasi;
    private AnaSayfa anaSayfa;
    private AddMoney addMoney;

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();

        //sayfaların nesneleri oluşturuldu
        loginSayfasi = new LoginSayfasi(driver);
        openSayfasi = new OpenSayfasi(driver);
        anaSayfa = new AnaSayfa(driver);
        addMoney = new AddMoney(driver);

        driver.manage().window().maximize(); //açılan chrome sayfasını tam ekran boyutu yapmak için kullanıldı.
        driver.get("https://catchylabs-webclient.testinium.com/"); //uygulama sitesine git

    }


    @Test
    void KrediKartiylaParaYatirma_HappyPath() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20L)); //bu method sayesinde static wait olan thread yerine dinamic wait kullanıyoruz.

        System.out.println("KrediKartiylaParaYatirma_HappyPath testi basladi.");

                loginSayfasi.ElementGozukeneKadarBekleCssSelector(loginSayfasi.loginButton); //login butonunu görene kadar bekle
                loginSayfasi.yaziGirCssSelector(loginSayfasi.username,"burak.tigli"); //username gir
                loginSayfasi.yaziGirCssSelector(loginSayfasi.password,"Catchylabs.3568"); //password gir
                loginSayfasi.tusaBasCssSelector(loginSayfasi.loginButton); // login butonuna tıkla

                openSayfasi.ElementGozukeneKadarBekleCssSelector(openSayfasi.openSayfasi); // sayfa açılana kadar bekle
                String ilkSayfaMesaji = openSayfasi.yaziyiKaydetCssSelector(openSayfasi.challengeNameYazisi); // ilk sayfadaki challenge name'i kaydet
                Assertions.assertEquals("Challenge name: QA ve/veya QE Uygulaması 28.01.25",ilkSayfaMesaji);//sayfanın açıldığını anlayabilmek için beklenen yazı ile çıkan sonuçu kıyasla
                System.out.println("    ilk sayfanin acildigi dogrulandi ->> " + ilkSayfaMesaji);
                openSayfasi.tusaBasCssSelector(openSayfasi.openMoneyTransferButton); // open money transfer butonuna tıkla

                anaSayfa.ElementGozukeneKadarBekleCssSelector(anaSayfa.addMoneyButton); // add money butonunu görene kadar bekle
                String oldAmountPart1 = anaSayfa.yaziyiKaydetCssSelector(anaSayfa.amount).substring(0,3); // ana sayfadaki amount alanının ilk 3 hanesini kaydet
                String oldAmountPart2 = anaSayfa.yaziyiKaydetCssSelector(anaSayfa.amount).substring(4,7); // ana sayfadaki amount alanının virgülden sonraki 3 hanesini kaydet
                String totalOldAmount = oldAmountPart1+oldAmountPart2; //kaydedilen 2 sayıyı birleştir. böylece virgülden kurtulmuş oldum. elde edilen String değerli sayıyı int e dönüştürebileceğim
            Integer totalOldAmountInt = Integer.valueOf(totalOldAmount); // String veri tipli sayıyı integer a çevir
            System.out.println("    oldAmount: " + totalOldAmountInt); //oldAmount değerini yazdır
                anaSayfa.tusaBasCssSelector(anaSayfa.addMoneyButton); //add money butonuna tıkla
                Thread.sleep(500);

                addMoney.yaziGirCssSelector(addMoney.cardHolderPlace,"deneme"); //card holder gir
                addMoney.yaziGirCssSelector(addMoney.cardNumberPlace,"1234123412341234"); //card number gir
                addMoney.yaziGirCssSelector(addMoney.cardExpiryDatePlace,"1026"); //expiry date gir
                addMoney.yaziGirCssSelector(addMoney.cardCvvPlace,"110"); //cvv gir
                addMoney.yaziGirCssSelector(addMoney.cardAmountPlace,"1000"); //amount gir
                addMoney.tusaBasCssSelector(addMoney.addButton); //add butonuna tıkla
                System.out.println("Hesaba 1000 dolar yuklendi.");

                anaSayfa.ElementGozukeneKadarBekleCssSelector(anaSayfa.amount); //amount gözükene kadar bekle
                Thread.sleep(5000);
                String newAmountPart1 = anaSayfa.yaziyiKaydetCssSelector(anaSayfa.amount).substring(0,3); // ana sayfadaki amount alanının ilk 3 hanesini kaydet
                String newAmountPart2 = anaSayfa.yaziyiKaydetCssSelector(anaSayfa.amount).substring(4,7); // ana sayfadaki amount alanının virgülden sonraki 3 hanesini kaydet
                String totalNewAmount = newAmountPart1+newAmountPart2; //kaydedilen 2 sayıyı birleştir. böylece virgülden kurtulmuş oldum. elde edilen String değerli sayıyı int e dönüştürebileceğim
            Integer totalNewAmountInt = Integer.valueOf(totalNewAmount); // String veri tipli sayıyı integer a çevir
            System.out.println("    newAmount: " + totalNewAmountInt); //newAmount değerini yazdır
                Assertions.assertEquals(totalOldAmountInt+1000,totalNewAmountInt);// old amount ile new amount u kıyasla.

    }


    @Test
    void KrediKartiylaParaYatirma_KartNo15Hane() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20L)); //bu method sayesinde static wait olan thread yerine dinamic wait kullanıyoruz.

        System.out.println("KrediKartiylaParaYatirma_KartNo15Hane testi basladi.");

                loginSayfasi.ElementGozukeneKadarBekleCssSelector(loginSayfasi.loginButton); //login butonunu görene kadar bekle
                loginSayfasi.yaziGirCssSelector(loginSayfasi.username,"burak.tigli"); //username gir
                loginSayfasi.yaziGirCssSelector(loginSayfasi.password,"Catchylabs.3568"); //password gir
                loginSayfasi.tusaBasCssSelector(loginSayfasi.loginButton); // login butonuna tıkla

                openSayfasi.ElementGozukeneKadarBekleCssSelector(openSayfasi.openSayfasi); // sayfa açılana kadar bekle
                String ilkSayfaMesaji = openSayfasi.yaziyiKaydetCssSelector(openSayfasi.challengeNameYazisi); // ilk sayfadaki challenge name'i kaydet
                Assertions.assertEquals("Challenge name: QA ve/veya QE Uygulaması 28.01.25",ilkSayfaMesaji);//login butonuna tıkladıktan sonra, sayfanın açıldığını anlayabilmek için beklenen yazı ile çıkan sonuçu kıyasla
                System.out.println("    ilk sayfanin acildigi dogrulandi ->> " + ilkSayfaMesaji);
                openSayfasi.tusaBasCssSelector(openSayfasi.openMoneyTransferButton); // open money transfer butonuna tıkla

                anaSayfa.ElementGozukeneKadarBekleCssSelector(anaSayfa.addMoneyButton); // add money butonunu görene kadar bekle
                String oldAmountPart1 = anaSayfa.yaziyiKaydetCssSelector(anaSayfa.amount).substring(0,3); // ana sayfadaki amount alanının ilk 3 hanesini kaydet
                String oldAmountPart2 = anaSayfa.yaziyiKaydetCssSelector(anaSayfa.amount).substring(4,7); // ana sayfadaki amount alanının virgülden sonraki 3 hanesini kaydet
                String totalOldAmount = oldAmountPart1+oldAmountPart2; //kaydedilen 2 sayıyı birleştir. böylece virgülden kurtulmuş oldum. elde edilen String değerli sayıyı int e dönüştürebileceğim
            Integer totalOldAmountInt = Integer.valueOf(totalOldAmount); // String veri tipli sayıyı integer a çevir
            System.out.println("    oldAmount: " + totalOldAmountInt); //oldAmount değerini yazdır
                anaSayfa.tusaBasCssSelector(anaSayfa.addMoneyButton);
                Thread.sleep(500);

                addMoney.yaziGirCssSelector(addMoney.cardHolderPlace,"deneme"); //card holder gir
                addMoney.yaziGirCssSelector(addMoney.cardNumberPlace,"123412341234123"); //card number gir
                addMoney.yaziGirCssSelector(addMoney.cardExpiryDatePlace,"1026"); //expiry date gir
                addMoney.yaziGirCssSelector(addMoney.cardCvvPlace,"110"); //cvv gir
                addMoney.yaziGirCssSelector(addMoney.cardAmountPlace,"1000"); //amount gir
                addMoney.tusaBasCssSelector(addMoney.addButton); //add butonuna tıkla
                System.out.println("add butonuna tiklandi");
                Thread.sleep(2000);

                if (anaSayfa.isEnabledCssSelector(anaSayfa.accountName)){
                System.out.println("kart no 15 hane olmasına rağmen islem yapilmistir");
                String AddMoneySayfasi = addMoney.yaziyiKaydetCssSelector(addMoney.cardNumberPlace); // add money sayfasının kapanmadığını kontrol etmek için başlık kaydedilir.
                Assertions.assertEquals("Add money",AddMoneySayfasi); // add money sayfası kapandığı için assertions hata aldı.
                }
                else{
                System.out.println("kart no 15 hane oldugu icin beklendigi sekilde islem ilerlememiştir.");
                }

    }

    @Test
    void KrediKartiylaParaYatirma_KartNo17Hane() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20L)); //bu method sayesinde static wait olan thread yerine dinamic wait kullanıyoruz.

        System.out.println("KrediKartiylaParaYatirma_HappyPath testi basladi.");

                loginSayfasi.ElementGozukeneKadarBekleCssSelector(loginSayfasi.loginButton); //login butonunu görene kadar bekle
                loginSayfasi.yaziGirCssSelector(loginSayfasi.username,"burak.tigli"); //username gir
                loginSayfasi.yaziGirCssSelector(loginSayfasi.password,"Catchylabs.3568"); //password gir
                loginSayfasi.tusaBasCssSelector(loginSayfasi.loginButton); // login butonuna tıkla

                openSayfasi.ElementGozukeneKadarBekleCssSelector(openSayfasi.openSayfasi); // sayfa açılana kadar bekle
                String ilkSayfaMesaji = openSayfasi.yaziyiKaydetCssSelector(openSayfasi.challengeNameYazisi); // ilk sayfadaki challenge name'i kaydet
                Assertions.assertEquals("Challenge name: QA ve/veya QE Uygulaması 28.01.25",ilkSayfaMesaji);//sayfanın açıldığını anlayabilmek için beklenen yazı ile çıkan sonuçu kıyasla
                System.out.println("    ilk sayfanin acildigi dogrulandi ->> " + ilkSayfaMesaji);
                openSayfasi.tusaBasCssSelector(openSayfasi.openMoneyTransferButton); // open money transfer butonuna tıkla

                anaSayfa.ElementGozukeneKadarBekleCssSelector(anaSayfa.addMoneyButton); // add money butonunu görene kadar bekle
                String oldAmountPart1 = anaSayfa.yaziyiKaydetCssSelector(anaSayfa.amount).substring(0,3); // ana sayfadaki amount alanının ilk 3 hanesini kaydet
                String oldAmountPart2 = anaSayfa.yaziyiKaydetCssSelector(anaSayfa.amount).substring(4,7); // ana sayfadaki amount alanının virgülden sonraki 3 hanesini kaydet
                String totalOldAmount = oldAmountPart1+oldAmountPart2; //kaydedilen 2 sayıyı birleştir. böylece virgülden kurtulmuş oldum. elde edilen String değerli sayıyı int e dönüştürebileceğim
            Integer totalOldAmountInt = Integer.valueOf(totalOldAmount); // String veri tipli sayıyı integer a çevir
            System.out.println("    oldAmount: " + totalOldAmountInt); //oldAmount değerini yazdır
                anaSayfa.tusaBasCssSelector(anaSayfa.addMoneyButton); // add money butonuna tıkla
                Thread.sleep(500);

                addMoney.yaziGirCssSelector(addMoney.cardHolderPlace,"deneme"); //card holder gir
                addMoney.yaziGirCssSelector(addMoney.cardNumberPlace,"12341234123412341"); //card number gir
                addMoney.yaziGirCssSelector(addMoney.cardExpiryDatePlace,"1026"); //expiry date gir
                addMoney.yaziGirCssSelector(addMoney.cardCvvPlace,"110"); //cvv gir
                addMoney.yaziGirCssSelector(addMoney.cardAmountPlace,"1000"); //amount gir
                addMoney.tusaBasCssSelector(addMoney.addButton); //add butonuna tıkla
                System.out.println("add butonuna tiklandi");
                Thread.sleep(2000);

                if (addMoney.isEnabledCssSelector(addMoney.addMoneyPage)){
                String tooLongUyarisi = driver.findElement(By.cssSelector(".r-1pi2tsx.r-150rngu div:nth-of-type(2) > .r-yv33h5")).getText(); // too long yazısı kaydedilir.
                Assertions.assertEquals("Too Long!",tooLongUyarisi); // Başlık kıyaslaması yap
                System.out.println("kart no 17 hane oldugu icin islem ilerlememiştir. ekranda too long uyarısı verilmistir."); // add money sayfası kapandığı için assertions hata aldı.
                }
                else{
                System.out.println("kart no 17 hane olmasına rağmen islem yapilmistir");
                }

    }

    @AfterEach
    void teardown () {

        driver.quit();
    }


}
