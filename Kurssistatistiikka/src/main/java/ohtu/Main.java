package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        Course[] courses = getCourses();
        
        Submission[] subs = getSubmissions(studentNr);
        
        System.out.println("Opiskelijanumero: " + studentNr);
        System.out.println("");
        
        for (Course course : courses) {
            System.out.println("");
            System.out.println(course.toString());
            System.out.println("");
            int doneTotal = 0;
            int exercisesTotal = 0;
            for (int exercise : course.getExercises()) {
                exercisesTotal += exercise;
            }
            int hoursTotal = 0;
            for (Submission submission : subs) {
                if (submission.getCourse().equals(course.getName())) {
                    int week = submission.getWeek();
                    System.out.println("viikko " + submission.getWeek() + ":");
                    System.out.println(" tehtyjä tehtäviä " + submission.getExercises().size() + "/" + course.getExercises().get(week) + 
                            " aikaa kului " + submission.getHours() + " tehdyt tehtävät " + submission.getExercises().toString());
                    doneTotal += submission.getExercises().size();
                    hoursTotal += submission.getHours();
                }
            }
            System.out.println("");
            System.out.println("Yhteensä: " + doneTotal + "/" + exercisesTotal + " tehtävää " + hoursTotal + " tuntia");
        }
        

    }

    private static Submission[] getSubmissions(String studentNr) throws IOException {
        
        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";
        String bodyText = Request.Get(url).execute().returnContent().asString();

        //System.out.println("json-muotoinen data:");
        //System.out.println( bodyText );

        Gson mapper = new Gson();
        return mapper.fromJson(bodyText, Submission[].class);

    }
    
    private static Course[] getCourses() throws IOException {
        
        String url = "https://studies.cs.helsinki.fi/courses/courseinfo";
        String bodyText = Request.Get(url).execute().returnContent().asString();

        System.out.println("Kurssit: json-muotoinen data:");
        System.out.println( bodyText );

        Gson mapper = new Gson();
        return mapper.fromJson(bodyText, Course[].class);
    }

}
