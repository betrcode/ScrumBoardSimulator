package se.bettercode.scrum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStoryTitleGenerator {

    static String[] verbs = {"Create", "Update", "Remove", "Refactor", "Implement", "Rewrite", "Make", "Redesign", "Reduce"};
    static String[] subject = {"GUI", "Merchant Service", "Sendout", "Customer Callback", "Retention Service", "Checkout",
                        "German Store", "French Store", "Nordic Suffering", "User Tracking", "Out-out Service",
                        "Self Service", "Facebook Integration", "G+ Integration", "PSP Integration", "Bank Integration",
                        "User ID Integration", "Deposit Service", "Statistics Overview", "Spike", "Random Idea"};

    private Random random = new Random();

    public String generateOne() {
        int randomVerbIndex = random.nextInt(verbs.length);
        int randomSubjectIndex = random.nextInt(subject.length);
        return verbs[randomVerbIndex] + " " + subject[randomSubjectIndex];
    }

    public ArrayList<String> generate(int n) {
        String title;
        ArrayList<String> nRandomTitles = new ArrayList<String>();
        while (nRandomTitles.size() < n) {
            title = this.generateOne();
            if (!isStringInList(title, nRandomTitles)) {
                nRandomTitles.add(title);
            }
        }
        return nRandomTitles;
    }

    private boolean isStringInList(String search, List<String> list) {
        for (String str: list) {
            if (str.equals(search)) {
                return true;
            }
        }
        return false;
    }
}
