package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloCard;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static uk.org.lidalia.slf4jtest.LoggingEvent.info;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloCardValidatorTest {

    @Autowired
    private TrelloValidator trelloValidator;

    TestLogger logger = TestLoggerFactory.getTestLogger(TrelloValidator.class);

    @Test
    public void testValidateCard() {
        //Given
        TrelloCard trelloCard = new TrelloCard("TestTrelloCard", "Description", "Top", "1");
        //When
        trelloValidator.validateCard(trelloCard);
        //Then
        assertThat(logger.getLoggingEvents(), is(asList(info("Someone is testing my application!"))));
    }
}
