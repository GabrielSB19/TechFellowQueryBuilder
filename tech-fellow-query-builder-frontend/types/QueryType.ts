import CommenType from "./CommentType";

type QueryType = {
  id: string;
  queryName: string;
  description: string;
  userClient: string;
  query: string;
  comments: CommenType[];
  worldType: string;
  codeCountry: string;
  codeRegion: string;
  gender: string;
  ageMin: string;
  ageMax: string;
  yearMin: string;
  yearMax: string;
};

export default QueryType;
