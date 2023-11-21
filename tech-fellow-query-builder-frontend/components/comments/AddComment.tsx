import React, { useState, useEffect } from "react";
import { Button, Textarea } from "@nextui-org/react";

interface AddCommentProps {
  isCommentCreating: boolean;
  setIsCommentCreating: (value: boolean) => void;
  fetchOnCreateComment: (commentText: string) => void;
}

const AddComment = ({
  isCommentCreating,
  setIsCommentCreating,
  fetchOnCreateComment,
}: AddCommentProps) => {
  const [commentText, setCommentText] = useState<string>("");

  const handleCommentText = (value: string) => {
    setCommentText(value);
  };

  const onSubmitComment = () => {
    setIsCommentCreating(!isCommentCreating);
    fetchOnCreateComment(commentText);
  };
  return (
    <div className="flex items-center justify-between w-full">
      <div className="flex-grow pr-4">
        <Textarea
          value={commentText}
          label="Comment"
          placeholder="Enter your comment"
          className="w-full"
          onChange={(e) => handleCommentText(e.target.value)}
        />
      </div>
      <div>
        <Button color="primary" onClick={onSubmitComment}>
          Submit
        </Button>
      </div>
    </div>
  );
};

export default AddComment;
