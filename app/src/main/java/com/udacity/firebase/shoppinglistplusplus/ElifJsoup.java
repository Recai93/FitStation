package com.udacity.firebase.shoppinglistplusplus;

import android.os.AsyncTask;
import android.util.Log;

import com.firebase.client.Firebase;
import com.udacity.firebase.shoppinglistplusplus.model.Meal;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajaee on 3/23/16.
 */
public class ElifJsoup {

    public void getData() {
        FetchNextVenuesTask weatherTask = new FetchNextVenuesTask();
        weatherTask.execute("http://www.kalori.biz/kalori-cetveli/");
    }

    public void getData2() {
        String string = "Kaliteli beslenin. Taze meyve ve sebze, balık, fındık, yeşil çay mutlaka menünüzde bulunsun 2. Akciğerleri çalıştırın. Düzenli egzersiz yaparak ciğer kapasitenizi 50 yaşından sonra bile %25 artırmanız mümkün 3. Sigarayı kesinlikle bırakın. 4. Doğru nefes almayı öğrenin. İdeali karından alınan dakikada 12-14 nefestir 5. Kilonuza dikkat edin. Her fazla kilo, eklemleriniz üzerinde gereksiz bir yüktür 6. Kahvaltıyı asla ihmal etmeyin. Kahvaltıda lifli gıdalar almaya dikkat edin. 7. Kaslarınızı çalıştırın. Düzenli çalışmayla 12 hafta içinde orta yaşlarda bile kas gücünüzü geliştirmeniz mümkündür 8. Seks hayatınızı ihmal etmeyin. Seks yapmak, yalnızca egzersiz olarak değil, vücuttaki hormon salgılarının düzenli şekilde devam etmesi için de gerekli bir aktivitedir. 9. Formunuzu koruyun. Yoga ve pilates yararlı ama yüzmek ve yürümek de asla ihmal edilmemesi gereken egzersizler 10. Yağ dengenize dikkat. Vücut için en iyi yağ balıkta ve cevizde bulunur 11. Vücuttaki pas ve tortuyu atın. Miktarda aşırıya kaçmamak şartıyla yeşil çay, siyah çikolata tüketin. 12. Kötü diyetlerden uzak durun. Kemik sağlığı için her yaşta kalsiyum almanız gerektiğini unutmayın 13. Güneşlenmeyi ihmal etmeyin. Her gün birkaç dakikalığına da olsa gün ışığına çıkın. Vücudunuzun D vitamini üretebilmesi için güneş ışığına ihtiyacı var 14. Günde en az bir buçuk litre temiz su için. İdrarının rengini kontrol edin, eğer sarıysa yeteri kadar su içmiyorsunuz demektir. 15. Yürüyüşe çıkın. Düzenli yürüyüş östrojen riskini azaltır, stresi önler 16. Kalbinizi koruyun ve güçlendirin: B vitamini, magnezyum ve çinko almayı unutmayın. Havuç, lahana, avokado, fındık ve sarımsak mutfağınızdan eksik olmasın 17. Tansiyonunuzu takip edin. Tuz, aşırı kilo ve stres, tansiyon riskini artıran faktörlerdir. 18. Kalbinizi kontrol ettirin. Yılda bir kez check-up yaptırın 19. Stresten uzak durun. 20. Güneşte uzun süre kalacaksanız mutlaka UV filtreli güneş yağı kullanın. 21. Şeker tüketiminizi azaltın. Canınız tatlı çektiğinde bitter çikolata tercih edin. 22. Günde en az 7 saat uyumayı ihmal etmeyin 23. Cildinizi besleyin. A, C ve E vitaminleri içeren cilt losyonu kullanın. 24. Vücudunuzun ihtiyaçlarını öncelik sırasına sokun. Gün 24 saat. Bunu üçe bölün: 8 saati çalışmaya, 8 saati kendinize ve 8 saati uyku ve istirahat için ayırın 25. Gününüzü planlayın. Planlı yaşamak, vaktinizi daha verimli ve yararlı geçirmenizi ve stresten kaçınmanızı sağlar. 26. Dişlerinize iyi bakın. Günde en az iki kere dişlerinizi fırçalayın, kahveyi fazla içmekten kaçının. 27. Şeklinizi koruyun. Vücudunuz bütün gün televizyon veya bilgisayar başında oturmak için dizayn edilmedi. Kalkın, hareket edin. 28. Olumlu düşünün. Uzmanlar \u0091varlığıyla mutlu olduğunuz\u0092 şeyleri düşünmenizi tavsiye ediyorlar. Pozitif düşünce hem sizi genç tutar, hem stresi azaltır 29. Zihinsel bakımınızı ihmal etmeyin. Televizyon karşısında çok fazla vakit geçirmek bunama riskini artırıyor. Yeni bir şeyler öğrenip yeni alışkanlıklar geliştirerek zihinsel faaliyetlerinizi aralıksız sürdürün.";
        String[] array = string.split("[\\d]+\\.");
        for (String str : array) {
            Log.i("**********", str);
        }
        Log.i("","");

    }

    public class FetchNextVenuesTask extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... params) {
            Document doc = null;
            List<Meal> mealList = new ArrayList<>();
            try {
                doc = Jsoup.connect("http://www.kalori.biz/kalori-cetveli/").get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (doc != null) {
                Elements els = doc.select("ul.menu > .item-108 > ul > li");
                for (Element liElement : els) {
                    Log.i("*******************", liElement.className());
                    Element anchorEl = liElement.select("> a").first();
                    Elements ulEls = anchorEl.siblingElements().select("ul");
                    if (ulEls == null || ulEls.isEmpty()) {
                        String link = anchorEl.attr("abs:href");
                        Log.i("********Link: ", link);
                        getMeals(doc, link, mealList);
                    } else {
                        Elements anchorTags = ulEls.first().select("li a");
                        for (Element aTag : anchorTags) {
                            String link = aTag.attr("abs:href");
                            Log.i("********Link: ", link);
                            if (!link.endsWith("starbucks.html")) {
                                getMeals(doc, link, mealList);
                            }
                        }
                    }
                }
            }
            saveToDB(mealList);
            return null;
        }

        private void getMeals(Document doc, String url, List<Meal> mealList) {
            try {
                doc = Jsoup.connect(url).timeout(20000).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (doc != null) {
                Element table = doc.select("table.tablo_kalori > tbody").first();
                for (Element trEl : table.select("> tr")) {
                    Log.i("********tr: ", trEl.toString());
                    if (trEl.select("*").size() > 1) {
                        String name = trEl.select("> th").text().trim();
                        String calorie = trEl.select("> td").first().text().trim();
                        String description = "100 gr. = " + calorie;
                        if (trEl.select("> td").size() > 1) {
                            description = trEl.select("> td").last().text().trim();
                        }
                        Meal meal = new Meal(name, calorie, description);
                        mealList.add(meal);
                    }
                }
            }
        }

        private void saveToDB(List<Meal> meals) {
            Firebase firebaseRef = new Firebase(Constants.FIREBASE_URL + "/meals");
            for (Meal meal : meals) {
                Firebase ref = firebaseRef.push();
                String pushId = ref.getKey();
                firebaseRef.child(pushId).setValue(meal);
            }
        }

        @Override
        protected void onPostExecute(String[] result) {
            if (result != null) {
                Log.i("*************", "completed");
            }
        }
    }
}
