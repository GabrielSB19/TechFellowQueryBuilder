import axios from "axios";
import CommentFormType from "@/types/CommentFormType";

const URL_API = "http://localhost:8000/api/comment";

const createComment = (comment: CommentFormType) => {
  return axios.post(`${URL_API}/create`, comment);
};

const getComments = (queryId: string) => {
  return axios.get(`${URL_API}/get/${queryId}`);
};

const comment = {
  createComment,
  getComments,
};

export default comment;
