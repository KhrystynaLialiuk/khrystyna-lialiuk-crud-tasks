package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTest {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void testValidateTrelloBoardsWithTestBoard() {
        //Given
        TrelloList testTrelloList1 = new TrelloList("1", "test", false);
        TrelloList testTrelloList2 = new TrelloList("2", "test2", false);
        List<TrelloList> testLists = new ArrayList<>();
        testLists.add(testTrelloList1);
        testLists.add(testTrelloList2);
        TrelloBoard testTrelloBoard = new TrelloBoard("123", "Test", testLists);

        TrelloList realTrelloList = new TrelloList("3", "To Do", false);
        List<TrelloList> realLists = new ArrayList<>();
        realLists.add(realTrelloList);
        TrelloBoard realTrelloBoard = new TrelloBoard("235", "Kodilla", realLists);

        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(realTrelloBoard);
        boards.add(testTrelloBoard);

        //When
        List<TrelloBoard> validatedBoards = trelloValidator.validateTrelloBoards(boards);

        //Then
        assertEquals(1, validatedBoards.size());
        assertEquals(realTrelloBoard.getId(), validatedBoards.get(0).getId());
        assertEquals(realTrelloBoard.getName(), validatedBoards.get(0).getName());
        assertEquals(realTrelloBoard.getLists().size(), validatedBoards.get(0).getLists().size());
    }

    @Test
    public void testValidateTrelloBoardsWithoutRealBoard() {
        //Given
        TrelloList testTrelloList1 = new TrelloList("1", "test", false);
        TrelloList testTrelloList2 = new TrelloList("2", "test2", false);
        List<TrelloList> testLists = new ArrayList<>();
        testLists.add(testTrelloList1);
        testLists.add(testTrelloList2);
        TrelloBoard testTrelloBoard = new TrelloBoard("123", "Test", testLists);
        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(testTrelloBoard);

        //When
        List<TrelloBoard> validatedBoards = trelloValidator.validateTrelloBoards(boards);

        //Then
        assertEquals(0, validatedBoards.size());
    }
}
