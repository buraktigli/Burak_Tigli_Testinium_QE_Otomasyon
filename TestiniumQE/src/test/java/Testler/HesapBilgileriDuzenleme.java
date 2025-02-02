package Testler;

import Sayfalar.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.safari.SafariDriver;
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



public class HesapBilgileriDuzenleme{

        WebDriver driver;

    //sayfalar için tanımlamalar yapıldı.
    private LoginSayfasi loginSayfasi;
    private OpenSayfasi openSayfasi;
    private AnaSayfa anaSayfa;
    private EditAccount editAccount;


        @BeforeEach
        void setup(){
            driver = new ChromeDriver();
            //driver = new SafariDriver();

            //sayfaların nesneleri oluşturuldu
            loginSayfasi = new LoginSayfasi(driver);
            openSayfasi = new OpenSayfasi(driver);
            anaSayfa = new AnaSayfa(driver);
            editAccount = new EditAccount(driver);

            driver.manage().window().maximize(); //açılan chrome sayfasını tam ekran boyutu yapmak için kullanıldı.
            driver.get("https://catchylabs-webclient.testinium.com/"); //uygulama sitesine git

        }

        @Test
        void hesapBilgileriDuzenlemeTesti_UygunFormat() throws InterruptedException {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20L)); //bu method sayesinde static wait olan thread yerine dinamic wait kullanıyoruz.

            System.out.println("hesapBilgileriDuzenlemeTesti_UygunFormat testi basladi. ismin basarili sekilde degistirildigi gorulmeli.");

                    loginSayfasi.ElementGozukeneKadarBekleCssSelector(loginSayfasi.loginButton); //login butonunu görene kadar bekle
                    loginSayfasi.yaziGirCssSelector(loginSayfasi.username,"burak.tigli"); //username gir
                    loginSayfasi.yaziGirCssSelector(loginSayfasi.password,"Catchylabs.3568"); //password gir
                    loginSayfasi.tusaBasCssSelector(loginSayfasi.loginButton); // login butonuna tıkla

                    openSayfasi.ElementGozukeneKadarBekleCssSelector(openSayfasi.openSayfasi); // sayfa açılana kadar bekle
                    String ilkSayfaMesaji = openSayfasi.yaziyiKaydetCssSelector(openSayfasi.challengeNameYazisi); // ilk sayfadaki challenge name'i kaydet
                    Assertions.assertEquals("Challenge name: QA ve/veya QE Uygulaması 28.01.25",ilkSayfaMesaji);//sayfanın açıldığını anlayabilmek için beklenen yazı ile çıkan sonuçu kıyasla
                    System.out.println("    ilk sayfanin acildigi dogrulandi ->> " + ilkSayfaMesaji);
                    openSayfasi.tusaBasCssSelector(openSayfasi.openMoneyTransferButton); // open money transfer butonuna tıkla

                    anaSayfa.ElementGozukeneKadarBekleCssSelector(anaSayfa.editAccountButton); // edit account butonunu görene kadar bekle
                    anaSayfa.tusaBasCssSelector(anaSayfa.editAccountButton); // edit account butonuna tıkla

                    editAccount.ElementGozukeneKadarBekleCssSelector(editAccount.newAccountName); //account isminin girileceği alan açılana kadar bekle
                    editAccount.tusaBasCssSelector(editAccount.newAccountName); //account name girilecek olan boş alana tıkla
                    editAccount.yaziGirCssSelector(editAccount.newAccountName,Keys.COMMAND + Keys.chord("a")); //account name'deki eski yazıyı ctrl+a ile seç
                    editAccount.yaziGirCssSelector(editAccount.newAccountName,"new account name"); //yeni account name gir
                    editAccount.tusaBasCssSelector(editAccount.updateButton); // update butonuna tıkla
                    Thread.sleep(2000); //2 sn bekle

                    String newAccountName = anaSayfa.yaziyiKaydetCssSelector(anaSayfa.accountName).substring(0,16); // ana sayfadaki account name alanını kaydet
                    Assertions.assertEquals("new account name",newAccountName);// ismin doğru güncellendiğini anlayabilmek için beklenen yazı ile çıkan sonuçu kıyasla
                    System.out.println("    account name'in basarili sekilde guncellendigi dogrulandi ->> " + newAccountName);

        }

        @Test
        void hesapBilgileriDuzenlemeTesti_SadeceNumara() throws InterruptedException {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20L)); //bu method sayesinde static wait olan thread yerine dinamic wait kullanıyoruz.

            System.out.println("hesapBilgileriDuzenlemeTesti_SadeceNumara testi basladi. Sadece numara girilerek update edilmesine izin verilmemeli.");

                    loginSayfasi.ElementGozukeneKadarBekleCssSelector(loginSayfasi.loginButton); //login butonunu görene kadar bekle
                    loginSayfasi.yaziGirCssSelector(loginSayfasi.username,"burak.tigli"); //username gir
                    loginSayfasi.yaziGirCssSelector(loginSayfasi.password,"Catchylabs.3568"); //password gir
                    loginSayfasi.tusaBasCssSelector(loginSayfasi.loginButton); // login butonuna tıkla

                    openSayfasi.ElementGozukeneKadarBekleCssSelector(openSayfasi.openSayfasi); // sayfa açılana kadar bekle
                    String ilkSayfaMesaji = openSayfasi.yaziyiKaydetCssSelector(openSayfasi.challengeNameYazisi); // ilk sayfadaki challenge name'i kaydet
                    Assertions.assertEquals("Challenge name: QA ve/veya QE Uygulaması 28.01.25",ilkSayfaMesaji);//sayfanın açıldığını anlayabilmek için beklenen yazı ile çıkan sonuçu kıyasla
                    System.out.println("    ilk sayfanin acildigi dogrulandi ->> " + ilkSayfaMesaji);
                    openSayfasi.tusaBasCssSelector(openSayfasi.openMoneyTransferButton); // open money transfer butonuna tıkla

                    anaSayfa.ElementGozukeneKadarBekleCssSelector(anaSayfa.editAccountButton); // edit account butonunu görene kadar bekle
                    anaSayfa.tusaBasCssSelector(anaSayfa.editAccountButton); // edit account butonuna tıkla

                    editAccount.ElementGozukeneKadarBekleCssSelector(editAccount.newAccountName); //account isminin girileceği alan açılana kadar bekle
                    editAccount.tusaBasCssSelector(editAccount.newAccountName); //account name girilecek olan boş alana tıkla
                    editAccount.yaziGirCssSelector(editAccount.newAccountName,Keys.COMMAND + Keys.chord("a")); //account name'deki eski yazıyı ctrl+a ile seç
                    editAccount.yaziGirCssSelector(editAccount.newAccountName,"123456"); //yeni account name gir
                    editAccount.tusaBasCssSelector(editAccount.updateButton); // update butonuna tıkla
                    Thread.sleep(2000); //2 sn bekle

            if (driver.findElement(By.cssSelector(".r-d23pfw.r-97e31f")).isEnabled()){
                String newAccountName = anaSayfa.yaziyiKaydetCssSelector(anaSayfa.accountName).substring(0,6); //anasayfadaki account name i kaydet
                Assertions.assertEquals("new account name",newAccountName);// sadece sayi girildigi icin account name ayni kalmali. bu kiyaslama dogru donmeli
            }
            else{
                String editAccountBasligi = driver.findElement(By.cssSelector(".r-1wtj0ep")).getText(); // add money sayfasının kapanmadığını kontrol etmek için başlık kaydedilir.
                Assertions.assertEquals("Edit Account" ,editAccountBasligi); // Başlık kıyaslaması yap
            }

        }

        @Test
        void hesapBilgileriDuzenlemeTesti_BosBirakma() throws InterruptedException {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20L)); //bu method sayesinde static wait olan thread yerine dinamic wait kullanıyoruz.

            System.out.println("hesapBilgileriDuzenlemeTesti_BosBirakma testi basladi. Bos birakmaya izin verilmemeli.");

                    loginSayfasi.ElementGozukeneKadarBekleCssSelector(loginSayfasi.loginButton); //login butonunu görene kadar bekle
                    loginSayfasi.yaziGirCssSelector(loginSayfasi.username,"burak.tigli"); //username gir
                    loginSayfasi.yaziGirCssSelector(loginSayfasi.password,"Catchylabs.3568"); //password gir
                    loginSayfasi.tusaBasCssSelector(loginSayfasi.loginButton); // login butonuna tıkla

                    openSayfasi.ElementGozukeneKadarBekleCssSelector(openSayfasi.openSayfasi); // sayfa açılana kadar bekle
                    String ilkSayfaMesaji = openSayfasi.yaziyiKaydetCssSelector(openSayfasi.challengeNameYazisi); // ilk sayfadaki challenge name'i kaydet
                    Assertions.assertEquals("Challenge name: QA ve/veya QE Uygulaması 28.01.25",ilkSayfaMesaji);//login butonuna tıkladıktan sonra, sayfanın açıldığını anlayabilmek için beklenen yazı ile çıkan sonuçu kıyasla
                    System.out.println("    ilk sayfanin acildigi dogrulandi ->> " + ilkSayfaMesaji);
                    openSayfasi.tusaBasCssSelector(openSayfasi.openMoneyTransferButton); // open money transfer butonuna tıkla

                    anaSayfa.ElementGozukeneKadarBekleCssSelector(anaSayfa.editAccountButton); // edit account butonunu görene kadar bekle
                    anaSayfa.tusaBasCssSelector(anaSayfa.editAccountButton); // edit account butonuna tıkla

                    editAccount.ElementGozukeneKadarBekleCssSelector(editAccount.newAccountName); //account isminin girileceği alan açılana kadar bekle
                    editAccount.tusaBasCssSelector(editAccount.newAccountName); //account name girilecek olan boş alana tıkla
                    editAccount.yaziGirCssSelector(editAccount.newAccountName,Keys.COMMAND + Keys.chord("a")); //account name'deki yazıyı ctrl+a ile seç
                    editAccount.yaziGirCssSelector(editAccount.newAccountName, String.valueOf(Keys.DELETE)); //yeni account name gir (boş olarak)
                    editAccount.tusaBasCssSelector(editAccount.updateButton); // update butonuna tıkla
                    Thread.sleep(2000); //sayfanın değişmediğini görmek için 2 sn bekle
                    editAccount.ElementGozukeneKadarBekleCssSelector(editAccount.editAccountPage); //sayfanın değişmediği dolayısıyla update işleminin gerçekleşmediği görülür.
                    System.out.println("Bos birakilan account name alaninin update edilemedigi dogrulandi.");

        }

        @AfterEach
        void teardown () {

            driver.quit();
        }
    }
