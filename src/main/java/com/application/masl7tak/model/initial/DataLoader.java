package com.application.masl7tak.model.initial;

import com.application.masl7tak.Repository.*;
import com.application.masl7tak.Repository.ServicesRepository;
import com.application.masl7tak.Repository.ReadmeRepository;
import com.application.masl7tak.model.*;
import com.application.masl7tak.model.EventOffers;
import com.application.masl7tak.model.Services;
import com.application.masl7tak.model.Readme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
@Slf4j
//@Transactional
public class DataLoader implements CommandLineRunner {
    @Value("${data-loader.enabled}")
    private boolean dataLoaderEnabled;

    private LocalDateTime now = LocalDateTime.now();
    private  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final EventOffersRepository eventOffersRepository;

    private final ServicesRepository servicesRepository;
    private final ReadmeRepository readmeRepository;
    private final BusinessRepository businessRepository;
    private final CategoryRepository categoryRepository;
    private final CarModelRepository carModelRepository;
    private final CarBrandRepository carBrandRepository;
    private final ProductRepository productsRepository;
    private final ReplacementRepository replacementRepository;
    private final UserRepository userRepository;
    private final RegionRepository regionRepository;
    private final BranchesRepository branchesRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public DataLoader(EventOffersRepository eventOffersRepository,
                      ServicesRepository servicesRepository, ReadmeRepository readmeRepository,
                      BusinessRepository businessRepository, CategoryRepository categoryRepository,
                      CarModelRepository carModelRepository, CarBrandRepository carBrandRepository,
                      ProductRepository productsRepository, ReplacementRepository replacementRepository,
                      UserRepository userRepository, RegionRepository regionRepository,
                      BranchesRepository branchesRepository, PasswordEncoder passwordEncoder) {
        this.eventOffersRepository = eventOffersRepository;
        this.servicesRepository = servicesRepository;
        this.readmeRepository = readmeRepository;
        this.businessRepository = businessRepository;
        this.categoryRepository = categoryRepository;
        this.carModelRepository = carModelRepository;
        this.carBrandRepository = carBrandRepository;
        this.productsRepository = productsRepository;
        this.replacementRepository = replacementRepository;
        this.userRepository = userRepository;
        this.regionRepository = regionRepository;
        this.branchesRepository = branchesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void loadData() {
        loadUsers();
        loadCategories();
        loadBusinesses();
        loadRegion();
        loadBranches();
        loadProducts();
        loadEventOffers();
        loadServices();
//        loadMyCouponsLists();
        loadReadme();
        loadReplacements();
        loadModeAndBrand();
    }


    private void loadUsers() {
        User user = new User();
        user.setName("John Doe");
        user.setContactNumber("555-123-4567");
        user.setEmail("johndoe@example.com");
        user.setPassword(passwordEncoder.encode("0123456789"));
        user.setCarModel(1);
        user.setCarBrand(1);
        user.setFacebook_id("John");
        user.setRole("user");
        user.setStatus("active");
        user.setGmail_id("John");
        user.setGmail_id("John");
        user.setCreationDate(formatter.format(now));
        user.setPoints(100);
        userRepository.save(user);

        user = new User();
        user.setName("Ali Mohamed");
        user.setContactNumber("555-123-4567");
        user.setEmail("ali@example.com");
        user.setCarModel(1);
        user.setPassword(passwordEncoder.encode("0123456789"));
        user.setRole("user");
        user.setStatus("active");
        user.setPoints(100);
        user.setCarModel(1);
        user.setCarBrand(1);
        user.setCreationDate(formatter.format(now));
        user.setFacebook_id("Ali");
        user.setGmail_id("Ali");
        userRepository.save(user);
        user = new User();

        user.setName("Hassan ahmed");
        user.setEmail("hassan@example.com");

        user.setCarModel(1);
        user.setCarBrand(2);

        user.setContactNumber("555-123-4567");
        user.setPassword(passwordEncoder.encode("0123456789"));
        user.setRole("user");
        user.setStatus("active");
        user.setPoints(100);
        user.setCreationDate(formatter.format(now));
        user.setFacebook_id("Hassan");
        user.setGmail_id("Hassan");
        userRepository.save(user);
        user = new User();

        user.setName("Abdallah ali");
        user.setEmail("abdallah@example.com");
        user.setContactNumber("555-123-4567");

        user.setCarModel(1);
        user.setCarBrand(3);

        user.setFacebook_id("Abdallah");
        user.setRole("user");
        user.setStatus("active");
        user.setPassword(passwordEncoder.encode("0123456789"));


        user.setCreationDate(formatter.format(now));
        user.setPoints(100);
        user.setGmail_id("Abdallah");
        userRepository.save(user);
        user = new User();

        user.setName("Hazem ali");
        user.setEmail("hazem@example.com");
        user.setContactNumber("555-123-4567");

        user.setRole("user");
        user.setStatus("active");
        user.setFacebook_id("Hazem");
        user.setPassword(passwordEncoder.encode("0123456789"));

        user.setCarModel(3);
        user.setCarBrand(2);

        user.setCreationDate(formatter.format(now));
        user.setPoints(100);
        user.setGmail_id("Hazem");
        userRepository.save(user);
        user = new User();

        user.setName("Ahmed ali");
        user.setEmail("ahmed@example.com");
        user.setContactNumber("555-123-4567");

        user.setCarModel(1);
        user.setCarBrand(2);

        user.setRole("user");
        user.setStatus("active");

        user.setCreationDate(formatter.format(now));
        user.setFacebook_id("Ahmed");
        user.setPassword(passwordEncoder.encode("0123456789"));
        user.setPoints(100);
        user.setGmail_id("Ahmed");
        userRepository.save(user);
        user = new User();

        user.setName("Mohamed ali");
        user.setEmail("mohamed@example.com");
        user.setContactNumber("555-123-4567");
        user.setRole("user");
        user.setStatus("active");
        user.setPassword(passwordEncoder.encode("0123456789"));

        user.setCarModel(2);
        user.setCarBrand(2);
        user.setCreationDate(formatter.format(now));
        user.setPoints(100);
        user.setFacebook_id("Mohamed");
        user.setGmail_id("Mohamed");
        userRepository.save(user);
        user = new User();

        user.setName("Mohamed ali");
        user.setEmail("mohamedali@example.com");
        user.setContactNumber("555-123-4567");

        user.setCarModel(4);
        user.setCarBrand(2);
        user.setRole("user");
        user.setStatus("active");
        user.setPassword(passwordEncoder.encode("0123456789"));
        user.setCreationDate(formatter.format(now));
        user.setPoints(100);
        user.setFacebook_id("Mohamedali");
        user.setGmail_id("Mohamedali");
        userRepository.save(user);
        user = new User();

        user.setName("Ali Mohamed");
        user.setEmail("alimohamed@example.com");
        user.setContactNumber("555-123-4567");
        user.setRole("user");
        user.setStatus("active");

        user.setCarModel(1);
        user.setCarBrand(2);
        user.setCreationDate(formatter.format(now));
        user.setPassword(passwordEncoder.encode("0123456789"));
        user.setPoints(100);
        user.setFacebook_id("AliMohamed");
        user.setGmail_id("AliMohamed");
        userRepository.save(user);
        user = new User();

        user.setName("Jon ali");
        user.setEmail("jon@example.com");
        user.setContactNumber("555-123-4567");
        user.setRole("user");
        user.setStatus("active");

        user.setCarModel(3);
        user.setCarBrand(2);
        user.setCreationDate(formatter.format(now));
        user.setPassword(passwordEncoder.encode("0123456789"));
        user.setPoints(100);
        user.setFacebook_id("Jon");
        user.setGmail_id("Jon");
        userRepository.save(user);
    }

    private void loadBusinesses() {
        Business business = new Business();
        business.setName("فيلو");

        business.setEmail("1newbusiness@example.com");
        business.setStatus("active");
        business.setSubscriptionType("Premium");
        business.setDescription("خدمات غسيل السيارات");
        business.setLogo("allianz.png");
        business.setTermsConditions("حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل" +
                " الخارجي للنص أو شكل توضع الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم" +
                " إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد" +
                " محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء. العديد من برامح النشر المكتبي وبرامح تحرير صفحات" +
                " الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن النص، وإذا قمت بإدخال  في أي محرك بحث ستظهر العديد من المواقع" +
                " الحديثة العهد في نتائج البحث. على مدى السنين ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،" +
                " حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل الخارجي للنص أو شكل توضع " +
                "الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- " +
                "للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء" +
                ". العديد من برامح النشر المكتبي وبرامح تحرير صفحات الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن" +
                " النص، وإذا قمت بإدخال في أي محرك بحث ستظهر العديد من المواقع الحديثة العهد في نتائج البحث. على مدى السنين " +
                "ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،   وأحياناً عن عمد كإدخال بعض العبارات الفكاهية إليها.");
        business.setCategory(categoryRepository.findById(1L).orElseThrow());
        businessRepository.save(business);

        business = new Business();

        business.setStatus("active");
        business.setSubscriptionType("Premium");
        business.setLogo("ff.png");
        //("https://maps.google.com/location_link");
        //("123 Main St, New York, NY");
        //("09:00");
        //("18:00");
        business.setDescription("خدمات غسيل السيارات");
        business.setName("موبيل سيرفيسيز");
        business.setEmail("2newbusiness@example.com");
        business.setTermsConditions("حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل" +
                " الخارجي للنص أو شكل توضع الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم" +
                " إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد" +
                " محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء. العديد من برامح النشر المكتبي وبرامح تحرير صفحات" +
                " الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن النص، وإذا قمت بإدخال  في أي محرك بحث ستظهر العديد من المواقع" +
                " الحديثة العهد في نتائج البحث. على مدى السنين ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،" +
                " حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل الخارجي للنص أو شكل توضع " +
                "الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- " +
                "للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء" +
                ". العديد من برامح النشر المكتبي وبرامح تحرير صفحات الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن" +
                " النص، وإذا قمت بإدخال في أي محرك بحث ستظهر العديد من المواقع الحديثة العهد في نتائج البحث. على مدى السنين " +
                "ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،   وأحياناً عن عمد كإدخال بعض العبارات الفكاهية إليها.");
        business.setDescription("تغير الزيوت و الاطارات");
        business.setCategory(categoryRepository.findById(1L).orElseThrow());
        businessRepository.save(business);

        business = new Business();

        business.setStatus("active");
        business.setSubscriptionType("Premium");
        business.setLogo("Group.png");
        //("123 Main St, New York, NY");
        //("09:00");
        //("18:00");
        business.setTermsConditions("حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل" +
                " الخارجي للنص أو شكل توضع الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم" +
                " إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد" +
                " محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء. العديد من برامح النشر المكتبي وبرامح تحرير صفحات" +
                " الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن النص، وإذا قمت بإدخال  في أي محرك بحث ستظهر العديد من المواقع" +
                " الحديثة العهد في نتائج البحث. على مدى السنين ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،" +
                " حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل الخارجي للنص أو شكل توضع " +
                "الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- " +
                "للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء" +
                ". العديد من برامح النشر المكتبي وبرامح تحرير صفحات الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن" +
                " النص، وإذا قمت بإدخال في أي محرك بحث ستظهر العديد من المواقع الحديثة العهد في نتائج البحث. على مدى السنين " +
                "ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،   وأحياناً عن عمد كإدخال بعض العبارات الفكاهية إليها.");

        //("https://maps.google.com/location_link");
        business.setName("فيت اند فيكس");
        business.setEmail("3newbusiness@example.com");
        business.setDescription("خدمات الاطارات والزوايا");
        business.setCategory(categoryRepository.findById(1L).orElseThrow());
        businessRepository.save(business);

        business = new Business();

        business.setStatus("active");
        business.setSubscriptionType("Premium");
        business.setLogo("Mask group.png");
        business.setTermsConditions("حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل" +
                " الخارجي للنص أو شكل توضع الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم" +
                " إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد" +
                " محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء. العديد من برامح النشر المكتبي وبرامح تحرير صفحات" +
                " الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن النص، وإذا قمت بإدخال  في أي محرك بحث ستظهر العديد من المواقع" +
                " الحديثة العهد في نتائج البحث. على مدى السنين ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،" +
                " حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل الخارجي للنص أو شكل توضع " +
                "الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- " +
                "للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء" +
                ". العديد من برامح النشر المكتبي وبرامح تحرير صفحات الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن" +
                " النص، وإذا قمت بإدخال في أي محرك بحث ستظهر العديد من المواقع الحديثة العهد في نتائج البحث. على مدى السنين " +
                "ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،   وأحياناً عن عمد كإدخال بعض العبارات الفكاهية إليها.");

        //("123 Main St, New York, NY");
        //("09:00");
        //("18:00");
        //("https://maps.google.com/location_link");
        business.setName("شارب");
        business.setEmail("4newbusiness@example.com");
        business.setDescription("تجارة قطع غيار السيارات");
        business.setCategory(categoryRepository.findById(2L).orElseThrow());
        businessRepository.save(business);

        business = new Business();

        business.setStatus("active");
        business.setSubscriptionType("Premium");
        business.setLogo("ff.png");
        //("https://maps.google.com/location_link");
        //("123 Main St, New York, NY");
        //("09:00");
        //("18:00");
        business.setTermsConditions("حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل" +
                " الخارجي للنص أو شكل توضع الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم" +
                " إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد" +
                " محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء. العديد من برامح النشر المكتبي وبرامح تحرير صفحات" +
                " الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن النص، وإذا قمت بإدخال  في أي محرك بحث ستظهر العديد من المواقع" +
                " الحديثة العهد في نتائج البحث. على مدى السنين ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،" +
                " حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل الخارجي للنص أو شكل توضع " +
                "الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- " +
                "للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء" +
                ". العديد من برامح النشر المكتبي وبرامح تحرير صفحات الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن" +
                " النص، وإذا قمت بإدخال في أي محرك بحث ستظهر العديد من المواقع الحديثة العهد في نتائج البحث. على مدى السنين " +
                "ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،   وأحياناً عن عمد كإدخال بعض العبارات الفكاهية إليها.");

        business.setName("الفاروق");
        business.setEmail("5newbusiness@example.com");
        business.setDescription("تجارة قطع غيار السيارات الاصليه");
        business.setCategory(categoryRepository.findById(2L).orElseThrow());
        businessRepository.save(business);

        business = new Business();

        business.setStatus("active");
        business.setSubscriptionType("Premium");
        business.setLogo("allianz.png");
        //("https://maps.google.com/location_link");
        //("123 Main St, New York, NY");
        //("09:00");
        business.setTermsConditions("حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل" +
                " الخارجي للنص أو شكل توضع الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم" +
                " إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد" +
                " محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء. العديد من برامح النشر المكتبي وبرامح تحرير صفحات" +
                " الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن النص، وإذا قمت بإدخال  في أي محرك بحث ستظهر العديد من المواقع" +
                " الحديثة العهد في نتائج البحث. على مدى السنين ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،" +
                " حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل الخارجي للنص أو شكل توضع " +
                "الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- " +
                "للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء" +
                ". العديد من برامح النشر المكتبي وبرامح تحرير صفحات الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن" +
                " النص، وإذا قمت بإدخال في أي محرك بحث ستظهر العديد من المواقع الحديثة العهد في نتائج البحث. على مدى السنين " +
                "ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،   وأحياناً عن عمد كإدخال بعض العبارات الفكاهية إليها.");

        //("18:00");
        business.setName("China Souq");
        business.setEmail("6newbusiness@example.com");
        business.setDescription("تجارة قطع غيار السيارات الاصليه");
        business.setCategory(categoryRepository.findById(3L).orElseThrow());
        businessRepository.save(business);

        business = new Business();

        business.setStatus("active");
        business.setSubscriptionType("Premium");
        //("https://maps.google.com/location_link");
        business.setLogo("Group.png");
        //("123 Main St, New York, NY");
        //("09:00");
        //("18:00");
        business.setTermsConditions("حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل" +
                " الخارجي للنص أو شكل توضع الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم" +
                " إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد" +
                " محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء. العديد من برامح النشر المكتبي وبرامح تحرير صفحات" +
                " الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن النص، وإذا قمت بإدخال  في أي محرك بحث ستظهر العديد من المواقع" +
                " الحديثة العهد في نتائج البحث. على مدى السنين ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،" +
                " حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل الخارجي للنص أو شكل توضع " +
                "الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- " +
                "للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء" +
                ". العديد من برامح النشر المكتبي وبرامح تحرير صفحات الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن" +
                " النص، وإذا قمت بإدخال في أي محرك بحث ستظهر العديد من المواقع الحديثة العهد في نتائج البحث. على مدى السنين " +
                "ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،   وأحياناً عن عمد كإدخال بعض العبارات الفكاهية إليها.");

        business.setName("دوبي اوتو");
        business.setEmail("7newbusiness@example.com");
        business.setDescription("تجارة قطع غيار السيارات و فلاتر الزيت و حساس الشكمان");
        business.setCategory(categoryRepository.findById(3L).orElseThrow());
        businessRepository.save(business);

        business = new Business();

        business.setStatus("active");
        //("https://maps.google.com/location_link");
        business.setSubscriptionType("Premium");
        business.setLogo("Mask group.png");
        //("123 Main St, New York, NY");
        //("09:00");
        business.setTermsConditions("حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل" +
                " الخارجي للنص أو شكل توضع الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم" +
                " إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد" +
                " محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء. العديد من برامح النشر المكتبي وبرامح تحرير صفحات" +
                " الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن النص، وإذا قمت بإدخال  في أي محرك بحث ستظهر العديد من المواقع" +
                " الحديثة العهد في نتائج البحث. على مدى السنين ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،" +
                " حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل الخارجي للنص أو شكل توضع " +
                "الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- " +
                "للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء" +
                ". العديد من برامح النشر المكتبي وبرامح تحرير صفحات الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن" +
                " النص، وإذا قمت بإدخال في أي محرك بحث ستظهر العديد من المواقع الحديثة العهد في نتائج البحث. على مدى السنين " +
                "ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،   وأحياناً عن عمد كإدخال بعض العبارات الفكاهية إليها.");

        //("18:00");
        business.setName("الدولي ستورز");
        business.setEmail("8newbusiness@example.com");
        business.setDescription(" قطع غيار السيارات");
        business.setCategory(categoryRepository.findById(4L).orElseThrow());
        businessRepository.save(business);

        business = new Business();

        business.setStatus("active");
        //("https://maps.google.com/location_link");
        business.setSubscriptionType("Premium");
        business.setLogo("Group.png");
        //("123 Main St, New York, NY");
        //("09:00");
        business.setTermsConditions("حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل" +
                " الخارجي للنص أو شكل توضع الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم" +
                " إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد" +
                " محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء. العديد من برامح النشر المكتبي وبرامح تحرير صفحات" +
                " الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن النص، وإذا قمت بإدخال  في أي محرك بحث ستظهر العديد من المواقع" +
                " الحديثة العهد في نتائج البحث. على مدى السنين ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،" +
                " حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل الخارجي للنص أو شكل توضع " +
                "الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- " +
                "للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء" +
                ". العديد من برامح النشر المكتبي وبرامح تحرير صفحات الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن" +
                " النص، وإذا قمت بإدخال في أي محرك بحث ستظهر العديد من المواقع الحديثة العهد في نتائج البحث. على مدى السنين " +
                "ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،   وأحياناً عن عمد كإدخال بعض العبارات الفكاهية إليها.");

        //("18:00");
        business.setName("الجميل");
        business.setEmail("9newbusiness@example.com");
        business.setDescription(" قطع غيار السيارات");
        business.setCategory(categoryRepository.findById(4L).orElseThrow());
        businessRepository.save(business);

        business = new Business();

        business.setStatus("active");
        business.setTermsConditions("حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل" +
                " الخارجي للنص أو شكل توضع الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم" +
                " إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد" +
                " محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء. العديد من برامح النشر المكتبي وبرامح تحرير صفحات" +
                " الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن النص، وإذا قمت بإدخال  في أي محرك بحث ستظهر العديد من المواقع" +
                " الحديثة العهد في نتائج البحث. على مدى السنين ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،" +
                " حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل الخارجي للنص أو شكل توضع " +
                "الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- " +
                "للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء" +
                ". العديد من برامح النشر المكتبي وبرامح تحرير صفحات الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن" +
                " النص، وإذا قمت بإدخال في أي محرك بحث ستظهر العديد من المواقع الحديثة العهد في نتائج البحث. على مدى السنين " +
                "ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،   وأحياناً عن عمد كإدخال بعض العبارات الفكاهية إليها.");

        //("https://maps.google.com/location_link");
        business.setSubscriptionType("Premium");
        business.setLogo("allianz.png");
        //("123 Main St, New York, NY");
        //("09:00");
        //("18:00");
        business.setName("شركة الباز");
        business.setEmail("10newbusiness@example.com");
        business.setDescription(" قطع غيار السيارات");
        business.setCategory(categoryRepository.findById(4L).orElseThrow());
        businessRepository.save(business);

        business = new Business();

        business.setStatus("active");
        business.setTermsConditions("حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل" +
                " الخارجي للنص أو شكل توضع الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم" +
                " إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد" +
                " محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء. العديد من برامح النشر المكتبي وبرامح تحرير صفحات" +
                " الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن النص، وإذا قمت بإدخال  في أي محرك بحث ستظهر العديد من المواقع" +
                " الحديثة العهد في نتائج البحث. على مدى السنين ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،" +
                " حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل الخارجي للنص أو شكل توضع " +
                "الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- " +
                "للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء" +
                ". العديد من برامح النشر المكتبي وبرامح تحرير صفحات الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن" +
                " النص، وإذا قمت بإدخال في أي محرك بحث ستظهر العديد من المواقع الحديثة العهد في نتائج البحث. على مدى السنين " +
                "ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،   وأحياناً عن عمد كإدخال بعض العبارات الفكاهية إليها.");

        //("https://maps.google.com/location_link");
        business.setSubscriptionType("Premium");
        business.setLogo("Mask group.png");
        //("123 Main St, New York, NY");
        //("09:00");
        //("18:00");
        business.setName("شركة البالار");
        business.setEmail("11newbusiness@example.com");
        business.setDescription(" قطع غيار السيارات");
        business.setCategory(categoryRepository.findById(4L).orElseThrow());
        businessRepository.save(business);

    }

    private void loadBranches() {

        Branches branch = new Branches();
        branch.setAddress("123 Main St");
        branch.setLocationLink("https://example.com/1");
        branch.setPhone_number("555-1234");
        branch.setOpenTime("9:00 AM");
        branch.setClosureTime("5:00 PM");
        branch.setRegion(regionRepository.findById(1L).orElseThrow());
        branch.setBusiness(businessRepository.findById(1L).orElseThrow());


        branchesRepository.save(branch);
        ;

        branch = new Branches();
        branch.setAddress("456 Market St");
        branch.setLocationLink("https://example.com/2");
        branch.setPhone_number("555-5678");
        branch.setOpenTime("10:00 AM");
        branch.setClosureTime("6:00 PM");
        branch.setRegion(regionRepository.findById(2L).orElseThrow());
        branch.setBusiness(businessRepository.findById(1L).orElseThrow());


        branchesRepository.save(branch);
        ;

        branch = new Branches();
        branch.setAddress("789 Elm St");
        branch.setLocationLink("https://example.com/3");
        branch.setPhone_number("555-9012");
        branch.setOpenTime("11:00 AM");
        branch.setClosureTime("7:00 PM");
        branch.setRegion(regionRepository.findById(3L).orElseThrow());
        branch.setBusiness(businessRepository.findById(1L).orElseThrow());


        branchesRepository.save(branch);
        ;

        branch = new Branches();
        branch.setAddress("321 Oak St");
        branch.setLocationLink("https://example.com/4");
        branch.setPhone_number("555-3456");
        branch.setOpenTime("12:00 PM");
        branch.setClosureTime("8:00 PM");
        branch.setRegion(regionRepository.findById(1L).orElseThrow());
        branch.setBusiness(businessRepository.findById(2L).orElseThrow());


        branchesRepository.save(branch);
        ;

        // Create 5 more Branches objects with different properties and Region objects
        branch = new Branches();
        branch.setAddress("987 Maple St");
        branch.setLocationLink("https://example.com/6");
        branch.setPhone_number("555-7890");
        branch.setOpenTime("1:00 PM");
        branch.setClosureTime("9:00 PM");
        branch.setRegion(regionRepository.findById(1L).orElseThrow());
        branch.setBusiness(businessRepository.findById(3L).orElseThrow());


        branchesRepository.save(branch);
        ;

        branch = new Branches();
        branch.setAddress("654 Elm St");
        branch.setLocationLink("https://example.com/7");
        branch.setPhone_number("555-2345");
        branch.setOpenTime("2:00 PM");
        branch.setClosureTime("10:00 PM");
        branch.setRegion(regionRepository.findById(1L).orElseThrow());
        branch.setBusiness(businessRepository.findById(4L).orElseThrow());


        branchesRepository.save(branch);
        ;

        branch = new Branches();
        branch.setAddress("321 Oak St");
        branch.setLocationLink("https://example.com/8");
        branch.setPhone_number("555-6789");
        branch.setOpenTime("3:00 PM");
        branch.setClosureTime("11:00 PM");
        branch.setRegion(regionRepository.findById(2L).orElseThrow());
        branch.setBusiness(businessRepository.findById(4L).orElseThrow());


        branchesRepository.save(branch);
        ;

        branch = new Branches();
        branch.setAddress("987 Maple St");
        branch.setLocationLink("https://example.com/9");
        branch.setPhone_number("555-1234");
        branch.setOpenTime("4:00 PM");
        branch.setClosureTime("12:00 AM");
        branch.setRegion(regionRepository.findById(3L).orElseThrow());
        branch.setBusiness(businessRepository.findById(5L).orElseThrow());


        branchesRepository.save(branch);
        ;

        branch = new Branches();
        branch.setAddress("654 Pine St");
        branch.setLocationLink("https://example.com/10");
        branch.setPhone_number("555-5678");
        branch.setOpenTime("5:00 PM");
        branch.setClosureTime("1:00 AM");
        branch.setRegion(regionRepository.findById(4L).orElseThrow());
        branch.setBusiness(businessRepository.findById(6L).orElseThrow());


        branchesRepository.save(branch);
        ;

        branch = new Branches();
        branch.setAddress("123 Main St");
        branch.setLocationLink("https://example.com/11");
        branch.setPhone_number("555-1234");
        branch.setOpenTime("9:00 AM");
        branch.setClosureTime("5:00 PM");
        branch.setRegion(regionRepository.findById(5L).orElseThrow());
        branch.setBusiness(businessRepository.findById(7L).orElseThrow());


        branchesRepository.save(branch);
        ;

        branch = new Branches();
        branch.setAddress("456 Market St");
        branch.setLocationLink("https://example.com/12");
        branch.setPhone_number("555-5678");
        branch.setOpenTime("10:00 AM");
        branch.setClosureTime("6:00 PM");
        branch.setRegion(regionRepository.findById(6L).orElseThrow());
        branch.setBusiness(businessRepository.findById(8L).orElseThrow());


        branchesRepository.save(branch);
        ;

        branch = new Branches();
        branch.setAddress("789 Elm St");
        branch.setLocationLink("https://example.com/13");
        branch.setPhone_number("555-9012");
        branch.setOpenTime("11:00 AM");
        branch.setClosureTime("7:00 PM");
        branch.setRegion(regionRepository.findById(7L).orElseThrow());
        branch.setBusiness(businessRepository.findById(9L).orElseThrow());


        branchesRepository.save(branch);
        ;

        branch = new Branches();
        branch.setAddress("321 Oak St");
        branch.setLocationLink("https://example.com/14");
        branch.setPhone_number("555-3456");
        branch.setOpenTime("12:00 PM");
        branch.setClosureTime("8:00 PM");
        branch.setRegion(regionRepository.findById(8L).orElseThrow());
        branch.setBusiness(businessRepository.findById(9L).orElseThrow());


        branchesRepository.save(branch);
        ;

        // Create 5 more Branches objects with different properties and Region objects

        branch = new Branches();
        branch.setAddress("987 Maple St");
        branch.setLocationLink("https://example.com/16");
        branch.setPhone_number("555-7890");
        branch.setOpenTime("1:00 PM");
        branch.setClosureTime("9:00 PM");
        branch.setRegion(regionRepository.findById(9L).orElseThrow());
        branch.setBusiness(businessRepository.findById(9L).orElseThrow());


        branchesRepository.save(branch);
        ;


        branch = new Branches();
        branch.setAddress("654 Elm St");
        branch.setLocationLink("https://example.com/17");
        branch.setPhone_number("555-2345");
        branch.setOpenTime("2:00 PM");
        branch.setClosureTime("10:00 PM");
        branch.setRegion(regionRepository.findById(10L).orElseThrow());
        branch.setBusiness(businessRepository.findById(2L).orElseThrow());


        branchesRepository.save(branch);
        ;


        branch = new Branches();
        branch.setAddress("321 Oak St");
        branch.setLocationLink("https://example.com/18");
        branch.setPhone_number("555-6789");
        branch.setOpenTime("3:00 PM");
        branch.setClosureTime("11:00 PM");
        branch.setRegion(regionRepository.findById(1L).orElseThrow());
        branch.setBusiness(businessRepository.findById(10L).orElseThrow());


        branchesRepository.save(branch);
        ;


        branch = new Branches();
        branch.setAddress("987 Maple St");
        branch.setLocationLink("https://example.com/19");
        branch.setPhone_number("555-1234");
        branch.setOpenTime("4:00 PM");
        branch.setClosureTime("12:00 AM");
        branch.setRegion(regionRepository.findById(3L).orElseThrow());
        branch.setBusiness(businessRepository.findById(6L).orElseThrow());


        branchesRepository.save(branch);
        ;


        branch = new Branches();
        branch.setAddress("654 Pine St");
        branch.setLocationLink("https://example.com/20");
        branch.setPhone_number("555-5678");
        branch.setOpenTime("5:00 PM");
        branch.setClosureTime("1:00 AM");
        branch.setRegion(regionRepository.findById(2L).orElseThrow());
        branch.setBusiness(businessRepository.findById(7L).orElseThrow());


        branchesRepository.save(branch);


    }

    private void loadCategories() {
        Category category = new Category();
        category.setName("حماية السيارة");
        category.setImage("new_category_image.png");
        categoryRepository.save(category);
        category = new Category();

        category.setName("غسيل السيارة");
        category.setImage("new_category_image.png");

        categoryRepository.save(category);
        category = new Category();

        category.setName("الصيانه الدورية");
        category.setImage("new_category_image.png");
        categoryRepository.save(category);
        category = new Category();

        category.setName("الصيانه");
        category.setImage("new_category_image.png");
        categoryRepository.save(category);
        category = new Category();

        category.setName("قطع الغيار");
        category.setImage("new_category_image.png");
        categoryRepository.save(category);
        category = new Category();

        category.setName("قطع الغيار استيراد");
        category.setImage("new_category_image.png");
        categoryRepository.save(category);
        category = new Category();

        category.setName("سمكرة السيارات");
        category.setImage("new_category_image.png");
        categoryRepository.save(category);
        category = new Category();

        category.setName("دهان السيارات");
        category.setImage("new_category_image.png");
        categoryRepository.save(category);

    }

    private void loadRegion() {

        Region region = new Region();
        region.setName_ar("الإسكندرية");
        region.setName_en("Alexandria");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("الدقهلية");
        region.setName_en("Dakahlia");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("البحيرة");
        region.setName_en("Beheira");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("الشرقية");
        region.setName_en("Sharqia");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("المنوفية");
        region.setName_en("Monufia");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("الغربية");
        region.setName_en("Gharbia");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("القليوبية");
        region.setName_en("Qalyubia");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("الفيوم");
        region.setName_en("Faiyum");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("بني سويف");
        region.setName_en("Beni Suef");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("المنيا");
        region.setName_en("Minya");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("أسيوط");
        region.setName_en("Asyut");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("سوهاج");
        region.setName_en("Sohag");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("قنا");
        region.setName_en("Qena");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("الأقصر");
        region.setName_en("Luxor");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("أسوان");
        region.setName_en("Aswan");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("الشيخ زايد");
        region.setName_en("Sheikh Zayed");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("التجمع الخامس");
        region.setName_en("Fifth Settlement");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("مدينة نصر");
        region.setName_en("Nasr City");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("مصر الجديدة");
        region.setName_en("Heliopolis");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("مصر الجديدة");
        region.setName_en("Heliopolis");
        regionRepository.save(region);

        region = new Region();
        region.setName_ar("اكتوبر");
        region.setName_en("October");
        regionRepository.save(region);

    }


    private void loadProducts() {
        Products product = new Products();
        product.setName("صيانة 10 الاف");
        product.setDescription("A sample product for testing.");
        product.setImage("new_product_image.png");
        product.setPrice(10);
        product.setBusiness(businessRepository.findById(1L).orElseThrow());
        productsRepository.save(product);

        product = new Products();
        product.setName("افلام حماية");
        product.setDescription("A sample product for testing.");
        product.setImage("new_product_image.png");
        product.setBusiness(businessRepository.findById(1L).orElseThrow());
        product.setPrice(10);
        productsRepository.save(product);
        Business business = new Business();

        business = businessRepository.findById(1L).orElseThrow();

        product = new Products();
        product.setName("غسيل بره و جوا");
        product.setDescription("A sample product for testing.");
        product.setPrice(10);
        product.setImage("new_product_image.png");
        product.setBusiness(business);
        productsRepository.save(product);

        product = new Products();
        product.setName("غسيل بره و جوا");
        product.setDescription("A sample product for testing.");
        product.setPrice(10);
        product.setImage("new_product_image.png");
        product.setBusiness(businessRepository.findById(2L).orElseThrow());
        productsRepository.save(product);
        product = new Products();

        product.setName("غسل ماطور");
        product.setDescription("A sample product for testing.");
        product.setPrice(10);
        product.setImage("new_product_image.png");
        product.setBusiness(businessRepository.findById(3L).orElseThrow());
        productsRepository.save(product);
        business = businessRepository.findById(3L).orElseThrow();
        product = new Products();

        product.setName("المجموعه كامله");
        product.setDescription("A sample product for testing.");
        product.setPrice(10);
        product.setImage("new_product_image.png");
        product.setBusiness(business);
        productsRepository.save(product);
        business = businessRepository.findById(4L).orElseThrow();
        product = new Products();


        product.setName("صيانة 40 الاف ");
        product.setDescription("A sample product for testing.");
        product.setPrice(10);
        product.setImage("new_product_image.png");
        product.setBusiness(business);
        productsRepository.save(product);
        business = businessRepository.findById(4L).orElseThrow();
        product = new Products();

        product.setName("صيانة 80 الاف ");
        product.setDescription("A sample product for testing.");
        product.setPrice(10);
        product.setImage("new_product_image.png");
        product.setBusiness(business);
        productsRepository.save(product);
        business = businessRepository.findById(4L).orElseThrow();
        product = new Products();

        product.setName("صيانة 10 الاف ");
        product.setDescription("A sample product for testing.");
        product.setPrice(10);
        product.setImage("new_product_image.png");
        product.setBusiness(business);
        productsRepository.save(product);
        business = businessRepository.findById(4L).orElseThrow();
        product = new Products();

        product.setName("افلام حمايه");
        product.setDescription("A sample product for testing.");
        product.setPrice(10);
        product.setImage("new_product_image.png");
        product.setBusiness(business);
        productsRepository.save(product);
        business = businessRepository.findById(5L).orElseThrow();
        product = new Products();

        product.setName("دهان سياره");
        product.setDescription("A sample product for testing.");
        product.setPrice(10);
        product.setImage("new_product_image.png");
        product.setBusiness(business);
        productsRepository.save(product);
        business = businessRepository.findById(5L).orElseThrow();
        product = new Products();

        product.setName("قطع غيار MH");
        product.setDescription("A sample product for testing.");
        product.setPrice(10);
        product.setImage("new_product_image.png");
        product.setBusiness(business);
        productsRepository.save(product);
        business = businessRepository.findById(6L).orElseThrow();
        product = new Products();

        product.setName("صيانة 10 الاف ");
        product.setDescription("A sample product for testing.");
        product.setPrice(10);
        product.setImage("new_product_image.png");
        product.setBusiness(business);
        productsRepository.save(product);
        business = businessRepository.findById(6L).orElseThrow();
        product = new Products();

        product.setName("صيانة 10 الاف ");
        product.setDescription("A sample product for testing.");
        product.setImage("new_product_image.png");
        product.setPrice(10);
        product.setBusiness(business);
        productsRepository.save(product);
        business = businessRepository.findById(6L).orElseThrow();
        product = new Products();

        product.setName("غسيل بره و جو");
        product.setDescription("A sample product for testing.");
        product.setPrice(10);
        product.setImage("new_product_image.png");
        product.setBusiness(business);
        product.setPrice(10);
        productsRepository.save(product);
        business = businessRepository.findById(7L).orElseThrow();
        product = new Products();

        product.setName("غسيل بره و جو");
        product.setDescription("A sample product for testing.");
        product.setBusiness(business);
        product.setImage("new_product_image.png");
        productsRepository.save(product);
        product.setPrice(10);
        business = businessRepository.findById(7L).orElseThrow();
        product = new Products();

        product.setName("افلام حماية");
        product.setDescription("A sample product for testing.");
        product.setBusiness(business);
        product.setImage("new_product_image.png");
        productsRepository.save(product);
        product.setPrice(10);
        business = businessRepository.findById(8L).orElseThrow();
        product = new Products();

        product.setName("افلام حماية");
        product.setDescription("A sample product for testing.");
        product.setPrice(10);
        product.setImage("new_product_image.png");
        product.setBusiness(business);
        productsRepository.save(product);
        business = businessRepository.findById(9L).orElseThrow();
        product = new Products();

        product.setName("الزيت");
        product.setDescription("A sample product for testing.");
        product.setPrice(10);
        product.setImage("new_product_image.png");
        product.setBusiness(business);
        productsRepository.save(product);
        business = businessRepository.findById(10L).orElseThrow();
        product = new Products();

        product.setName("قطع غيار MG");
        product.setDescription("A sample product for testing.");
        product.setPrice(10);
        product.setImage("new_product_image.png");
        product.setBusiness(business);
        productsRepository.save(product);

    }

    private void loadServices() {
//        atarat.png,color.png,famai.png,siana.png,winsh.png,zait.png,zoaia.png
        Services service = new Services();
        service.setCarModel(1L);
        service.setCarBrand(1L);
        service.setImages("siana.png");
        service.setRate(4.5F);
        service.setDiscountValue(0.1);
        service.setQuantity(10);
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        service.setIs_available("true");
        service.setCategory(categoryRepository.findById(1L).orElseThrow());
        service.setProducts(productsRepository.findById(1L).orElseThrow());
        service.setBusiness(businessRepository.findById(1L).orElseThrow());
        Business business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);
        service.setEventOffers(eventOffersRepository.findById(1L).orElseThrow());

        servicesRepository.save(service);


        service = new Services();
        service.setCarModel(1L);
        service.setCarBrand(2L);

        service.setImages("winsh.png,siana.png");
        service.setRate(4.5F);
        service.setDiscountValue(0.2);
        service.setQuantity(10);
        service.setCategory(categoryRepository.findById(1L).orElseThrow());
        service.setProducts(productsRepository.findById(2L).orElseThrow());
        service.setBusiness(businessRepository.findById(1L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setEventOffers(eventOffersRepository.findById(2L).orElseThrow());
        service.setIs_available("true");
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);

        service = new Services();
        service.setCarModel(1L);
        service.setCarBrand(3L);

        service.setImages("atarat.png,siana.png");
        service.setRate(4.0F);
        service.setDiscountValue(0.5);
        service.setQuantity(10);
        service.setCategory(categoryRepository.findById(1L).orElseThrow());
        service.setProducts(productsRepository.findById(2L).orElseThrow());
        service.setBusiness(businessRepository.findById(1L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setEventOffers(eventOffersRepository.findById(1L).orElseThrow());
        service.setIs_available("true");
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);

        service = new Services();
        service.setCarModel(2L);

        service.setImages("color.png,siana.png");
        service.setRate(4.0F);
        service.setCarBrand(1L);
        service.setDiscountValue(0.3);
        service.setQuantity(10);
        service.setCategory(categoryRepository.findById(1L).orElseThrow());
        service.setProducts(productsRepository.findById(3L).orElseThrow());
        service.setBusiness(businessRepository.findById(1L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setEventOffers(eventOffersRepository.findById(3L).orElseThrow());
        service.setIs_available("true");
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);


//     -----------------------------------------------------

        service = new Services();
        service.setCarModel(2L);

        service.setImages("famai.png,siana.png");
        service.setRate(4.1F);
        service.setCarBrand(2L);

        service.setDiscountValue(0.1);
        service.setQuantity(10);
        service.setIs_available("true");
        service.setCategory(categoryRepository.findById(3L).orElseThrow());
        service.setProducts(productsRepository.findById(6L).orElseThrow());
        service.setBusiness(businessRepository.findById(2L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);

//     -----------------------------------------------------


        service = new Services();

        service.setCarModel(2L);
        service.setCarBrand(3L);

        service.setImages("siana.png,siana.png");
        service.setRate(4.1F);
        service.setDiscountValue(0.2);
        service.setQuantity(10);
        service.setCategory(categoryRepository.findById(3L).orElseThrow());
        service.setProducts(productsRepository.findById(7L).orElseThrow());
        service.setBusiness(businessRepository.findById(3L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setIs_available("true");
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);

        service = new Services();
        service.setCarModel(2L);
        service.setCarBrand(3L);

        service.setImages("winsh.png,siana.png");
        service.setRate(3.1F);
        service.setDiscountValue(0.5);
        service.setQuantity(10);
        service.setCategory(categoryRepository.findById(1L).orElseThrow());
        service.setProducts(productsRepository.findById(8L).orElseThrow());
        service.setBusiness(businessRepository.findById(3L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setIs_available("true");
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);

//     -----------------------------------------------------

        service = new Services();


        service.setImages("zait.png,siana.png");
        service.setRate(2.1F);
        service.setCarModel(2L);
        service.setCarBrand(4L);
        service.setDiscountValue(0.2);
        service.setQuantity(10);
        service.setCategory(categoryRepository.findById(4L).orElseThrow());
        service.setProducts(productsRepository.findById(9L).orElseThrow());
        service.setBusiness(businessRepository.findById(4L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setIs_available("true");
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);

        service = new Services();


        service.setImages("zoaia.png,siana.png");
        service.setRate(2.1F);
        service.setCarModel(3L);
        service.setCarBrand(1L);
        service.setDiscountValue(0.2);
        service.setQuantity(10);
        service.setCategory(categoryRepository.findById(4L).orElseThrow());
        service.setProducts(productsRepository.findById(10L).orElseThrow());
        service.setBusiness(businessRepository.findById(4L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setIs_available("true");
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);


        service = new Services();


        service.setImages("color.png,siana.png");
        service.setRate(1.1F);
        service.setCarModel(3L);
        service.setCarBrand(1L);
        service.setDiscountValue(0.15);
        service.setQuantity(10);
        service.setCategory(categoryRepository.findById(5L).orElseThrow());
        service.setProducts(productsRepository.findById(11L).orElseThrow());
        service.setBusiness(businessRepository.findById(4L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setIs_available("true");

        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);


        service = new Services();


        service.setImages("famai.png,siana.png");
        service.setRate(5.0F);
        service.setCarModel(3L);
        service.setCarBrand(2L);

        service.setDiscountValue(0.2);
        service.setQuantity(10);
        service.setCategory(categoryRepository.findById(5L).orElseThrow());
        service.setProducts(productsRepository.findById(12L).orElseThrow());
        service.setBusiness(businessRepository.findById(4L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setIs_available("true");
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);

//     ----------------------------------------------------

        service = new Services();


        service.setImages("atarat.png,siana.png");
        service.setRate(5.0F);
        service.setCarModel(3L);
        service.setCarBrand(2L);
        service.setDiscountValue(0.2);
        service.setQuantity(10);
        service.setIs_available("true");
        service.setCategory(categoryRepository.findById(5L).orElseThrow());
        service.setProducts(productsRepository.findById(13L).orElseThrow());
        service.setBusiness(businessRepository.findById(5L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);


        service = new Services();


        service.setImages("siana.png,siana.png");
        service.setRate(5.0F);

        service.setCarModel(3L);
        service.setCarBrand(3L);
        service.setDiscountValue(0.3);
        service.setQuantity(10);
        service.setCategory(categoryRepository.findById(5L).orElseThrow());
        service.setProducts(productsRepository.findById(14L).orElseThrow());
        service.setBusiness(businessRepository.findById(5L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setIs_available("true");
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);

//     ----------------------------------------------------


        service = new Services();


        service.setImages("winsh.png,siana.png");
        service.setRate(3.0F);
        service.setCarModel(4L);
        service.setCarBrand(4L);
        service.setDiscountValue(0.6);
        service.setQuantity(10);
        service.setCategory(categoryRepository.findById(6L).orElseThrow());
        service.setProducts(productsRepository.findById(15L).orElseThrow());
        service.setBusiness(businessRepository.findById(6L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setIs_available("true");
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);

        service = new Services();
        service.setCarModel(4L);
        service.setCarBrand(1L);


        service.setImages("zait.png,siana.png");
        service.setRate(3.0F);
        service.setDiscountValue(0.2);
        service.setQuantity(10);
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        service.setIs_available("true");
        service.setCategory(categoryRepository.findById(6L).orElseThrow());
        service.setProducts(productsRepository.findById(16L).orElseThrow());
        service.setBusiness(businessRepository.findById(6L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);


        service = new Services();

        service.setImages("zoaia.png,siana.png");
        service.setRate(3.5F);
        service.setCarModel(4L);
        service.setCarBrand(2L);
        service.setDiscountValue(0.3);
        service.setQuantity(10);
        service.setIs_available("true");
        service.setCategory(categoryRepository.findById(6L).orElseThrow());
        service.setProducts(productsRepository.findById(17L).orElseThrow());
        service.setBusiness(businessRepository.findById(6L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);

//     ----------------------------------------------------


        service = new Services();

        service.setImages("zoaia.png,siana.png");
        service.setRate(4.5F);
        service.setCarModel(4L);
        service.setCarBrand(3L);
        service.setDiscountValue(0.3);
        service.setQuantity(10);
        service.setIs_available("true");
        service.setCategory(categoryRepository.findById(7L).orElseThrow());
        service.setProducts(productsRepository.findById(18L).orElseThrow());
        service.setBusiness(businessRepository.findById(7L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);

        service = new Services();

        service.setImages("zoaia.png,siana.png");
        service.setRate(4.8F);
        service.setCarModel(4L);
        service.setCarBrand(4L);

        service.setDiscountValue(0.5);
        service.setQuantity(10);
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        service.setIs_available("true");
        service.setCategory(categoryRepository.findById(7L).orElseThrow());
        service.setProducts(productsRepository.findById(19L).orElseThrow());
        service.setBusiness(businessRepository.findById(7L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);

//     ----------------------------------------------------


        service = new Services();

        service.setImages("famai.png,siana.png");
        service.setRate(4.8F);
        service.setCarModel(5L);
        service.setCarBrand(4L);
        service.setDiscountValue(0.5);
        service.setQuantity(10);
        service.setIs_available("true");
        service.setCategory(categoryRepository.findById(8L).orElseThrow());
        service.setProducts(productsRepository.findById(20L).orElseThrow());
        service.setBusiness(businessRepository.findById(8L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);
//     ----------------------------------------------------


        service = new Services();

        service.setImages("famai.png,siana.png");
        service.setRate(4.0F);
        service.setCarModel(4L);
        service.setCarBrand(4L);
        service.setDiscountValue(0.7);
        service.setQuantity(10);
        service.setCategory(categoryRepository.findById(8L).orElseThrow());
        service.setProducts(productsRepository.findById(4L).orElseThrow());
        service.setBusiness(businessRepository.findById(9L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setIs_available("true");
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);

//     ----------------------------------------------------

        service = new Services();

        service.setImages("winsh.png,siana.png");
        service.setRate(4.0F);
        service.setCarModel(5L);
        service.setCarBrand(4L);
        service.setDiscountValue(0.8);
        service.setQuantity(10);
        service.setIs_available("true");
        service.setCategory(categoryRepository.findById(8L).orElseThrow());
        service.setProducts(productsRepository.findById(5L).orElseThrow());
        service.setBusiness(businessRepository.findById(10L).orElseThrow());
        business = businessRepository.findById(service.getBusiness().getId()).orElseThrow();
        business.setStart_discount_val(business.getStart_discount_val() < service.getDiscountValue() ? service.getDiscountValue() : business.getStart_discount_val());
        businessRepository.save(business);

        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);
        ///////////////////////////////////////////////////////////////////////////////
        service = new Services();

        service.setImages("winsh.png,siana.png");
        service.setRate(3.0F);
        service.setCarModel(6L);
        service.setCarBrand(4L);
        service.setDiscountValue(0.8);
        service.setQuantity(10);
        service.setIs_available("true");
        service.setCategory(categoryRepository.findById(8L).orElseThrow());
        service.setProducts(productsRepository.findById(5L).orElseThrow());
        service.setEventOffers(eventOffersRepository.findById(1L).orElseThrow());
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);
        service = new Services();

        service.setImages("atarat.png,siana.png");
        service.setRate(3.0F);
        service.setCarModel(6L);
        service.setCarBrand(1L);
        service.setDiscountValue(0.5);
        service.setQuantity(10);
        service.setIs_available("true");
        service.setCategory(categoryRepository.findById(8L).orElseThrow());
        service.setProducts(productsRepository.findById(5L).orElseThrow());
        service.setEventOffers(eventOffersRepository.findById(1L).orElseThrow());
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);
        service = new Services();

        service.setImages("atarat.png,siana.png");
        service.setRate(4.0F);
        service.setCarModel(6L);
        service.setCarBrand(2L);
        service.setDiscountValue(0.2);
        service.setQuantity(10);
        service.setIs_available("true");
        service.setCategory(categoryRepository.findById(8L).orElseThrow());
        service.setProducts(productsRepository.findById(5L).orElseThrow());
        service.setEventOffers(eventOffersRepository.findById(1L).orElseThrow());
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);
        service = new Services();

        service.setImages("color.png,siana.png");
        service.setRate(2.0F);
        service.setCarModel(6L);
        service.setCarBrand(3L);
        service.setDiscountValue(0.1);
        service.setQuantity(10);
        service.setIs_available("true");
        service.setCategory(categoryRepository.findById(8L).orElseThrow());
        service.setProducts(productsRepository.findById(5L).orElseThrow());
        service.setEventOffers(eventOffersRepository.findById(1L).orElseThrow());
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);
        service = new Services();

        service.setImages("color.png,siana.png");
        service.setRate(2.0F);
        service.setDiscountValue(0.6);
        service.setQuantity(10);
        service.setIs_available("true");
        service.setCategory(categoryRepository.findById(8L).orElseThrow());
        service.setProducts(productsRepository.findById(5L).orElseThrow());
        service.setEventOffers(eventOffersRepository.findById(2L).orElseThrow());
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);
        service = new Services();

        service.setImages("siana.png,siana.png");
        service.setRate(1.0F);
        service.setCarModel(6L);
        service.setCarBrand(4L);
        service.setDiscountValue(0.3);
        service.setQuantity(10);
        service.setIs_available("true");
        service.setCategory(categoryRepository.findById(8L).orElseThrow());
        service.setProducts(productsRepository.findById(5L).orElseThrow());
        service.setEventOffers(eventOffersRepository.findById(2L).orElseThrow());
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);
        service = new Services();

        service.setImages("winsh.png,siana.png");
        service.setRate(1.0F);
        service.setCarModel(5L);
        service.setCarBrand(1L);
        service.setDiscountValue(0.4);
        service.setQuantity(10);
        service.setIs_available("true");
        service.setCategory(categoryRepository.findById(8L).orElseThrow());
        service.setProducts(productsRepository.findById(5L).orElseThrow());
        service.setEventOffers(eventOffersRepository.findById(2L).orElseThrow());
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);
        service = new Services();

        service.setImages("zait.png,siana.png");
        service.setRate(5.0F);
        service.setCarModel(5L);
        service.setCarBrand(3L);
        service.setDiscountValue(0.3);
        service.setQuantity(10);
        service.setIs_available("true");
        service.setCategory(categoryRepository.findById(8L).orElseThrow());
        service.setProducts(productsRepository.findById(5L).orElseThrow());
        service.setEventOffers(eventOffersRepository.findById(2L).orElseThrow());
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);
        service = new Services();

        service.setImages("zoaia.png,siana.png");
        service.setRate(3.8F);
        service.setCarModel(5L);
        service.setCarBrand(2L);
        service.setDiscountValue(0.25);
        service.setQuantity(10);
        service.setIs_available("true");
        service.setCategory(categoryRepository.findById(8L).orElseThrow());
        service.setProducts(productsRepository.findById(5L).orElseThrow());
        service.setEventOffers(eventOffersRepository.findById(2L).orElseThrow());
        service.setCreationDate(formatter.format(now));
        service.setValidUntil(new java.sql.Date(System.currentTimeMillis() + 10000).toString());
        servicesRepository.save(service);
    }

    private void loadEventOffers() {
        EventOffers eventOffer = new EventOffers();
        eventOffer.setEvent_title("عروض و خصومات");
        eventOffer.setEvent_sub_title("رائس السنه 2024");
        eventOffer.setImage("sample_image.png");
        eventOffersRepository.save(eventOffer);

        eventOffer = new EventOffers();
        eventOffer.setEvent_title("عروض و خصومات");
        eventOffer.setEvent_sub_title("افتاح الفرع الجديد");
        eventOffer.setImage("sample_image.png");
        eventOffersRepository.save(eventOffer);

        eventOffer = new EventOffers();
        eventOffer.setEvent_title(" خصم 50%");
        eventOffer.setEvent_sub_title("تغير الطارات");
        eventOffer.setImage("sample_image.png");
        eventOffer.setBusiness(businessRepository.findById(1L).orElseThrow());
        eventOffersRepository.save(eventOffer);

        eventOffer = new EventOffers();
        eventOffer.setEvent_title(" خصم 20%");
        eventOffer.setEvent_sub_title("لصيانات");
        eventOffer.setImage("sample_image.png");
        eventOffer.setBusiness(businessRepository.findById(1L).orElseThrow());
        eventOffersRepository.save(eventOffer);

        eventOffer = new EventOffers();
        eventOffer.setEvent_title(" خصم 50%");
        eventOffer.setEvent_sub_title("غسيل السيارات");
        eventOffer.setBusiness(businessRepository.findById(1L).orElseThrow());
        eventOffer.setImage("sample_image.png");
        eventOffersRepository.save(eventOffer);

        eventOffer = new EventOffers();
        eventOffer.setEvent_title(" خصم 10%");
        eventOffer.setBusiness(businessRepository.findById(1L).orElseThrow());
        eventOffer.setEvent_sub_title("تغير الزيت");
        eventOffer.setImage("sample_image.png");
        eventOffersRepository.save(eventOffer);

        /////////////////////////////////////////
        eventOffer = new EventOffers();
        eventOffer.setEvent_title(" خصم 10%");
        eventOffer.setBusiness(businessRepository.findById(1L).orElseThrow());
        eventOffer.setEvent_sub_title("تغير الزيت");
        eventOffer.setImage("sample_image.png");
        eventOffersRepository.save(eventOffer);
    }

//    private void loadMyCouponsLists() {
//
//
//        MyCoupons myCoupons = new MyCoupons();
//        myCoupons.setInvoiced(false);
//        myCoupons.setComment(true);
//        myCoupons.setUser(userRepository.findById(1L).orElseThrow());
//        myCouponsRepository.save(myCoupons);
//
//        myCoupons = new MyCoupons();
//        myCoupons.setInvoiced(false);
//        myCoupons.setComment(false);
//        myCoupons.setUser(userRepository.findById(2L).orElseThrow());
//        myCouponsRepository.save(myCoupons);
//
//        myCoupons = new MyCoupons();
//        myCoupons.setInvoiced(true);
//        myCoupons.setComment(false);
//        myCoupons.setUser(userRepository.findById(3L).orElseThrow());
//        myCouponsRepository.save(myCoupons);
//
//        myCoupons = new MyCoupons();
//        myCoupons.setInvoiced(true);
//        myCoupons.setComment(false);
//        myCoupons.setUser(userRepository.findById(4L).orElseThrow());
//        myCouponsRepository.save(myCoupons);
//
//        myCoupons = new MyCoupons();
//        myCoupons.setUser(userRepository.findById(5L).orElseThrow());
//        myCoupons.setInvoiced(false);
//        myCoupons.setComment(false);
//        myCouponsRepository.save(myCoupons);
//
//        myCoupons = new MyCoupons();
//        myCoupons.setUser(userRepository.findById(6L).orElseThrow());
//        myCoupons.setInvoiced(true);
//        myCoupons.setComment(true);
//        myCouponsRepository.save(myCoupons);
//
//        myCoupons = new MyCoupons();
//        myCoupons.setUser(userRepository.findById(7L).orElseThrow());
//        myCoupons.setInvoiced(true);
//        myCoupons.setComment(true);
//        myCouponsRepository.save(myCoupons);
//
//        myCoupons = new MyCoupons();
//        myCoupons.setUser(userRepository.findById(8L).orElseThrow());
//        myCoupons.setInvoiced(true);
//        myCoupons.setComment(true);
//        myCouponsRepository.save(myCoupons);
//
//
//    }

    private void loadReadme() {

        Readme history = new Readme();
        history.setRate(3.8F);

        history.setReadme_date(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setSchedule_date(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setSchedule_time(new java.sql.Date(System.currentTimeMillis()).toString());

        history.setDocumentPath("sample_document_path");
        history.setStateName("pending");
        history.setServices(servicesRepository.findById(1L).orElseThrow());
        history.setBusiness_branch(branchesRepository.findBranchesByBusinessId(servicesRepository.findById(1L).orElseThrow().getBusiness().getId()).get(0).getId());
        history.setUser(userRepository.findById(1L).orElseThrow());
        readmeRepository.save(history);

        history = new Readme();
        history.setRate(4.8F);

        history.setComment("good or not good for the user test");
        history.setReadme_date(new java.sql.Date(System.currentTimeMillis()).toString());

        history.setSchedule_time(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setSchedule_date(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setDocumentPath("sample_document_path");
        history.setStateName("pending");
        history.setServices(servicesRepository.findById(1L).orElseThrow());
        history.setBusiness_branch(branchesRepository.findBranchesByBusinessId(servicesRepository.findById(1L).orElseThrow().getBusiness().getId()).get(0).getId());

        history.setUser(userRepository.findById(2L).orElseThrow());
        readmeRepository.save(history);

        history = new Readme();

        history.setReadme_date(new java.sql.Date(System.currentTimeMillis()).toString());

        history.setSchedule_time(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setSchedule_date(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setDocumentPath("sample_document_path");
        history.setStateName("pending");
        history.setServices(servicesRepository.findById(1L).orElseThrow());
        history.setBusiness_branch(branchesRepository.findBranchesByBusinessId(servicesRepository.findById(1L).orElseThrow().getBusiness().getId()).get(0).getId());

        history.setUser(userRepository.findById(3L).orElseThrow());
        readmeRepository.save(history);

        history = new Readme();
        history.setRate(4.8F);

        history.setComment("good or not good for the user test");
        history.setReadme_date(new java.sql.Date(System.currentTimeMillis()).toString());

        history.setSchedule_time(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setSchedule_date(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setDocumentPath("sample_document_path");
        history.setStateName("pending");
        history.setServices(servicesRepository.findById(1L).orElseThrow());
        history.setBusiness_branch(branchesRepository.findBranchesByBusinessId(servicesRepository.findById(1L).orElseThrow().getBusiness().getId()).get(0).getId());

        history.setUser(userRepository.findById(4L).orElseThrow());
        readmeRepository.save(history);

        history = new Readme();
        history.setRate(2.8F);

        history.setReadme_date(new java.sql.Date(System.currentTimeMillis()).toString());

        history.setSchedule_time(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setSchedule_date(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setDocumentPath("sample_document_path");
        history.setStateName("pending");
        history.setServices(servicesRepository.findById(2L).orElseThrow());
        history.setBusiness_branch(branchesRepository.findBranchesByBusinessId(servicesRepository.findById(1L).orElseThrow().getBusiness().getId()).get(0).getId());

        history.setUser(userRepository.findById(1L).orElseThrow());
        readmeRepository.save(history);

        history = new Readme();
        history.setRate(2.8F);
        history.setComment("good or not good for the user test");
        history.setReadme_date(new java.sql.Date(System.currentTimeMillis()).toString());

        history.setSchedule_time(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setSchedule_date(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setDocumentPath("sample_document_path");
        history.setStateName("pending");
        history.setServices(servicesRepository.findById(2L).orElseThrow());
        history.setBusiness_branch(branchesRepository.findBranchesByBusinessId(servicesRepository.findById(1L).orElseThrow().getBusiness().getId()).get(0).getId());

        history.setUser(userRepository.findById(1L).orElseThrow());
        readmeRepository.save(history);

        history = new Readme();
        history.setRate(3.9F);

        history.setComment("good or not good for the user test");
        history.setReadme_date(new java.sql.Date(System.currentTimeMillis()).toString());

        history.setSchedule_time(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setSchedule_date(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setDocumentPath("sample_document_path");
        history.setStateName("pending");
        history.setServices(servicesRepository.findById(3L).orElseThrow());
        history.setBusiness_branch(branchesRepository.findBranchesByBusinessId(servicesRepository.findById(1L).orElseThrow().getBusiness().getId()).get(0).getId());

        history.setUser(userRepository.findById(1L).orElseThrow());
        readmeRepository.save(history);

        history = new Readme();
        history.setRate(3.9F);

        history.setReadme_date(new java.sql.Date(System.currentTimeMillis()).toString());

        history.setSchedule_time(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setSchedule_date(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setDocumentPath("sample_document_path");
        history.setStateName("pending");
        history.setServices(servicesRepository.findById(4L).orElseThrow());
        history.setBusiness_branch(branchesRepository.findBranchesByBusinessId(servicesRepository.findById(1L).orElseThrow().getBusiness().getId()).get(0).getId());

        history.setUser(userRepository.findById(5L).orElseThrow());
        readmeRepository.save(history);

        history = new Readme();
        history.setRate(3.9F);

        history.setReadme_date(new java.sql.Date(System.currentTimeMillis()).toString());

        history.setSchedule_time(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setSchedule_date(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setDocumentPath("sample_document_path");
        history.setStateName("pending");
        history.setServices(servicesRepository.findById(4L).orElseThrow());
        history.setBusiness_branch(branchesRepository.findBranchesByBusinessId(servicesRepository.findById(1L).orElseThrow().getBusiness().getId()).get(0).getId());

        history.setUser(userRepository.findById(6L).orElseThrow());
        readmeRepository.save(history);

        history = new Readme();
        history.setRate(4.0F);

        history.setComment("good or not good for the user test");
        history.setReadme_date(new java.sql.Date(System.currentTimeMillis()).toString());

        history.setSchedule_time(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setSchedule_date(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setDocumentPath("sample_document_path");
        history.setStateName("pending");
        history.setServices(servicesRepository.findById(5L).orElseThrow());
        history.setBusiness_branch(branchesRepository.findBranchesByBusinessId(servicesRepository.findById(1L).orElseThrow().getBusiness().getId()).get(0).getId());

        history.setUser(userRepository.findById(7L).orElseThrow());
        readmeRepository.save(history);

        history = new Readme();
        history.setRate(4.0F);

        history.setComment("good or not good for the user test");
        history.setReadme_date(new java.sql.Date(System.currentTimeMillis()).toString());

        history.setSchedule_time(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setSchedule_date(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setDocumentPath("sample_document_path");
        history.setStateName("pending");
        history.setServices(servicesRepository.findById(5L).orElseThrow());
        history.setBusiness_branch(branchesRepository.findBranchesByBusinessId(servicesRepository.findById(1L).orElseThrow().getBusiness().getId()).get(0).getId());

        history.setUser(userRepository.findById(7L).orElseThrow());
        readmeRepository.save(history);

        history = new Readme();
        history.setRate(4.0F);

        history.setComment("good or not good for the user test");
        history.setReadme_date(new java.sql.Date(System.currentTimeMillis()).toString());

        history.setSchedule_time(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setSchedule_date(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setDocumentPath("sample_document_path");
        history.setStateName("pending");
        history.setServices(servicesRepository.findById(6L).orElseThrow());
        history.setBusiness_branch(branchesRepository.findBranchesByBusinessId(servicesRepository.findById(1L).orElseThrow().getBusiness().getId()).get(0).getId());

        history.setUser(userRepository.findById(8L).orElseThrow());
        readmeRepository.save(history);

        history = new Readme();
        history.setRate(4.0F);

        history.setComment("good or not good for the user test");
        history.setReadme_date(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setSchedule_time(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setSchedule_date(new java.sql.Date(System.currentTimeMillis()).toString());
        history.setDocumentPath("sample_document_path");
        history.setStateName("pending");
        history.setServices(servicesRepository.findById(7L).orElseThrow());
        history.setBusiness_branch(branchesRepository.findBranchesByBusinessId(servicesRepository.findById(1L).orElseThrow().getBusiness().getId()).get(0).getId());

        history.setUser(userRepository.findById(8L).orElseThrow());
        readmeRepository.save(history);

    }


    private void loadReplacements() {
        Replacement replacement = new Replacement();

        replacement.setPolicy("حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل" +
                " الخارجي للنص أو شكل توضع الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم" +
                " إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد" +
                " محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء. العديد من برامح النشر المكتبي وبرامح تحرير صفحات" +
                " الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن النص، وإذا قمت بإدخال  في أي محرك بحث ستظهر العديد من المواقع" +
                " الحديثة العهد في نتائج البحث. على مدى السنين ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،" +
                " حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل الخارجي للنص أو شكل توضع " +
                "الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- " +
                "للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء" +
                ". العديد من برامح النشر المكتبي وبرامح تحرير صفحات الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن" +
                " النص، وإذا قمت بإدخال في أي محرك بحث ستظهر العديد من المواقع الحديثة العهد في نتائج البحث. على مدى السنين " +
                "ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،   وأحياناً عن عمد كإدخال بعض العبارات الفكاهية إليها.");
        replacement.setTermsConditions("حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل" +
                " الخارجي للنص أو شكل توضع الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم" +
                " إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد" +
                " محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء. العديد من برامح النشر المكتبي وبرامح تحرير صفحات" +
                " الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن النص، وإذا قمت بإدخال  في أي محرك بحث ستظهر العديد من المواقع" +
                " الحديثة العهد في نتائج البحث. على مدى السنين ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،" +
                " حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل الخارجي للنص أو شكل توضع " +
                "الفقرات في الصفحة التي يقرأها.   ولذلك يتم استخدام طريقة لوريم إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- " +
                "للأحرف عوضاً عن استخدام هنا يوجد محتوى نصي، هنا يوجد محتوى نصي فتجعلها تبدو (أي الأحرف) وكأنها نص مقروء" +
                ". العديد من برامح النشر المكتبي وبرامح تحرير صفحات الويب تستخدم لوريم إيبسوم بشكل إفتراضي كنموذج عن" +
                " النص، وإذا قمت بإدخال في أي محرك بحث ستظهر العديد من المواقع الحديثة العهد في نتائج البحث. على مدى السنين " +
                "ظهرت نسخ جديدة ومختلفة من نص لوريم إيبسوم، أحياناً عن طريق الصدفة،   وأحياناً عن عمد كإدخال بعض العبارات الفكاهية إليها.");
        replacement.setInsurance_val("2.5");
        replacement.setPoint_for_registration(100);
        replacement.setStart_discount_val("10");


        replacementRepository.save(replacement);


    }

    private void loadModeAndBrand() {

        CarBrand carBrand = new CarBrand();
        carBrand.setName("BMW");
        carBrandRepository.save(carBrand);

        CarModel carModel = new CarModel();
        carModel.setName("XM");
        carModel.setCar_brand(carBrandRepository.findById(1L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("3 Series");
        carModel.setCar_brand(carBrandRepository.findById(1L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("I4");
        carModel.setCar_brand(carBrandRepository.findById(1L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("M8");
        carModel.setCar_brand(carBrandRepository.findById(1L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("X7");
        carModel.setCar_brand(carBrandRepository.findById(1L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("M4");
        carModel.setCar_brand(carBrandRepository.findById(1L).orElseThrow());
        carModelRepository.save(carModel);

/////////////////////////////////////////////////////////

        carBrand = new CarBrand();
        carBrand.setName("Honda");
        carBrandRepository.save(carBrand);

        carModel = new CarModel();
        carModel.setName("Civic");
        carModel.setCar_brand(carBrandRepository.findById(2L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("Accord");
        carModel.setCar_brand(carBrandRepository.findById(2L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("Insight");
        carModel.setCar_brand(carBrandRepository.findById(2L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("NSX");
        carModel.setCar_brand(carBrandRepository.findById(2L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("HR-V");
        carModel.setCar_brand(carBrandRepository.findById(2L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("Clarity");
        carModel.setCar_brand(carBrandRepository.findById(2L).orElseThrow());
        carModelRepository.save(carModel);
/////////////////////////////////////////////////////////

        carBrand = new CarBrand();
        carBrand.setName("Ford");
        carBrandRepository.save(carBrand);
        carModel = new CarModel();
        carModel.setName("Mach-E");
        carModel.setCar_brand(carBrandRepository.findById(3L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("EcoSport");
        carModel.setCar_brand(carBrandRepository.findById(3L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("F-150");
        carModel.setCar_brand(carBrandRepository.findById(3L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("GT");
        carModel.setCar_brand(carBrandRepository.findById(3L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("Focus");
        carModel.setCar_brand(carBrandRepository.findById(3L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("Escape");
        carModel.setCar_brand(carBrandRepository.findById(3L).orElseThrow());
        carModelRepository.save(carModel);
//////////////////////////////////////////////////////////
        carBrand = new CarBrand();
        carBrand.setName("Chevrolet");
        carBrandRepository.save(carBrand);
        carModel = new CarModel();
        carModel.setName("Cruze");
        carModel.setCar_brand(carBrandRepository.findById(4L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("Captiva");
        carModel.setCar_brand(carBrandRepository.findById(4L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("Colorado");
        carModel.setCar_brand(carBrandRepository.findById(4L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("Camaro");
        carModel.setCar_brand(carBrandRepository.findById(4L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("Silverado ");
        carModel.setCar_brand(carBrandRepository.findById(4L).orElseThrow());
        carModelRepository.save(carModel);

        carModel = new CarModel();
        carModel.setName("Suburban");
        carModel.setCar_brand(carBrandRepository.findById(4L).orElseThrow());
        carModelRepository.save(carModel);
        log.info("   Data Load it    ");

    }

    @Override
    public void run(String... args) throws Exception {
        if (dataLoaderEnabled) {
            loadData();
        }
    }
}
