package fr.pe.polygone.dbreaderservice;


import fr.pe.polygone.dbreaderservice.controller.MessageController;
import fr.pe.polygone.dbreaderservice.entity.Message;
import fr.pe.polygone.dbreaderservice.exception.EntityNotFoundException;
import fr.pe.polygone.dbreaderservice.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Test
    public void findMessageById_ShoudlReturnMessage() throws Exception {
            Message aMessage =new Message();
            aMessage.setId("1");
            aMessage.setSenderFirstName("John");
            aMessage.setSenderLastName("Doe");
            aMessage.setContent("a message content");

            when(messageService.findById("1")).thenReturn(aMessage);

            mockMvc.perform(get("/api/messages/{i}",aMessage.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is("1")))
                .andExpect(jsonPath("$.senderFirstName", is("John")))
                .andExpect(jsonPath("$.senderLastName", is("Doe")))
                    .andExpect(jsonPath("$.content", is("a message content")));


    }

    @Test
    public void findMessageById_EntityNotFound_ShouldReturnHttpStatusCode404() throws Exception {

        when(messageService.findById("1")).thenThrow(new EntityNotFoundException("1 non trouvé"));

        mockMvc.perform(get("/api/messages/{i}","1"))
                .andExpect(status().isNotFound());


    }

    //@Test
    public void createMessage_ShouldCreateMessageAndReturnCreatedMessage() throws Exception {
        Message aMessage =new Message();
        aMessage.setSenderFirstName("John");
        aMessage.setSenderLastName("Doe");
        aMessage.setContent("a message content");

        when(messageService.saveMessage(aMessage)).thenReturn(aMessage);

        mockMvc.perform(post("/api/messages")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(aMessage)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.senderFirstName", is("John")))
                .andExpect(jsonPath("$.senderLastName", is("Doe")))
                .andExpect(jsonPath("$.content", is("a message content")));

    }
}
