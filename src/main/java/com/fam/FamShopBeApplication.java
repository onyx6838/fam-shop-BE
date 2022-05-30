package com.fam;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.TimeZone;

@SpringBootApplication
@ConfigurationPropertiesScan("com.fam")
public class FamShopBeApplication {

    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = FamShopBeApplication.class.getClassLoader();

        File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
        FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);

        SpringApplication.run(FamShopBeApplication.class, args);
    }

//    @PostConstruct
//    public void init(){ TimeZone.setDefault(TimeZone.getTimeZone("UTC")); }

}
