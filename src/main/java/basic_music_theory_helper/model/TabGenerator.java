package basic_music_theory_helper.model;

public class TabGenerator {
    public String showTuningTypes() {
        String tuningTypes = "";
        int num = 1;
        OpenStrings[] openStrings = OpenStrings.values();
        for (OpenStrings tuningType : openStrings) {
            tuningTypes += num + ": " + tuningType.getTuningName() + " (" + tuningType.getTuningForm() + ")\n";
            num ++;
        }
        return tuningTypes.replaceAll("\n$","");
    }
}
