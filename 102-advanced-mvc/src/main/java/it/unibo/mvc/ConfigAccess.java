package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ConfigAccess {
    private ClassLoader loader = DrawNumberApp.class.getClassLoader();
    private File directory = new File(loader.getResource("it").getPath());
    private File res;

    private int min;
    private int max;
    private int attempts;

    public ConfigAccess(){
        this.settings();
    }
    private void settings(){
        try {
            directory=directory.getParentFile();
            for (File file : directory.listFiles()){
                if (file.getName().equals("config.yml")) {
                    this.res = file;
                }
            }
            BufferedReader buffer = new BufferedReader(new FileReader(res));
            String tab[],line = buffer.readLine();
            while (line != null) {
                tab = line.split(":");
                Config config=Config.valueOf(tab[0]);
                switch (config) {
                    case minimum:
                        this.min = Integer.parseInt(tab[1].replaceAll("\\s+",""));
                        break;
                    case maximum:
                        this.max = Integer.parseInt(tab[1].replaceAll("\\s+",""));
                        break;
                    case attempts:
                        this.attempts = Integer.parseInt(tab[1].replaceAll("\\s+",""));
                        break;
                    default:
                        System.out.println("oups");
                        break;
                }
                line = buffer.readLine();
            }
            buffer.close();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMin(){
        return this.min;
    }

    public int getMax(){
        return this.max;
    }

    public int getAttempts(){
        return this.attempts;
    }
}

enum Config{
    minimum,maximum,attempts;
}

