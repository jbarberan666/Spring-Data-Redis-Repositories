package fr.pe.polygone.dbreaderservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

/*
 * Messagge entity
 */
@RedisHash("message")
public class Message implements Serializable {

        @Id String id;

        private String senderFirstName;
        private String senderLastName;
        private String content;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getSenderFirstName() {
                return senderFirstName;
        }

        public void setSenderFirstName(String senderFirstName) {
                this.senderFirstName = senderFirstName;
        }

        public String getSenderLastName() {
                return senderLastName;
        }

        public void setSenderLastName(String senderLastName) {
                this.senderLastName = senderLastName;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }


}


