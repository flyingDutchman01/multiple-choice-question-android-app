package com.example.multiplechoicequestion.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Question.class}, version = 1, exportSchema = false)
public abstract class QuestionRoomDatabase extends RoomDatabase {

    public abstract QuestionDao questionDao();

    public static volatile QuestionRoomDatabase INSTANCE;
    public static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static QuestionRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (QuestionRoomDatabase.class){
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuestionRoomDatabase.class, "question_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback = new Callback(){
      @Override
      public void onOpen(@NonNull SupportSQLiteDatabase db){
          super.onOpen(db);

          databaseWriteExecutor.execute( () -> {
             QuestionDao dao = INSTANCE.questionDao();
             dao.deleteAll();
             
             Question q1 = new Question(" नेपालको इतिहासमा गोरे जर्नेल भनेर कसलाई चिनिन्छ ?  ","जगतजंग","भीमजंग कुँवर","रणविर जङ्ग","जीतजंग कुँवर    ",2);
             dao.insert(q1);
             
             Question q2 = new Question("नेपाल वायुसेवा निगमको स्थापना कहिले भएको हो ?","१९९३ जेठ २०","१९९७ माघ २०","२०१५ असार १७"," २००६ चैत्र १९",3);
             dao.insert(q2);
             
             Question q3 = new Question("विश्व इतिहासमा रक्तहिन क्रान्तिका नामले चिनिने क्रान्ति कुन हो ? ","भारतीय स्वतन्त्रता संग्राम","फ्रान्सेली राज्य क्रान्ति","रसियन अक्टोवर क्रान्त","वेलायतको गौरवमय क्रान्ति",4);
             dao.insert(q3);
             
             Question q4 = new Question("नेपालको इतिहासमा प्रथम पटक प्रधानमन्त्री समक्ष शपथ लिने प्रधानसेनापति को हुन् ? ","रुक्वाङ्गत कट्वाल","प्यारजंग थापा","कुबेर सिंह राणा ","गौरव शमशेर",1);
             dao.insert(q4);
             
             Question q5 = new Question("कलाकार वासुदेव मुनालको निधन कहिले भयो ? ","२०७४ असोज १०","२०७४ असोज १२","२०७४ असोज १३","२०७४ असोज १४",2);
             dao.insert(q5);
             
             Question q6 = new Question("सर्वसाधारणले रेडियो राख्न पाउने व्यवस्था कसले गरेका हुन् ?","चन्द्र शमशेर","जुद्ध शमशेर","पद्म शमशेर","मोहन शमशेर",3);
             dao.insert(q5);
             
             Question q7 = new Question("द राइजिङ नेपालका प्रथम सम्पादक को हुन् ? ","रिषिकेश शाह","हिरण्यध्वज जोशी","बबर शमशेर","बरुण शमशेर",4);
              dao.insert(q7);

              Question q8 = new Question("युरोपमा भएको  “Seven year war” कुन कुन देशका बिचमा भएको थियो ? ","ब्रिटेन र फ्रान्स","ब्रिटेन र जर्मन","ब्रिटेन र इटाली","ब्रिटेन र स्पेन",1);
              dao.insert(q8);

              Question q9 = new Question("कोत पर्वमा सर्वप्रथम मारिने अभिमानसिंहलाई कसले मारेका थिए ? ","जंगबहादुर राणा","युद्धवीर अधिकारी","अन्नत पाण्डे","कनन सिंह ",2);
              dao.insert(q9);

              Question q10 = new Question("उजुरी पेटिकाको ब्यवस्था गर्ने राणा प्रधानमन्त्री को हुन् ?","वीर शमशेर","देव शमशेर","चन्द्र शमशेर","जुद्ध शमशेर",2);
              dao.insert(q10);

              Question q11 = new Question("यहाङ्ग विभिन्न कालका प्रथम राजाहरुको नाम दिइएका छन् । 1. अरिदेव मल्ल\t2. भक्तमान\t3. जयदेव\t\t4. बरसिंह\n" +
                      "कालखण्डका आधारमा पहिलोदेखि अन्तिम क्रममा मिलाउनुहोस् ।","2,3,4,1","1,2,3,4","3,2,1,4","2,4,3,1",4);
              dao.insert(q11);

              Question q12 = new Question("2015 सालको निर्वाचनमा निर्वाचित हुने एक मात्र महिला प्रतिनिधि को हुन् ? ","सहानादेवी प्रधान","द्वारिकादेवी ठकुरानी","भद्रा कुमारी घले","शैलजा आचार्य",3);
              dao.insert(q12);

              Question q13 = new Question("टोलेमी (Plodemy) राजाहरुको कुन देशमा राज्य गर्दथे ?","सिरिया","प्यालेस्टाइन","इजिप्ट","मोरक्को ",3);
              dao.insert(q13);

              Question q14 = new Question("प्रथम विश्व युद्ध हुँदा नेपालका प्रधानमन्त्री को थिए ?","वीर शमशेर","देव शमशेर","चन्द्र शमशेर","भीम शमशेर",3);
              dao.insert(q14);

              Question q15 = new Question("द्वितिय विश्वयुद्ध हुँदा नेपालका प्रधानमन्त्री को थिए ?  ","देव शमशेर","बीर शमशेर","भीम शमशेर","जुद्ध शमशेर",4);
              dao.insert(q15);

              Question q16 = new Question("नेपालको इतिहासमा स्वर्णयुग भनि कुन काललाई भनिन्छ ? ","लिच्छवी","किराँत","मल्ल काल","शाह काल",1);
              dao.insert(q16);

              Question q17 = new Question("प्रधानमन्त्रीहरुको क्रम मिलाउनुहोस् । 1. पुष्कर शाह\t2. भिमसेन थापा\t3. रणजङ्ग पाण्डे\t4. रंगनाथ पौडेल\t5. माथवरसिंह थापा","2,4,1,3,5","2,4,1,5,3","2,4,3,1,5","2,4,5,3,1",1);
              dao.insert(q17);


              Question q18 = new Question("अष्ट्रियाले कुन देशमा आक्रमण गरेपछि प्रथम विश्व युद्धको शुरुवात भएको थियो ?  ","सर्विया","इटाली","जर्मनी","स्वीडेन",1);
              dao.insert(q18);

              Question q19 = new Question("प्रथम विश्व युद्धको क्रममा कति जना नेपालीले भिक्टोरिया क्रस प्राप्त गरेका थिए ? ","1 जना","2 जना","3 जना","4 जना",2);
              dao.insert(q19);
          });
      }
    };
}
