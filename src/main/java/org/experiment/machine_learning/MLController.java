package org.experiment.machine_learning;


import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Value;



@RestController
public class MLController {

    @Value("${sample.password}")
    public String samplePassword;
    @GetMapping("testenv")
    public String testEnv(){
        return samplePassword;
    }
    
    @GetMapping("linearRegression/{X}/{y}")
    public String lineRegSimp(@PathVariable String X, @PathVariable String y) {

    
        // Splitting comma-separated values into arrays
        String[] XValues = X.split(",");
        String[] yValues = y.split(",");
        

        // Checking if the arrays have the same length
        if (XValues.length != yValues.length) {
            return "Error: X and y arrays must have the same length.";
        }
        
        
        // Converting string arrays to double arrays
        double[] XArray = new double[XValues.length];
        double[] yArray = new double[yValues.length];

        try {
            for (int i = 0; i < XValues.length; i++) {
                XArray[i] = Double.parseDouble(XValues[i]);
                yArray[i] = Double.parseDouble(yValues[i]);
            }
        } catch (NumberFormatException e) {
            return "Error: Invalid input format. Please provide valid numeric values for X and y.";
        }
        

        // Performing linear regression
        SimpleRegression regression = new SimpleRegression();
        for (int i = 0; i < XArray.length && i < yArray.length; i++) {
            regression.addData(XArray[i], yArray[i]);
        }
        

        // Calculating intercept, slope, and prediction
        double intercept = regression.getIntercept();
        double slope = regression.getSlope();
        double prediction = regression.predict(18.8);
        

        // Constructing JSON response
        StringBuilder jsonResponse = new StringBuilder();
        jsonResponse.append("{");
        jsonResponse.append("\"intercept\": ").append(intercept).append(", ");
        jsonResponse.append("\"slope\": ").append(slope).append(", ");
        jsonResponse.append("\"prediction\": ").append(prediction);
        jsonResponse.append("}");
        
        return jsonResponse.toString();
    }


   
    
}
