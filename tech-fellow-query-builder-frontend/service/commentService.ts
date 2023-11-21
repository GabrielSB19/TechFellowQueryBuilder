import comment from "@/api/comment";
import { toast } from "react-hot-toast";
import CommentFormType from "@/types/CommentFormType";
import CommentType from "@/types/CommentType";

const fetchCreateComment = async (commentData: CommentFormType) => {
  try {
    const response = await comment.createComment(commentData);
    const commentGet: CommentType = response.data;
    console.log(commentGet);
    return commentGet;
  } catch (error) {
    toast.error("Error al enviar el comentario");
  }
};

const fetchGetCommentByQuery = async (queryId: string) => {
  try {
    const response = await comment.getComments(queryId);
    const comments: CommentType[] = response.data;
    return comments;
  } catch (error) {
    toast.error("Error al cargar los comentarios");
  }
};

const commentService = {
  fetchCreateComment,
  fetchGetCommentByQuery,
};

export default commentService;
