package com.crud.tasks.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void shouldFetchTrelloBoards() {
        //When
        trelloService.fetchTrelloBoards();
        //Then
        verify(trelloClient, times(1)).getTrelloBoards();
    }

    @Test
    public void testCreatedTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test task", "Test Description", "top", "test_id");
        CreatedTrelloCardDto newCard = new CreatedTrelloCardDto("1", "Test task", "http://test.com");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(newCard);
        when(adminConfig.getAdminMail()).thenReturn("test@test.com");

        //When
        CreatedTrelloCardDto createdCard = trelloService.createdTrelloCard(trelloCardDto);

        //Then
        assertEquals(createdCard.getId(), newCard.getId());
        assertEquals(createdCard.getName(), newCard.getName());
        assertEquals(createdCard.getShortUrl(), newCard.getShortUrl());
        verify(emailService, times(1)).send(any(Mail.class));
    }
}
