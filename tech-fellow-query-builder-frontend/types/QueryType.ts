import CommenType from "./CommentType";

type QueryType = {
  id: string;
  queryName: string;
  description: string;
  userClient: string;
  query: string;
  comments: CommenType[];
};

export default QueryType;
