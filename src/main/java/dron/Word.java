package dron;

import com.sun.deploy.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrey on 06.09.2016.
 */
public class Word {
//    public Word(String foreign, String original) {
//        this.foreign = foreign;
//        this.original = original;
//        knowledge = 0;
//    }
//
//    public Word(String foreign, String original, int knowledge) {
//        this.foreign = foreign;
//        this.original = original;
//        this.knowledge = knowledge;
//    }
//
//    private String foreign;
//    private String original;
//    private int knowledge;
//    private int sessionId;
//
//    public final String getForeign() {
//        return foreign;
//    }
//
//    public final String getOriginal() {
//        return original;
//    }
//
//    public final int getSessionId() {
//        return sessionId;
//    }
//
//    public final void setSessionId(int id) {
//        sessionId = id;
//    }
//
//    public final void updateKnowledge(boolean answer){
//        if (answer) knowledge++;
//        else knowledge = 0;
//    }
//
//    public final int getKnowledge(){
//        return knowledge;
//    }
//
//    @Override
//    public String toString() {
//        //Довольно примитивно, но с парсером вломень возиться
//        return String.join(System.lineSeparator(), foreign, original,
//                Integer.toString(knowledge));
//    }

    private String foreign;
    private List<String> translations;
    private int knowledge;

    public Word(String foreign, List<String> translations) {
        this.foreign = foreign;
        this.translations = Collections.synchronizedList(new LinkedList<>(translations));
        knowledge = 0;
    }

    public Word(String foreign, List<String> translations, int knowledge) {
        this.foreign = foreign;
        this.translations = Collections.synchronizedList(new LinkedList<>(translations));
        translations.sort(new java.util.Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });
        this.knowledge = knowledge;
    }

    public String getForeign() {
        return foreign;
    }

    public List<String> getTranslations() {
        return translations;
    }

    public static Comparator<Word> getKnowledgeComparator() {
        return (o1, o2) -> o1.knowledge - o2.knowledge;
    }

    public void updateKnowledge(boolean wasAnswerCorrect) {
        if (wasAnswerCorrect) {
            knowledge++;
        } else {
            knowledge = 0;
        }
    }

    public boolean checkAnswerForeign(String answer){
        return foreign.equals(answer);
    }

    public boolean checkAnswerTranslation(String answer){
        for (String translation: translations){
            if (translation.equals(answer)){
                return true;
            }
        }
        return false;
    }

    public boolean equals(Word w) {
        return foreign.equals(w.foreign);
    }

    @Override
    public String toString(){
        return String.join(System.lineSeparator(), foreign, "[",
                StringUtils.join(translations, System.lineSeparator()),
                "]", Integer.toString(knowledge));
    }
}