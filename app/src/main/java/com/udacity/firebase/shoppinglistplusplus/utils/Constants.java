package com.udacity.firebase.shoppinglistplusplus.utils;

import com.udacity.firebase.shoppinglistplusplus.BuildConfig;

/**
 * Constants class store most important strings and paths of the app
 */
public final class Constants {
    public static final String[] tips = {"Kaliteli beslenin. Taze meyve ve sebze, balık, fındık, yeşil çay mutlaka menünüzde bulunsun",
            "Akciğerleri çalıştırın. Düzenli egzersiz yaparak ciğer kapasitenizi 50 yaşından sonra bile %25 artırmanız mümkün",
            "Sigarayı kesinlikle bırakın.","Doğru nefes almayı öğrenin. İdeali karından alınan dakikada 12-14 nefestir",
            "Kilonuza dikkat edin. Her fazla kilo, eklemleriniz üzerinde gereksiz bir yüktür",
            "Kahvaltıyı asla ihmal etmeyin. Kahvaltıda lifli gıdalar almaya dikkat edin",
            "Kaslarınızı çalıştırın. Düzenli çalışmayla 12 hafta içinde orta yaşlarda bile kas gücünüzü geliştirmeniz mümkündür",
            "Seks hayatınızı ihmal etmeyin. Seks yapmak, yalnızca egzersiz olarak değil, vücuttaki hormon salgılarının düzenli şekilde devam etmesi için de gerekli bir aktivitedir",
            "Formunuzu koruyun. Yoga ve pilates yararlı ama yüzmek ve yürümek de asla ihmal edilmemesi gereken egzersizler",
            "Yağ dengenize dikkat. Vücut için en iyi yağ balıkta ve cevizde bulunur",
            "Vücuttaki pas ve tortuyu atın. Miktarda aşırıya kaçmamak şartıyla yeşil çay, siyah çikolata tüketin",
            "Kötü diyetlerden uzak durun. Kemik sağlığı için her yaşta kalsiyum almanız gerektiğini unutmayın",
            "Güneşlenmeyi ihmal etmeyin. Her gün birkaç dakikalığına da olsa gün ışığına çıkın. Vücudunuzun D vitamini üretebilmesi için güneş ışığına ihtiyacı var",
            "Günde en az bir buçuk litre temiz su için. İdrarının rengini kontrol edin, eğer sarıysa yeteri kadar su içmiyorsunuz demektir",
            "Yürüyüşe çıkın. Düzenli yürüyüş östrojen riskini azaltır, stresi önler",
            "Kalbinizi koruyun ve güçlendirin: B vitamini, magnezyum ve çinko almayı unutmayın. Havuç, lahana, avokado, fındık ve sarımsak mutfağınızdan eksik olmasın",
            "Tansiyonunuzu takip edin. Tuz, aşırı kilo ve stres, tansiyon riskini artıran faktörlerdir",
            "Kalbinizi kontrol ettirin. Yılda bir kez check-up yaptırın",
            "Stresten uzak durun",
            "Güneşte uzun süre kalacaksanız mutlaka UV filtreli güneş yağı kullanın",
            "Şeker tüketiminizi azaltın. Canınız tatlı çektiğinde bitter çikolata tercih edin",
            "Günde en az 7 saat uyumayı ihmal etmeyin","Cildinizi besleyin. A, C ve E vitaminleri içeren cilt losyonu kullanın",
            "Vücudunuzun ihtiyaçlarını öncelik sırasına sokun. Gün 24 saat. Bunu üçe bölün: 8 saati çalışmaya, 8 saati kendinize ve 8 saati uyku ve istirahat için ayırın",
            "Gününüzü planlayın. Planlı yaşamak, vaktinizi daha verimli ve yararlı geçirmenizi ve stresten kaçınmanızı sağlar",
            "Dişlerinize iyi bakın. Günde en az iki kere dişlerinizi fırçalayın, kahveyi fazla içmekten kaçının",
            "Şeklinizi koruyun. Vücudunuz bütün gün televizyon veya bilgisayar başında oturmak için dizayn edilmedi. Kalkın, hareket edin",
            "Olumlu düşünün. Uzmanlar varlığıyla mutlu olduğunuz şeyleri düşünmenizi tavsiye ediyorlar. Pozitif düşünce hem sizi genç tutar, hem stresi azaltır",
            "Zihinsel bakımınızı ihmal etmeyin. Televizyon karşısında çok fazla vakit geçirmek bunama riskini artırıyor. Yeni bir şeyler öğrenip yeni alışkanlıklar geliştirerek zihinsel faaliyetlerinizi aralıksız sürdürün"};

    /**
     * Constants related to locations in Firebase, such as the name of the node
     * where user lists are stored (ie "userLists")
     */
    public static final String FIREBASE_LOCATION_SHOPPING_LIST_ITEMS = "shoppingListItems";
    public static final String FIREBASE_LOCATION_USERS = "users";
    public static final String FIREBASE_LOCATION_USER_LISTS = "userLists";
    public static final String FIREBASE_LOCATION_USER_FRIENDS = "userFriends";
    public static final String FIREBASE_LOCATION_LISTS_SHARED_WITH = "sharedWith";
    public static final String FIREBASE_LOCATION_UID_MAPPINGS = "uidMappings";
    public static final String FIREBASE_LOCATION_OWNER_MAPPINGS = "ownerMappings";
    public static final String FIREBASE_LOCATION_TRAINER = "trainers";
    public static final String FIREBASE_LOCATION_WORKOUT = "workout";
    public static final String FIREBASE_LOCATION_CLIENT_MEAL_LIST = "clientMeals";
    public static final String FIREBASE_LOCATION_MEAL_LIST = "mealList";
    public static final String FIREBASE_LOCATION_CLIENT_WORKOUT_LIST = "workoutList";

    /**
     * Constants for Firebase object properties
     */
    public static final String FIREBASE_PROPERTY_BOUGHT = "bought";
    public static final String FIREBASE_PROPERTY_BOUGHT_BY = "boughtBy";
    public static final String FIREBASE_PROPERTY_LIST_NAME = "listName";
    public static final String FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED = "timestampLastChanged";
    public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";
    public static final String FIREBASE_PROPERTY_ITEM_NAME = "itemName";
    public static final String FIREBASE_PROPERTY_EMAIL = "email";
    public static final String FIREBASE_PROPERTY_USERS_SHOPPING = "usersShopping";
    public static final String FIREBASE_PROPERTY_USER_HAS_LOGGED_IN_WITH_PASSWORD = "hasLoggedInWithPassword";
    public static final String FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED_REVERSE = "timestampLastChangedReverse";

    /**
     * Constants for Firebase URL
     */
    public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;
    public static final String FIREBASE_URL_SHOPPING_LIST_ITEMS = FIREBASE_URL + "/" + FIREBASE_LOCATION_SHOPPING_LIST_ITEMS;
    public static final String FIREBASE_URL_USERS = FIREBASE_URL + "/" + FIREBASE_LOCATION_USERS;
    public static final String FIREBASE_URL_TRAINER = FIREBASE_URL + "/" + FIREBASE_LOCATION_TRAINER;
    public static final String FIREBASE_URL_USER_LISTS = FIREBASE_URL + "/" + FIREBASE_LOCATION_USER_LISTS;
    public static final String FIREBASE_URL_USER_FRIENDS = FIREBASE_URL + "/" + FIREBASE_LOCATION_USER_FRIENDS;
    public static final String FIREBASE_URL_LISTS_SHARED_WITH = FIREBASE_URL + "/" + FIREBASE_LOCATION_LISTS_SHARED_WITH;
    public static final String FIREBASE_URL_WORKOUT = FIREBASE_URL + "/" + FIREBASE_LOCATION_WORKOUT;
    public static final String FIREBASE_URL_CLIENT_MEAL_LIST = FIREBASE_URL + "/" + FIREBASE_LOCATION_CLIENT_MEAL_LIST;;

    /**
     * Constants for bundles, extras and shared preferences keys
     */
    public static final String KEY_LIST_NAME = "LIST_NAME";
    public static final String KEY_LAYOUT_RESOURCE = "LAYOUT_RESOURCE";
    public static final String KEY_LIST_ID = "LIST_ID";
    public static final String KEY_SIGNUP_EMAIL = "SIGNUP_EMAIL";
    public static final String KEY_LIST_ITEM_NAME = "ITEM_NAME";
    public static final String KEY_LIST_ITEM_ID = "LIST_ITEM_ID";
    public static final String KEY_PROVIDER = "PROVIDER";
    public static final String KEY_ENCODED_EMAIL = "ENCODED_EMAIL";
    public static final String KEY_LIST_OWNER = "LIST_OWNER";
    public static final String KEY_GOOGLE_EMAIL = "GOOGLE_EMAIL";
    public static final String KEY_PREF_SORT_ORDER_LISTS = "PERF_SORT_ORDER_LISTS";
    public static final String KEY_SHARED_WITH_USERS = "SHARED_WITH_USERS";
    public static final String KEY_USER_NAME = "USER_NAME";
    public static final String KEY_MEASUREMENT_OBJECT = "MEASUREMENT_OBJECT";

    /**
     * Constants for Firebase login
     */
    public static final String PASSWORD_PROVIDER = "password";
    public static final String GOOGLE_PROVIDER = "google";
    public static final String PROVIDER_DATA_DISPLAY_NAME = "displayName";

    /**
     * Constant for sorting
     */
    public static final String ORDER_BY_KEY = "orderByPushKey";
    public static final String ORDER_BY_OWNER_EMAIL = "orderByOwnerEmail";
    public static final int FRIEND_LIST_REQUEST_CODE = 50;
    private static final String FIREBASE_LOCATION_CLIENT_WORKOUTS = "clientWorkouts";
    public static final String FIREBASE_URL_CLIENT_WORKOUTS = FIREBASE_URL + "/" + FIREBASE_LOCATION_CLIENT_WORKOUTS;
    private static final String FIREBASE_LOCATION_CLIENT_MEASUREMENT = "clientMeasurement";
    public static final String FIREBASE_URL_CLIENT_MEASUREMENTS = FIREBASE_URL + "/" + FIREBASE_LOCATION_CLIENT_MEASUREMENT;

    public static final String MEAL_TYPE_BREAKFAST = "breakfast";
    public static final String MEAL_TYPE_LUNCH = "lunch";
    public static final String MEAL_TYPE_DINNER = "dinner";
    public static final String MEAL_TYPE_SNACK = "snack";
    public static final String MEAL_TYPE_DETOX = "detox";

    public static final String WORKOUT_TYPE_CHEST = "chest";
    public static final String WORKOUT_TYPE_BACK = "back";
    public static final String WORKOUT_TYPE_BICEPS = "biceps";
    public static final String WORKOUT_TYPE_TRICEPS = "triceps";
    public static final String WORKOUT_TYPE_ABDOMEN = "abdomen";
    public static final String WORKOUT_TYPE_SHOULDER = "shoulder";
    public static final String WORKOUT_TYPE_LEGS = "legs";
    public static final String WORKOUT_TYPE_FUNCTIONAL = "functional";



    public static final String DATE_FORMAT = "dd-MM-yyyy";
}
