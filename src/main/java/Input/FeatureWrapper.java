package Input;

import http.HttpServerManager;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public abstract class FeatureWrapper implements Feature {


    private static final HashMap<FeatureWrapper, String> featureList;
    
    static {
        featureList = new LinkedHashMap<>();
    }

    private final String inputFeatureName;
    private LinkedList<String> setOutput;
    private System input;


    public FeatureWrapper(Class feature) {
        this.inputFeatureName = feature.getClass().getSimpleName();
        featureList.put(this, feature.getClass().getSimpleName());

    }

    public static HashMap<FeatureWrapper, String> getFeatureList() {
        return featureList;
    }

    public static boolean exit() {
        System.out.println("---GOODBYE!---");
        HttpServerManager.getActiveServers().get(8080).stop(0);
        System.exit(0);
        return true;
    }

    public String getInputFeatureName() {
        return inputFeatureName;
    }

    @Override
    public LinkedList<String> getSetOutput() {
        return setOutput;
    }


    @Override
    public String getInput() {
        return "";
    }

    @Override
    public void setInput(String input) {

    }
}
