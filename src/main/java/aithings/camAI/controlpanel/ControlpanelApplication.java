package aithings.camAI.controlpanel;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import aithings.camAI.controlpanel.utils.auditQueue.AuditConsumer;
import aithings.camAI.controlpanel.utils.auditQueue.AuditProducer;
import aithings.camAI.controlpanel.utils.constant.BEConstant;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.TimeZone;

@EnableJms
@EnableAsync
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = {"aithings.camAI.controlpanel"}, exclude = {DataSourceAutoConfiguration.class})
@Slf4j
public class ControlpanelApplication{
    @Value(value = "${spring.data.mongodb.uri}")
    private String uriMongoDB;
    @Value(value = "${spring.data.mongodb.database}")
    private String databaseMongoDB;

    public static void main(String[] args) {
        SpringApplication.run(ControlpanelApplication.class, args);
    }
    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        MongoClient mongoClient = MongoClients.create(uriMongoDB);
        boolean dataExist = false;
        for(String dbName : mongoClient.listDatabaseNames()){
            if(dbName.equals(databaseMongoDB)){
                dataExist = true;
                break;
            }
        }
        String jsonUserPath = "";
        String jsonRolePath ="";
        String jsonFunctionPath ="";
        String jsonWorkingUnitsPath = "";
        try {
            String root = FileSystems.getDefault().getPath("").toAbsolutePath().toString().replaceAll("\\\\","/");
            jsonUserPath = root + "/collection-default/sa_user.json";
            jsonRolePath = root + "/collection-default/sa_role.json";
            jsonFunctionPath = root +"/collection-default/sa_function.json";
            jsonWorkingUnitsPath = root + "/collection-default/sa_working_units.json";
        }catch (Exception e){
            e.printStackTrace();
        }
        if(!dataExist){
            MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseMongoDB);
            MongoCollection<Document> collectionSAUSer = mongoDatabase.getCollection("sa_user");
            MongoCollection<Document> collectionSARole = mongoDatabase.getCollection("sa_role");
            MongoCollection<Document> collectionSAFunction = mongoDatabase.getCollection("sa_function");
            MongoCollection<Document> collectionSAWorkingUnit = mongoDatabase.getCollection("sa_working_units");
            try {
                JsonArray dataUser = (JsonArray) JsonParser.parseReader(new FileReader(jsonUserPath));
                JsonArray dataRole = (JsonArray) JsonParser.parseReader(new FileReader(jsonRolePath));
                JsonArray dataFunction = (JsonArray) JsonParser.parseReader(new FileReader(jsonFunctionPath));
                JsonArray dataWorkingUnit = (JsonArray) JsonParser.parseReader(new FileReader(jsonWorkingUnitsPath));
                for(JsonElement jsonDatauser : dataUser){
                    Document document = Document.parse(jsonDatauser.toString());
                    collectionSAUSer.insertOne(document);
                }
                for(JsonElement jsonDataRole : dataRole){
                    Document document = Document.parse(jsonDataRole.toString());
                    collectionSARole.insertOne(document);
                }
                for(JsonElement jsonDatFunction : dataFunction){
                    Document document = Document.parse(jsonDatFunction.toString());
                    collectionSAFunction.insertOne(document);
                }
                for(JsonElement jsonDataWorkingUnit : dataWorkingUnit){
                    Document document = Document.parse(jsonDataWorkingUnit.toString());
                    collectionSAWorkingUnit.insertOne(document);
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    @Bean
    @DependsOn(BEConstant.CONSUMER_AUDIT)
    public AuditProducer auditProducer() {
        return new AuditProducer();
    }

    @Bean(name = BEConstant.CONSUMER_AUDIT)
    public AuditConsumer auditConsumer() {
        return new AuditConsumer();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
