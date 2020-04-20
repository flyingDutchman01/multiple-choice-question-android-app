package com.example.multiplechoicequestion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.multiplechoicequestion.QuizContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="AwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;



    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                 QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }
    private void fillQuestionsTable(){
        Question q1 = new Question(" नेपालको इतिहासमा गोरे जर्नेल भनेर कसलाई चिनिन्छ ?  ","जगतजंग","भीमजंग कुँवर","रणविर जङ्ग","जीतजंग कुँवर    ",2);
        addQuestion(q1);

        Question q2 = new Question("नेपाल वायुसेवा निगमको स्थापना कहिले भएको हो ?","१९९३ जेठ २०","१९९७ माघ २०","२०१५ असार १७"," २००६ चैत्र १९",3);
        addQuestion(q2);

        Question q3 = new Question("विश्व इतिहासमा रक्तहिन क्रान्तिका नामले चिनिने क्रान्ति कुन हो ? ","भारतीय स्वतन्त्रता संग्राम","फ्रान्सेली राज्य क्रान्ति","रसियन अक्टोवर क्रान्त","वेलायतको गौरवमय क्रान्ति",4);
        addQuestion(q3);

        Question q4 = new Question("नेपालको इतिहासमा प्रथम पटक प्रधानमन्त्री समक्ष शपथ लिने प्रधानसेनापति को हुन् ? ","रुक्वाङ्गत कट्वाल","प्यारजंग थापा","कुबेर सिंह राणा ","गौरव शमशेर",1);
        addQuestion(q4);

        Question q5 = new Question("कलाकार वासुदेव मुनालको निधन कहिले भयो ? ","२०७४ असोज १०","२०७४ असोज १२","२०७४ असोज १३","२०७४ असोज १४",2);
        addQuestion(q5);

        Question q6 = new Question("सर्वसाधारणले रेडियो राख्न पाउने व्यवस्था कसले गरेका हुन् ?","चन्द्र शमशेर","जुद्ध शमशेर","पद्म शमशेर","मोहन शमशेर",3);
        addQuestion(q6);

        Question q7 = new Question("द राइजिङ नेपालका प्रथम सम्पादक को हुन् ? ","रिषिकेश शाह","हिरण्यध्वज जोशी","बबर शमशेर","बरुण शमशेर",4);
        addQuestion(q7);

        Question q8 = new Question("युरोपमा भएको  “Seven year war” कुन कुन देशका बिचमा भएको थियो ? ","ब्रिटेन र फ्रान्स","ब्रिटेन र जर्मन","ब्रिटेन र इटाली","ब्रिटेन र स्पेन",1);
        addQuestion(q8);

        Question q9 = new Question("कोत पर्वमा सर्वप्रथम मारिने अभिमानसिंहलाई कसले मारेका थिए ? ","जंगबहादुर राणा","युद्धवीर अधिकारी","अन्नत पाण्डे","कनन सिंह ",2);
        addQuestion(q9);

        Question q10 = new Question("उजुरी पेटिकाको ब्यवस्था गर्ने राणा प्रधानमन्त्री को हुन् ?","वीर शमशेर","देव शमशेर","चन्द्र शमशेर","जुद्ध शमशेर",2);
        addQuestion(q10);

        Question q11 = new Question("यहाङ्ग विभिन्न कालका प्रथम राजाहरुको नाम दिइएका छन् ।  १.अरिदेव मल्ल   २.भक्तमान  ३.जयदेव  ४.बरसिंह" +
                "कालखण्डका आधारमा पहिलोदेखि अन्तिम क्रममा मिलाउनुहोस् ।","२,३,४,१","१,२,३,४","३,२,१,४","२,४,३,१",4);
        addQuestion(q11);

        Question q12 = new Question("2015 सालको निर्वाचनमा निर्वाचित हुने एक मात्र महिला प्रतिनिधि को हुन् ? ","सहानादेवी प्रधान","द्वारिकादेवी ठकुरानी","भद्रा कुमारी घले","शैलजा आचार्य",3);
        addQuestion(q12);

        Question q13 = new Question("टोलेमी (Plodemy) राजाहरुको कुन देशमा राज्य गर्दथे ?","सिरिया","प्यालेस्टाइन","इजिप्ट","मोरक्को ",3);
        addQuestion(q13);

        Question q14 = new Question("प्रथम विश्व युद्ध हुँदा नेपालका प्रधानमन्त्री को थिए ?","वीर शमशेर","देव शमशेर","चन्द्र शमशेर","भीम शमशेर",3);
        addQuestion(q14);

        Question q15 = new Question("द्वितिय विश्वयुद्ध हुँदा नेपालका प्रधानमन्त्री को थिए ?  ","देव शमशेर","बीर शमशेर","भीम शमशेर","जुद्ध शमशेर",4);
        addQuestion(q15);

        Question q16 = new Question("नेपालको इतिहासमा स्वर्णयुग भनि कुन काललाई भनिन्छ ? ","लिच्छवी","किराँत","मल्ल काल","शाह काल",1);
        addQuestion(q16);

        Question q17 = new Question("प्रधानमन्त्रीहरुको क्रम मिलाउनुहोस् । १.पुष्कर शाह  २.भिमसेन थापा  ३.रणजङ्ग पाण्डे  4.रंगनाथ पौडेल  ५.माथवरसिंह थापा","२,४,१,३,५","२,४,१,५,३","२,४,३,१,५","२,४,५,३,१",1);
        addQuestion(q17);


        Question q18 = new Question("अष्ट्रियाले कुन देशमा आक्रमण गरेपछि प्रथम विश्व युद्धको शुरुवात भएको थियो ?  ","सर्विया","इटाली","जर्मनी","स्वीडेन",1);
        addQuestion(q18);

        Question q19 = new Question("प्रथम विश्व युद्धको क्रममा कति जना नेपालीले भिक्टोरिया क्रस प्राप्त गरेका थिए ? ","१ जना","२ जना","३ जना","४ जना",2);
        addQuestion(q19);


    }
    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnsNum());
        db.insert(QuestionsTable.TABLE_NAME,null,cv);

    }

    public ArrayList<Question> getAllQuestions(){
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
         Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnsNum(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            }while(c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
