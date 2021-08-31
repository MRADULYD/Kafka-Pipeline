package com.rb.test.APIs;

import com.rb.test.Models.Battle;
import com.rb.test.Models.Session;
import com.rb.test.Producer.KafkaProducer1;
import com.rb.test.Repository.IMongoBattleRepository;
import com.rb.test.Repository.IMongoSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("kafka")
public class RestApi {

    @Autowired
    KafkaProducer1 kafkaProducer1;

    @Autowired
    IMongoBattleRepository battleRepository;

    @Autowired
    IMongoSessionRepository sessionRepository;

    @Value(value = "${kafka.topic1}")
    private String topic1;

    @Value(value = "${kafka.battle-topic}")
    private String battle_topic;

    @Value(value = "${kafka.session-topic}")
    private String session_topic;

    @GetMapping("/publish/{message}")
    public void post(@PathVariable("message") String message){
         kafkaProducer1.sendMessage(topic1, message);
    }

    @GetMapping("/battle1/{battle}")
    public void postBattle(@PathVariable("battle") String message){
        kafkaProducer1.sendMessage(battle_topic , message);
    }

    @GetMapping("/hi")
    public String hi(){
        return  "Hi";
    }

    @PostMapping("/battle")
    public ResponseEntity<Battle> addBattleDocument(@RequestBody Battle battle){
        try{
            Battle _battle = battleRepository.save(new Battle( battle.getBattle_id(),
            battle.getUser_ip(),
            battle.getUser_server_region(),
            battle.getServer_version(),
            battle.getPlayer_name(),
            battle.getEvent_name(),
            battle.getEvent_timestamp(),
            battle.getUser_device_country(),
            battle.getUser_id(),
            battle.getUser_type(),
            battle.getClient_version(),
            battle.getIs_premium(),
            battle.getPlatform(),
            battle.getUser_is_spender(),
            battle.getOutcome(),
            battle.getShip_destroyed()));
            kafkaProducer1.sendMessage(battle_topic, "Received new battle event");
            return new ResponseEntity<>(_battle, HttpStatus.CREATED);
        }catch (Exception ex){
            System.out.println("Error while saving to MongoDB: " + ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/session")
    public ResponseEntity<Session> addSessionDocument(@RequestBody Session session){
        try{
            Session _session = sessionRepository.save(new Session( session.getSession_id(),
            session.getUser_ip(),
            session.getUser_server_region(),
            session.getServer_version(),
            session.getPlayer_name(),
            session.getLogin_attempt_id(),
            session.getEvent_name(),
            session.getEvent_timestamp(),
            session.getUser_device_country(),
            session.getUser_id(),
            session.getUser_type(),
            session.getClient_version(),
            session.getIs_premium(),
            session.getPlatform(),
            session.getUser_is_spender()));
            kafkaProducer1.sendMessage(session_topic, "Received new session event");
            return new ResponseEntity<>(_session, HttpStatus.CREATED);
        }catch (Exception ex){
            System.out.println("Error while saving to MongoDB: " + ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
