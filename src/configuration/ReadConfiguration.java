package configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadConfiguration {
    private String name;
    private String host;

    public ReadConfiguration() {
        ArrayList<String> configurationArray = new ArrayList<String>();
        try {
            File sensorData = new File("configuration.txt");
            Scanner myReader = new Scanner(sensorData);
            while (myReader.hasNextLine()) {
                configurationArray.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }

        if (!configurationArray.isEmpty()){
            this.name = configurationArray.get(0);
            this.host = configurationArray.get(1);
        }
    }

    public String getName() {
        return this.name;
    }

    public String getHost() {
        return this.host;
    }
}
