package org.experiment.machine_learning;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MachineLearningApplication {

	public static void main(String[] args) {


		SimpleRegression regression = new SimpleRegression();
		double [] X = {1.0, 2.0, 5.0, 10.0, 23.0};
		double [] y = {5.0, 8.0, 10.0, 35.0, 62.0};
		for (int index = 0; index < X.length && index < y.length; index++) {
			regression.addData(X[index], y[index]);
		}
		
		double intercept = regression.getIntercept();
		double slope = regression.getSlope();
		System.err.println("Intercept: "+intercept+" Slope: "+slope+" y_pred: "+regression.predict(18.8));
		
		
		OLSMultipleLinearRegression mRegression = new OLSMultipleLinearRegression();
		double[] y1 = {4.5, 5.0, 6.0, 6.5};
        double[][] x1 = {
            {1.0, 2.0},
            {2.0, 1.0},
            {3.0, 3.0},
            {4.0, 4.0}
        };
		mRegression.newSampleData(y1, x1);
		double beta[] = mRegression.estimateRegressionParameters();

		System.out.println("Intercept: " + beta[0]);
        for (int i = 1; i < beta.length; i++) {
            System.out.println("Coefficient of x" + i + ": " + beta[i]);
        }

        // Predict a new value
        double[] newX = {5.0, 6.0};
        double prediction = beta[0];
        for (int i = 0; i < newX.length; i++) {
            prediction += beta[i + 1] * newX[i];
        }
        System.out.println("Predicted value for new input: " + prediction);
		
		SpringApplication.run(MachineLearningApplication.class, args);
	}

}
