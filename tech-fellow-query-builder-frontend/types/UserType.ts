import QueryType from "./QueryType";
import CommenType from "./CommentType";

type UserType = {
  id: string;
  username: string;
  queries: QueryType[];
  comments: CommenType[];
};

export default UserType;
