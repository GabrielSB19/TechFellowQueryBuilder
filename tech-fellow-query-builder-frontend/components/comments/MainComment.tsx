import CommentType from "@/types/CommentType";
import React, { useState, useEffect } from "react";
import { toast } from "react-hot-toast";
import commentService from "@/service/commentService";
import { Button } from "@nextui-org/button";
import AddComment from "./AddComment";
import CommentFormType from "@/types/CommentFormType";
import CardComment from "./CardComment";

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

  const fetchOnCreateComment = async (commentText: string) => {
    try {
      const commentToSend: CommentFormType = {
        comment: commentText,
        queryId: queryId,
        userClientId: localStorage.getItem("username") || "",
      };
      const response = await commentService.fetchCreateComment(commentToSend);
      if (response) {
        toast.success("Comment created successfully");
        fetchGetComments();
      }
    } catch (error) {
      toast.error("Error creating comment");
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
          fetchOnCreateComment={fetchOnCreateComment}
        />
      )}
      {comments.length === 0 ? (
        <div className="ml-1 mt-4">
          <div className="font-bold">
            There are no comments to show you. ¡Be the first with an opinion!
          </div>
        </div>
      ) : (
        <div className="flex flex-col w-full mt-4 gap-4">
          {comments
            .slice()
            .reverse()
            .map((comment, index) => (
              <CardComment comment={comment} key={index} />
            ))}
        </div>
      )}
    </div>
  );
};

export default MainComment;
