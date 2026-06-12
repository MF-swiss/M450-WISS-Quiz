package ch.wiss.wiss_quiz.training;

import java.util.List;

public class CleanCodeTrainingExamples {

    // Schlechter Methodenname + unnötige Variable + verschachtelte ifs
    public String doStuff(String name, int points, boolean active) {
        String result = "";

        if (active == true) {
            if (points > 80) {
                result = "User " + name + " passed";
            } else {
                result = "User " + name + " failed";
            }
        } else {
            result = "User inactive";
        }

        return result;
    }

    // Duplikation (DRY verletzt)
    public void printAdminMessage(String name) {
        System.out.println("Hello " + name);
        System.out.println("Welcome to WissQuiz");
        System.out.println("Role: Admin");
    }

    public void printTeacherMessage(String name) {
        System.out.println("Hello " + name);
        System.out.println("Welcome to WissQuiz");
        System.out.println("Role: Teacher");
    }

    // Magic Number
    public boolean isQuizPassed(int scorePercent) {
        return scorePercent >= 60;
    }

    public boolean getsCertificate(int scorePercent) {
        return scorePercent >= 60;
    }

    // Zu viele Verantwortlichkeiten + schlechte Lesbarkeit
    public int calculateSomething(List<Integer> numbers) {
        int result = 0;

        for (int i = 0; i < numbers.size(); i++) {
            int n = numbers.get(i);

            if (n > 0) {
                if (n % 2 == 0) {
                    result = result + n * 2;
                } else {
                    result = result + n;
                }
            }
        }

        return result;
    }
    
    // Lange Methode + mehrere Verantwortlichkeiten + schlechte Lesbarkeit
    public String processUserData(String name, int age, boolean isMember, int points) {
        String result = "";

        if (name != null && name.length() > 0) {
            if (age > 18) {
                if (isMember == true) {
                    if (points > 100) {
                        result = "Premium Member: " + name;
                    } else {
                        result = "Standard Member: " + name;
                    }
                } else {
                    result = "Guest: " + name;
                }
            } else {
                result = "Too young";
            }
        } else {
            result = "Invalid name";
        }

        return result;
    }
    
    // Schlechte Parameter-Namen + unnötige Abkürzungen
    public boolean chk(int a, int b) {
        if (a > b) {
            return true;
        } else {
            return false;
        }
    }
    
 // Schlechte Fehlerbehandlung + unklare Logik
    public int divide(int a, int b) {
        int result = 0;

        try {
            result = a / b;
        } catch (Exception e) {
            System.out.println("Error");
        }

        return result;
    }
    
 // Unnötige Komplexität + schlechte Lesbarkeit
    public boolean isEvenAndPositive(int number) {
        if (number > 0) {
            if (number % 2 == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}