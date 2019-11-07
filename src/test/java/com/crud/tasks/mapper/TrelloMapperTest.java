package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test", "Test description", "Top", "123");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertThat(trelloCard).isNotNull()
                .hasFieldOrPropertyWithValue("name", trelloCardDto.getName())
                .hasFieldOrPropertyWithValue("description", trelloCardDto.getDescription())
                .hasFieldOrPropertyWithValue("pos", trelloCardDto.getPos())
                .hasFieldOrPropertyWithValue("listId", trelloCardDto.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("TestTrelloCard", "Test description", "Top", "1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertThat(trelloCardDto).isNotNull()
                .hasFieldOrPropertyWithValue("name", trelloCard.getName())
                .hasFieldOrPropertyWithValue("description", trelloCard.getDescription())
                .hasFieldOrPropertyWithValue("pos", trelloCard.getPos())
                .hasFieldOrPropertyWithValue("listId", trelloCard.getListId());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "TrelloList1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "TrelloList2", false);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto1);
        trelloListDtos.add(trelloListDto2);
        //When
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListDtos);
        //Then
        Assert.assertEquals(2, trelloList.size());
        assertThat(trelloList.get(0)).isNotNull()
                .hasFieldOrPropertyWithValue("id", trelloListDtos.get(0).getId())
                .hasFieldOrPropertyWithValue("name", trelloListDtos.get(0).getName())
                .hasFieldOrPropertyWithValue("isClosed", trelloListDtos.get(0).isClosed());
        assertThat(trelloList.get(1)).isNotNull()
                .hasFieldOrPropertyWithValue("id", trelloListDtos.get(1).getId())
                .hasFieldOrPropertyWithValue("name", trelloListDtos.get(1).getName())
                .hasFieldOrPropertyWithValue("isClosed", trelloListDtos.get(1).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "List1", false);
        TrelloList trelloList2 = new TrelloList("2", "List2", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        //Then
        Assert.assertEquals(2, trelloListDtos.size());
        assertThat(trelloListDtos.get(0)).isNotNull()
                .hasFieldOrPropertyWithValue("id", trelloLists.get(0).getId())
                .hasFieldOrPropertyWithValue("name", trelloLists.get(0).getName())
                .hasFieldOrPropertyWithValue("isClosed", trelloLists.get(0).isClosed());
        assertThat(trelloListDtos.get(1)).isNotNull()
                .hasFieldOrPropertyWithValue("id", trelloLists.get(1).getId())
                .hasFieldOrPropertyWithValue("name", trelloLists.get(1).getName())
                .hasFieldOrPropertyWithValue("isClosed", trelloLists.get(1).isClosed());
    }

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "TrelloList1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "TrelloList2", false);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto1);
        trelloListDtos.add(trelloListDto2);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("123", "Testing name 1", trelloListDtos);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("234", "Testing name 2", trelloListDtos);
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto1);
        trelloBoardDtos.add(trelloBoardDto2);
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);
        //Then
        Assert.assertEquals(2, trelloBoards.size());
        assertThat(trelloBoards.get(0)).isNotNull()
                .hasFieldOrPropertyWithValue("id", trelloBoardDtos.get(0).getId())
                .hasFieldOrPropertyWithValue("name", trelloBoardDtos.get(0).getName());
        assertThat(trelloBoards.get(1)).isNotNull()
                .hasFieldOrPropertyWithValue("id", trelloBoardDtos.get(1).getId())
                .hasFieldOrPropertyWithValue("name", trelloBoardDtos.get(1).getName());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "List1", false);
        TrelloList trelloList2 = new TrelloList("2", "List2", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        TrelloBoard trelloBoard1 = new TrelloBoard("5", "Board name 1", trelloLists);
        TrelloBoard trelloBoard2 = new TrelloBoard("6", "Board name 2", trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        Assert.assertEquals(2, trelloBoardDtos.size());
        assertThat(trelloBoardDtos.get(0)).isNotNull()
                .hasFieldOrPropertyWithValue("id", trelloBoards.get(0).getId())
                .hasFieldOrPropertyWithValue("name", trelloBoards.get(0).getName());
        assertThat(trelloBoardDtos.get(1)).isNotNull()
                .hasFieldOrPropertyWithValue("id", trelloBoards.get(1).getId())
                .hasFieldOrPropertyWithValue("name", trelloBoards.get(1).getName());
    }
}
