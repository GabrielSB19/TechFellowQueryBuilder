package com.example.TechFellowQueryBuilder.Unit.service;

import com.example.TechFellowQueryBuilder.dto.request.CommentRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.CommentResponseDTO;
import com.example.TechFellowQueryBuilder.mapper.CommentMapper;
import com.example.TechFellowQueryBuilder.mapper.CommentMapperImpl;
import com.example.TechFellowQueryBuilder.mapper.UserMapperImpl;
import com.example.TechFellowQueryBuilder.model.ownModel.Comment;
import com.example.TechFellowQueryBuilder.model.ownModel.Query;
import com.example.TechFellowQueryBuilder.model.ownModel.UserClient;
import com.example.TechFellowQueryBuilder.repository.CommentRepository;
import com.example.TechFellowQueryBuilder.repository.QueryRepository;
import com.example.TechFellowQueryBuilder.repository.UserRepository;
import com.example.TechFellowQueryBuilder.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CommentServiceTest {

    private CommentRepository commentRepository;
    private CommentMapper commentMapper;
    private UserRepository userRepository;
    private QueryRepository queryRepository;

    private CommentService commentService;

    @BeforeEach
    public void setup() {
        commentMapper = spy(CommentMapperImpl.class);
        userRepository = mock(UserRepository.class);
        queryRepository = mock(QueryRepository.class);
        commentRepository = mock(CommentRepository.class);

        commentService = new CommentService(commentRepository, commentMapper, userRepository, queryRepository);
    }

    @Test
    void testCreateComment() {
        UserClient userClient = new UserClient();
        userClient.setUsername("test");
        userClient.setId(UUID.randomUUID());
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(userClient));

        Query query = new Query();
        query.setQuery("test");
        query.setId(UUID.randomUUID());
        when(queryRepository.findById(any(UUID.class))).thenReturn(Optional.of(query));

        CommentRequestDTO commentRequestDTO = new CommentRequestDTO();
        commentRequestDTO.setComment("test");
        commentRequestDTO.setQueryId(query.getId().toString());
        commentRequestDTO.setUserClientId(userClient.getId().toString());

        when(queryRepository.save(any(Query.class))).thenReturn(query);
        when(commentMapper.toComment(any(CommentRequestDTO.class))).thenReturn(new Comment());
        when(commentMapper.toCommentResponseDTO(any(Comment.class))).thenReturn(new CommentResponseDTO());

        Comment comment = new Comment();
        comment.setComment("test");
        comment.setQuery(query);
        comment.setUserClient(userClient);
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        CommentResponseDTO commentResponseDTO = commentService.createComment(commentRequestDTO);

        verify(userRepository, times(1)).findByUsername(anyString());
        verify(queryRepository, times(1)).findById(any(UUID.class));
        verify(commentRepository, times(1)).save(any(Comment.class));
        verify(commentMapper, times(1)).toCommentResponseDTO(any(Comment.class));

        assertEquals(CommentResponseDTO.class, commentResponseDTO.getClass());
    }

    @Test
    void testCreateCommentWithOutUser() {
        UserClient userClient = new UserClient();
        userClient.setUsername("test");
        userClient.setId(UUID.randomUUID());
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        Query query = new Query();
        query.setQuery("test");
        query.setId(UUID.randomUUID());
        when(queryRepository.findById(any(UUID.class))).thenReturn(Optional.of(query));

        CommentRequestDTO commentRequestDTO = new CommentRequestDTO();
        commentRequestDTO.setComment("test");
        commentRequestDTO.setQueryId(query.getId().toString());
        commentRequestDTO.setUserClientId(userClient.getId().toString());

        when(queryRepository.save(any(Query.class))).thenReturn(query);
        when(commentMapper.toComment(any(CommentRequestDTO.class))).thenReturn(new Comment());
        when(commentMapper.toCommentResponseDTO(any(Comment.class))).thenReturn(new CommentResponseDTO());

        Comment comment = new Comment();
        comment.setComment("test");
        comment.setQuery(query);
        comment.setUserClient(userClient);
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        assertThrows(RuntimeException.class, () -> {
            commentService.createComment(commentRequestDTO);
        });

        verify(userRepository, times(1)).findByUsername(anyString());
        verify(queryRepository, times(0)).findById(any(UUID.class));
        verify(commentRepository, times(0)).save(any(Comment.class));
        verify(commentMapper, times(0)).toCommentResponseDTO(any(Comment.class));
    }

    @Test
    void testCreateCommentWithOutQuery() {
        UserClient userClient = new UserClient();
        userClient.setUsername("test");
        userClient.setId(UUID.randomUUID());
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(userClient));

        Query query = new Query();
        query.setQuery("test");
        query.setId(UUID.randomUUID());
        when(queryRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        CommentRequestDTO commentRequestDTO = new CommentRequestDTO();
        commentRequestDTO.setComment("test");
        commentRequestDTO.setQueryId(query.getId().toString());
        commentRequestDTO.setUserClientId(userClient.getId().toString());

        when(queryRepository.save(any(Query.class))).thenReturn(query);
        when(commentMapper.toComment(any(CommentRequestDTO.class))).thenReturn(new Comment());
        when(commentMapper.toCommentResponseDTO(any(Comment.class))).thenReturn(new CommentResponseDTO());

        Comment comment = new Comment();
        comment.setComment("test");
        comment.setQuery(query);
        comment.setUserClient(userClient);
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        assertThrows(RuntimeException.class, () -> {
            commentService.createComment(commentRequestDTO);
        });

        verify(userRepository, times(1)).findByUsername(anyString());
        verify(queryRepository, times(1)).findById(any(UUID.class));
        verify(commentRepository, times(0)).save(any(Comment.class));
        verify(commentMapper, times(0)).toCommentResponseDTO(any(Comment.class));
    }

    @Test
    void testGetAllCommentsByQuery(){
        String queryId = UUID.randomUUID().toString();
        List<Comment> mockedComments = new ArrayList<>();
        Comment mockedComment = new Comment();
        mockedComments.add(mockedComment);

        when(commentRepository.findAllByQuery_Id(UUID.fromString(queryId))).thenReturn(mockedComments);

        CommentResponseDTO mockedResponseDTO = new CommentResponseDTO();
        when(commentMapper.toCommentResponseDTO(any(Comment.class))).thenReturn(mockedResponseDTO);

        List<CommentResponseDTO> result = commentService.getAllCommentsByQuery(queryId);

        verify(commentRepository, times(1)).findAllByQuery_Id(UUID.fromString(queryId));
        verify(commentMapper, times(mockedComments.size())).toCommentResponseDTO(any(Comment.class));

        assertEquals(mockedComments.size(), result.size());

        for (CommentResponseDTO responseDTO : result) {
            assertEquals(mockedResponseDTO, responseDTO);
        }
    }
}
