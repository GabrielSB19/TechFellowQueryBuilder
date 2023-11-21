import CommentType from "@/types/CommentType";
import React, { useState, useEffect } from "react";
import { toast } from "react-hot-toast";
import commentService from "@/service/commentService";
import { Button } from "@nextui-org/button";
import AddComment from "./AddComment";

interface MainCommentProps {
  queryId: string;
}

const MainComment = ({ queryId }: MainCommentProps) => {
  const [comments, setComments] = useState<CommentType[]>([]);
  const [isCommentCreating, setIsCommentCreating] = useState<boolean>(false);

  const fetchGetComments = async () => {
    try {
      const response = await commentService.fetchGetCommentByQuery(queryId);
      if (response) setComments(response);
    } catch (error) {
      toast.error("Error fetching comments");
    }
  };

  useEffect(() => {
    fetchGetComments();
  }, []);

  return (
    <div className="ml-12">
      {!isCommentCreating ? (
        <div>
          <Button
            color="primary"
            onClick={() => setIsCommentCreating(!isCommentCreating)}
          >
            Add Comment
          </Button>
        </div>
      ) : (
        <AddComment
          isCommentCreating={isCommentCreating}
          setIsCommentCreating={setIsCommentCreating}
        />
      )}
      {comments.length === 0 ? (
        <div className="ml-1 mt-4">
          <div className="font-bold">
            There are no comments to show you. ¡Be the first with an opinion!
          </div>
        </div>
      ) : (
        <div>XDS2</div>
      )}
    </div>
  );
};

export default MainComment;
